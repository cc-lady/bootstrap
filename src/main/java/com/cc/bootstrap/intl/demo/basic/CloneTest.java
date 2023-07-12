package com.cc.bootstrap.intl.demo.basic;

/**
 * @Description: 克隆
 * @author: ChenChen
 * @date: 2023/5/18 8:53
 */
class Address
{
    String detail;
    public Address(String detail)
    {
        this.detail = detail;
    }
}
// 实现Cloneable接口
class User implements Cloneable
{
    int age;
    Address address;
    public User(int age)
    {
        this.age = age;
        address = new Address("广州天河");
    }
    // 通过调用super.clone()来实现clone()方法 ”—克隆出来的对象只是原有对象的副本。
    public User clone()
        throws CloneNotSupportedException
    {
        return (User) super.clone();
    }
}

public class CloneTest
{
    public static void main(String[] args)
            throws CloneNotSupportedException
    {
        User u1 = new User(29);
        // 克隆得到u1对象的副本
        User u2 = u1.clone();
        // 判断u1、u2是否相同
        System.out.println(u1 == u2);   // ① false
        // 判断u1、u2的address是否相同
        System.out.println(u1.address == u2.address);   // ② true Object 类提供的克隆机制只对对象里的各实例变量进行“简单复制”，
        // 即使实例变量的类型是引用类型，Object的克隆机制也只是简单地复制这个引用变量，这样原有对象的引用类型的实例变量与克隆对象的引用类型的实例变量
        // 依然指向内存中的同一个实例
    }
}