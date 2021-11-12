package com.cc.bootstrap.intl.demo.xmlcomposite.nodata.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description XmlNode节点
 * @createTime 2021年11月11日 09:54:00
 */
public class XmlNode extends AbstractNode{
    private List<AbstractNode> xmlNodes = new ArrayList<AbstractNode>();
    private StringBuilder sb = new StringBuilder();
    private Integer level;
    private String type;//Object , List

    public XmlNode(String name, Integer level, String type) {
        super(name);
        this.level = level;
        this.type = type;
    }

    //添加节点
    public void addNode(AbstractNode node) {
        this.xmlNodes.add(node);
    }

    @Override
    public String showNode() {
        sb.append("<" + this.name + ">\n");//本节点名称 - 开始

        //打印树枝节点包含的所有节点：树枝节点和叶子节点
        for (AbstractNode node: xmlNodes) {
            if (this.level != null) {
                for (int i = 0; i < this.level; i++) {
                    sb.append("\t");
                }
            }
            sb.append(node.showNode());
        }

        if (this.level != null) {
            for (int i = 1; i < this.level; i++) {
                sb.append("\t");
            }
        }
        sb.append("</" + this.name +">\n");//本节点名称 - 结束

        String result = sb.toString();
        return result;
    }
}
