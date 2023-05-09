package com.cc.bootstrap.intl.demo.lambda.exp;

/**
 * @Description: 复合 Lambda 表达式的有用方法
 * @author: ChenChen
 * @date: 2023/1/28 9:26
 */
public class UseY4 {
    //比较器复合
    //1.逆序 默认方法reversed
//    inventory.sort(comparing(Apple::getWeight).reversed());

    //2.比较器链 如果发现有两个苹果一样重怎么办？哪个苹果应该排在前面呢？ thenComparing方法
//    inventory.sort(comparing(Apple::getWeight)
//             .reversed()
//             .thenComparing(Apple::getCountry));

    //谓词复合 negate、and和or
    //1.negate 非
//    Predicate<Apple> notRedApple = redApple.negate();

    //2.and 且
//    Predicate<Apple> redAndHeavyApple =
//            redApple.and(a -> a.getWeight() > 150);

    //3.or 或
//    要么是重（150克以上）的红苹果，要么是绿苹果
//    Predicate<Apple> redAndHeavyAppleOrGreen =
//            redApple.and(a -> a.getWeight() > 150)
//                    .or(a -> "green".equals(a.getColor()));

//    ！请注意，and和or方法是按照在表达式链中的位置，从左向右确定优先级的。！！！！
//    因此，a.or(b).and(c)可以看作(a || b) && c。

    //函数复合
//    你还可以把Function接口所代表的Lambda表达式复合起来。
//    Function接口为此配了andThen和compose两个默认方法，它们都会返回Function的一个实例。
    //1. andThen方法 会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数。 数学上会写作f(g(x))
//    假设有一个函数f给数字加1 (x -> x + 1)，另一个函数g给数字乘2，你可以将它们组合成一个函数h，先给数字加1，再给结果乘2
//    Function<Integer, Integer> f = x -> x + 1;
//    Function<Integer, Integer> g = x -> x * 2;
//    Function<Integer, Integer> h = f.andThen(g);
//    int result = h.apply(1);//这将返回4

    //2.compose方法
//    先把给定的函数用作compose的参数里面给的那个函数，然后再把函数本身用于结果。
//    比如在上一个例子里用compose的话，它将意味着f(g(x))，而andThen则意味着g(f(x))
//    Function<Integer, Integer> f = x -> x + 1;
//    Function<Integer, Integer> g = x -> x * 2;
//    Function<Integer, Integer> h = f.compose(g);
//    int result = h.apply(1);//这将返回3

    //函数复合在实际运用时可以做流水线步骤，比如先增加头，在增加体部文字，在增加信尾文字
}




