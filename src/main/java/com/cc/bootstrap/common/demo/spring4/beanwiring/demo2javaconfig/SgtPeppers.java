package com.cc.bootstrap.common.demo.spring4.beanwiring.demo2javaconfig;

/**
 * @Description: 我们还需要一个CompactDisc的实现，实际上，我们可以有CompactDisc接口的多个实现。在本例中，我们首先会创建其中的一个实现
 * @author: ChenChen
 * @date: 2024-02-28 15:57
 */
public class SgtPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
