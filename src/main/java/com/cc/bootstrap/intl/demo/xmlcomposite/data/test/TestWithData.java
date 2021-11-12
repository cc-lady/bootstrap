package com.cc.bootstrap.intl.demo.xmlcomposite.data.test;

import com.cc.bootstrap.common.util.ObjectFactory;
import com.cc.bootstrap.intl.demo.xmlcomposite.data.design.AbstractNode;
import com.cc.bootstrap.intl.demo.xmlcomposite.data.design.XmlElement;
import com.cc.bootstrap.intl.demo.xmlcomposite.data.design.XmlNode;
import com.cc.bootstrap.common.schema.XmlNodeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description xml配置化-测试场景（数据从数据库取出）
 * @createTime 2021年11月11日 12:06:00
 */
public class TestWithData {
    private static Logger LOGGER = LoggerFactory.getLogger(TestWithData.class);

    //获取节点数据
    public static Map<String, List<? extends AbstractNode>> getNodeDataList() throws IllegalAccessException, InstantiationException {
        Map<String, List<? extends AbstractNode>> abstractNodeMap = new HashMap<String, List<? extends AbstractNode>>();
        //测试数据
        List<XmlNodeEntity> xmlNodeEntityList = new ArrayList<XmlNodeEntity>();
        XmlNodeEntity xmlNode1 = new XmlNodeEntity("id111","Family",null, 1, "Object", null);
        XmlNodeEntity xmlNode2 = new XmlNodeEntity("id112","Members",null, 2, "List", "id111");
        XmlNodeEntity xmlNode3 = new XmlNodeEntity("id113","Member",null, 3, "Object", "id112");
        XmlNodeEntity xmlNode4 = new XmlNodeEntity("id114","Job",null, 4, "Object", "id113");
        XmlNodeEntity xmlNode5 = new XmlNodeEntity("id115","Member",null, 3, "Object", "id112");
        XmlNodeEntity xmlNode6 = new XmlNodeEntity("id116","Job",null, 4, "Object", "id115");
        XmlNodeEntity xmlNode7 = new XmlNodeEntity("id117","Member",null, 3, "Object", "id112");
        XmlNodeEntity xmlNode8 = new XmlNodeEntity("id118","Job",null, 4, "Object","id117");
        XmlNodeEntity xmlNode9 = new XmlNodeEntity("id119","Address",null, 2, "Object", "id111");
        xmlNodeEntityList.add(xmlNode1);
        xmlNodeEntityList.add(xmlNode2);
        xmlNodeEntityList.add(xmlNode3);
        xmlNodeEntityList.add(xmlNode4);
        xmlNodeEntityList.add(xmlNode5);
        xmlNodeEntityList.add(xmlNode6);
        xmlNodeEntityList.add(xmlNode7);
        xmlNodeEntityList.add(xmlNode8);
        xmlNodeEntityList.add(xmlNode9);
        //转换成逻辑需要的List
        List<XmlNode> xmlNodeList = new ArrayList<XmlNode>();
        xmlNodeList = ObjectFactory.toVOList(xmlNodeEntityList, XmlNode.class);

        List<XmlNodeEntity> xmlElementNodeList = new ArrayList<XmlNodeEntity>();
        XmlNodeEntity xmlElement1 = new XmlNodeEntity("id120","familyId","111", null, "Element", "id111");
        XmlNodeEntity xmlElement2 = new XmlNodeEntity("id121","familyName","cc的家", null, "Element", "id111");
        XmlNodeEntity xmlElement3 = new XmlNodeEntity("id122","memberId","1", null, "Element", "id113");
        XmlNodeEntity xmlElement4 = new XmlNodeEntity("id123","name","qh", null, "Element", "id113");
        XmlNodeEntity xmlElement5 = new XmlNodeEntity("id124","name","工人", null, "Element", "id114");
        XmlNodeEntity xmlElement6 = new XmlNodeEntity("id125","company","北京工区", null, "Element", "id114");
        XmlNodeEntity xmlElement7 = new XmlNodeEntity("id126","salary","8000", null, "Element", "id114");
        XmlNodeEntity xmlElement8 = new XmlNodeEntity("id127","memberId","2", null, "Element", "id115");
        XmlNodeEntity xmlElement9 = new XmlNodeEntity("id128","name","yy", null, "Element", "id115");
        XmlNodeEntity xmlElement10 = new XmlNodeEntity("id129","name","家政中介人员", null, "Element", "id116");
        XmlNodeEntity xmlElement11 = new XmlNodeEntity("id130","company","北京家政", null, "Element", "id116");
        XmlNodeEntity xmlElement12 = new XmlNodeEntity("id131","salary","8000", null, "Element", "id116");
        XmlNodeEntity xmlElement13 = new XmlNodeEntity("id132","province","北京", null, "Element", "id119");
        XmlNodeEntity xmlElement14 = new XmlNodeEntity("id133","city","北京", null, "Element", "id119");
        XmlNodeEntity xmlElement15 = new XmlNodeEntity("id134","district","海淀区", null, "Element", "id119");
        XmlNodeEntity xmlElement16 = new XmlNodeEntity("id135","location","上地11号", null, "Element", "id119");
        xmlElementNodeList.add(xmlElement1);
        xmlElementNodeList.add(xmlElement2);
        xmlElementNodeList.add(xmlElement3);
        xmlElementNodeList.add(xmlElement4);
        xmlElementNodeList.add(xmlElement5);
        xmlElementNodeList.add(xmlElement6);
        xmlElementNodeList.add(xmlElement7);
        xmlElementNodeList.add(xmlElement8);
        xmlElementNodeList.add(xmlElement9);
        xmlElementNodeList.add(xmlElement10);
        xmlElementNodeList.add(xmlElement11);
        xmlElementNodeList.add(xmlElement12);
        xmlElementNodeList.add(xmlElement13);
        xmlElementNodeList.add(xmlElement14);
        xmlElementNodeList.add(xmlElement15);
        xmlElementNodeList.add(xmlElement16);
        //转换成逻辑需要的List
        List<XmlElement> xmlElementList = new ArrayList<XmlElement>();
        xmlElementList = ObjectFactory.toVOList(xmlElementNodeList, XmlElement.class);

        //转换成逻辑需要的List
        abstractNodeMap.put("xmlNodeList", xmlNodeList);
        abstractNodeMap.put("xmlElementList", xmlElementList);
        return abstractNodeMap;
    }

    public static void main(String[] args) {
        try {
            //获取所有节点数据，并转换成逻辑需要类型
            Map<String, List<? extends AbstractNode>> abstractNodeMap = TestWithData.getNodeDataList();
            List<XmlNode> xmlNodeList = (List<XmlNode>) abstractNodeMap.get("xmlNodeList");//所有节点
            List<XmlElement> xmlElementList = (List<XmlElement>) abstractNodeMap.get("xmlElementList");//所有元素

            /**
             * 循环1：将节点类型添加到节点中
             * （1）parentNode与Id相等，则添加进去
             * （2）记录根节点对象，parentNode为Null的节点为根节点
             * （3）查出的数据已根据层级排序，增加效率
             */
            //默认第一个为前节点，已排序，默认为根节点
            XmlNode root = xmlNodeList.get(0);
            for (int i = 0;i < xmlNodeList.size(); i++) {
                for (int j = i+1;j < xmlNodeList.size(); j++) {
                    if (xmlNodeList.get(j).getParentNode().equals(xmlNodeList.get(i).getId())) {
                        xmlNodeList.get(i).addNode(xmlNodeList.get(j));
                    }
                }
            }
            //LOGGER.info("root = \n{}" + root.showNode());
            /**
             * 循环2：将元素类型添加到节点中
             * （1）parentNode与Id相等，则添加进去
             */
            for (int i = 0;i < xmlNodeList.size(); i++) {
                for (int j = 0;j < xmlElementList.size(); j++) {
                    if (xmlElementList.get(j).getParentNode().equals(xmlNodeList.get(i).getId())) {
                        xmlNodeList.get(i).addNode(xmlElementList.get(j));
                    }
                }
            }
            LOGGER.info("root = \n{}" + root.showNode());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
