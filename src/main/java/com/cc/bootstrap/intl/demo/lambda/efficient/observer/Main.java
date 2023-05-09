package com.cc.bootstrap.intl.demo.lambda.efficient.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 观察者模式
 * 好几家报纸机构，比如《纽约时报》《卫报》以及《世
 * 界报》都订阅了新闻，他们希望当接收的新闻中包含他们感兴趣的关键字时，能得到特别通知。
 *
 * 首先，你需要一个观察者接口，它将不同的观察者聚合在一起。它仅有一个名为notify的
 * 方法，一旦接收到一条新的新闻，该方法就会被调用
 * @author: ChenChen
 * @date: 2023/2/6 10:40
 */
public class Main {
    public static void main(String[] args) {
        //未使用Lambda表达式
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");

//        使用Lambda表达式
//        你可能会疑惑Lambda表达式在观察者设计模式中如何发挥它的作用。不知道你有没有注意
//        到，Observer接口的所有实现类都提供了一个方法：notify。新闻到达时，它们都只是对同一
//        段代码封装执行。Lambda表达式的设计初衷就是要消除这样的僵化代码。使用Lambda表达式后，
//        你无需显式地实例化三个观察者对象，直接传递Lambda表达式表示需要执行的行为即可：
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet);
            }
        });
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
    }
}
interface Observer {
    void notify(String tweet);
}

//下面是三个不同的观察者
class NYTimes implements Observer{
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("money")){
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
class Guardian implements Observer{
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("queen")){
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}
class LeMonde implements Observer{
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("wine")){
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}

//Subject使用registerObserver方法可以注册一个新的观察者，使用notifyObservers方法通知它的观察者一个新闻的到来。
interface Subject{
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}

class Feed implements Subject{
    private final List<Observer> observers = new ArrayList<>();
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}
