package com.cc.bootstrap.intl.demo.lambda.study3;

import com.cc.bootstrap.intl.demo.lambda.vo.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName com.cc.bootstrap.intl.demo.lambda.study3.Note
 * @Description 第三章学习笔记 —— 常用的流操作
 * 为了更好地理解 Stream API，掌握一些常用的 Stream 操作十分必要。除此处讲述的几种重 要操作之外，
 * 该 API 的 Javadoc 中还有更多信息。
 * @createTime 2021年12月08日 21:42:00
 */
public class Note {
    private static Logger LOGGER = LoggerFactory.getLogger(Note.class);

    public static void main(String[] args) {
        /**
         * 1.collect(toList())
         * collect(toList()) 方法由 Stream 里的值生成一个列表，是一个及早求值操作。
         */
        //Stream 的 of 方法使用一组初始值生成新的 Stream。事实上，collect 的用法不仅限于此，
        // 它是一个非常通用的强大结构，第 5 章将详细介绍它的其他用途。下面是使用 collect 方 法的一个例子：
        List<String> collected = Stream.of("a", "b", "c")// 生成一个Stream
                                .collect(Collectors.toList());//由Stream生成列表
        if(Arrays.asList("a", "b", "c").equals(collected)) {
            LOGGER.info("true");
        }

        /**
         * map
         * 如果有一个函数可以将一种类型的值转换成另外一种类型，map 操作就可以 使用该函数，将一个流中的值转换成一个新的流。
         */
        //以前写法-for循环将字符串转换为大写
        List<String> collected1 = new ArrayList<>();
        for (String string : Arrays.asList("a", "b", "hello")) {
            String uppercaseString = string.toUpperCase();
            collected1.add(uppercaseString);
        }
        if(Arrays.asList("A", "B", "HELLO").equals(collected1)) {
            LOGGER.info("true");
        }
        //使用map操作将字符串转换为大写形式
        List<String> collected2 = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());
        if(Arrays.asList("A", "B", "HELLO").equals(collected2)) {
            LOGGER.info("true");
        }

        /**
         * filter
         *的核心思想是保留 Stream 中的一些元素，而过滤掉其他的。
         * 假设要找出一组字符串 中以数字开头的字符串，比如字符串 "1abc" 和 "abc"，其中 "1abc" 就是符合条件的字符串。
         */
        //以前使用一个 for 循环，内部用 if 条件语句判断字符串的第一个字符来解决这个问题
        List<String> beginningWithNumbers = new ArrayList<>();
        for(String value : Arrays.asList("a", "1abc", "abc1")) {
            if (Character.isDigit(value.charAt(0))) {
                beginningWithNumbers.add(value);
            }
        }
        //现在使用函数式风格 filter模式 保留 Stream 中的一些元素，而过滤掉其他的。
        List<String> beginningWithNumbers1 = Stream.of("a", "1abc", "abc1")
                .filter(value -> Character.isDigit(value.charAt(0)))
                .collect(Collectors.toList());

        /**
         * flatMap
         * 前面已介绍过 map 操作，它可用一个新的值代替 Stream 中的值。但有时，用户希望让 map 操作有点变化，
         * 生成一个新的 Stream 对象取而代之。用户通常不希望结果是一连串的流， 此时 flatMap 最能派上用场。
         */
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        LOGGER.info(together.toString());

        /**
         * max和min
         * 求最大值和最小值
         */
        //使用 Stream 查找最短曲目
        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(track -> track.getName().length()))//最短曲目比较的是曲目长度
                .get();
        LOGGER.info("shortestTrack = {}", shortestTrack);

        //其实 是 分为 以下 两步：
        Optional<Track> optional = tracks.stream()
                .min(Comparator.comparing(track -> track.getName().length()));//min返回的是Optional<T>
        Track shortestTrack2 = optional.get();

        // reduce模式
        /**
         * 首先赋给 accumulator 一个初始值：initialValue，然后在循环体中，通过调用 combine 函 数，
         * 拿 accumulator 和集合中的每一个元素做运算，再将运算结果赋给 accumulator，最后 accumulator 的值就是想要的结果。
         * 这个模式中的两个可变项是 initialValue 初始值和 combine 函数。
         * 在例 3-14 中，我们选列 表中的第一个元素为初始值，但也不必需如此。
         * 为了找出最短曲目，combine 函数返回当 前元素和 accumulator 中较短的那个。
         */
        //以下是reduce模式 传统伪代码
//        Object accumula
//        tor = initialValue;
//        for(Object element : collection) {
//            accumulator = combine(accumulator, element);
//        }
        //接下来看一下 Stream API 中的 reduce 操作是怎么工作的。
        /**
         * reduce 操作可以实现从一组值中生成一个值。在上述例子中用到的 count、min 和 max 方 法，因为常用而被纳入标准库中。
         * 事实上，这些方法都是 reduce 操作。
         * 例如：何通过 reduce 操作对 Stream 中的数字求和。
         */
        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);//acc为累计求和值， element为遍历的每个元素
        LOGGER.info("reduce method, count = {}", count);






















    }
}
