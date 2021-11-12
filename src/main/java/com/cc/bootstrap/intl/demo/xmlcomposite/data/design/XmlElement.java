package com.cc.bootstrap.intl.demo.xmlcomposite.data.design;

import lombok.Data;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 测试从数据中获取节点信息
 * @createTime 2021年11月11日 10:10:00
 */
@Data
public class XmlElement extends AbstractNode {

    public XmlElement(){}

    //为测试基础功能，Test类所需，后续不要可删除
    public XmlElement(String name, String value) {
        super.name = name;
        super.value = value;
    }

    public XmlElement(String id, String name, String value, String type, String parentNode) {
        super(id, name, type, parentNode);
        super.value = value;
        super.level = null;//元素类型，层级为空
    }

    @Override
    protected String showNode() {
        String thisNode = "<" + super.name + ">"+super.value+"</" + super.name +">\n";
        return thisNode;
    }
}
