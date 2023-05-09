package com.cc.bootstrap.intl.demo.lambda.optional;

/**
 * @Description: 应用 Optional 的几种模式
 * 你已经知道了如何使用Optional类型来声明你的域模型，也
 * 了解了这种方式与直接使用null引用表示变量值的缺失的优劣。但是，我们该如何使用呢？用
 * 这种方式能做什么，或者怎样使用Optional封装的值呢？
 *
 * @author: ChenChen
 * @date: 2023/2/7 10:37
 */
public class Study3_Use {
    public static void main(String[] args) {
        //1.创建 Optional 对象
        //1.1 声明一个空的Optional
//        Optional<Car> optCar = Optional.empty();
        //1.2 依据一个非空值创建Optional
//        Optional<Car> optCar = Optional.of(car);
//        如果car是一个null，这段代码会立即抛出一个NullPointerException，而不是等到你
//        试图访问car的属性值时才返回一个错误。
        //1.3 可接受null的Optional
        //最后，使用静态工厂方法Optional.ofNullable，你可以创建一个允许null值的Optional对象：
        //Optional<Car> optCar = Optional.ofNullable(car);
        //如果car是null，那么得到的Optional对象就是个空对象。

//        Optional
//        提供了一个get方法，它能非常精准地完成这项工作，我们在后面会详细介绍这部分内容。不过
//        get方法在遭遇到空的Optional对象时也会抛出异常，所以不按照约定的方式使用它，又会让
//        我们再度陷入由null引起的代码维护的梦魇。因此，我们首先从无需显式检查的Optional值的
//        使用入手，这些方法与Stream中的某些操作极其相似。

        //2.使用 map 从 Optional 对象中提取和转换值
//        从对象中提取信息是一种比较常见的模式。比如，你可能想要从insurance公司对象中提取
//        公司的名称。提取名称之前，你需要检查insurance对象是否为null，代码如下所示：
//        String name = null;
//        if(insurance != null){
//            name = insurance.getName();
//        }
//        为了支持这种模式，Optional提供了一个map方法。它的工作方式如下（这里，我们继续
//        借用了代码清单10-4的模式）：
//        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
//        Optional<String> name = optInsurance.map(Insurance::getName);

        //3.使用 flatMap 链接 Optional 对象
//        flatMap方法接受一个函数作为参数，这个函数的返回值是另一个流。
//        这个方法会应用到流中的每一个元素，最终形成一个新的流的流。但是flagMap会用流的内容替
//        换每个新生成的流。换句话说，由方法生成的各个流会被合并或者扁平化为一个单一的流。
        //(1) 使用Optional获取car的保险公司名称
        //(2) 使用Optional解引用串接的Person/Car/Insurance对象
//        public String getCarInsuranceName(Optional<Person> person) {
//            return person.flatMap(Person::getCar)
//                    .flatMap(Car::getInsurance)
//                    .map(Insurance::getName)
//                    .orElse("Unknown");
//        }
//        我们可以看到，处理潜在可能缺失的值时，
//        使用Optional具有明显的优势。这一次，你可以用非常容易却又普适的方法实现之前你期望的
//        效果——不再需要使用那么多的条件分支，也不会增加代码的复杂性。

//        从具体的代码实现来看，首先我们注意到你修改了代码清单10-2和代码清单10-3中的
//        getCarInsuranceName方法的签名，因为我们很明确地知道存在这样的用例，即一个不存在的
//        Person被传递给了方法，比如，Person是使用某个标识符从数据库中查询出来的，你想要对数
//        据库中不存在指定标识符对应的用户数据的情况进行建模。你可以将方法的参数类型由Person
//        改为Optional<Person>，对这种特殊情况进行建模。
//        我们再一次看到这种方式的优点，它通过类型系统让你的域模型中隐藏的知识显式地体现在
//        你的代码中，换句话说，你永远都不应该忘记语言的首要功能就是沟通，即使对程序设计语言而
//        言也没有什么不同。声明方法接受一个Optional参数，或者将结果作为Optional类型返回，让
//        你的同事或者未来你方法的使用者，很清楚地知道它可以接受空值，或者它可能返回一个空值。



//        在域模型中使用Optional，以及为什么它们无法序列化
//        在代码清单10-4中，我们展示了如何在你的域模型中使用Optional，将允许缺失或者暂
//        无定义的变量值用特殊的形式标记出来。然而，Optional类设计者的初衷并非如此，他们构
//        思时怀揣的是另一个用例。这一点，Java语言的架构师Brian Goetz曾经非常明确地陈述过，
//        Optional的设计初衷仅仅是要支持能返回Optional对象的语法。
//        由于Optional类设计时就没特别考虑将其作为类的字段使用，所以它也并未实现
//        Serializable接口。由于这个原因，如果你的应用使用了某些要求序列化的库或者框架，在
//        域模型中使用Optional，有可能引发应用程序故障。然而，我们相信，通过前面的介绍，你
//        已经看到用Optional声明域模型中的某些类型是个不错的主意，尤其是你需要遍历有可能全
//        部或部分为空，或者可能不存在的对象时。如果你一定要实现序列化的域模型，作为替代方案，
//        我们建议你像下面这个例子那样，提供一个能访问声明为Optional、变量值可能缺失的接口，
//        代码清单如下：
//        public class Person {
//            private Car car;
//            public Optional<Car> getCarAsOptional() {
//                return Optional.ofNullable(car);
//            }
//        }

        //4.默认行为及解引用 Optional 对象
//        我们决定采用orElse方法读取这个变量的值，使用这种方式你还可以定义一个默认值，遭
//        遇空的Optional变量时，默认值会作为该方法的调用返回值。Optional类提供了多种方法读取
//        Optional实例中的变量值。
// get()是这些方法中最简单但又最不安全的方法。如果变量存在，它直接返回封装的变量
//        值，否则就抛出一个NoSuchElementException异常。所以，除非你非常确定Optional
//        变量一定包含值，否则使用这个方法是个相当糟糕的主意。此外，这种方式即便相对于
//        嵌套式的null检查，也并未体现出多大的改进。
// orElse(T other)是我们在代码清单10-5中使用的方法，正如之前提到的，它允许你在
//        Optional对象不包含值时提供一个默认值。
// orElseGet(Supplier<? extends T> other)是orElse方法的延迟调用版，Supplier
//        方法只有在Optional对象不含值时才执行调用。如果创建默认值是件耗时费力的工作，
//        你应该考虑采用这种方式（借此提升程序的性能），或者你需要非常确定某个方法仅在
//        Optional为空时才进行调用，也可以考虑该方式（这种情况有严格的限制条件）。
// orElseThrow(Supplier<? extends X> exceptionSupplier)和get方法非常类似，
//        它们遭遇Optional对象为空时都会抛出一个异常，但是使用orElseThrow你可以定制希
//        望抛出的异常类型。
// ifPresent(Consumer<? super T>)让你能在变量值存在时执行一个作为参数传入的
//        方法，否则就不进行任何操作。
//        Optional类和Stream接口的相似之处，远不止map和flatMap这两个方法。还有第三个方
//        法filter，它的行为在两种类型之间也极其相似，我们会在6节做进一步的介绍。

        //5.两个 Optional 对象的组合
//        现在，我们假设你有这样一个方法，它接受一个Person和一个Car对象，并以此为条件对外
//        部提供的服务进行查询，通过一些复杂的业务逻辑，试图找到满足该组合的最便宜的保险公司：
//        public Insurance findCheapestInsurance(Person person, Car car) {
//            // 不同的保险公司提供的查询服务
//            // 对比所有数据
//            return cheapestCompany;
//        }
//        我们还假设你想要该方法的一个null-安全的版本，它接受两个Optional对象作为参数，
//        返回值是一个Optional<Insurance>对象，如果传入的任何一个参数值为空，它的返回值亦为
//        空。Optional类还提供了一个isPresent方法，如果Optional对象包含值，该方法就返回true，
//        所以你的第一想法可能是通过下面这种方式实现该方法：
//        public Optional<Insurance> nullSafeFindCheapestInsurance(
//                Optional<Person> person, Optional<Car> car) {
//            if (person.isPresent() && car.isPresent()) {
//                return Optional.of(findCheapestInsurance(person.get(), car.get()));
//            } else {
//                return Optional.empty();
//            }
//        }
//        这个方法具有明显的优势，我们从它的签名就能非常清楚地知道无论是person还是car，它
//        的值都有可能为空，出现这种情况时，方法的返回值也不会包含任何值。不幸的是，该方法的具
//        体实现和你之前曾经实现的null检查太相似了：方法接受一个Person和一个Car对象作为参数，
//        而二者都有可能为null。利用Optional类提供的特性，有没有更好或更地道的方式来实现这个
//        方法呢? 花几分钟时间思考一下测验10.1，试试能不能找到更优雅的解决方案。

//        以不解包的方式组合两个Optional对象
//        结合本节中介绍的map和flatMap方法，用一行语句重新实现之前出现的nullSafeFindCheapestInsurance()方法。
//        答案：你可以像使用三元操作符那样，无需任何条件判断的结构，以一行语句实现该方法，
//        代码如下。
//        public Optional<Insurance> nullSafeFindCheapestInsurance(
//                Optional<Person> person, Optional<Car> car) {
//            return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
//        }
//        这段代码中，你对第一个Optional对象调用flatMap方法，如果它是个空值，传递给它
//        的Lambda表达式不会执行，这次调用会直接返回一个空的Optional对象。反之，如果person
//        对象存在，这次调用就会将其作为函数Function的输入，并按照与flatMap方法的约定返回
//        一个Optional<Insurance>对象。这个函数的函数体会对第二个Optional对象执行map操
//        作，如果第二个对象不包含car，函数Function就返回一个空的Optional对象，整个
//        nullSafeFindCheapestInsuranc方法的返回值也是一个空的Optional对象。最后，如果
//        person和car对象都存在，作为参数传递给map方法的Lambda表达式能够使用这两个值安全
//        地调用原始的findCheapestInsurance方法，完成期望的操作。

        //6.使用 filter 剔除特定的值
//        filter方法接受一个谓词作为参数。如果Optional对象的值存在，并且它符合谓词的条件，
//        filter方法就返回其值；否则它就返回一个空的Optional对象。

//        你经常需要调用某个对象的方法，查看它的某些属性。比如，你可能需要检查保险公司的名
//        称是否为“Cambridge-Insurance”。为了以一种安全的方式进行这些操作，你首先需要确定引用指
//        向的Insurance对象是否为null，之后再调用它的getName方法，如下所示：
//        Insurance insurance = ...;
//        if(insurance != null && "CambridgeInsurance".equals(insurance.getName())){
//            System.out.println("ok");
//        }
//        使用Optional对象的filter方法，这段代码可以重构如下：
//        Optional<Insurance> optInsurance = ...;
//        optInsurance.filter(insurance ->
//                        "CambridgeInsurance".equals(insurance.getName()))
//                .ifPresent(x -> System.out.println("ok"));
//        filter方法接受一个谓词作为参数。如果Optional对象的值存在，并且它符合谓词的条件，
//        filter方法就返回其值；否则它就返回一个空的Optional对象。如果你还记得我们可以将
//        Optional看成最多包含一个元素的Stream对象，这个方法的行为就非常清晰了。如果Optional
//        对象为空，它不做任何操作，反之，它就对Optional对象中包含的值施加谓词操作。如果该操
//        作的结果为true，它不做任何改变，直接返回该Optional对象，否则就将该值过滤掉，将
//        Optional的值置空。

//        表10-1对Optional类中的方法进行了分类和概括。
//        表10-1 Optional类的方法
//        方 法                    描 述
//        empty                   返回一个空的 Optional 实例
//        filter                  如果值存在并且满足提供的谓词，就返回包含该值的 Optional 对象；否则返回一个空的Optional 对象
//        flatMap                 如果值存在，就对该值执行提供的 mapping 函数调用，返回一个 Optional 类型的值，否则就返回一个空的 Optional 对象
//        get                     如果该值存在，将该值用 Optional 封装返回，否则抛出一个 NoSuchElementException 异常
//        ifPresent               如果值存在，就执行使用该值的方法调用，否则什么也不做
//        isPresent               如果值存在就返回 true，否则返回 false
//        map                     如果值存在，就对该值执行提供的 mapping 函数调用of
//                                将指定值用 Optional 封装之后返回，如果该值为 null，则抛出一个 NullPointerException异常
//        ofNullable              将指定值用 Optional 封装之后返回，如果该值为 null，则返回一个空的 Optional 对象
//        orElse                  如果有值则将其返回，否则返回一个默认值
//        orElseGet               如果有值则将其返回，否则返回一个由指定的 Supplier 接口生成的值
//        orElseThrow             如果有值则将其返回，否则抛出一个由指定的 Supplier 接口生成的异常
    }
}
