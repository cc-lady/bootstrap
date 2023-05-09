package com.cc.bootstrap.intl.demo.lambda.default_method;

/**
 * @Description: 解决冲突的规则
 * 如果一个类同时实现了两个接口，这两个接口恰巧又提供了同样的默认方法签名，这时会发生什
 * 么情况？类会选择使用哪一个方法？这些问题，我们会在接下来的一节进行讨论
 * @author: ChenChen
 * @date: 2023/2/7 9:52
 */
public class Study2_Conflict {
    public static void main(String[] args) {
//        我们知道Java语言中一个类只能继承一个父类，但是一个类可以实现多个接口。随着默认方
//        法在Java 8中引入，有可能出现一个类继承了多个方法而它们使用的却是同样的函数签名。这种
//        情况下，类会选择使用哪一个函数？(虽然并不会经常发生)
//
//        Java编译器如何解决这种潜在的冲突。

//        public interface A {
//            default void hello() {
//                System.out.println("Hello from A");
//            }
//        }
//        public interface B extends A {
//            default void hello() {
//                System.out.println("Hello from B");
//            }
//        }
//        public class C implements B, A {
//            public static void main(String... args) {
//                new C().hello(); //这里会打印什么呢
//            }
//        }
        //1.解决问题的三条规则
//        如果一个类使用相同的函数签名从多个地方（比如另一个类或接口）继承了方法，通过三条
//        规则可以进行判断。
//        (1) 类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优
//        先级。
//        (2) 如果无法依据第一条进行判断，那么子接口的优先级更高：函数签名相同时，优先选择
//        拥有最具体实现的默认方法的接口，即如果B继承了A，那么B就比A更加具体。
//        (3) 最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，
//        显式地选择使用哪一个默认方法的实现。
//        我们保证，这些就是你需要知道的全部！
        //1.1 选择提供了最具体实现的默认方法的接口
//        这个例子中C类同时实现了B接口和A接口，而这两个接口
//        恰巧又都定义了名为hello的默认方法。另外，B继承自A。图9-5是这个场景的UML图。
//        图9-5 提供最具体的默认方法实现的接口，其优先级更高
//        编译器会使用声明的哪一个hello方法呢？按照规则(2)，应该选择的是提供了最具体实现的
//        默认方法的接口。由于B比A更具体，所以应该选择B的hello方法。所以，程序会打印输出“Hello
//        from B”。
//        现在，我们看看如果C像下面这样（如图9-6所示）继承自D，会发生什么情况：
//        public class D implements A{ }
//        public class C extends D implements B, A {
//            public static void main(String... args) {
//                new C().hello();
//            }
//        }
//        依据规则(1)，类中声明的方法具有更高的优先级。D并未覆盖hello方法，可是它实现了接
//        口A。所以它就拥有了接口A的默认方法。规则(2)说如果类或者父类没有对应的方法，那么就应
//        该选择提供了最具体实现的接口中的方法。因此，编译器会在接口A和接口B的hello方法之间做
//        选择。由于B更加具体，所以程序会再次打印输出“Hello from B”。
        //1.2 冲突及如何显式地消除歧义
//        让我们更进一步，假设B不再继承A：
//        public interface A {
//            void hello() {
//                System.out.println("Hello from A");
//            }
//        }
//        public interface B {
//            void hello() {
//                System.out.println("Hello from B");
//            }
//        }
//        public class C implements B, A { }
//        这时规则(2)就无法进行判断了，因为从编译器的角度看没有哪一个接口的实现更加具体，两
//        个都差不多。A接口和B接口的hello方法都是有效的选项。所以，Java编译器这时就会抛出一个
//        编译错误，因为它无法判断哪一个方法更合适：“Error: class C inherits unrelated defaults for hello()
//        from types B and A.”

//        解决这种两个可能的有效方法之间的冲突，没有太多方案；你只能显式地决定你希望在C中
//        使用哪一个方法。为了达到这个目的，你可以覆盖类C中的hello方法，在它的方法体内显式地
//        调用你希望调用的方法。Java 8中引入了一种新的语法X.super.m(…)，其中X是你希望调用的m
//        方法所在的父接口。举例来说，如果你希望C使用来自于B的默认方法，它的调用方式看起来就如
//        下所示：
//        public class C implements B, A {
//            void hello(){
//                B.super.hello();
//            }
//        }

        //1.3 菱形继承问题
//        让我们考虑最后一种场景，它亦是C++里中最令人头痛的难题。
//        public interface A{
//            default void hello(){
//                System.out.println("Hello from A");
//            }
//        }
//        public interface B extends A { }
//        public interface C extends A { }
//        public class D implements B, C {
//            public static void main(String... args) {
//                new D().hello();
//            }
//        }
//        图9-8以UML图的方式描述了出现这种问题的场景。这种问题叫“菱形问题”，因为类的继承
//        关系图形状像菱形。这种情况下类D中的默认方法到底继承自什么地方 ——源自B的默认方法，
//        还是源自C的默认方法？实际上只有一个方法声明可以选择。只有A声明了一个默认方法。由于这
//        个接口是D的父接口，代码会打印输出“Hello from A”。
//        图9-8 菱形问题
//        现在，我们看看另一种情况，如果B中也提供了一个默认的hello方法，并且函数签名跟A
//        中的方法也完全一致，这时会发生什么情况呢？根据规则(2)，编译器会选择提供了更具体实现的
//        接口中的方法。由于B比A更加具体，所以编译器会选择B中声明的默认方法。如果B和C都使用相
//        同的函数签名声明了hello方法，就会出现冲突，正如我们之前所介绍的，你需要显式地指定使
//        用哪个方法。
//        顺便提一句，如果你在C接口中添加一个抽象的hello方法（这次添加的不是一个默认方法），
//        会发生什么情况呢？你可能也想知道答案。
//        public interface C extends A {
//            void hello();
//        }
//        这个新添加到C接口中的抽象方法hello比由接口A继承而来的hello方法拥有更高的优先级，
//        因为C接口更加具体。因此，类D现在需要为hello显式地添加实现，否则该程序无法通过编译。

//        现在你应该已经了解了，如果一个类的默认方法使用相同的函数签名继承自多个接口，解决
//        冲突的机制其实相当简单。你只需要遵守下面这三条准则就能解决所有可能的冲突。
//       首先，类或父类中显式声明的方法，其优先级高于所有的默认方法。
//       如果用第一条无法判断，方法签名又没有区别，那么选择提供最具体实现的默认方法的
//        接口。
//       最后，如果冲突依旧无法解决，你就只能在你的类中覆盖该默认方法，显式地指定在你
//        的类中使用哪一个接口中的方法。
    }
}
