package com.cc.bootstrap.intl.demo.xmlcomposite.data.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.bootstrap.common.util.ObjectFactory;
import com.cc.bootstrap.intl.dao.XmlNodeEntityMapper;
import com.cc.bootstrap.intl.demo.xmlcomposite.data.design.XmlElement;
import com.cc.bootstrap.intl.demo.xmlcomposite.data.design.XmlNode;
import com.cc.bootstrap.common.schema.XmlNodeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description XmlCompositeService
 * @createTime 2021年11月12日 14:16:00
 */
@Service
public class XmlCompositeService extends ServiceImpl<XmlNodeEntityMapper, XmlNodeEntity> {
    private static Logger LOGGER = LoggerFactory.getLogger(XmlCompositeService.class);
    /**
     * 展示xml
     * @return
     */
    public String showXmlInfo() {
        //1.查询所有节点 - 节点：level不为空,value为空
        List<XmlNode> xmlNodeList = this.getXmlNodeList();
        //2.查询所有元素 - 元素：level为空,value不为空
        List<XmlElement> xmlElementList = this.getXmlElementList();

        //3.组装成XML
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
        String xml = root.showNode();
        LOGGER.info("root = \n{}" + xml);
        return xml;
    }

    //查询所有元素 - 元素：level为空,value不为空
    private List<XmlElement> getXmlElementList() {
        QueryWrapper<XmlNodeEntity> queryWrapper = new QueryWrapper<XmlNodeEntity>();
        queryWrapper.isNull("level");
        queryWrapper.isNotNull("value");
        List<XmlNodeEntity> xmlElementNodeList = this.list(queryWrapper);
        //转换成逻辑需要的List
        List<XmlElement> xmlElementList = new ArrayList<XmlElement>();
        try {
            xmlElementList = ObjectFactory.toVOList(xmlElementNodeList, XmlElement.class);
        } catch (Exception e) {
            LOGGER.error("List转换失败 - xmlElementList from XmlNodeEntity to XmlNode", e);
            e.printStackTrace();
        }
        return xmlElementList;
    }

    //查询所有节点 - 节点：level不为空,value为空
    private List<XmlNode> getXmlNodeList() {
        QueryWrapper<XmlNodeEntity> queryWrapper = new QueryWrapper<XmlNodeEntity>();
        queryWrapper.isNotNull("level");
        queryWrapper.isNull("value");
        List<XmlNodeEntity> xmlNodeEntityList = this.list(queryWrapper);
        //转换成逻辑需要的List
        List<XmlNode> xmlNodeList = new ArrayList<XmlNode>();
        try {
            xmlNodeList = ObjectFactory.toVOList(xmlNodeEntityList, XmlNode.class);
        } catch (Exception e) {
            LOGGER.error("List转换失败 - xmlNodeList from XmlNodeEntity to XmlNode", e);
            e.printStackTrace();
        }
        return xmlNodeList;
    }
}
