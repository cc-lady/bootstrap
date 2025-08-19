package com.cc.bootstrap.intl.demo.easyexcel;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cc.bootstrap.common.schema.HighAvailabilitiesEntity;
import com.cc.bootstrap.intl.dao.HighAvailabitiesEntityMapper;
import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 高保障表服务
 * @author: ChenChen
 * @date: 2025-08-19 9:35
 */
@Service
public class HighAvailabilitiesService {

    @Autowired
    @Qualifier("batchSqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private HighAvailabilitiesLogic highAvailabilitiesLogic;

    /**
     * @Description 批量保存
     * @param highAvailabilitiesEntities
     * @author ChenChen
     * @return void
     * @date 2025-08-19 10:34
     */
    @Transactional
    public void addHighAvailabilities(List<HighAvailabilitiesEntity> highAvailabilitiesEntities) {
        if (CollectionUtils.isEmpty(highAvailabilitiesEntities)) {
            return;
        }

        // 批量入库
        HighAvailabitiesEntityMapper highAvailabitiesEntityMapperBatch = sqlSessionTemplate.getMapper(HighAvailabitiesEntityMapper.class);
        highAvailabilitiesEntities.stream().forEach(each -> highAvailabitiesEntityMapperBatch.insert(each));
    }

    /**
     * @Description 删除本次批量导入的高保障表信息
     * @param importId
     * @author ChenChen
     * @return void
     * @date 2025-08-19 16:10
     */
    @Transactional
    public void deleteByImportId(String importId) {
        LambdaQueryWrapper<HighAvailabilitiesEntity> highAvailabilitiesEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        highAvailabilitiesEntityLambdaQueryWrapper.eq(HighAvailabilitiesEntity::getImportId, importId);
        highAvailabilitiesLogic.getBaseMapper().delete(highAvailabilitiesEntityLambdaQueryWrapper);
    }
}
