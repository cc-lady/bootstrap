package com.cc.bootstrap.common.demo.spring4.beanwiring.demo1soundsystem;

import org.springframework.stereotype.Component;

/**
 * @Description: 我们还需要一个CompactDisc的实现，实际上，我们可以有CompactDisc接口的多个实现。在本例中，我们首先会创建其中的一个实现
 * 带有@Component注解的CompactDisc实现类SgtPeppers
 * !!!使用了@Component注解。这个简单的注解表明该类会作为组件类，并告知Spring要为这个类创建bean。
 * 没有必要显式配置SgtPeppersbean，因为这个类使用了@Component注解，所以Spring会为你把事情处理妥当。
 * @author: ChenChen
 * @date: 2024-02-28 15:57
 */
@Component//不指明bean的ID，默认为类名第一个字母小写，同@Component("sgtPeppers")
public class SgtPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
