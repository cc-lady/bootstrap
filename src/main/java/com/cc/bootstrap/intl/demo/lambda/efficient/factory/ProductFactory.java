package com.cc.bootstrap.intl.demo.lambda.efficient.factory;

/**
 * @Description: 工厂模式
 * 。比如，我们假定你为
 * 一家银行工作，他们需要一种方式创建不同的金融产品：贷款、期权、股票，等等。
 * 通常，你会创建一个工厂类，它包含一个负责实现不同对象的方法
 * @author: ChenChen
 * @date: 2023/2/6 15:02
 */
public class ProductFactory {
    //createProduct方法可以通过附加的逻辑来设置每个创建的产品。但是带来的好处也显而易
    //见，你在创建对象时不用再担心会将构造函数或者配置暴露给客户，这使得客户创建产品时更
    //加简单
//    public static Product createProduct(String name){
//        switch(name){
//            case "loan": return new Loan();
//            case "stock": return new Stock();
//            case "bond": return new Bond();
//            default: throw new RuntimeException("No such product " + name);
//        }
//    }

    public static void main(String[] args) {
//        这里贷款（Loan）、股票（Stock）和债券（Bond）都是产品（Product）的子类。
//        createProduct方法可以通过附加的逻辑来设置每个创建的产品。但是带来的好处也显而易
//        见，你在创建对象时不用再担心会将构造函数或者配置暴露给客户，这使得客户创建产品时更
//                加简单
//        Product p = ProductFactory.createProduct("loan");
    }
}
