package com.cc.bootstrap.common.demo.spring4.beanwiring.demo1soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 组件扫描默认是不启用的。我们还需要显式配置一下Spring，从而命令它去寻找带有@Component注解的类，并为其创建bean。
 * 如果没有其他配置的话，@ComponentScan默认会扫描与配置类相同的包。因为CDPlayerConfig类位于soundsystem包中，
 * 因此Spring将会扫描这个包以及这个包下的所有子包，查找带有@Component注解的类。
 * 这样的话，就能发现CompactDisc，并且会在Spring中自动为其创建一个bean。
 *
 * 尽管我们可以通过XML的方案来启用组件扫描，但是在后面的讨论中，我更多的还是会使用基于Java的配置。如果你更喜欢XML的话，
 * <context:component-scan>元素会有与@ComponentScan注解相对应的属性和子元素。
 * 例如：
 * <context:component-scan base-package="com.cc.bootstrap.common.demo.spring4.beanwiring.demo1soundsystem" />
 * @author: ChenChen
 * @date: 2024-02-28 15:59
 */
@Configuration
@ComponentScan//!!!@ComponentScan注解启用了组件扫描，按照默认规则，它会以配置类所在的包作为基础包（base package）来扫描组件。
//@ComponentScan(basePackages={"soundsystem", "video"}) 多个基础包配置
//@ComponentScan(basePackageClasses={CDPlayer.class, DVDPlayer.class}) 多个基础包配置
public class CDPlayerConfig {
}
