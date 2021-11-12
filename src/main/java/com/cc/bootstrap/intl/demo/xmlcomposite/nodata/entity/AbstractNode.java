package com.cc.bootstrap.intl.demo.xmlcomposite.nodata.entity;

import com.cc.bootstrap.common.base.entity.Schema;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description xml节点抽象类
 * @createTime 2021年11月11日 09:50:00
 */
public abstract class AbstractNode implements Schema {
    private static final long serialVersionUID = 1L;

    protected String name;//节点名称
    protected AbstractNode(String name) {
        this.name = name;
    }

    protected abstract String showNode();//展示，显示XML节点，递归子节点
}
