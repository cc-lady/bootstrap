package com.cc.bootstrap.intl.demo.design_pattern.stratege;

/**
 * @Description: 银联支付
 * @author: ChenChen
 * @date: 2025-10-15 14:13
 */
public class UnionPay extends Payment{
    @Override
    public String getName() {
        return "银联支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 120;
    }
}
