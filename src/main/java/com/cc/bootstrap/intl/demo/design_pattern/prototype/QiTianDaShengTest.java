package com.cc.bootstrap.intl.demo.design_pattern.prototype;

/**
 * @Description: 原型模式测试类：深克隆浅克隆测试
 * @author: ChenChen
 * @date: 2025-10-10 16:55
 */
public class QiTianDaShengTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        QiTianDaSheng deepClone = (QiTianDaSheng) qiTianDaSheng.clone();
        System.out.println("深克隆：" + (qiTianDaSheng.getJinGuBang() == deepClone.getJinGuBang()));//深克隆：false

        QiTianDaSheng shallowClone = qiTianDaSheng.shallowClone(qiTianDaSheng);
        System.out.println("浅克隆：" + (qiTianDaSheng.getJinGuBang() == shallowClone.getJinGuBang()));//浅克隆：true
    }
}
