package com.cc.bootstrap.common.demo.spring4.beanwiring.demo2javaconfig;

/**
 * @Description: 通过javaConfig代码显示装配，将一个CompactDisc注入到CDPlayer之中
 * @author: ChenChen
 * @date: 2024-02-29 10:48
 */
public class CDPlayer {
    private CompactDisc cd;

    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}
