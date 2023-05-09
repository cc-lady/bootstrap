package com.cc.bootstrap.intl.demo.lambda.stream_api.exercises.vo;

/**
 * @Description: 交易员
 * @author: ChenChen
 * @date: 2023/1/29 9:32
 */
public class Trader{
    private final String name;
    private final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }

    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}
