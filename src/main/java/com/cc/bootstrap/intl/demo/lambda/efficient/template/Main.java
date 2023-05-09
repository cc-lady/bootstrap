package com.cc.bootstrap.intl.demo.lambda.efficient.template;

/**
 * @Description: TODO
 * @author: ChenChen
 * @date: 2023/2/6 10:30
 */
public class Main {
    public static void main(String[] args) {
//        使用Lambda表达式
//        使用你偏爱的Lambda表达式同样也可以解决这些问题（创建算法框架，让具体的实现插入
//        某些部分）。你想要插入的不同算法组件可以通过Lambda表达式或者方法引用的方式实现。
//        这里我们向processCustomer方法引入了第二个参数，它是一个Consumer<Customer>类
//        型的参数，与前文定义的makeCustomerHappy的特征保持一致：
//        public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
//            Customer c = Database.getCustomerWithId(id);
//            makeCustomerHappy.accept(c);
//        }
//        现在，你可以很方便地通过传递Lambda表达式，直接插入不同的行为，不再需要继承
//        OnlineBanking类了：
//        new OnlineBankingLambda().processCustomer(1337, (Customer c) ->
//                System.out.println("Hello " + c.getName());
//        这是又一个例子，佐证了Lamba表达式能帮助你解决设计模式与生俱来的设计僵化问题。
    }
}
