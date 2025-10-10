package com.cc.bootstrap.intl.demo.design_pattern.prototype;

import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 齐天大圣 有一个金箍棒
 * @author: ChenChen
 * @date: 2025-10-10 16:46
 */
@Data
public class QiTianDaSheng extends Monkey implements Cloneable, Serializable {
    private JinGuBang jinGuBang;

    // 初始化
    public QiTianDaSheng() {
        this.setBirthday(new Date());
        this.setJinGuBang(new JinGuBang());
    }

    // 需深克隆
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    // 深克隆
    public Object deepClone() {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(this);
            oos.flush();
            oos.close();

            ByteArrayInputStream fis = new ByteArrayInputStream(os.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(fis);
            QiTianDaSheng copy = (QiTianDaSheng) ois.readObject();
            ois.close();
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 浅克隆
    public QiTianDaSheng shallowClone(QiTianDaSheng target) {
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        qiTianDaSheng.setHeight(target.getHeight());
        qiTianDaSheng.setWeight(target.getWeight());
        qiTianDaSheng.setBirthday(new Date());
        qiTianDaSheng.setJinGuBang(target.getJinGuBang());
        return qiTianDaSheng;
    }
}
