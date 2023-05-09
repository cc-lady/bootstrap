package com.cc.bootstrap.intl.demo.faster.async;

import com.cc.bootstrap.intl.demo.faster.vo.Shop;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: ChenChen
 * @date: 2023/5/9 10:54
 */
@Component
public class Price {

    @Async("asyncThreadPool")
    public double getPriceValue(String product) {
        return new Shop().getPrice(product);
    }
}
