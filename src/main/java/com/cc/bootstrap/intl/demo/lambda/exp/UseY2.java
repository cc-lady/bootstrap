package com.cc.bootstrap.intl.demo.lambda.exp;

/**
 * @Description: 异常、Lambda，还有函数式接口
 * 任何函数式接口都不允许抛出受检异常（checked exception）。
 * @author: ChenChen
 * @date: 2023/1/28 9:17
 */
public class UseY2 {
//    请注意，任何函数式接口都不允许抛出受检异常（checked exception）。如果你需要Lambda
//    表达式来抛出异常，有两种办法：定义一个自己的函数式接口，并声明受检异常，或者把Lambda
//    包在一个try/catch块中。
//    比如，在3.3节我们介绍了一个新的函数式接口BufferedReaderProcessor，它显式声
//    明了一个IOException：
//    @FunctionalInterface
//    public interface BufferedReaderProcessor {
//        String process(BufferedReader b) throws IOException;
//    }
//    BufferedReaderProcessor p = (BufferedReader br) -> br.readLine();
//    但是你可能是在使用一个接受函数式接口的API，比如Function<T, R>，没有办法自己
//    创建一个（你会在下一章看到，Stream API中大量使用表3-2中的函数式接口）。这种情况下，
//    你可以显式捕捉受检异常：
//    Function<BufferedReader, String> f = (BufferedReader b) -> {
//        try {
//            return b.readLine();
//        }
//        catch(IOException e) {
//            throw new RuntimeException(e);
//        }
//    };
}
