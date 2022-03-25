package com.cc.bootstrap.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @Description: 字符串工具类
 * @author: ChenChen
 * @date: 2022/3/25 16:08
 */
public class StringUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    /**
     * @Description 按字节长度截取字符串
     * 字符串的.length方法只会获取字符长度，例如汉字的“我们”和英文的“ab”长度（.length）都是2，但是有时候我们插入数据库需要根据
     * 字节长度截取不超过多少的值插入数据库，并且不能有乱码，例如“我们”在utf-8中算6个字节，那么仅截取字节长度4的时候，应该只截取值为“我”。
     * @param str
     * @param limitByteLength
     * @author ChenChen
     * @return java.lang.String
     * @date 2022/3/25 16:28
     */
    public static String substringByBytes(String str, int limitByteLength) throws UnsupportedEncodingException {
        // 不超过字节长度的，不进行截取
        if(org.springframework.util.StringUtils.isEmpty(str)
                || str.getBytes(StandardCharsets.UTF_8).length <= limitByteLength) {
            return str;
        }

        // 截取字节长度，并不能有乱码
        int sumBytes = 0;
        int length = str.length();
        int subLengh = 0;
        for (int i = 0; i < length; i++) {
            sumBytes += String.valueOf(str.charAt(i)).getBytes(StandardCharsets.UTF_8).length;
            if(sumBytes > limitByteLength) {
                break;
            }

            subLengh = i + 1;
        }

        return str.substring(0, subLengh);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        // data
        String str = "我们哈哈哈23如028390909c9";
        int limitByteLength  = 11;
        // test
        String resultString = StringUtils.substringByBytes(str, limitByteLength);
        LOGGER.info("[{}] 截取字节长度 [{}] 后的值为 [{}]", str, limitByteLength, resultString);
    }
}
