package com.cc.bootstrap.intl.demo.faster.completablefuture;

import com.cc.bootstrap.intl.demo.faster.vo.Shop;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: ChenChen
 * @date: 2023/5/9 10:54
 */
@Component
public class Price3 {

    public double getPriceValue(String product) {
        return new Shop().getPrice(product);
    }
}
