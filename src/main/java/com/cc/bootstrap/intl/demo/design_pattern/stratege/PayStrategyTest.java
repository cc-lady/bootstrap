package com.cc.bootstrap.intl.demo.design_pattern.stratege;

/**
 * @Description: 策略模式测试类
 * @author: ChenChen
 * @date: 2025-10-15 14:28
 */
public class PayStrategyTest {

    public static void main(String[] args) {
        // 下订单
        Order order = new Order("1", "202510150001", 324.15);

        // 选择渠道支付，每个渠道支付的具体算法不一样，基本算法是固定的

        // 在支付的时候才决定这个值用哪个
        System.out.println(order.pay(PayStrategy.ALI_PAY));
    }
}
