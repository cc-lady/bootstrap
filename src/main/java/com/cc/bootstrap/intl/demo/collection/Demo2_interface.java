package com.cc.bootstrap.intl.demo.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * @Description: Collection和Iterator接口
 * @author: ChenChen
 * @date: 2023/5/18 16:58
 */
public class Demo2_interface {
    public static void main(String[] args) {
        /*Collection接口是List、Set和Queue接口的父接口，该接口里定义的方法既可用于操作Set集合，也可用于操作List和Queue集合。Collection接口里定义了如下操作集合元素的方法。
➢boolean add(Object o)：该方法用于向集合里添加一个元素。如果集合对象被添加操作改变了，则返回true。
➢boolean addAll(Collection c)：该方法把集合c里的所有元素添加到指定集合中。如果集合对象被添加操作改变了，则返回true。
➢void clear()：清除集合里的所有元素，将集合长度变为0。
➢boolean contains(Object o)：返回集合里是否包含指定元素。
➢boolean containsAll(Collection c)：返回集合中是否包含集合c里的所有元素。
➢boolean isEmpty()：返回集合是否为空。当集合长度为0时返回true，否则返回false。
➢Iterator iterator()：返回一个Iterator对象，用于遍历集合里的元素。
➢boolean remove(Object o)：删除集合中的指定元素o，当集合中包含了一个或多个元素o时，该方法只删除第一个符合条件的元素，该方法将返回true。
➢boolean removeAll(Collection c)：从集合中删除集合c里包含的所有元素（相当于用调用该方法的集合减集合c）。如果删除了一个或一个以上的元素，则该方法返回true。
➢boolean retainAll(Collection c)：从集合中删除集合c里不包含的元素（相当于把调用该方法的集合变成该集合和集合c的交集）。如果该操作改变了调用该方法的集合，则该方法返回true。
➢int size()：该方法返回集合里元素的个数。
➢Object[] toArray()：该方法把集合转换成一个数组，所有的集合元素变成对应的数组元素。*/
        Collection c = new ArrayList();
        // 添加元素
        c.add("孙悟空");
        // 虽然集合里不能放基本类型的值，但Java支持自动装箱
        c.add(6);
        System.out.println("c集合的元素个数为：" + c.size()); // 输出2
        // 删除指定元素
        c.remove(6);
        System.out.println("c集合的元素个数为：" + c.size()); // 输出1
        // 判断是否包含指定字符串
        System.out.println("c集合是否包含\"孙悟空\"字符串："
                + c.contains("孙悟空")); // 输出true
        c.add("轻量级Java EE企业应用实战");
        System.out.println("c集合的元素：" + c);
        Collection books = new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");
        System.out.println("c集合是否完全包含books集合？"
                + c.containsAll(books)); // 输出false
        // 用c集合减去books集合里的元素
        c.removeAll(books);
        System.out.println("c集合的元素：" + c);//c集合的元素：[孙悟空]
        // 删除c集合里的所有元素
        c.clear();
        System.out.println("c集合的元素：" + c);//[]
        // 控制books集合里只剩下c集合中也包含的元素
        books.retainAll(c);
        System.out.println("books集合的元素：" + books);//[]


        /*当使用System.out的println()方法来输出集合对象时，将输出[ele1, ele2, ...]的形式，这显然是因为所有的Collection实现类都重写了toString()方法，该方法可以一次性地输出集合中的所有元素。
        如果想依次访问集合里的每一个元素，则需要使用某种方式来遍历集合元素。下面介绍遍历集合元素的两种方法。*/
/*        Java 11为Collection新增了一个toArray(IntFunction)方法，使用该方法的主要目的就是利用泛型。对于传统的toArray()方法而言，不管Collection 本身是否使用泛型，
        toArray()的返回值总是Object[]；但新增的toArray(IntFunction)方法不同，当Collection使用泛型时，toArray(IntFunction)可以返回特定类型的数组。例如如下代码：
// 该Collection使用了泛型，指定它的集合元素都是String
        var strColl = List.of("Java", "Kotlin", "Swift", "Python");
// toArray()方法的参数是一个Lambda表达式，代表IntFunction对象
// 此时toArray()方法的返回值类型是String[]，而不是Object[]String[] sa = strColl.toArray(String[]::new);
        System.out.println(Arrays.toString(sa));
        上面的粗体字代码示范了toArray(IntFunction)方法的特点：由于编译器推断strColl 的类型为List<String>（带泛型），因此该方法的返回值就是String[]类型。
        需要额外说明的是，由于使用该方法的主要目的就是利用泛型，因此toArray(IntFunction)方法的参数通常就是它要返回的数组类型后面加双冒号和new（构造器引用）。*/

        //
    }
}
