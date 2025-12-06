package com.cc.bootstrap.intl.demo.design_pattern.stratege;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 支付策略类
 * @author: ChenChen
 * @date: 2025-10-15 14:18
 */
public class PayStrategy {

    public static final String ALI_PAY = "AliPay";
    public static final String JD_PAY = "JdPay";
    public static final String UNION_PAY = "UnionPay";
    public static final String WEICHAT_PAY = "WeichatPay";
    public static final String DEFAULT_PAY = "AliPay";

    private static Map<String,Payment> payStrategy = new HashMap<>();
    static {
        payStrategy.put(ALI_PAY, new AliPay());
        payStrategy.put(JD_PAY, new JDPay());
        payStrategy.put(UNION_PAY, new UnionPay());
        payStrategy.put(WEICHAT_PAY, new WeichatPay());
    }

    public static Payment get(String payKey) {
        if (!payStrategy.containsKey(payKey)) {
            return payStrategy.get(DEFAULT_PAY);
        }
        return payStrategy.get(payKey);
    }
}
