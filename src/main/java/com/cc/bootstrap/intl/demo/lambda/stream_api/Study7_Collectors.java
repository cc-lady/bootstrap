package com.cc.bootstrap.intl.demo.lambda.stream_api;

import com.cc.bootstrap.intl.demo.lambda.stream_api.vo.Dish;

import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

/**
 * @Description: 收集器
 * @author: ChenChen
 * @date: 2023/1/29 14:14
 */
public class Study7_Collectors {
    //预定义收集器
//    预定义收集器的功能，也就是那些可以从Collectors
//    类提供的工厂方法（例如groupingBy）创建的收集器。它们主要提供了三大功能：
//     将流元素归约和汇总为一个值
//     元素分组
//     元素分区
    public static void main(String[] args) {
        //1.归约和汇总
//        在需要将流项目重组成集合时，一般会使用收集器（Stream方法collect
//        的参数）。再宽泛一点来说，但凡要把流中所有的项目合并成一个结果时就可以用。这个结果可以
//        是任何类型，可以复杂如代表一棵树的多级映射，或是简单如一个整数
        long howManyDishes = Dish.menu.stream().collect(counting());
//        这还可以写得更为直接：
        long howManyDishes1 = Dish.menu.stream().count();

        //1.1 查找流中的最大值和最小值 ---------------Collectors.maxBy和Collectors.minBy
        Comparator<Dish> dishCaloriesComparator =
                comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish =
                Dish.menu.stream()
                        .collect(maxBy(dishCaloriesComparator));
        //1.2 汇总
        //(1)Collectors.summingInt 汇总 ---------------
//        Collectors类专门为汇总提供了一个工厂方法：Collectors.summingInt。它可接受一
//        个把对象映射为求和所需int的函数，并返回一个收集器；该收集器在传递给普通的collect方
//        法后即执行我们需要的汇总操作。举个例子来说，你可以这样求出菜单列表的总热量：
        int totalCalories = Dish.menu.stream().collect(summingInt(Dish::getCalories));
//        这里的收集过程如图6-2所示。在遍历流时，会把每一道菜都映射为其热量，然后把这个数
//        字累加到一个累加器（这里的初始值0）。
//        Collectors.summingLong和Collectors.summingDouble方法的作用完全一样，可以用
//        于求和字段为long或double的情况。
        //(2)averagingInt 求平均数
//        还有Collectors.averagingInt，连同对应的averagingLong和
//        averagingDouble可以计算数值的平均数：
        double avgCalories =
                Dish.menu.stream().collect(averagingInt(Dish::getCalories));
        //(3)summarizing 计数  ---------------
        IntSummaryStatistics menuStatistics =
                Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);
//        这个收集器会把所有这些信息收集到一个叫作IntSummaryStatistics的类里，它提供了
//        方便的取值（getter）方法来访问结果。打印menuStatisticobject会得到以下输出：
//        IntSummaryStatistics{count=9, sum=4300, min=120,
//                average=477.777778, max=800}
//        同样，相应的summarizingLong和summarizingDouble工厂方法有相关的LongSummaryStatistics
//        和DoubleSummaryStatistics类型，适用于收集的属性是原始类型long或
//        double的情况。

        //1.3 连接字符串 ---------------joining工厂方法
//        joining工厂方法返回的收集器会把对流中每一个对象应用toString方法得到的所有字符串连接成一个字符串。
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);
        String shortMenu1 = Dish.menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu1);//pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon

        //1.4 广义的归约汇总 ---------------Collectors.reducing工厂方法
//        事实上，我们已经讨论的所有收集器，都是一个可以用reducing工厂方法定义的归约过程
//        的特殊情况而已。Collectors.reducing工厂方法是所有这些特殊情况的一般化。可以说，先
//        前讨论的案例仅仅是为了方便程序员而已。（但是，请记得方便程序员和可读性是头等大事！）
        //可以用reducing方法创建的收集器来计算你菜单的总热量，如下所示：
        int totalCalories3 = Dish.menu.stream().collect(reducing(
                0, Dish::getCalories, (i, j) -> i + j));
        //同样，你可以使用下面这样单参数形式的reducing来找到热量最高的菜，如下所示：
        Optional<Dish> mostCalorieDish2 =
                Dish.menu.stream().collect(reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        //2.分组------------------Collectors.groupingBy工厂方法
        //按菜类别分组
        Map<Dish.Type, List<Dish>> dishesByType =
                Dish.menu.stream().collect(groupingBy(Dish::getType));
        //把热量不到400卡路里的菜划分为“低热量”（diet），热量400到700卡路里的菜划为“普通”（normal），高于700卡路里的划为“高热量”（fat）
        Map<Dish.CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                    else return Dish.CaloricLevel.FAT;
                }));
        //2.1 多级分组 ------由双参数版本的Collectors.groupingBy工厂方法
//        要实现多级分组，我们可以使用一个由双参数版本的Collectors.groupingBy工厂方法创
//        建的收集器，它除了普通的分类函数之外，还可以接受collector类型的第二个参数。那么要进
//        行二级分组的话，我们可以把一个内层groupingBy传递给外层groupingBy，并定义一个为流
//                中项目分类的二级标准
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                                    else return Dish.CaloricLevel.FAT;
                                } )
                        )
                );
        System.out.println(dishesByTypeCaloricLevel);
        /*{MEAT={DIET=[chicken], NORMAL=[beef], FAT=[pork]},
            FISH={DIET=[prawns], NORMAL=[salmon]},
            OTHER={DIET=[rice, seasonal fruit], NORMAL=[french fries, pizza]}}*/

        //2.2 按子组收集数据
//        在上一节中，我们看到可以把第二个groupingBy收集器传递给外层收集器来实现多级分
//        组。但进一步说，传递给第一个groupingBy的第二个收集器可以是任何类型，而不一定是另一
//        个groupingBy。
        //例如，计数
        Map<Dish.Type, Long> typesCount = Dish.menu.stream().collect(
                groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);//{MEAT=3, FISH=2, OTHER=4}

        //查找菜单中热量最高的菜肴
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                Dish.menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);//{FISH=Optional[salmon], OTHER=Optional[pizza], MEAT=Optional[pork]}

        //(1)把收集器的结果转换为另一种类型 ---------Collectors.collectingAndThen工厂方法
        Map<Dish.Type, Dish> mostCaloricByType1 =
        Dish.menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricByType1);
//        这个工厂方法接受两个参数——要转换的收集器以及转换函数，并返回另一个收集器。这个
//        收集器相当于旧收集器的一个包装，collect操作的最后一步就是将返回值用转换函数做一个映
//        射。在这里，被包起来的收集器就是用maxBy建立的那个，而转换函数Optional::get则把返
//        回的Optional中的值提取出来。前面已经说过，这个操作放在这里是安全的，因为reducing
//        收集器永远都不会返回Optional.empty()。其结果是下面的Map：
//        {FISH=salmon, OTHER=pizza, MEAT=pork}

        //(2) 与groupingBy联合使用的其他收集器的例子
//        一般来说，通过groupingBy工厂方法的第二个参数传递的收集器将会对分到同一组中的所
//        有流元素执行进一步归约操作。
        //例如，你还重用求出所有菜肴热量总和的收集器，不过这次是对每一组Dish求和：
        Map<Dish.Type, Integer> totalCaloriesByType =
                Dish.menu.stream().collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);//{OTHER=1550, FISH=750, MEAT=1900}

//        然而常常和groupingBy联合使用的另一个收集器是mapping方法生成的。这个方法接受两
//        个参数：一个函数对流中的元素做变换，另一个则将变换的结果对象收集起来。其目的是在累加
//        之前对每个输入元素应用一个映射函数，这样就可以让接受特定类型元素的收集器适应不同类型
//        的对象。
        //比方说你想要知道，对于每种类型的Dish，菜单中都有哪些CaloricLevel
        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelsByType =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> { if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                                else return Dish.CaloricLevel.FAT; },
                                toSet() )));
        System.out.println(caloricLevelsByType);//{OTHER=[DIET, NORMAL], FISH=[DIET, NORMAL], MEAT=[DIET, FAT, NORMAL]}

        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelsByType1 =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> { if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                                else return Dish.CaloricLevel.FAT; },
                                toCollection(HashSet::new) )));
        System.out.println(caloricLevelsByType1);//{OTHER=[DIET, NORMAL], FISH=[DIET, NORMAL], MEAT=[DIET, FAT, NORMAL]}

        //3.分区------------------Collectors.partitioningBy工厂方法
//        分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函
//        数。分区函数返回一个布尔值，这意味着得到的分组Map的键类型是Boolean，于是它最多可以
//        分为两组——true是一组，false是一组。
        //素食和非素食分开
        Map<Boolean, List<Dish>> partitionedMenu =
                Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);//{false=[pork, beef, chicken, prawns, salmon], true=[french fries, rice, season fruit, pizza]}
        //那么通过Map中键为true的值，就可以找出所有的素食菜肴了：
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println(vegetarianDishes);//[french fries, rice, season fruit, pizza]

        //3.1 分区的优势
//        分区的好处在于保留了分区函数返回true或false的两套流元素列表。在上一个例子中，要
//        得到非素食Dish的List，你可以使用两个筛选操作来访问partitionedMenu这个Map中false
//        键的值：一个利用谓词，一个利用该谓词的非。

        //partitioningBy工厂方法有一个重载版本，可以像下面这样传递第二个收集器：
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                Dish.menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                groupingBy(Dish::getType)));
        System.out.println(vegetarianDishesByType);//{false={FISH=[prawns, salmon], MEAT=[pork, beef, chicken]}, true={OTHER=[french fries, rice, season fruit, pizza]}}

        //你可以重用前面的代码来找到素食和非素食中热量最高的菜：
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                Dish.menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)));
        System.out.println(mostCaloricPartitionedByVegetarian);//{false=pork, true=pizza}
    }
}
