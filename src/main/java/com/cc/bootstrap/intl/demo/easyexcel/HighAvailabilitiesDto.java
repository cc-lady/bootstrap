package com.cc.bootstrap.intl.demo.easyexcel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Description: HighAvailabilitiesDto
 * @author: ChenChen
 * @date: 2025-08-19 10:07
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HighAvailabilitiesDto {

    @ExcelIgnore
    private String id;

    @ExcelProperty("数据表名称")
    @Pattern(regexp = "[a-zA-Z0-9\\_]+", message = "高保障表[数据表名称]只支持字母、数字或下划线")
    @Length(max = 100, message = "高保障表[数据表名称]长度不能超过100")
    @NotBlank(message = "高保障表[数据表名称]不能为空")
    private String tableName;

    @ExcelProperty("数据表中文名称")
    @Length(max = 100, message = "高保障表[数据表中文名称]长度不能超过100")
    @NotBlank(message = "高保障表[数据表中文名称]不能为空")
    private String tableNameCh;

    @ExcelIgnore
    private String importId;//导入ID
    @ExcelIgnore
    private String errorMsg;//导入失败原因
    @ExcelIgnore
    private long rowNum;//导入行号
}
