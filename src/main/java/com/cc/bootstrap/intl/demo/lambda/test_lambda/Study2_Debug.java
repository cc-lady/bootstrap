package com.cc.bootstrap.intl.demo.lambda.test_lambda;

/**
 * @Description: 调试
 * 事情可能不会一帆风顺，你的测试可能会返回错误，报告说你使用Lambda表达式的方式不对。因此，我们现在进入调试的环节。
 *
 * 调试有问题的代码时，程序员的兵器库里有两大老式武器，分别是：
 *  查看栈跟踪
 *  输出日志
 * @author: ChenChen
 * @date: 2023/2/7 9:05
 */
public class Study2_Debug {
    public static void main(String[] args) {
        //1.查看栈跟踪
//        程序的每次方法调用都会产生相应的调用信息，包括程序中方法调用的位置、该方法调用使用的参数、被调用方法的本地变量。这些信
//        息被保存在栈帧上。
//        程序失败时，你会得到它的栈跟踪，通过一个又一个栈帧，你可以了解程序失败时的概略信
//        息。换句话说，通过这些你能得到程序失败时的方法调用列表。这些方法调用列表最终会帮助你
//        发现问题出现的原因。

        //Lambda表达式和栈跟踪
//        不幸的是，由于Lambda表达式没有名字，它的栈跟踪可能很难分析。
//        Exception in thread "main" java.lang.NullPointerException
//        at Debugging.lambda$main$0(Debugging.java:6)
//        at Debugging$$Lambda$5/284720968.apply(Unknown Source)
//
//        如果方法引用指向的是同一个类中声明的方法，那么它的名称是可以在栈跟踪中显示的。比如，下面这个例子：
//        import java.util.*;
//        public class Debugging{
//            public static void main(String[] args) {
//                List<Integer> numbers = Arrays.asList(1, 2, 3);
//                numbers.stream().map(Debugging::divideByZero).forEach(System
//                        .out::println);
//            }
//            public static int divideByZero(int n){
//                return n / 0;
//            }
//        }
//        方法divideByZero在栈跟踪中就正确地显示了：
//        Exception in thread "main" java.lang.ArithmeticException: / by zero
//        at Debugging.divideByZero(Debugging.java:10)
//        at Debugging$$Lambda$1/999966131.apply(Unknown Source)
//        at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline
//                .java:193)
//        总的来说，我们需要特别注意，涉及Lambda表达式的栈跟踪可能非常难理解。这是Java编译
//        器未来版本可以改进的一个方面。

        //2.使用日志调试 -----------------------------------------------peek
//        peek的设计初衷就是在流的每个元素恢复运行之
//        前，插入执行一个动作。但是它不像forEach那样恢复整个流的运行，而是在一个元素上完成操
//        作之后，它只会将操作顺承到流水线中的下一个操作。

//        假设你试图对流操作中的流水线进行调试，该从何入手呢？你可以像下面的例子那样，使用
//        forEach将流操作的结果日志输出到屏幕上或者记录到日志文件中：
//        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
//        numbers.stream()
//                .map(x -> x + 17)
//                .filter(x -> x % 2 == 0)
//                .limit(3)
//                .forEach(System.out::println);
//        这段代码的输出如下：
//        20
//        22
//        不幸的是，一旦调用forEach，整个流就会恢复运行。到底哪种方式能更有效地帮助我们理
//        解Stream流水线中的每个操作（比如map、filter、limit）产生的输出？
//        这就是流操作方法peek大显身手的时候。peek的设计初衷就是在流的每个元素恢复运行之
//        前，插入执行一个动作。但是它不像forEach那样恢复整个流的运行，而是在一个元素上完成操
//        作之后，它只会将操作顺承到流水线中的下一个操作。图8-4解释了peek的操作流程。下面的这
//        段代码中，我们使用peek输出了Stream流水线操作之前和操作之后的中间值：
//        List<Integer> result =
//                numbers.stream()
//                        .peek(x -> System.out.println("from stream: " + x))
//                        .map(x -> x + 17)
//                        .peek(x -> System.out.println("after map: " + x))
//                        .filter(x -> x % 2 == 0)
//                        .peek(x -> System.out.println("after filter: " + x))
//                        .limit(3)
//                        .peek(x -> System.out.println("after limit: " + x))
//                        .collect(toList());
//        通过peek操作我们能清楚地了解流水线操作中每一步的输出结果

//        流提供的peek方法在分析Stream流水线时，能将中间变量的值输出到日志中，是非常有用的工具。
    }
}
