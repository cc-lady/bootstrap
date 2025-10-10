package com.cc.bootstrap.intl.demo.design_pattern.prototype;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 金箍棒 可变大变小
 * @author: ChenChen
 * @date: 2025-10-10 16:44
 */
@Data
public class JinGuBang implements Serializable {
    private float h = 100;
    private float d = 10;

    public void big() {
        this.h *= 2;
        this.d *= 2;
    }

    public void small() {
        this.h /= 2;
        this.d /= 2;
    }
}
