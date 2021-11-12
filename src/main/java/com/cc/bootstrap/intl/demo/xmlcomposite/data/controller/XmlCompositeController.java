package com.cc.bootstrap.intl.demo.xmlcomposite.data.controller;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.intl.demo.xmlcomposite.data.service.XmlCompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description XmlCompositeController xml组合控制类
 * @createTime 2021年11月12日 14:13:00
 */
@Controller
@RequestMapping(value="/xmlcomposite")
public class XmlCompositeController {

    @Autowired
    private XmlCompositeService xmlCompositeService;

    //1.显示xml http://localhost:8013/xmlcomposite/showXml
    @ResponseBody//需要加上，否则默认本系统默认是跳转页面
    @GetMapping(value = "/showXml")
    public ResponseResult<String> showXml() {
        try {
            String xml = xmlCompositeService.showXmlInfo();
            return ResponseResult.success(XmlCompositeEnum.SUCCESS_XML, xml);
        }catch (Exception e) {
            return ResponseResult.fail(XmlCompositeEnum.FAIL_XML, e.getMessage());
        }
    }
}
