package com.cc.bootstrap.intl.demo.lambda.vo;

import com.cc.bootstrap.common.base.entity.Schema;

import java.util.Arrays;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName Artist-创作音乐的个人或团队
 * @Description java8函数式编程示例代码使用
 * @createTime 2021年12月09日 17:02:00
 */
public class Artist implements Schema {
    private static final long serialVersionUID = 1L;

    // 艺术家的名字（例如“甲壳虫乐队”）。
    private String name;

    // 乐队成员（例如“约翰 · 列侬”），该字段可为空。
    private String members;

    // 乐队来自哪里（例如“利物浦”）。
    private String origin;

    public Artist() {
    }

    public Artist(String name, String members, String origin) {
        this.name = name;
        this.members = members;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public long memberCount() {
        if(null == this.members) {
            return 0;
        }

        return Arrays.stream(this.members.split(",")).count();
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", members='" + members + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }

    /**
     * 是否是独唱歌手
     * @return boolean
     */
    public boolean isSolo() {
        if(null == this.members) {
            return false;
        }

        return this.members.split(",").length == 1;
    }
}
