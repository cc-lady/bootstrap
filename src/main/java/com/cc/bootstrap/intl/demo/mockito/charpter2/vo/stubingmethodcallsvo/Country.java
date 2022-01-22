package com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description Country
 * @createTime 2022年01月07日 10:53:00
 */
public class Country {

    private String name;

    private String shortName;

    private String iso;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, String iso) {
        this.name = name;
        this.iso = iso;
    }

    public Country(String name, String shortName, String iso) {
        this.name = name;
        this.shortName = shortName;
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", iso='" + iso + '\'' +
                '}';
    }
}
