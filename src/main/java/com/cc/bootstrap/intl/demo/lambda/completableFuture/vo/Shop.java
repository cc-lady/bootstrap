package com.cc.bootstrap.intl.demo.lambda.completableFuture.vo;

import java.util.Random;

/**
 * @Description: 在线商店
 * 很明显，这个API的使用者（这个例子中为最佳价格查询器）调用该方法时，它依旧会被
 * 阻塞。为等待同步事件完成而等待1秒钟，这是无法接受的，尤其是考虑到最佳价格查询器对
 * 网络中的所有商店都要重复这种操作。本章接下来的小节中，你会了解如何以异步方式使用同
 * 步API解决这个问题。但是，出于学习如何设计异步API的考虑，我们会继续这一节的内容，假
 * 装我们还在深受这一困难的烦扰：你是一个睿智的商店店主，你已经意识到了这种同步API会
 * 为你的用户带来多么痛苦的体验，你希望以异步API的方式重写这段代码，让用户更流畅地访
 * 问你的网站。
 * @author: ChenChen
 * @date: 2023/2/7 16:17
 */
public class Shop {
    //为了实现最佳价格查询器应用，让我们从每个商店都应该提供的API定义入手。首先，商店
    //应该声明依据指定产品名称返回价格的方法
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    //该方法的内部实现会查询商店的数据库，但也有可能执行一些其他耗时的任务，比如联系其
    //他外部服务（比如，商店的供应商，或者跟制造商相关的推广折扣）。我们在本章剩下的内容中，
    //采用delay方法模拟这些长期运行的方法的执行，它会人为地引入1秒钟的延迟
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //根据商品获取随机价格
    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
