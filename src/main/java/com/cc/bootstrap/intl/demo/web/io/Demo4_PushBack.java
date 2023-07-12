package com.cc.bootstrap.intl.demo.web.io;

import java.io.FileReader;
import java.io.PushbackReader;

/**
 * @Description: 推回输入流
 * @author: ChenChen
 * @date: 2023/5/16 10:27
 */
public class Demo4_PushBack {
    public static void main(String[] args) {
        try (PushbackReader pushbackReader = new PushbackReader(
                new FileReader("PushbackTest"), 64)) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
