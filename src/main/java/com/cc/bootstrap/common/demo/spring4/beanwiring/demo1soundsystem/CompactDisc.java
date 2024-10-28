package com.cc.bootstrap.common.demo.spring4.beanwiring.demo1soundsystem;

/**
 * @Description CompactDisc接口在Java中定义了CD的概念
 * CompactDisc的具体内容并不重要，重要的是你将其定义为一个接口。
 * 作为接口，它定义了CD播放器对一盘CD所能进行的操作。它将CD播放器的任意实现与CD本身的耦合降低到了最小的程度。
 * @author ChenChen
 * @return 
 * @date 2024-02-28 15:56
 */
public interface CompactDisc {
    void play();
}
