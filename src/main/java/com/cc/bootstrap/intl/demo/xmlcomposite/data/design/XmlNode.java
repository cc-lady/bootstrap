package com.cc.bootstrap.intl.demo.xmlcomposite.data.design;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description XmlNode节点
 * @createTime 2021年11月11日 09:54:00
 */
@Data
public class XmlNode extends AbstractNode {
    @Setter(AccessLevel.NONE)@Getter(AccessLevel.NONE)//不生成set/get方法
    private List<AbstractNode> xmlNodes = new ArrayList<AbstractNode>();
    @Setter(AccessLevel.NONE)@Getter(AccessLevel.NONE)//不生成set/get方法
    private StringBuilder sb = new StringBuilder();

    public XmlNode() {}

    //为测试基础功能，Test类所需，后续不要可删除
    public XmlNode(String name, Integer level, String type) {
        super.name = name;
        super.level = level;
        super.type = type;
    }

    public XmlNode(String id, String name, Integer level, String type, String parentNode) {
        super(id, name, type, parentNode);
        super.level = level;
    }

    //添加节点
    public void addNode(AbstractNode node) {
        //校验List类型节点下只能添加Object类型节点
        if(this.type.equals("List")) {
            String nodeType = null;
            if(node instanceof XmlNode) {
                nodeType = ((XmlNode) node).getType();
            }else if(node instanceof XmlElement) {
                nodeType = ((XmlElement) node).getType();
            }

            if(!nodeType.equals("Object")) {
                throw new UnsupportedOperationException("List类型节点下不支持添加【"
                        + nodeType + "】类型节点");
            }
        }
        this.xmlNodes.add(node);
    }

    @Override
    public String showNode() {
        sb.append("<" + this.name + ">\n");//本节点名称 - 开始

        //打印树枝节点包含的所有节点：树枝节点和叶子节点
        for (AbstractNode node: xmlNodes) {
            //为格式
            if (this.level != null) {
                for (int i = 0; i < this.level; i++) {
                    sb.append("\t");
                }
            }
            sb.append(node.showNode());
        }

        //为格式
        if (this.level != null) {
            for (int i = 1; i < this.level; i++) {
                sb.append("\t");
            }
        }
        sb.append("</" + this.name +">\n");//本节点名称 - 结束

        String result = sb.toString();
        sb.setLength(0);
        return result;
    }
}
