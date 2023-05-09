package com.cc.bootstrap.intl.demo.lambda.stream_api;

/**
 * @Description: Optional
 * @author: ChenChen
 * @date: 2023/1/28 16:24
 */
public class Study5_Optional {
//    Optional<T>类（java.util.Optional）是一个容器类，代表一个值存在或不存在。在
//    上面的代码中，findAny可能什么元素都没找到。Java 8的库设计人员引入了Optional<T>，这
//    样就不用返回众所周知容易出问题的null了。我们在这里不会详细讨论Optional，因为第10章
//    会详细解释你的代码如何利用Optional，避免和null检查相关的bug。

//    现在，了解一下
//    Optional里面几种可以迫使你显式地检查值是否存在或处理值不存在的情形的方法也不错。
//     isPresent()将在Optional包含值的时候返回true, 否则返回false。
//     ifPresent(Consumer<T> block)会在值存在的时候执行给定的代码块。我们在第3章
//    介绍了Consumer函数式接口；它让你传递一个接收T类型参数，并返回void的Lambda
//    表达式。
//     T get()会在值存在时返回值，否则抛出一个NoSuchElement异常。
//     T orElse(T other)会在值存在时返回值，否则返回一个默认值。
//    例如，在前面的代码中你需要显式地检查Optional对象中是否存在一道菜可以访问其名称：
//            menu.stream()
//            .filter(Dish::isVegetarian)
//            .findAny()
//            .ifPresent(d -> System.out.println(d.getName());


}
