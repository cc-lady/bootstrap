package com.cc.bootstrap.intl.demo.lambda.exp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Description: 那到底在哪里可以使用Lambda呢？你可以在函数式接口上使用Lambda表达式。
 * 函数式接口就是只定义一个抽象方法的接口。
 * public interface Predicate<T>{
 *  boolean test (T t);
 * }
 * @author: ChenChen
 * @date: 2023/1/28 9:06
 */
public class UseWhere {

    //打印文件第一行代码
    public static String processFile() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    //第 1 步：记得行为参数化
//    现在这段代码是有局限的。你只能读文件的第一行。如果你想要返回头两行，甚至是返回使
//    用最频繁的词，该怎么办呢？在理想的情况下，你要重用执行设置和清理的代码，并告诉
//    processFile方法对文件执行不同的操作。这听起来是不是很耳熟？是的，你需要把
//    processFile的行为参数化。你需要一种方法把行为传递给processFile，以便它可以利用
//    BufferedReader执行不同的行为。
    //下面就是从BufferedReader中打印两行的写法
//    String result = processFile((BufferedReader br) ->
//            br.readLine() + br.readLine());


    //第 2 步：使用函数式接口来传递行为
//    你需要创建一个能匹配BufferedReader -> String，还可以抛出IOException异常的接口。
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
//    现在你就可以把这个接口作为新的processFile方法的参数了：
//    public static String processFile(BufferedReaderProcessor p) throws
//            IOException {
// …
//    }


    //第 3 步：执行一个行为
    public static String processFile(BufferedReaderProcessor p) throws
            IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);//处理BufferedReader对象
        }
    }


    //第 4 步：传递 Lambda
//    现在你就可以通过传递不同的Lambda重用processFile方法，并以不同的方式处理文件了。
//    处理一行：
//    String oneLine =
//            processFile((BufferedReader br) -> br.readLine());
//    处理两行：
//    String twoLines =
//            processFile((BufferedReader br) -> br.readLine() + br.readLine());
}
