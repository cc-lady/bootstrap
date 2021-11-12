package com.cc.bootstrap.intl.demo.xmlcomposite.data.design;

import com.cc.bootstrap.common.base.entity.Schema;
import lombok.Data;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description xml节点抽象类
 * @createTime 2021年11月11日 09:50:00
 */
@Data
public class AbstractNode implements Schema {
    private static final long serialVersionUID = 1L;

    protected String id;//Id唯一标识
    protected String name;//名称
    protected String value;//元素值， 节点类型为空
    protected Integer level;//节点层级，元素类型为空
    protected String type;//节点：Object/List， 元素：Element
    protected String parentNode;//父节点

    public AbstractNode(){}

    public AbstractNode(String id, String name, String type, String parentNode) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parentNode = parentNode;
    }

    protected String showNode(){return null;};//展示，显示XML节点，递归子节点
}
