package com.cc.bootstrap.intl.demo.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @Description: 与用户互动
 * @author: ChenChen
 * @date: 2023/5/17 16:33
 */
public class Demo1_contactUser {
    public static void main(String[] args) throws FileNotFoundException {
        //1.运行Java程序的参数 -- 主要讲的main方法里面的参数是由JVM传递的
        //2.使用Scanner获取键盘输入
        // System.in代表标准输入，就是键盘输入
        Scanner sc = new Scanner(System.in);
        // 增加下面一行，只把回车符作为分隔符
        // sc.useDelimiter("\n"); ---- 通过此代码设置分隔符
        // 判断是否还有下一个输入项
        while (sc.hasNext())
        {
            // 输出输入项
            System.out.println("键盘输入的内容是："+ sc.next());
        }

//        Spring
//        键盘输入的内容是：Spring
//        hahah fjlkd fak   ------------- 默认分隔符按空格截取的sc.next()
//        键盘输入的内容是：hahah
//        键盘输入的内容是：fjlkd
//        键盘输入的内容是：fak

        //2.1 Scanner不仅能读取用户的键盘输入，还能读取文件输入。只要在创建Scanner对象时传入一个File对象作为参数，就可以让Scanner读取该文件的内容。
        // 将一个File对象作为Scanner的构造器参数，Scanner读取文件内容
        Scanner sc1 = new Scanner(new File("ScannerFileTest.java"));
        System.out.println("ScannerFileTest.java文件内容如下：");
        // 判断是否还有下一行
        while (sc1.hasNextLine())
        {
            // 输出文件中的下一行
            System.out.println(sc1.nextLine());
        }

    }
}
