package com.cc.bootstrap.intl.demo.lambda;

import com.cc.bootstrap.common.schema.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName com.cc.bootstrap.intl.demo.lambda.study3.Note
 * @Description 第四章学习笔记 —— 类库
 * @createTime 2021年12月08日 21:42:00
 */
public class Study4 {
    private static Logger LOGGER = LoggerFactory.getLogger(Study4.class);

    // 启用 Lambda 表达式实现的日志记录器
    public static void debug(Supplier<String> message) {
        if (isDebugEnabled()) { debugMessage(message.get()); }
    }

    private static void debugMessage(String s) {
        LOGGER.debug(s);
    }

    private static boolean isDebugEnabled() {
        return LOGGER.isDebugEnabled();
    }

    public static void main(String[] args) {
        // 4.1 在代码中使用Lambda表达式 - 启用 Lambda 表达式实现的日志记录器
        /**
         * 在 slf4j 和 log4j 等几种常用的日志系统中，有 一些记录日志的方法，当日志级别不低于某个固定级别时就会开始记录日志。如此一来，
         * 在日志框架中设置类似 void debug(String message) 这样的方法，当级别为 debug 时，它 们就开始记录日志消息。
         * 问题在于，频繁计算消息是否应该记录日志会对系统性能产生影响。程序员通过显式调用 isDebugEnabled 方法来优化系统性能，
         * 如例 4-1 所示。即使直接调用 debug 方法能省去记录文本信息，也仍然需要调用 expensiveOperation 方法，
         * 并且需要将执行结果和已有字符 串连接起来，
         * 因此，使用 if 语句显式判断，可以让程序跑得更快。
         */
        String message = "异常信息";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Look at this: " + message);
        }

        // 启用 Lambda 表达式实现的日志记录器
        debug(() -> "Look at this: " + message);

        // 4.2 使用 summaryStatistics 方法统计
        // 例如查找密码长度
        List<User> userList = new ArrayList<>();
        userList.add(new User("cc", "123456", "cc@qq.com"));
        userList.add(new User("dd", "123", "dd@qq.com"));
        userList.add(new User("ee", "2544514545", "ee@qq.com"));

        IntSummaryStatistics intSummaryStatistics =
                userList.stream()
                        .filter(user -> user.getPassword().length() > 3)
                        .mapToInt(user -> user.getPassword().length()).summaryStatistics();
        LOGGER.info("max [{}] min [{}] average [{}] sum [{}] count [{}]",
                intSummaryStatistics.getMax(), intSummaryStatistics.getMin(), intSummaryStatistics.getAverage(),
                intSummaryStatistics.getSum(), intSummaryStatistics.getCount());
        // 16:45:12.829 [main] INFO  Note -max [10] min [6] average [8.0] sum [16] count [2]

        // 4.3 重载解析
        /**
         * 在 Java 中可以重载方法，造成多个方法有相同的方法名，但签名确不一样。这在推断参数 类型时会带来问题，因为系统可能会推断出多种类型。
         * 这时，javac 会挑出最具体的类型。
         *
         * Lambda 表达式的类型就是对应的函数接口类型，因此，将 Lambda 表达式作为参数 传递时，情况也依然如此。操作时可以重载一个方法，
         * 分别接受 BinaryOperator 和该 接口的一个子类作为参数。调用这些方法时，Java 推导出的 Lambda 表达式的类型正
         * 是最具体的函数接口的类型。比如，例 4-7 在例 4-8 的两个方法中选择时，输出的是 IntegerBinaryOperator
         *
         * 总而言之，Lambda 表达式作为参数时，其类型由它的目标类型推导得出，推导过程遵循 如下规则：
         * Ŗ 如果只有一个可能的目标类型，由相应函数接口里的参数类型推导得出；
         * Ŗ 如果有多个可能的目标类型，由最具体的类型推导得出；
         * Ŗ 如果有多个可能的目标类型且最具体的类型不明确，则需人为指定类型
         */
        Study4 note = new Study4();
        note.overloadedMethod((x, y) -> x + y);
        // IntegerBinaryOperator

        // 4.4 @FunctionalInterface
        /**
         * 该注释会强制 javac 检查一个接口是否符合函数接口的标准。如果该注释添加给一个枚举 类型、类或另一个注释，
         * 或者接口包含不止一个抽象方法，javac 就会报错。重构代码时， 使用它能很容易发现问题。
         */

    }

    // 重载解析
    private interface IntegerBiFunction extends BinaryOperator<Integer> { }
    private void overloadedMethod(BinaryOperator<Integer> Lambda) { System.out.print("BinaryOperator"); }
    private void overloadedMethod(IntegerBiFunction Lambda) { System.out.print("IntegerBinaryOperator"); }
}
