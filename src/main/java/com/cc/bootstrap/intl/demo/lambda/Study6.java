package com.cc.bootstrap.intl.demo.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 第六章 数据并行化
 * 本章主要内容并不在于如何更改代码，而是讲述为什么需要并 行化和什么时候会带来性能的提升。
 * 要提醒大家的是，本章并不是关于 Java 性能的泛泛之 谈，我们只关注 Java 8 轻松提升性能的技术。
 */
public class Study6 {
    private static Logger LOGGER = LoggerFactory.getLogger(Study6.class);

    public static void main(String[] args) {
        Study6 study6 = new Study6();
        // 6.1 并行和并发
        /**
         * 并发是两个任务共享时间段，并行则是两个任务在同一时间发生，比如运行在多核 CPU 上。如果一个程序要运行两个任务，
         * 并且只有一个 CPU 给它们分配了不同的时间片，那 么这就是并发，而不是并行。
         *
         * 并行化是指为缩短任务执行时间，将一个任务分解成几部分，然后并行执行。这和顺序执 行的任务量是一样的，区别就像用更多的马来拉车，
         * 花费的时间自然减少了。实际上，和 顺序执行相比，并行化执行任务时，CPU 承载的工作量更大。 本章会讨论一种特殊形式的并行化：
         * 数据并行化。数据并行化是指将数据分成块，为每块 数据分配单独的处理单元。还是拿马拉车那个例子打比方，就像从车里取出一些货物，
         * 放 到另一辆车上，两辆马车都沿着同样的路径到达目的地。 当需要在大量数据上执行同样的操作时，数据并行化很管用。
         * 它将问题分解为可在多块数 据上求解的形式，然后对每块数据执行运算，最后将各数据块上得到的结果汇总，从而获 得最终答案。
         *
         * 人们经常拿任务并行化和数据并行化做比较，在任务并行化中，线程不同，工作各异。我 们最常遇到的 Java EE 应用容器便是任务并行化的例子之一，
         * 每个线程不光可以为不同用 户服务，还可以为同一个用户执行不同的任务，比如登录或往购物车添加商品。
         */

        // 6.2 为什么并行化如此重要
        /**
         * 过去我们可以指望 CPU 时钟频率会变得越来越快。1979 年，英特尔公司推出的 8086 处理 器的时钟频率为 5 MHz；
         * 到了 1993 年，奔腾芯片的速度达到了 60 MHz。在 21 世纪早期， CPU 的处理速度一直以这种方式增长。
         * 然而在过去十年中，主流的芯片厂商转向了多核处理器。
         *
         * 这种变化影响到了软件设计。我们不能再依赖提升 CPU 的时钟频率来提高现有代码的计 算能力，
         * 需要利用现代 CPU 的架构，而这唯一的办法就是编写并行化的代码。
         *
         * 以这样的方式思考性能问题，优化任何和计算相关的任务立即变成了如何有效利用现有硬 件的问题。
         */

        // 6.3 并行化流操作
        /**
         * 并 行 化 操 作 流 只 需 改 变 一 个 方 法 调 用。 如 果 已 经 有 一 个 Stream 对 象，
         * 调 用 它 的 parallel 方法就能让其拥有并行操作的能力。如果想从一个集合类创建一个流，
         * 调用 parallelStream 就能立即获得一个拥有并行能力的流。
         *
         * 例 6-1 串行化计算专辑曲目长度
         * public int serialArraySum() {
         *   return albums.stream()
         *   .flatMap(Album::getTracks)
         *   .mapToInt(Track::getLength)
         *   .sum();
         * }
         * 调用 parallelStream 方法即能并行处理，如例 6-2 所示，剩余代码都是一样的，并行化就 是这么简单！
         * 例 6-2 并行化计算专辑曲目长度
         * public int parallelArraySum() {
         *   return albums.parallelStream()
         *   .flatMap(Album::getTracks)
         *   .mapToInt(Track::getLength).sum();
         * }
         *
         * 我们先要问自己一个问题：并行化运行基于流的代码是否比串行化运行更快？这不是一 个简单的问题。
         * 回到前面的例子，哪种方式花的时间更多取决于串行或并行化运行时的 环境。
         * 以例 6-1 和例 6-2 中的代码为准，在一个四核电脑上，
         * 如果有 10 张专辑，串行化代码的速 度是并行化代码速度的 8 倍；
         * 如果将专辑数量增至 100 张，串行化和并行化速度相当；如 果将专辑数量增值 10 000 张，
         * 则并行化代码的速度是串行化代码速度的 2.5 倍。
         */

        // 6.4 模拟系统
        // 并行化流操作的用武之地是使用简单操作处理大量数据，比如模拟系统。

        // 6.5 限制
        /**
         * 之前调用 reduce 方法，初始值可以为任意值，为了让其在并行化时能工作正常，初值必须 为组合函数的恒等值。
         * 拿恒等值和其他值做 reduce 操作时，其他值保持不变。比如，使用 reduce 操作求和，组合函数为 (acc, element) -> acc + element，
         * 则其初值必须为 0，因 为任何数字加 0，值不变。 reduce 操作的另一个限制是组合操作必须符合结合律。这意味着只要序列的值不变，组合操作的顺序不重要。
         *
         * 要避免的是持有锁。流框架会在需要时，自己处理同步操作，因此程序员没有必要为自己 的数据结构加锁。如果你执意为流中要使用的数据结构加锁，
         * 比如操作的原始集合，那么 有可能是自找麻烦。 在前面我还解释过，使用 parallel 方法能轻易将流转换为并行流。如果读者在阅读本书的 同时，
         * 还查看了相应的 API，那么可能会发现还有一个叫 sequential 的方法。在要对流求 值时，不能同时处于两种模式，要么是并行的，要么是串行的。
         * 如果同时调用了 parallel 和 sequential 方法，最后调用的那个方法起效。
         */

        // 6.6 性能
        /**
         * 在前面我简要提及了影响并行流是否比串行流快的一些因素，现在让我们仔细看看它们。
         * 理解哪些能工作、哪些不能工作，能帮助在如何使用、什么时候使用并行流这一问题上做 出明智的决策。影响并行流性能的主要因素有 5 个，依次分析如下。
         * (1)数据大小 输入数据的大小会影响并行化处理对性能的提升。
         * 将问题分解之后并行化处理，再将结 果合并会带来额外的开销。因此只有数据足够大、每个数据处理管道花费的时间足够多 时，并行化处理才有意义。
         * (2)源数据结构 每个管道的操作都基于一些初始数据源，通常是集合。将不同的数据源分割相对容易，
         * 这里的开销影响了在管道中并行处理数据时到底能带来多少性能上的提升。
         * (3)装箱 处理基本类型比处理装箱类型要快。
         * (4)核的数量 极端情况下，只有一个核，因此完全没必要并行化。显然，拥有的核越多，获得潜在性 能提升的幅度就越大。
         * 在实践中，核的数量不单指你的机器上有多少核，更是指运行时 你的机器能使用多少核。这也就是说同时运行的其他进程，
         * 或者线程关联性（强制线程 在某些核或 CPU 上运行）会影响性能。
         * (5)单元处理开销 比如数据大小，这是一场并行执行花费时间和分解合并操作开销之间的战争。
         * 花在流中 每个元素身上的时间越长，并行操作带来的性能提升越明显。
         *
         * 初始的数据结构影响巨大。举一个极端的例子，对比对 10 000 个整数并行求和，使用 ArrayList 要比使用 LinkedList 快 10 倍。
         * 这不是说业务逻辑的性能情况也会如此，只是说明了数据结构 对于性能的影响之大。使用形如 LinkedList 这样难于分解的数据结构并行运行可能更慢。
         * 理想情况下，一旦流框架将问题分解成小块，就可以在每个线程里单独处理每一小块，线 程之间不再需要进一步通信。无奈现实不总遂人愿！
         * 在讨论流中单独操作每一块的种类时，可以分成两种不同的操作：无状态的和有状态的。
         * 无状态操作整个过程中不必维护状态，有状态操作则有维护状态所需的开销和限制。 如果能避开有状态，选用无状态操作，就能获得更好的并行性能。
         * 无状态操作包括 map、 filter 和 flatMap，有状态操作包括 sorted、distinct 和 limit。
         * 要对自己的代码进行性能测试。本节只给出了哪些性能特征需要调查，但什 么都比不上实际的测试和分析。
         */

        // 6.7 并行化数组操作
        /**
         * Java 8 还引入了一些针对数组的并行操作，脱离流框架也可以使用 Lambda 表达式。像流 框架上的操作一样，这些操作也都是针对数据的并行化操作。
         * 这些操作都在工具类 Arrays 中，该类还包括 Java 以前版本中提供的和数组相关的有用方 法，表 6-1 总结了新增的并行化操作。
         * 表6-1：数组上的并行化操作
         * 方法名            操　　作
         * parallelPrefix 任意给定一个函数，计算数组的和
         * parallelSetAll 使用 Lambda 表达式更新数组元素
         * parallelSort   并行化对数组元素排序
         */
        long time1 = System.currentTimeMillis();
        double[] initialArray_old = study6.imperativeInitilize(2);
        long time2 = System.currentTimeMillis();
        LOGGER.info("initialArray_old is initialized, initialArray_old: 【{}】, spend time: 【{}】 millis",
                initialArray_old, (time2 - time1));
        //使用 parallelSetAll 方法能轻松地并行化该过程。使用这些方法有一点要小心：它们改变了传入的数组，而 没有创建一个新的数组。
        long time3 = System.currentTimeMillis();
        double[] initialArray_parallel = study6.parallelInitialize(2);
        long time4 = System.currentTimeMillis();
        LOGGER.info("initialArray_parallel is initialized, initialArray_parallel: 【{}】, spend time: 【{}】 millis",
                initialArray_parallel, (time4 - time3));


        /**
         * parallelPrefix 操作擅长对时间序列数据做累加，它会更新一个数组，将每一个元素替换 为当前元素和其前驱元素的和，
         * 这里的“和”是一个宽泛的概念，它不必是加法，可以是 任意一个 BinaryOperator。
         * 使用该方法能计算的例子之一是一个简单的滑动平均数。
         */

    }

    //例 6-7 使用 for 循环初始化数组
    public static double[] imperativeInitilize(int size) {
        double[] values = new double[size];
        for(int i = 0; i < values.length;i++) {
            values[i] = i;
        }
        return values;
    }

    //例 6-8 使用并行化数组操作初始化数组
    public static double[] parallelInitialize(int size) {
        double[] values = new double[size];
        Arrays.parallelSetAll(values, i -> i);
        return values;
    }

    //例 6-9 计算简单滑动平均数
    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n - 1;
        return IntStream.range(start, sums.length).mapToDouble(i -> {
                double prefix = i == start ? 0 : sums[i - n];
                return (sums[i] - prefix) / n;
            }).toArray();
    }
}
