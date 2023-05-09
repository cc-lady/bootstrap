package com.cc.bootstrap.intl.demo.lambda.completableFuture.vo;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @Description: 改成异步的在线商店
 * @author: ChenChen
 * @date: 2023/2/7 16:37
 */
public class Shop2 {
    private String name;

    public Shop2(String name) {
        this.name = name;
    }

    public Future<Double> getPriceAsync(String product) {
        //创建CompletableFuture对象，它会包含计算的结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            double price = calculatePrice(product);//在另一个线程中以异步方式执行计算
            futurePrice.complete(price);//需长时间计算的任务结束并得出结果时，设置Future的返回值
        }).start();
        return futurePrice;//无需等待还没结束的计算，直接返回Future对象
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

    public static void main(String[] args) {
        Shop2 shop = new Shop2("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");
        // 执行更多任务，比如查询其他商店
        doSomethingElse();
        // 在计算商品价格的同时
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomethingElse() {
        System.out.println("执行更多任务，比如查询其他商店");
    }

}
