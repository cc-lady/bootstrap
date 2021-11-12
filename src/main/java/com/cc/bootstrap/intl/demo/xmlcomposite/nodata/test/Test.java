package com.cc.bootstrap.intl.demo.xmlcomposite.nodata.test;

import com.cc.bootstrap.intl.demo.xmlcomposite.nodata.entity.XmlElement;
import com.cc.bootstrap.intl.demo.xmlcomposite.nodata.entity.XmlNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description xml配置化-测试场景
 * @createTime 2021年11月11日 11:07:00
 */
public class Test {
    private static Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        //Family
        XmlNode xmlNode = new XmlNode("Family",1, "Object");
        //familyId
        XmlElement xmlElement_familyId = new XmlElement("familyId", "111");
        //familyName
        XmlElement xmlElement_familyName = new XmlElement("familyName", "cc的家");
        xmlNode.addNode(xmlElement_familyId);
        xmlNode.addNode(xmlElement_familyName);

        //Members
        XmlNode members = new XmlNode("Members",2, "List");
        //member_1
        XmlNode member_1 = new XmlNode("Member",3, "Object");
        XmlElement xmlElement_1_memberId = new XmlElement("memberId", "1");
        XmlElement xmlElement_1_name = new XmlElement("name", "qh");
        member_1.addNode(xmlElement_1_memberId);
        member_1.addNode(xmlElement_1_name);
        //job_1
        XmlNode job_1 = new XmlNode("Job",4, "Object");
        XmlElement xmlElement_1_jobName = new XmlElement("name", "工人");
        XmlElement xmlElement_1_company = new XmlElement("company", "北京工区");
        XmlElement xmlElement_1_salary = new XmlElement("salary", "8000");
        job_1.addNode(xmlElement_1_jobName);
        job_1.addNode(xmlElement_1_company);
        job_1.addNode(xmlElement_1_salary);
        member_1.addNode(job_1);
        members.addNode(member_1);
        //member_2
        XmlNode member_2 = new XmlNode("Member",3, "Object");
        XmlElement xmlElement_2_memberId = new XmlElement("memberId", "2");
        XmlElement xmlElement_2_name = new XmlElement("name", "yy");
        member_2.addNode(xmlElement_2_memberId);
        member_2.addNode(xmlElement_2_name);
        //job_2
        XmlNode job_2 = new XmlNode("Job",4, "Object");
        XmlElement xmlElement_2_jobName = new XmlElement("name", "家政中介人员");
        XmlElement xmlElement_2_company = new XmlElement("company", "北京家政");
        XmlElement xmlElement_2_salary = new XmlElement("salary", "8000");
        job_2.addNode(xmlElement_2_jobName);
        job_2.addNode(xmlElement_2_company);
        job_2.addNode(xmlElement_2_salary);
        member_2.addNode(job_2);
        members.addNode(member_2);
        xmlNode.addNode(members);

        //Address
        XmlNode address = new XmlNode("Address",2, "Object");
        XmlElement xmlElement_province = new XmlElement("province", "北京");
        XmlElement xmlElement_city = new XmlElement("city", "北京");
        XmlElement xmlElement_district = new XmlElement("district", "海淀区");
        XmlElement xmlElement_location = new XmlElement("location", "上地11号");
        address.addNode(xmlElement_province);
        address.addNode(xmlElement_city);
        address.addNode(xmlElement_district);
        address.addNode(xmlElement_location);
        xmlNode.addNode(address);

        String xml = xmlNode.showNode();
        LOGGER.info("xml = \n{}", xml);
    }
}
