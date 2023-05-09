package com.cc.bootstrap.intl.demo.lambda.efficient;

/**
 * @Description: 重构
 * 这里我们会介绍三种简单的重构，利用Lambda表达式、方法引用以及Stream改善程序代码的可读性和灵活性：
 *  重构代码，用Lambda表达式取代匿名类
 *  用方法引用重构Lambda表达式
 *  用Stream API重构命令式的数据处理
 * @author: ChenChen
 * @date: 2023/2/3 16:24
 */
public class Study1_Reconfiguration {
    public static void main(String[] args) {
        //1.从匿名类到 Lambda 表达式的转换
        Runnable r1 = new Runnable(){
            public void run(){
                System.out.println("Hello");
            }
        };
        Runnable r2 = () -> System.out.println("Hello");

//        首先，匿名类和Lambda表达式中的this和super的含义是不同的。在匿名类中，this代表的是类自身，但
//        是在Lambda中，它代表的是包含类。其次，匿名类可以屏蔽包含类的变量，而Lambda表达式不能（它们会导致编译错误）
//        在涉及重载的上下文里，将匿名类转换为Lambda表达式可能导致最终的代码更加晦涩。
//        interface Task{
//            public void execute();
//        }
//        public static void doSomething(Runnable r){ r.run(); }
//        public static void doSomething(Task a){ a.execute(); }
//        现在，你再传递一个匿名类实现的Task，不会碰到任何问题：
//        doSomething(new Task() {
//            public void execute() {
//                System.out.println("Danger danger!!");
//            }
//        });
//        但是将这种匿名类转换为Lambda表达式时，就导致了一种晦涩的方法调用，因为Runnable
//        和Task都是合法的目标类型：
//        doSomething(() -> System.out.println("Danger danger!!"));
//        你可以对Task尝试使用显式的类型转换来解决这种模棱两可的情况：
//        doSomething((Task)() -> System.out.println("Danger danger!!"));

        //2.从 Lambda 表达式到方法引用的转换
//        下面这段代码，它的功能是按照食物的热量级别对菜肴进行分类：
//        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
//                menu.stream()
//                        .collect(
//                                groupingBy(dish -> {
//                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
//                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//                                    else return CaloricLevel.FAT;
//                                }));
//        你可以将Lambda表达式的内容抽取到一个单独的方法中，将其作为参数传递给groupingBy
//        方法。变换之后，代码变得更加简洁，程序的意图也更加清晰了：
//        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
//                menu.stream().collect(groupingBy(Dish::getCaloricLevel));
//        为了实现这个方案，你还需要在Dish类中添加getCaloricLevel方法：
//        public class Dish{
// …
//            public CaloricLevel getCaloricLevel(){
//                if (this.getCalories() <= 400) return CaloricLevel.DIET;
//                else if (this.getCalories() <= 700) return CaloricLevel.NORMAL;
//                else return CaloricLevel.FAT;
//            }
//        }
        //尽量考虑使用静态辅助方法，比如comparing、maxBy。
//        inventory.sort(
//                (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
//        inventory.sort(comparing(Apple::getWeight));

        //很多通用的归约操作，比如sum、maximum，都有内建的辅助方法可以和方法引用结合使用。
//        与其编写：
//        int totalCalories =
//                menu.stream().map(Dish::getCalories)
//                        .reduce(0, (c1, c2) -> c1 + c2);
//        不如尝试使用内置的集合类，它能更清晰地表达问题陈述是什么。下面的代码中，我们使用了集
//        合类summingInt（方法的名词很直观地解释了它的功能）：
//        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));

        //3.从命令式的数据处理切换到 Stream
//        建议你将所有使用迭代器这种数据处理模式处理集合的代码都转换成Stream API的方
//        式。为什么呢？Stream API能更清晰地表达数据处理管道的意图。除此之外，通过短路和延迟载
//        入以及利用第7章介绍的现代计算机的多核架构，我们可以对Stream进行优化。
//        比如，下面的命令式代码使用了两种模式：筛选和抽取，这两种模式被混在了一起，这样的
//        代码结构迫使程序员必须彻底搞清楚程序的每个细节才能理解代码的功能。此外，实现需要并行
//        运行的程序所面对的困难也多得多（具体细节可以参考7.2节的分支/合并框架）：
//        List<String> dishNames = new ArrayList<>();
//        for(Dish dish: menu){
//            if(dish.getCalories() > 300){
//                dishNames.add(dish.getName());
//            }
//        }
//        替代方案使用Stream API，采用这种方式编写的代码读起来更像是问题陈述，并行化也非常
//        容易：
//        menu.parallelStream()
//                .filter(d -> d.getCalories() > 300)
//                .map(Dish::getName)
//                .collect(toList());
//        不幸的是，将命令式的代码结构转换为Stream API的形式是个困难的任务，因为你需要考虑
//        控制流语句，比如break、continue、return，并选择使用恰当的流操作。好消息是已经有一
//        些工具可以帮助我们完成这个任务①。//请参考http://refactoring.info/tools/LambdaFicator/。

        //4.增加代码的灵活性-------------------------------------------
        //4.1 采用函数接口-------------------------------------------有条件的延迟执行,环绕执行
//        首先，你必须意识到，没有函数接口，你就无法使用Lambda表达式。因此，你需要在代码
//        中引入函数接口。听起来很合理，但是在什么情况下使用它们呢？这里我们介绍两种通用的模式，
//        你可以依照这两种模式重构代码，利用Lambda表达式带来的灵活性，它们分别是：有条件的延
//        迟执行和环绕执行。
        //(1) 有条件的延迟执行
//        if (logger.isLoggable(Log.FINER)){
//            logger.finer("Problem: " + generateDiagnostic());
//        }
//        优化为：
//        更好的方案是使用log方法，该方法在输出日志消息之前，会在内部检查日志对象是否已经设置为恰当的日志等级：
//        logger.log(Level.FINER, "Problem: " + generateDiagnostic());
//        这种方式更好的原因是你不再需要在代码中插入那些条件判断，与此同时日志器的状态也不
//        再被暴露出去。不过，这段代码依旧存在一个问题。日志消息的输出与否每次都需要判断，即使你已经传递了参数，不开启日志。
//        这就是Lambda表达式可以施展拳脚的地方。你需要做的仅仅是延迟消息构造，如此一来，
//        日志就只会在某些特定的情况下才开启（以此为例，当日志器的级别设置为FINER时）。显然，
//        Java 8的API设计者们已经意识到这个问题，并由此引入了一个对log方法的重载版本，这个版本
//        的log方法接受一个Supplier作为参数。这个替代版本的log方法的函数签名如下：
//        public void log(Level level, Supplier<String> msgSupplier)
//        你可以通过下面的方式对它进行调用：
//        logger.log(Level.FINER, () -> "Problem: " + generateDiagnostic());
//        如果日志器的级别设置恰当，log方法会在内部执行作为参数传递进来的Lambda表达式。这
//        里介绍的Log方法的内部实现如下：
//        public void log(Level level, Supplier<String> msgSupplier){
//            if(logger.isLoggable(level)){
//                log(level, msgSupplier.get());
//            }
//        }
//        如果你发现你需要频繁地从客户端代码去查询一个对象
//        的状态（比如前文例子中的日志器的状态），只是为了传递参数、调用该对象的一个方法（比如
//        输出一条日志），那么可以考虑实现一个新的方法，以Lambda或者方法表达式作为参数，新方法
//        在检查完该对象的状态之后才调用原来的方法。你的代码会因此而变得更易读（结构更清晰），
//        封装性更好（对象的状态也不会暴露给客户端代码了）。

        //(2)环绕执行
//        如果你发现虽然你的业务
//        代码千差万别，但是它们拥有同样的准备和清理阶段，这时，你完全可以将这部分代码用Lambda
//        实现。这种方式的好处是可以重用准备和清理阶段的逻辑，减少重复冗余的代码。
        //典型地：文件读写
//        String oneLine =
//                processFile((BufferedReader b) -> b.readLine());
//        String twoLines =
//                processFile((BufferedReader b) -> b.readLine() + b.readLine());
//        public static String processFile(BufferedReaderProcessor p) throws
//        IOException {
//            try(BufferedReader br = new BufferedReader(new FileReader("java8inaction/ chap8/data.txt"))){
//                     return p.process(br);
//                }
//            }
//        public interface BufferedReaderProcessor{
//            String process(BufferedReader b) throws IOException;
//        }
//        这一优化是凭借函数式接口BufferedReaderProcessor达成的，通过这个接口，你可以传
//        递各种Lamba表达式对BufferedReader对象进行处理。

        //5.了解Lambada表达式如何避免常规面向对象设计中的僵化的模板代码。-----使用 Lambda 重构面向对象的设计模式
//        这一节中，我们会针对五个设计模式展开讨论，它们分别是：
//         策略模式
//         模板方法
//         观察者模式
//         责任链模式
//         工厂模式
//        我们会展示Lambda表达式是如何另辟蹊径解决设计模式原来试图解决的问题的。
        //5.1 策略模式
//        策略模式包含三部分内容，如图8-1所示。
// 一个代表某个算法的接口（它是策略模式的接口）。
// 一个或多个该接口的具体实现，它们代表了算法的多种实现（比如，实体类ConcreteStrategyA或者ConcreteStrategyB）。
// 一个或多个使用策略对象的客户。
//        Lambda表达式避免了采用策略设计模式时僵化的模板代码。如果你仔细分
//        析一下个中缘由，可能会发现，Lambda表达式实际已经对部分代码（或策略）进行了封装，而
//        这就是创建策略设计模式的初衷。因此，我们强烈建议对类似的问题，你应该尽量使用Lambda
//        表达式来解决。

        //5.2 模板方法
//        模板方法模式在你“希望使用这个算法，但是需要对其中的某些行进行改进，才能达到希望的效果”
//        时是非常有用的。

        //5.3 观察者模式
//        观察者模式是一种比较常见的方案，某些事件发生时（比如状态转变），如果一个对象（通
//        常我们称之为主题）需要自动地通知其他多个对象（称为观察者），就会采用该方案。
//        它们都只是对同一
//        段代码封装执行。Lambda表达式的设计初衷就是要消除这样的僵化代码。使用Lambda表达式后，
//        你无需显式地实例化三个观察者对象，直接传递Lambda表达式表示需要执行的行为即可

//        那么，是否我们随时随地都可以使用Lambda表达式呢？答案是否定的！我们前文介绍的例
//        子中，Lambda适配得很好，那是因为需要执行的动作都很简单，因此才能很方便地消除僵化代
//        码。但是，观察者的逻辑有可能十分复杂，它们可能还持有状态，抑或定义了多个方法，诸如此
//        类。在这些情形下，你还是应该继续使用类的方式。

        //5.4 责任链模式
//        责任链模式是一种创建处理对象序列（比如操作序列）的通用方案。一个处理对象可能需要
//        在完成一些工作之后，将结果传递给另一个对象，这个对象接着做一些工作，再转交给下一个处
//        理对象，以此类推。

        //5.5 工厂模式
//        使用工厂模式，你无需向客户暴露实例化的逻辑就能完成对象的创建。








    }



}
