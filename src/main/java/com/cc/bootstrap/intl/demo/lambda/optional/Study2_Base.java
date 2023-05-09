package com.cc.bootstrap.intl.demo.lambda.optional;

/**
 * @Description: Optional 类入门
 * @author: ChenChen
 * @date: 2023/2/7 10:33
 */
public class Study2_Base {
    public static void main(String[] args) {
//        Java 8中引入了一个新的类java.util.Optional<T>。这
//        是一个封装Optional值的类。举例来说，使用新的类意味着，如果你知道一个人可能有也可能
//        没有车，那么Person类内部的car变量就不应该声明为Car，遭遇某人没有车时把null引用赋值
//        给它，而是应该像图10-1那样直接将其声明为Optional<Car>类型。

//        变量存在时，Optional类只是对类简单封装。变量不存在时，缺失的值会被建模成一个“空”
//        的Optional对象，由方法Optional.empty()返回。Optional.empty()方法是一个静态工厂
//        方法，它返回Optional类的特定单一实例。

//        null引用和Optional.empty()
//        有什么本质的区别吗？从语义上，你可以把它们当作一回事儿，但是实际中它们之间的差别非常
//        大：如果你尝试解引用一个 null ，一定会触发 NullPointerException ，不过使用
//        Optional.empty()就完全没事儿，它是Optional类的一个有效对象，多种场景都能调用，非
//        常有用。关于这一点，接下来的部分会详细介绍。

//        使用Optional而不是null的一个非常重要而又实际的语义区别是，第一个例子中，我们
//        在声明变量时使用的是Optional<Car>类型，而不是Car类型，这句声明非常清楚地表明了这
//        里发生变量缺失是允许的。与此相反，使用Car这样的类型，可能将变量赋值为null，这意味
//        着你需要独立面对这些，你只能依赖你对业务模型的理解，判断一个null是否属于该变量的有
//        效范畴。
//        牢记上面这些原则，你现在可以使用Optional类对代码清单10-1中最初的代码进行重构
//        public class Person {
//            private Optional<Car> car;
//            public Optional<Car> getCar() { return car; }
//        }
//        public class Car {
//            private Optional<Insurance> insurance;
//            public Optional<Insurance> getInsurance() { return insurance; }
//        }
//        public class Insurance {
//            private String name;
//            public String getName() { return name; }
//        }
//        发现Optional是如何丰富你模型的语义了吧。代码中person引用的是Optional<Car>，
//        而car引用的是Optional<Insurance>，这种方式非常清晰地表达了你的模型中一个person
//        可能拥有也可能没有car的情形，同样，car可能进行了保险，也可能没有保险。

//        与此同时，我们看到insurance公司的名称被声明成String类型，而不是Optional-
//                <String>，这非常清楚地表明声明为insurance公司的类型必须提供公司名称。使用这种方式，
//        一旦解引用insurance公司名称时发生NullPointerException，你就能非常确定地知道出错
//        的原因，不再需要为其添加null的检查，因为null的检查只会掩盖问题，并未真正地修复问题。
//        insurance公司必须有个名字，所以，如果你遇到一个公司没有名称，你需要调查你的数据出了
//        什么问题，而不应该再添加一段代码，将这个问题隐藏。

//        在你的代码中始终如一地使用Optional，能非常清晰地界定出变量值的缺失是结构上的问
//        题，还是你算法上的缺陷，抑或是你数据中的问题。另外，我们还想特别强调，引入Optional
//        类的意图并非要消除每一个null引用。与此相反，它的目标是帮助你更好地设计出普适的API，
//        让程序员看到方法签名，就能了解它是否接受一个Optional的值。这种强制会让你更积极地将
//        变量从Optional中解包出来，直面缺失的变量值。
    }
}
