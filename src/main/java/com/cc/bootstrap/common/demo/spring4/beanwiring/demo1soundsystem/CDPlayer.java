package com.cc.bootstrap.common.demo.spring4.beanwiring.demo1soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 通过自动装配，将一个CompactDisc注入到CDPlayer之中
 * @author: ChenChen
 * @date: 2024-02-29 10:48
 */
@Component
public class CDPlayer {
    private CompactDisc cd;

    @Autowired
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }
    public void play() {
        cd.play();
    }
}
