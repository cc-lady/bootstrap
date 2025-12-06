package com.cc.bootstrap.intl.demo.design_pattern.stratege;

/**
 * @Description: 支付宝
 * @author: ChenChen
 * @date: 2025-10-15 14:04
 */
public class AliPay extends Payment{
    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    protected double queryBalance(String uid) {
        return 900;
    }
}
