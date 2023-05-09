package com.cc.bootstrap.intl.demo.lambda.exp;

/**
 * @Description: Lambda 和方法引用实战
 * inventory.sort(comparing(Apple::getWeight));//我们想要实现的最终解决方案是这样的
 * @author: ChenChen
 * @date: 2023/1/28 9:20
 */
public class UseY3 {
    //第 1 步：传递代码
//    你很幸运，Java 8的API已经为你提供了一个List可用的sort方法，你不用自己去实现它。
//    那么最困难的部分已经搞定了！但是，如何把排序策略传递给sort方法呢？你看，sort方法的
//    签名是这样的：
//    void sort(Comparator<? super E> c)
//    它需要一个Comparator对象来比较两个Apple！这就是在Java中传递策略的方式：它们必
//    须包裹在一个对象里。我们说sort的行为被参数化了：传递给它的排序策略不同，其行为也会
//    不同。
//    你的第一个解决方案看上去是这样的：
//    public class AppleComparator implements Comparator<Apple> {
//        public int compare(Apple a1, Apple a2){
//            return a1.getWeight().compareTo(a2.getWeight());
//        }
//    }
//    inventory.sort(new AppleComparator());

    //第 2 步：使用匿名类
//    你在前面看到了，你可以使用匿名类来改进解决方案，而不是实现一个Comparator却只实
//    例化一次：
//    inventory.sort(new Comparator<Apple>() {
//        public int compare(Apple a1, Apple a2){
//            return a1.getWeight().compareTo(a2.getWeight());
//        }
//    });

    //第 3 步：使用 Lambda 表达式
    //在需要函数式接口的地方可以使用Lambda表达式。我们
    //回顾一下：函数式接口就是仅仅定义一个抽象方法的接口。抽象方法的签名（称为函数描述符）
    //描述了Lambda表达式的签名。在这个例子里，Comparator代表了函数描述符(T, T) -> int。
    //因为你用的是苹果，所以它具体代表的就是(Apple, Apple) -> int。
//    inventory.sort((Apple a1, Apple a2)
//            -> a1.getWeight().compareTo(a2.getWeight())
//            );

//    我们前面解释过了，Java编译器可以根据Lambda出现的上下文来推断Lambda表达式参数的
//    类型。那么你的解决方案就可以重写成这样：
//    inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

//    你的代码还能变得更易读一点吗？Comparator具有一个叫作comparing的静态辅助方法，
//    它可以接受一个Function来提取Comparable键值，并生成一个Comparator对象（我们会在第
//9章解释为什么接口可以有静态方法）。它可以像下面这样用（注意你现在传递的Lambda只有一
//    个参数：Lambda说明了如何从苹果中提取需要比较的键值）：
//    Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
//    现在你可以把代码再改得紧凑一点了：
//    import static java.util.Comparator.comparing;
//    inventory.sort(comparing((a) -> a.getWeight()));

    //第 4 步：使用方法引用
//    方法引用就是替代那些转发参数的Lambda表达式的语法糖。你可以用方法引
//    用让你的代码更简洁（假设你静态导入了java.util.Comparator.comparing）：
//    inventory.sort(comparing(Apple::getWeight));
//    恭喜你，这就是你的最终解决方案！这比Java 8之前的代码好在哪儿呢？它比较短；它的意
//    思也很明显，并且代码读起来和问题描述差不多：“对库存进行排序，比较苹果的重量。”
}
