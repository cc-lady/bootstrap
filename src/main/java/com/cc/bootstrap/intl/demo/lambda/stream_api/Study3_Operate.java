package com.cc.bootstrap.intl.demo.lambda.stream_api;

/**
 * @Description: 流操作
 * @author: ChenChen
 * @date: 2023/1/28 14:35
 */
public class Study3_Operate {
//    java.util.stream.Stream中的Stream接口定义了许多操作。它们可以分为两大类。
//    可以连接起来的流操作称为中间操作，关闭流的操作称为终端操作。
    //1.中间操作
//    诸如filter或sorted等中间操作会返回另一个流。这让多个操作可以连接起来形成一个查
//    询。重要的是，除非流水线上触发一个终端操作，否则中间操作不会执行任何处理——它们很懒。
//    这是因为中间操作一般都可以合并起来，在终端操作时一次性全部处理。

//    只选出了前三个！这是因为limit操作和一种称为短路的技巧，我们会在下一章中解释。第二，
//    尽管filter和map是两个独立的操作，但它们合并到同一次遍历中了（我们把这种技术叫作循环
//    合并）。

    //2.终端操作
//    终端操作会从流的流水线生成结果。其结果是任何不是流的值，比如List、Integer，甚至void。
//    例如：menu.stream().forEach(System.out::println);

    //3.使用流
//    总而言之，流的使用一般包括三件事：
//    一个数据源（如集合）来执行一个查询；
//    一个中间操作链，形成一条流的流水线；
//    一个终端操作，执行流水线，并能生成结果。

//    流的流水线背后的理念类似于构建器模式。①在构建器模式中有一个调用链用来设置一套配
//    置（对流来说这就是一个中间操作链），接着是调用built方法（对流来说就是终端操作）。
//    以下并不能涵盖Stream API提供的操作，你在下一章中还会看到更多！！！！
//    表4-1 中间操作
//    操 作         类 型         返回类型        操作参数                函数描述符
//    filter      中间            Stream<T>     Predicate<T>          T -> boolean
//    map         中间            Stream<R>     Function<T, R>        T -> R
//    limit       中间            Stream<T>
//    sorted      中间            Stream<T>     Comparator<T>         (T, T) -> int
//    distinct    中间            Stream<T>

//    表4-2 终端操作
//    操 作           类 型           目 的
//    forEach         终端          消费流中的每个元素并对其应用 Lambda。这一操作返回 void
//    count           终端          返回流中元素的个数。这一操作返回 long
//    collect         终端          把流归约成一个集合，比如 List、Map 甚至是 Integer。详见第 6 章

//    表5-1 中间操作和终端操作
//    操 作           类 型                      返回类型         使用的类型/函数式接口       函数描述符
//    filter          中间                      Stream<T>       Predicate<T>            T -> boolean
//    distinct        中间(有状态无界)          Stream<T>
//    skip            中间(有状态有界)          Stream<T>       long
//    limit           中间(有状态有界)          Stream<T>       long
//    map             中间                      Stream<R>       Function<T, R>          T -> R
//    flatMap         中间                      Stream<R>       Function<T, Stream<R>>  T -> Stream<R>
//    sorted          中间(有状态无界)          Stream<T>       Comparator<T>           (T, T) -> int
//    anyMatch        终端                      boolean         Predicate<T>            T -> boolean
//    noneMatch       终端                      boolean         Predicate<T>            T -> boolean
//    allMatch        终端                      boolean         Predicate<T>            T -> boolean
//    findAny         终端                      Optional<T>
//    findFirst       终端                      Optional<T>
//    forEach         终端                      void            Consumer<T>             T -> void
//    collect         终端                      R               Collector<T, A, R>
//    reduce          终端(有状态有界)          Optional<T>     BinaryOperator<T>       (T, T) -> T
//    count           终端                      long

}
