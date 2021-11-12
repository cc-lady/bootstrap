package com.cc.bootstrap.intl.demo.xmlcomposite.nodata.entity;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description TODO
 * @createTime 2021年11月11日 10:10:00
 */
public class XmlElement extends AbstractNode{
    private String value;//元素值

    public XmlElement(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    protected String showNode() {
        String thisNode = "<" + this.name + ">"+this.value+"</" + this.name +">\n";
        return thisNode;
    }
}
