package com.cc.bootstrap.intl.demo.lambda.efficient.template;

/**
 * @Description: 模板方法示例 -- 在线银行
 * 假设你需要编写一个简单的在线银行应用。
 * 通常，用户需要输入一个用户账户，之后应用才能从银行的数据库中得到用户的详细信息，
 * 最终完成一些让用户满意的操作。
 * 不同分行的在线银行应用让客户满意的方式可能还略有不同，
 * 比如给客户的账户发放红利，或者仅仅是少发送一些推广文件。
 *
 * processCustomer方法搭建了在线银行算法的框架：获取客户提供的ID，然后提供服务让
 * 用户满意。不同的支行可以通过继承OnlineBanking类，对该方法提供差异化的实现。
 * @author: ChenChen
 * @date: 2023/2/6 10:26
 */
public abstract class OnlineBanking {
//    //获取客户提供的ID，然后提供服务让用户满意。
//    public void processCustomer(int id) {
//        Customer c = Database.getCustomerWithId(id);
//        makeCustomerHappy(c);
//    }
//
//    //提供服务让用户满意。
//    abstract void makeCustomerHappy(Customer c);
}
