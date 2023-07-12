package com.cc.bootstrap.intl.demo.web.io;

import java.io.File;
import java.util.Arrays;

/**
 * @Description: 文件过滤器
 * @author: ChenChen
 * @date: 2023/5/15 16:51
 */
public class Demo2_FileFilter {
    public static void main(String[] args) {
        File file = new File("D:\\cc_study\\book");

        String[] nameList = file.list((dir, name) -> name.startsWith("H"));
        Arrays.stream(nameList).forEach(name -> System.out.println(name));
    }
}
