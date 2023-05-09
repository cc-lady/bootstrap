package com.cc.bootstrap.intl.demo.lambda.stream_api;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Description: 构建流
 * 本节将介绍如何从值序列、数组、文件来创建流，甚至由生成函数来创建无限流！
 * @author: ChenChen
 * @date: 2023/1/29 10:51
 */
public class Study6_Create {
    public static void main(String[] args) {
        //1.由值创建流-------------------Stream.of
//    你可以使用静态方法Stream.of，通过显式值创建一个流。它可以接受任意数量的参数。
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
//        你可以使用empty得到一个空流，如下所示：
        Stream<String> emptyStream = Stream.empty();

        //2.由数组创建流-------------------Arrays.stream
//        你可以使用静态方法Arrays.stream从数组创建一个流。它接受一个数组作为参数。
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        //3.由文件生成流
//        Java中用于处理文件等I/O操作的NIO API（非阻塞 I/O）已更新，以便利用Stream API。
//        java.nio.file.Files中的很多静态方法都会返回一个流。例如，一个很有用的方法是
//        Files.lines，它会返回一个由指定文件中的各行构成的字符串流。
        long uniqueWords = 0;//一个文件中有多少各不相同的词：
        try(Stream<String> lines =
                    Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }
        catch(IOException e){

        }

        //4.由函数生成流：创建无限流
//        Stream API提供了两个静态方法来从函数生成流：Stream.iterate和Stream.generate。
//        这两个操作可以创建所谓的无限流：不像从固定集合创建的流那样有固定大小的流。由iterate
//        和generate产生的流会用给定的函数按需创建值，因此可以无穷无尽地计算下去！一般来说，
//        应该使用limit(n)来对这种流加以限制，以避免打印无穷多个值。
        //4.1 迭代 -------------------Stream.iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        //4.2 生成 -------------------Stream.generate
//        与iterate方法类似，generate方法也可让你按需生成一个无限流。但generate不是依次
//        对每个新生成的值应用函数的。它接受一个Supplier<T>类型的Lambda提供新的值。我们先来
//        看一个简单的用法：
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

    }

}
