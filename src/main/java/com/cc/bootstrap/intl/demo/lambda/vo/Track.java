package com.cc.bootstrap.intl.demo.lambda.vo;

import com.cc.bootstrap.common.base.entity.Schema;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName Track-专辑中的一支曲目
 * @Description java8函数式编程示例代码使用
 * @createTime 2021年12月09日 17:05:00
 */
public class Track implements Schema {
    private static final long serialVersionUID = 1L;

    // ：曲目名称（例如《黄色潜水艇》）
    private String name;

    // 曲目号码
    private Integer number;

    public Track() {
    }

    public Track(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
