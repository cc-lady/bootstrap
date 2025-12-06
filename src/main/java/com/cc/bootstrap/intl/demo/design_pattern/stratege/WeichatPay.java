package com.cc.bootstrap.intl.demo.design_pattern.stratege;

/**
 * @Description: 微信支付
 * @author: ChenChen
 * @date: 2025-10-15 14:11
 */
public class WeichatPay extends Payment{
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 256;
    }
}
