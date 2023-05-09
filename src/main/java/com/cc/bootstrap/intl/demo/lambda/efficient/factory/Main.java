package com.cc.bootstrap.intl.demo.lambda.efficient.factory;

/**
 * @Description: 使用lambda表达式
 * @author: ChenChen
 * @date: 2023/2/6 16:25
 */
public class Main {
    public static void main(String[] args) {
//        我们已经知道可以像引用方法一样引用构造函数。比如，下面就是一个引用贷款
//（Loan）构造函数的示例：
//        Supplier<Product> loanSupplier = Loan::new;
//        Loan loan = loanSupplier.get();
//        通过这种方式，你可以重构之前的代码，创建一个Map，将产品名映射到对应的构造函数：
//        final static Map<String, Supplier<Product>> map = new HashMap<>();
//        static {
//            map.put("loan", Loan::new);
//            map.put("stock", Stock::new);
//            map.put("bond", Bond::new);
//        }
//        现在，你可以像之前使用工厂设计模式那样，利用这个Map来实例化不同的产品。
//        public static Product createProduct(String name){
//            Supplier<Product> p = map.get(name);
//            if(p != null) return p.get();
//            throw new IllegalArgumentException("No such product " + name);
//        }
//        这是个全新的尝试，它使用Java 8中的新特性达到了传统工厂模式同样的效果。但是，如果
//        工厂方法createProduct需要接收多个传递给产品构造方法的参数，这种方式的扩展性不是很
//        好。你不得不提供不同的函数接口，无法采用之前统一使用一个简单接口的方式。
//        比如，我们假设你希望保存具有三个参数（两个参数为Integer类型，一个参数为String
//        类型）的构造函数；为了完成这个任务，你需要创建一个特殊的函数接口TriFunction。最终
//        的结果是Map变得更加复杂。
//        public interface TriFunction<T, U, V, R>{
//            R apply(T t, U u, V v);
//        }
//        Map<String, TriFunction<Integer, Integer, String, Product>> map
//                = new HashMap<>();
//        你已经了解了如何使用Lambda表达式编写和重构代码。接下来，我们会介绍如何确保新编
//        写代码的正确性。
    }
}
