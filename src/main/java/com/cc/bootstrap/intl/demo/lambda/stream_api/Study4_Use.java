package com.cc.bootstrap.intl.demo.lambda.stream_api;

import com.cc.bootstrap.intl.demo.lambda.stream_api.vo.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description: 使用流
 *  筛选、切片和匹配
 *  查找、匹配和归约
 *  使用数值范围等数值流
 *  从多个源创建流
 *  无限流
 * @author: ChenChen
 * @date: 2023/1/28 14:54
 */
public class Study4_Use {
    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

    //1.筛选和切片
    //1.1 用谓词筛选 -------------------filter
//    Streams接口支持filter方法（你现在应该很熟悉了）。该操作会接受一个谓词（一个返回
//    boolean的函数）作为参数，并返回一个包括所有符合谓词的元素的流。
    //1.2 筛选各异的元素 -------------------distinct
//    流还支持一个叫作distinct的方法，它会返回一个元素各异（根据流所生成元素的
//    hashCode和equals方法实现）的流。
//    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
//    numbers.stream()
//            .filter(i -> i % 2 == 0)
//            .distinct()
//            .forEach(System.out::println);
    //1.3 截短流 -------------------limit(n)
//    流支持limit(n)方法，该方法会返回一个不超过给定长度的流。所需的长度作为参数传递
//    给limit。如果流是有序的，则最多会返回前n个元素。
    //1.4 跳过元素-------------------skip(n)
//    流还支持skip(n)方法，返回一个扔掉了前n个元素的流。如果流中元素不足n个，则返回一
//    个空流。请注意，limit(n)和skip(n)是互补的！

    //2.映射
    //2.1 对流中每一个元素应用函数
//    流支持map方法，它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映
//    射成一个新的元素（使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一
//    个新版本”而不是去“修改”）。例如，下面的代码把方法引用Dish::getName传给了map方法，
//    来提取流中菜肴的名称：
//    List<String> dishNames = menu.stream()
//            .map(Dish::getName)
//            .collect(toList());

    //2.2 流的扁平化 -------------------Arrays.stream() flatMap
    //(1) 尝试使用map和Arrays.stream()
//    首先，你需要一个字符流，而不是数组流。有一个叫作Arrays.stream()的方法可以接受
//    一个数组并产生一个流，例如：
//    String[] arrayOfWords = {"Goodbye", "World"};
//    Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
//    把它用在前面的那个流水线里，看看会发生什么：
//            words.stream()
//            .map(word -> word.split(""))
//            .map(Arrays::stream)
// .distinct()
// .collect(toList());
//    当前的解决方案仍然搞不定！这是因为，你现在得到的是一个流的列表（更准确地说是
//    Stream<String>）！的确，你先是把每个单词转换成一个字母数组，然后把每个数组变成了一
//    个独立的流。

    //(2) 使用flatMap ---- flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流。
//    你可以像下面这样使用flatMap来解决这个问题：
//    List<String> uniqueCharacters =
//            words.stream()
//                    .map(w -> w.split(""))
//                    .flatMap(Arrays::stream)
//                    .distinct()
//                    .collect(Collectors.toList());

    //3.查找和匹配
//    另一个常见的数据处理套路是看看数据集中的某些元素是否匹配一个给定的属性。Stream
//    API通过allMatch、anyMatch、noneMatch、findFirst和findAny方法提供了这样的工具。
    //3.1 检查谓词是否至少匹配一个元素 anyMatch
//    anyMatch方法可以回答“流中是否有一个元素能匹配给定的谓词”。比如，你可以用它来看
//    看菜单里面是否有素食可选择：
//            if(menu.stream().anyMatch(Dish::isVegetarian)){
//        System.out.println("The menu is (somewhat) vegetarian friendly!!");
//    }
//    anyMatch方法返回一个boolean，因此是一个终端操作。
    //3.2 检查谓词是否匹配所有元素 -------------------allMatch  noneMatch
//    allMatch方法的工作原理和anyMatch类似，但它会看看流中的元素是否都能匹配给定的谓
//    词。比如，你可以用它来看看菜品是否有利健康（即所有菜的热量都低于1000卡路里）：
//    boolean isHealthy = menu.stream()
//            .allMatch(d -> d.getCalories() < 1000);
//    noneMatch
//    和allMatch相对的是noneMatch。它可以确保流中没有任何元素与给定的谓词匹配。比如，
//    你可以用noneMatch重写前面的例子：
//    boolean isHealthy = menu.stream()
//            .noneMatch(d -> d.getCalories() >= 1000);
//    anyMatch、allMatch和noneMatch这三个操作都用到了我们所谓的短路，这就是大家熟悉
//    的Java中&&和||运算符短路在流中的版本。

    //3.3 查找元素-------------------findAny findFirst
    //(1)findAny
//    findAny方法将返回当前流中的任意元素。它可以与其他流操作结合使用。比如，你可能想
//    找到一道素食菜肴。你可以结合使用filter和findAny方法来实现这个查询：
//    Optional<Dish> dish =
//            menu.stream()
//                    .filter(Dish::isVegetarian)
//                    .findAny();
//    流水线将在后台进行优化使其只需走一遍，并在利用短路找到结果时立即结束。

    //查找第一个元素 findFirst
//    有些流有一个出现顺序（encounter order）来指定流中项目出现的逻辑顺序（比如由List或
//    排序好的数据列生成的流）。对于这种流，你可能想要找到第一个元素。

    //4.归约
    //到目前为止，你见到过的终端操作都是返回一个boolean（allMatch之类的）、void
    //（forEach）或Optional对象（findAny等）。你也见过了使用collect来将流中的所有元素组合成一个List。
    //在本节中，你将看到如何把一个流中的元素组合起来，使用reduce操作来表达更复杂的查
    //询，比如“计算菜单中的总卡路里”或“菜单中卡路里最高的菜是哪一个”。此类查询需要将流
    //中所有元素反复结合起来，得到一个值，比如一个Integer。这样的查询可以被归类为归约操作
    //（将流归约成一个值）。用函数式编程语言的术语来说，这称为折叠（fold），因为你可以将这个操
    //作看成把一张长长的纸（你的流）反复折叠成一个小方块，而这就是折叠操作的结果。
    //4.1 元素求和 -------------------reduce
//    要是还能把所有的数字相乘，而不必去复制粘贴这段代码，岂不是很好？这正是reduce操
//    作的用武之地，它对这种重复应用的模式做了抽象。你可以像下面这样对流中所有的元素求和：
//    int sum = numbers.stream().reduce(0, (a, b) -> a + b);
//    reduce接受两个参数：
//             一个初始值，这里是0；
//             一个BinaryOperator<T>来将两个元素结合起来产生一个新值，这里我们用的是
//    lambda (a, b) -> a + b。
//    你也很容易把所有的元素相乘，只需要将另一个Lambda：(a, b) -> a * b传递给reduce
//    操作就可以了：
//    int product = numbers.stream().reduce(1, (a, b) -> a * b);
//    无初始值
//    reduce还有一个重载的变体，它不接受初始值，但是会返回一个Optional对象：
//    Optional<Integer> sum = numbers.stream().reduce((a, b) -> (a + b));
//    为什么它返回一个Optional<Integer>呢？考虑流中没有任何元素的情况。reduce操作无
//    法返回其和，因为它没有初始值。这就是为什么结果被包裹在一个Optional对象里，以表明和
//    可能不存在。

    //4.2 最大值和最小值
    //你将会在5.6节看到，诸如sum和max等内置的方法可以让常见归约模式的代码再简洁一点儿。
    Optional<Integer> max = numbers.stream().reduce(Integer::max);
    Optional<Integer> min = numbers.stream().reduce(Integer::min);

//    怎样用map和reduce方法数一数流中有多少个菜呢？
//    答案：要解决这个问题，你可以把流中每个元素都映射成数字1，然后用reduce求和。这
//    相当于按顺序数流中的元素个数。
    int count = Dish.menu.stream()
            .map(d -> 1)
            .reduce(0, (a, b) -> a + b);

//    流操作：无状态和有状态
//    诸如map或filter等操作会从输入流中获取每一个元素，并在输出流中得到0或1个结果。
//    这些操作一般都是无状态的：它们没有内部状态（假设用户提供的Lambda或方法引用没有内
//    部可变状态）。
//    但诸如reduce、sum、max等操作需要内部状态来累积结果。在上面的情况下，内部状态
//    很小。在我们的例子里就是一个int或double。不管流中有多少元素要处理，内部状态都是
//    有界的。
//    相反，诸如sort或distinct等操作一开始都和filter和map差不多——都是接受一个
//    流，再生成一个流（中间操作），但有一个关键的区别。从流中排序和删除重复项时都需要知
//    道先前的历史。例如，排序要求所有元素都放入缓冲区后才能给输出流加入一个项目，这一操
//    作的存储要求是无界的。要是流比较大或是无限的，就可能会有问题（把质数流倒序会做什么
//    呢？它应当返回最大的质数，但数学告诉我们它不存在）。我们把这些操作叫作有状态操作。

    //5.数值流
//    我们在前面看到了可以使用reduce方法计算流中元素的总和。例如，你可以像下面这样计
//    算菜单的热量：
//    int calories = menu.stream()
//            .map(Dish::getCalories)
//            .reduce(0, Integer::sum);
//    这段代码的问题是，它有一个暗含的装箱成本。每个Integer都必须拆箱成一个原始类型，
//    再进行求和。
//    要是可以直接像下面这样调用sum方法，岂不是更好？
//    int calories = menu.stream()
//            .map(Dish::getCalories)
//            .sum();
//    但这是不可能的。问题在于map方法会生成一个Stream<T>。虽然流中的元素是Integer类
//    型，但Streams接口没有定义sum方法。为什么没有呢？比方说，你只有一个像menu那样的
//    Stream<Dish>，把各种菜加起来是没有任何意义的。但不要担心，Stream API还提供了原始类
//    型流特化，专门支持处理数值流的方法。
    //5.1 原始类型流特化 -------------------IntStream DoubleStream和 LongStream
//    Java 8引入了三个原始类型特化流接口来解决这个问题：IntStream、DoubleStream和
//    LongStream，分别将流中的元素特化为int、long和double，从而避免了暗含的装箱成本。每
//    个接口都带来了进行常用数值归约的新方法，比如对数值流求和的sum，找到最大元素的max。
//    此外还有在必要时再把它们转换回对象流的方法。要记住的是，这些特化的原因并不在于流的复
//    杂性，而是装箱造成的复杂性——即类似int和Integer之间的效率差异。
    //(1)映射到数值流 -------------------mapToInt、mapToDouble和mapToLong
//    将流转换为特化版本的常用方法是mapToInt、mapToDouble和mapToLong。这些方法和前
//    面说的map方法的工作方式一样，只是它们返回的是一个特化流，而不是Stream<T>。例如，你
//    可以像下面这样用mapToInt对menu中的卡路里求和：
//    int calories = menu.stream()
//            .mapToInt(Dish::getCalories)
//            .sum();
    //(2)转换回对象流 -------------------boxed
//    同样，一旦有了数值流，你可能会想把它转换回非特化流。例如，IntStream上的操作只能
//    产生原始整数： IntStream 的 map 操作接受的 Lambda 必须接受 int 并返回 int （一个
//    IntUnaryOperator）。但是你可能想要生成另一类值，比如Dish。为此，你需要访问Stream
//    接口中定义的那些更广义的操作。要把原始流转换成一般流（每个int都会装箱成一个
//    Integer），可以使用boxed方法
    IntStream intStream = Dish.menu.stream().mapToInt(Dish::getCalories);
    Stream<Integer> stream = intStream.boxed();
    //(3) 默认值OptionalInt -------------------OptionalInt、OptionalDouble和OptionalLong。
//    Optional可以用
//    Integer、String等参考类型来参数化。对于三种原始流特化，也分别有一个Optional原始类
//    型特化版本：OptionalInt、OptionalDouble和OptionalLong。
//    例如，要找到IntStream中的最大元素，可以调用max方法，它会返回一个OptionalInt：
//    OptionalInt maxCalories = menu.stream()
//            .mapToInt(Dish::getCalories)
//            .max();
//    现在，如果没有最大值的话，你就可以显式处理OptionalInt去定义一个默认值了：
//    int max = maxCalories.orElse(1);

    //5.2 数值范围 -------------------range和rangeClosed
//    和数字打交道时，有一个常用的东西就是数值范围。比如，假设你想要生成1和100之间的所
//    有数字。Java 8引入了两个可以用于IntStream和LongStream的静态方法，帮助生成这种范围：
//    range和rangeClosed。这两个方法都是第一个参数接受起始值，第二个参数接受结束值。但
//    range是不包含结束值的，而rangeClosed则包含结束值。
//    IntStream evenNumbers = IntStream.rangeClosed(1, 100)//如果用range那么下面结果会是49，因为不包含结束值。
//            .filter(n -> n % 2 == 0);
//      System.out.println(evenNumbers.count());//50
//    这里我们用了rangeClosed方法来生成1到100之间的所有数字。它会产生一个流，然后你
//    可以链接filter方法，只选出偶数。到目前为止还没有进行任何计算。最后，你对生成的流调
//    用count。因为count是一个终端操作，所以它会处理流，并返回结果50，这正是1到100（包括
//    两端）中所有偶数的个数。请注意，比较一下，如果改用IntStream.range(1, 100)，则结果
//    将会是49个偶数，因为range是不包含结束值的。


    public static void main(String[] args) {
        int max = Integer.max(1,3);
    }

































}
