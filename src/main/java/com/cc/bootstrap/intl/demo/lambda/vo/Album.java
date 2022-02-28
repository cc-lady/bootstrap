package com.cc.bootstrap.intl.demo.lambda.vo;

import com.cc.bootstrap.common.base.entity.Schema;

import java.util.List;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName Album-专辑，由若干曲目组成。
 * @Description java8函数式编程示例代码使用
 * @createTime 2021年12月09日 17:07:00
 */
public class Album implements Schema {
    private static final long serialVersionUID = 1L;

    // 专辑名（例如《左轮手枪》）
    private String name;

    // 专辑上所有曲目的列表
    private List<Track> tracks;

    // 参与创作本专辑的艺术家列表 - 第一个为主唱
    private List<Artist> musicians;

    public Album() {
    }

    public Album(String name, List<Track> tracks, List<Artist> musicians) {
        this.name = name;
        this.tracks = tracks;
        this.musicians = musicians;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Artist> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<Artist> musicians) {
        this.musicians = musicians;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", tracks=" + tracks +
                ", musicians=" + musicians +
                '}';
    }
}
