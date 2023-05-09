package com.cc.bootstrap.intl.demo.faster.vo;

import java.util.Random;

/**
 * @Description: 为了模拟调用第三方
 * @author: ChenChen
 * @date: 2023/5/9 10:50
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
