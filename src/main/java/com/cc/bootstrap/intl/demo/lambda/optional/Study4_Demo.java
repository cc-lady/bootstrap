package com.cc.bootstrap.intl.demo.lambda.optional;

import java.util.Optional;
import java.util.Properties;

/**
 * @Description: 使用 Optional 的实战示例
 * 相信你已经了解，有效地使用Optional类意味着你需要对如何处理潜在缺失值进行全面的
 * 反思。这种反思不仅仅限于你曾经写过的代码，更重要的可能是，你如何与原生Java API实现共
 * 存共赢。
 * 实际上，我们相信如果Optional类能够在这些API创建之初就存在的话，很多API的设计编
 * 写可能会大有不同。为了保持后向兼容性，我们很难对老的Java API进行改动，让它们也使用
 * Optional，但这并不表示我们什么也做不了。你可以在自己的代码中添加一些工具方法，修复
 * 或者绕过这些问题，让你的代码能享受Optional带来的威力。我们会通过几个实际的例子讲解
 * 如何达到这样的目的。
 * @author: ChenChen
 * @date: 2023/2/7 11:03
 */
public class Study4_Demo {
    public static void main(String[] args) {
        //1.用 Optional 封装可能为 null 的值
//        现存Java API几乎都是通过返回一个null的方式来表示需要值的缺失，或者由于某些原因计
//        算无法得到该值。比如，如果Map中不含指定的键对应的值，它的get方法会返回一个null。但
//        是，正如我们之前介绍的，大多数情况下，你可能希望这些方法能返回一个Optional对象。你
//        无法修改这些方法的签名，但是你很容易用Optional对这些方法的返回值进行封装。我们接着
//        用Map做例子，假设你有一个Map<String, Object>方法，访问由key索引的值时，如果map
//        中没有与key关联的值，该次调用就会返回一个null。
//        Object value = map.get("key");
//        使用Optional封装map的返回值，你可以对这段代码进行优化。要达到这个目的有两种方
//        式：你可以使用笨拙的if-then-else判断语句，毫无疑问这种方式会增加代码的复杂度；或者
//        你可以采用我们前文介绍的Optional.ofNullable方法：
//        Optional<Object> value = Optional.ofNullable(map.get("key"));
//        每次你希望安全地对潜在为null的对象进行转换，将其替换为Optional对象时，都可以考
//        虑使用这种方法。

        //2.异常与 Optional 的对比
//        由于某种原因，函数无法返回某个值，这时除了返回null，Java API比较常见的替代做法是
//        抛出一个异常。这种情况比较典型的例子是使用静态方法Integer.parseInt(String)，将
//        String转换为int。在这个例子中，如果String无法解析到对应的整型，该方法就抛出一个
//        NumberFormatException。最后的效果是，发生String无法转换为int时，代码发出一个遭遇
//        非法参数的信号，唯一的不同是，这次你需要使用try/catch 语句，而不是使用if条件判断来
//        控制一个变量的值是否非空。
//        你也可以用空的Optional对象，对遭遇无法转换的String时返回的非法值进行建模，这时
//        你期望parseInt的返回值是一个optional。我们无法修改最初的Java方法，但是这无碍我们进
//        行需要的改进，你可以实现一个工具方法，将这部分逻辑封装于其中，最终返回一个我们希望的
//        Optional对象，代码如下所示。
//        代码清单10-6 将String转换为Integer，并返回一个Optional对象
//        public static Optional<Integer> stringToInt(String s) {
//            try {
//                return Optional.of(Integer.parseInt(s));
//            } catch (NumberFormatException e) {
//                return Optional.empty();
//            }
//        }
//        我们的建议是，你可以将多个类似的方法封装到一个工具类中，让我们称之为OptionalUtility。通过这种方式，你以后就能直接调用OptionalUtility.stringToInt方法，将
//        String转换为一个Optional<Integer>对象，而不再需要记得你在其中封装了笨拙的
//        try/catch的逻辑了。

        //基础类型的Optional对象，以及为什么应该避免使用它们
//        不推荐大家使用基础类型的Optional，因为基础类型的Optional不支持map、
//        flatMap以及filter方法，而这些却是Optional类最有用的方法（正如我们在10.2节所看到的
//        那样）。此外，与Stream一样，Optional对象无法由基础类型的Optional组合构成，所以，举
//        例而言，如果代码清单10-6中返回的是OptionalInt类型的对象，你就不能将其作为方法引用传
//        递给另一个Optional对象的flatMap方法。

        //3.把所有内容整合起来
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

//        现在，我们假设你的程序需要从这些属性中读取一个值，该值是以秒为单位计量的一段时间。
//        由于一段时间必须是正数，你想要该方法符合下面的签名：
//        public int readDuration(Properties props, String name)
//        即，如果给定属性对应的值是一个代表正整数的字符串，就返回该整数值，任何其他的情况都返
//        回0。为了明确这些需求，你可以采用JUnit的断言，将它们形式化：
//        assertEquals(5, readDuration(param, "a"));
//        assertEquals(0, readDuration(param, "b"));
//        assertEquals(0, readDuration(param, "c"));
//        assertEquals(0, readDuration(param, "d"));
//        这些断言反映了初始的需求：如果属性是a，readDuration方法返回5，因为该属性对应的
//        字符串能映射到一个正数；对于属性b，方法的返回值是0，因为它对应的值不是一个数字；对于
//        c，方法的返回值是0，因为虽然它对应的值是个数字，不过它是个负数；对于d，方法的返回值
//        是0，因为并不存在该名称对应的属性。让我们以命令式编程的方式实现满足这些需求的方法，
//        代码清单如下所示。

//        注意到使用Optional和Stream时的那些通用模式了吗？它们都是对数据库查询过程的反
//        思，查询时，多种操作会被串接在一起执行。
    }

    //原始写法
    public int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) {
            }
        }
        return 0;
    }

    //优化后写法
    public int readDuration1(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(Study4_Demo::stringToInt)
                .filter(i -> i>0)
                .orElse(0);
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
