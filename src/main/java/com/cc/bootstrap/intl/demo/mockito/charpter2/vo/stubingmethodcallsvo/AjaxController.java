package com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.bootstrap.common.base.restful.ResponseResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 我们需要创建一个控制器类来接受来自jQuery表的Ajax调用并返回一个国家列表。
 * Ajax请求包含请求的页号、每页的行、排序顺序、排序列名和搜索查询。控制器需 要从数据库表中检索国家详细信息，
 * 并仅返回过滤后的国家作为Ajax响应。
 * @createTime 2022年01月07日 09:32:00
 */
@Controller
@Scope("session")
public class AjaxController {
    private final CountryDao countryDao;
    public AjaxController(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @RequestMapping(value = "retrieveCountries", method =
            RequestMethod.POST)
    public @ResponseBody Page<Country> retrieve(HttpServletRequest webRequest)
    {
        List<Country> countries = new ArrayList<Country>();
        RetrieveCountryRequest request = RequestBuilder.build(webRequest);
        countries = countryDao.retrieve(request);
        Long startIndex = (request.getPage() - 1) *
                (request.getRowPerPage());
        int size = countries.size();
        Long endIndex = (startIndex + request.getRowPerPage()) > size
                ? size: (startIndex + request.getRowPerPage());
        if (startIndex < endIndex) {
            countries = countries.subList(startIndex.intValue(),
                    endIndex.intValue());
        }
        // 最后，检索方法从国家列表中构建一个Json数据捕获器对象，并将其作为JSON数据移 交给Ajax请求。
        // @响应体注释指示JSON响应
        Page<Country> wrapper = new Page
                <Country>(request.getPage(), size, Long.valueOf(countries.size()));
        wrapper.setRecords(countries);
        return wrapper;
    }

    @RequestMapping(value = "retrieveCountries2", method =
            RequestMethod.POST)
    public @ResponseBody Page<Country> retrieve2(HttpServletRequest webRequest, boolean isSearch)
    {
        List<Country> countries = new ArrayList<Country>();
        RetrieveCountryRequest request = RequestBuilder.build(webRequest);

        countries = countryDao.retrieve2(request, isSearch);

        int size = countries.size();
        Page<Country> wrapper = new Page
                <Country>(request.getPage(), size, Long.valueOf(countries.size()));
        wrapper.setRecords(countries);
        return wrapper;
    }
}
