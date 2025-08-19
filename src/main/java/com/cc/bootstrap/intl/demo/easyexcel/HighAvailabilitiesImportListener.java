package com.cc.bootstrap.intl.demo.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.cc.bootstrap.common.schema.HighAvailabilitiesEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Description: HighAvailabilitiesImportListener
 * @author: ChenChen
 * @date: 2025-08-19 9:54
 */
@NoArgsConstructor
@Slf4j
@Getter
public class HighAvailabilitiesImportListener extends AnalysisEventListener<HighAvailabilitiesDto> {

    private HighAvailabilitiesService highAvailabilitiesService;
    private LocalValidatorFactoryBean validator;
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public HighAvailabilitiesImportListener(HighAvailabilitiesService highAvailabilitiesService,
                                            LocalValidatorFactoryBean validator,
                                            ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.highAvailabilitiesService = highAvailabilitiesService;
        this.validator = validator;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    private String importId = UUID.randomUUID().toString();
    private int batchSize = 500;
    private boolean hasNext = true;
    private List<HighAvailabilitiesEntity> highAvailabilitiesEntities = new ArrayList<>();
    private List<HighAvailabilitiesDto> errorList = new ArrayList<>();
    private List<Callable<Boolean>> callableList = new ArrayList<>();

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        Integer rowIndex = context.readRowHolder().getRowIndex();
        HighAvailabilitiesDto errorDto = (HighAvailabilitiesDto) context.readRowHolder().getCurrentRowAnalysisResult();
        errorDto.setErrorMsg(exception.getMessage());
        log.error("批量导入高保障表，解析到行号为[{}]的数据异常，并继续读取下一行", rowIndex, exception);
        errorList.add(errorDto);
    }

    @Override
    public void invoke(HighAvailabilitiesDto highAvailabilitiesDto, AnalysisContext analysisContext) {
        StringBuilder rowErrorMsg = new StringBuilder();

        // 解析为实体信息
        highAvailabilitiesDto.setId(UUID.randomUUID().toString());
        highAvailabilitiesDto.setImportId(importId);
        int rowNum = analysisContext.readRowHolder().getRowIndex() - 1;
        highAvailabilitiesDto.setRowNum(rowNum);

        // 校验输入数据
        this.validateDTO(highAvailabilitiesDto, rowErrorMsg);
        // 校验当前excel是否重复 - 略
        // 校验与数据库中是否重复 - 略

        // 错误信息记录，并清理，最多20条
        if (rowErrorMsg.length() > 0 && errorList.size() < 20) {
            throw new UnsupportedOperationException(rowErrorMsg.toString());
        }

        // 错误信息达到20条直接返回
        if (errorList.size() == 20) {
            this.hasNext = false;
            return;
        }

        // 放入待保存集合
        HighAvailabilitiesEntity highAvailabilitiesEntity = new HighAvailabilitiesEntity();
        BeanUtils.copyProperties(highAvailabilitiesDto, highAvailabilitiesEntity);
        highAvailabilitiesEntities.add(highAvailabilitiesEntity);

        // 本批次数据准备，放入待保存任务
        if (batchSize == highAvailabilitiesEntities.size()) {
            this.saveToTask(highAvailabilitiesEntities);
            highAvailabilitiesEntities = new ArrayList<>();
        }
    }

    private void validateDTO(HighAvailabilitiesDto highAvailabilitiesDto, StringBuilder rowErrorMsg) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(highAvailabilitiesDto, "importDTO");
        validator.validate(highAvailabilitiesDto, bindingResult);
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining("；")) + "。";
            log.warn("批量导入高保障表，手动校验DTO异常：{}", errorMsg);
            rowErrorMsg.append(errorMsg);
        } else {
            // 若格式校验正常，则校验输入值是否在正常范围内
            // this.checkDataFields(highAvailabilitiesDto, rowErrorMsg);
        }
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        super.extra(extra, context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 最后一次批量数据添加
        this.saveToTask(highAvailabilitiesEntities);

        // 如果存在错误数据，不保存数据库，直接返回错误信息
        if (CollectionUtils.isNotEmpty(errorList)) {
            return;
        }

        // 尝试保存数据库
        boolean failPresent = this.tryBatchSave();
        if (failPresent) {
            throw new PersistenceException("批量导入高保障，入库发生异常，请联系管理员！");
        }
    }

    private boolean tryBatchSave() {
        if (CollectionUtils.isEmpty(callableList)) {
            log.info("批量导入高保障，批量任务为0，无需入库");
            return true;
        }

        // 批量入库
        List<Future<Boolean>> futureList = callableList.stream().map(callable ->
                threadPoolTaskExecutor.submit(callable)).collect(Collectors.toList());
        boolean failPresent = futureList.stream().map(future -> {
            Boolean result = null;
            try {
                result = future.get(5, TimeUnit.MINUTES);
                log.info("批量导入高保障表，批量保存任务执行成功");
            } catch (Exception e) {
                result = false;
                log.info("批量导入高保障表，批量保存任务执行失败");
            }
            return  result;
        }).filter(flag -> !flag.booleanValue()).findAny().isPresent();

        // 全部失败（基本不会有，已经检查了数据）
        if (failPresent) {
            highAvailabilitiesService.deleteByImportId(importId);
        }
        return failPresent;
    }

    private void saveToTask(List<HighAvailabilitiesEntity> highAvailabilitiesEntities) {
        if (CollectionUtils.isEmpty(highAvailabilitiesEntities)) {
            return;
        }

        Callable<Boolean> task = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    highAvailabilitiesService.addHighAvailabilities(highAvailabilitiesEntities);
                    log.info("批量导入高保障表，分批插入成功。");
                    return true;
                } catch (Exception e) {
                    log.error("批量导入高保障表，分批插入失败。", e);
                    return false;
                }
            }
        };
        callableList.add(task);
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        return hasNext;
    }
}
