package com.cc.bootstrap.intl.demo.design_pattern.stratege;

/**
 * @Description: 京东白条支付
 * @author: ChenChen
 * @date: 2025-10-15 14:14
 */
public class JDPay extends Payment{
    @Override
    public String getName() {
        return "京东白条支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 500;
    }
}
