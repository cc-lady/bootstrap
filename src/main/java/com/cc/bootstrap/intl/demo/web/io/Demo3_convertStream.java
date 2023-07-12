package com.cc.bootstrap.intl.demo.web.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @Description: 转换流
 * @author: ChenChen
 * @date: 2023/5/16 10:23
 */
public class Demo3_convertStream {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("D:\\cc_study\\book")))) {//java7改写了所有IO资源类，都实现了AutoCloseable接口，自动关闭文件输入流

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
