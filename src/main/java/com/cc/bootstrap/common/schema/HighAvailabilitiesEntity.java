package com.cc.bootstrap.common.schema;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cc.bootstrap.common.base.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 高保障表实体类
 * @author: ChenChen
 * @date: 2025-08-19 9:37
 */
@Data
@NoArgsConstructor
@TableName("HIGH_AVALIABILITIES")
public class HighAvailabilitiesEntity extends AbstractEntity {
    @TableField(value = "ID")
    private String id;//Id唯一标识
    @TableField(value = "TABLE_NAME")
    private String tableName;//表名
    @TableField(value = "TABLE_NAME_CH")
    private String tableNameCh;//表中文名
    @TableField(value = "IMPORT_ID")
    private String importId;//导入ID
}
