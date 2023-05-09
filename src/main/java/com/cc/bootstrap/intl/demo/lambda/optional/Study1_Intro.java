package com.cc.bootstrap.intl.demo.lambda.optional;

/**
 * @Description: 用Optional取代null
 * 本章内容
 *  null引用引发的问题，以及为什么要避免null引用
 *  从null到Optional：以null安全的方式重写你的域模型
 *  让Optional发光发热： 去除代码中对null的检查
 *  读取Optional中可能值的几种方法
 *  对可能缺失值的再思考
 * @author: ChenChen
 * @date: 2023/2/7 10:05
 */
public class Study1_Intro {
    public static void main(String[] args) {
        //1. null引用引发的问题
        //1.1 如何为缺失的值建模
//        下面这段代码存在怎样的问题呢？
//        public String getCarInsuranceName(Person person) {
//            return person.getCar().getInsurance().getName();
//        }
//        这段代码看起来相当正常，但是现实生活中很多人没有车。所以调用getCar方法的结果会
//        怎样呢？在实践中，一种比较常见的做法是返回一个null引用，表示该值的缺失，即用户没有
//        车。而接下来，对getInsurance的调用会返回null引用的insurance，这会导致运行时出现
//        一个NullPointerException，终止程序的运行。但这还不是全部。如果返回的person值为null
//        会怎样？如果getInsurance的返回值也是null，结果又会怎样？
        //(1) 采用防御式检查减少 NullPointerException

//        通常，你可以在需要的地方添加null的检查（过于激进的防御式检查甚至会在不太需要的地方添加检测代码），并且添加的方式
//        往往各有不同。
//        public String getCarInsuranceName(Person person) {
//            if (person != null) {
//                Car car = person.getCar();
//                if (car != null) {
//                    Insurance insurance = car.getInsurance();
//                    if (insurance != null) {
//                        return insurance.getName();
//                    }
//                }
//            }
//            return "Unknown";
//        }

        //null-安全的第二种尝试：过多的退出语句
//        public String getCarInsuranceName(Person person) {
//            if (person == null) {
//                return "Unknown";
//            }
//            Car car = person.getCar();
//            if (car == null) {
//                return "Unknown";
//            }
//            Insurance insurance = car.getInsurance();
//            if (insurance == null) {
//                return "Unknown";
//            }
//            return insurance.getName();
//        }
//        第二种尝试中，你试图避免深层递归的if语句块，采用了一种不同的策略：每次你遭遇null
//        变量，都返回一个字符串常量“Unknown”。然而，这种方案远非理想，现在这个方法有了四个
//        截然不同的退出点，使得代码的维护异常艰难。更糟的是，发生null时返回的默认值，即字符
//        串“Unknown”在三个不同的地方重复出现——出现拼写错误的概率不小！当然，你可能会说，
//        我们可以用把它们抽取到一个常量中的方式避免这种问题。
//        进一步而言，这种流程是极易出错的；如果你忘记检查了那个可能为null的属性会怎样？
//        通过这一章的学习，你会了解使用null来表示变量值的缺失是大错特错的。你需要更优雅的方
//        式来对缺失的变量值建模。



// 它是错误之源。
//        NullPointerException是目前Java程序开发中最典型的异常。
// 它会使你的代码膨胀。
//        它让你的代码充斥着深度嵌套的null检查，代码的可读性糟糕透顶。
// 它自身是毫无意义的。
//        null自身没有任何的语义，尤其是，它代表的是在静态类型语言中以一种错误的方式对
//        缺失变量值的建模。
// 它破坏了Java的哲学。
//        Java一直试图避免让程序员意识到指针的存在，唯一的例外是：null指针。
// 它在Java的类型系统上开了个口子。
//        null并不属于任何类型，这意味着它可以被赋值给任意引用类型的变量。这会导致问题，
//        原因是当这个变量被传递到系统中的另一个部分后，你将无法获知这个null变量最初的
//        赋值到底是什么类型。
//        为了解业界针对这个问题给出的解决方案，我们一起简单看看其他语言提供了哪些功能。

//        实际上Java 8从“optional值”的想法中吸取了灵感，引入了一个名为
//        java.util.Optional<T>的新的类。这一章里，我们会展示使用这种方式对可能缺失的值建模，
//        而不是直接将null赋值给变量所带来的好处。我们还会阐释从null到Optional的迁移，你需要
//        反思的是：如何在你的域模型中使用optional值。最后，我们会介绍新的Optional类提供的功
//        能，并附几个实际的例子，展示如何有效地使用这些特性。最终，你会学会如何设计更好的API——
//        用户只需要阅读方法签名就能知道它是否接受一个optional的值。
    }
}
