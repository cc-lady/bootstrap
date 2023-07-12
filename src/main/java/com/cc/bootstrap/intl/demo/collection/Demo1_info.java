package com.cc.bootstrap.intl.demo.collection;

/**
 * @Description: 概述
 * @author: ChenChen
 * @date: 2023/5/18 16:47
 */
public class Demo1_info {
    public static void main(String[] args) {
        /*在编程时，常常需要集中存放多个数据，例如，第6章练习题中梭哈游戏里剩下的牌。虽然可以使用数组来保存多个对象，但数组长度不可变化，
        一旦在初始化数组时指定了数组长度，这个数组长度就是不可变的，如果需要保存数量变化的数据，数组就有点无能为力了；而且数组无法保存具有映射关系的数据，
        如成绩表：语文—79，数学—80，这种数据看上去像两个数组，但这两个数组的元素之间有一定的关联关系。
        为了保存数量不确定的数据，以及保存具有映射关系的数据（也被称为关联数组）, Java提供了集合类。集合类主要负责保存、盛装其他数据，因此集合类也被称为容器类。
        所有的集合类都位于java.util包下，后来为了处理多线程环境下的并发安全问题，Java还在java.util.concurrent包下提供了一些多线程支持的集合类。
        集合类和数组不一样，数组元素既可以是基本类型的值，也可以是对象（实际上保存的是对象的引用变量）；而集合中只能保存对象（实际上只是保存对象的引用变量，但通常习惯上认为集合中保存的是对象）。
        Java的集合类主要由两个接口派生：Collection和Map。Collection和Map是Java集合框架的根接口，这两个接口又包含了一些子接口或实现类。如图8.1所示是Collection接口、子接口及其实现类的继承树。
        如果访问List集合中的元素，则可以直接根据元素的索引来访问；如果访问Map集合中的元素，则可以根据每项元素的key来访问其value；如果访问Set集合中的元素，则只能根据元素本身来访问
    （这也是Set集合中元素不允许重复的原因）。
        对于Set、List、Queue和Map这4种集合，最常用的实现类（在图8.1、图8.2中以灰色背景色覆盖）分别是HashSet、TreeSet、ArrayList、ArrayDeque、LinkedList和HashMap、TreeMap等。

        本章主要讲解没有涉及并发控制的集合类，对于Java 5新增的具有并发控制的集合类，以及Java 7新增的TransferQueue及其实现类LinkedTransferQueue，将在第16章中与多线程一起介绍。*/


    }
}
