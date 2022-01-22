package com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 检索方法接受一个HttpServlet请求对象，并从该对象构建一个数据库访问请求。以 下是请求的生成器代码
 * @createTime 2022年01月07日 09:34:00
 */
public class RequestBuilder {
    public static RetrieveCountryRequest build(HttpServletRequest webReq) {
        RetrieveCountryRequest request = new RetrieveCountryRequest();
        request.setPage(getLong(webReq.getParameter("page")));
        request.setRowPerPage(getInt(webReq.getParameter("rp")));
        request.setSortOrder(webReq.getParameter
                ("sortorder"));
        request.setSortname(webReq.getParameter
                ("sortname"));
        request.setSerachQuery(webReq.getParameter("qtype"));
        return request;
    }

    private static Integer getInt(String val) {
        Integer retVal = null;
        try {
            retVal = Integer.parseInt(val);
        } catch (Exception e) {
        }
        return retVal;
    }

    private static Long getLong(String val) {
        Long retVal = null;
        try {
            retVal = Long.parseLong(val);
        } catch (Exception e) {
        }
        return retVal;
    }
}
