package com.cc.bootstrap.common.schema;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cc.bootstrap.common.base.entity.Schema;
import lombok.Data;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 实体类
 * @createTime 2021年11月11日 17:00:00
 */
@Data
@TableName(value = "t_xml_node")
public class XmlNodeEntity implements Schema {
    private static final long serialVersionUID = 1L;

    @TableField(value = "id")
    private String id;//Id唯一标识
    @TableField(value = "name")
    private String name;//名称
    @TableField(value = "value")
    private String value;//元素值， 节点类型为空
    @TableField(value = "level")
    private Integer level;//节点层级，元素类型为空
    @TableField(value = "type")
    private String type;//节点：Object/List， 元素：Element
    @TableField(value = "parentNode")
    private String parentNode;//父节点

    public XmlNodeEntity(){}

    public XmlNodeEntity(String id, String name, String value, Integer level, String type, String parentNode) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.level = level;
        this.type = type;
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        return "XmlNodeEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", level=" + level +
                ", type='" + type + '\'' +
                ", parentNode='" + parentNode + '\'' +
                '}';
    }
}
