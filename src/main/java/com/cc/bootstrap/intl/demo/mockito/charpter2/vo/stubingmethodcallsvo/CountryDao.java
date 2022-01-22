package com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description TODO
 * @createTime 2022年01月07日 10:56:00
 */
public class CountryDao {
    public List<Country> retrieve(RetrieveCountryRequest request) {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("China"));
        return countries;
    }

    public List<Country> retrieve2(RetrieveCountryRequest request, boolean isSearch) {
        if (isSearch) {
            return this.retrieve(request);
        }
        return null;
    }
}
