package com.cc.bootstrap.intl.demo.design_pattern.stratege;

/**
 * @Description: 订单类
 * @author: ChenChen
 * @date: 2025-10-15 14:23
 */

import lombok.Data;

@Data
public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    // 不需要在代码中写switch了
    // 更不需要写if elseif
    private PayState pay() {
        return pay(PayStrategy.DEFAULT_PAY);
    }

    public PayState pay(String payKey) {
        Payment payment = PayStrategy.get(payKey);
        System.out.println("欢迎使用"+ payment.getName());
        System.out.println("本次交易金额为：" + amount + "，开始扣款。。。");
        return payment.pay(uid, amount);
    }
}
