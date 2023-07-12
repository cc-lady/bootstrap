package com.cc.bootstrap.intl.demo.web.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @Description: 编码集
 * @author: ChenChen
 * @date: 2023/5/17 14:29
 */
public class Demo6_Charset {
    public static void main(String[] args) throws CharacterCodingException {
        // 获取Java所支持的全部字符集
        Charset.availableCharsets()
                .forEach((alias, charset) -> {
                    // 输出字符集的别名和对应的Charset对象
                    System.out.println(alias + "----->"
                            + charset);
                });

/*
GBK：简体中文字符集。
➢BIG5：繁体中文字符集。
➢ISO-8859-1:ISO拉丁字母表No.1，也叫作ISO-LATIN-1。
➢UTF-8:8位UCS转换格式。
➢UTF-16BE:16位UCS转换格式，Big-endian（最低地址存放高位字节）字节顺序。
➢UTF-16LE:16位UCS转换格式，Little-endian（最高地址存放低位字节）字节顺序。
➢UTF-16:16位UCS转换格式，字节顺序由可选的字节顺序标记来标识。
Java 7新增了一个StandardCharsets类，该类中包含了ISO_8859_1、UTF_8、UTF_16等类变量，这些类变量代表了最常用的字符集对应的Charset对象。
一旦知道了字符集的别名，程序就可以调用Charset的forName()方法来创建对应的Charset对象，该方法的参数就是相应字符集的别名。*/
        Charset cs = Charset.forName("ISO-8859-1");
        Charset csCn = Charset.forName("GBK");

/*        在获得了 Charset 对象之后，就可以通过该对象的 newDecoder()、newEncoder()两个方法分别返回CharsetDecoder和CharsetEncoder对象，
        代表该Charset的解码器和编码器。
        调用CharsetDecoder的decode()方法就可以将ByteBuffer（字节序列）转换成CharBuffer（字符序列），
        调用CharsetEncoder的encode()方法就可以将 CharBuffer 或 String（字符序列）转换成 ByteBuffer（字节序列）。
        如下程序使用CharsetEncoder和CharsetDecoder完成了ByteBuffer和CharBuffer之间的转换。
        在String类中也提供了一个getBytes(String charset)方法，该方法返回byte[]，该方法也是使用指定的字符集将字符串转换成字节序列的。*/
        // 创建简体中文对应的Charset
        Charset cn = Charset.forName("GBK");
        // 获取cn对象对应的编码器和解码器
        CharsetEncoder cnEncoder = cn.newEncoder();
        CharsetDecoder cnDecoder = cn.newDecoder();
        // 创建一个CharBuffer对象
        CharBuffer cbuff = CharBuffer.allocate(8);
        cbuff.put('孙');
        cbuff.put('悟');
        cbuff.put('空');
        cbuff.flip();
        // 将CharBuffer中的字符序列转换成字节序列
        ByteBuffer bbuff = cnEncoder.encode(cbuff);
        // 循环访问ByteBuffer中的每个字节
        for (int i = 0; i < bbuff.capacity(); i++)
        {
            System.out.print(bbuff.get(i) + " ");
        }
        // 将ByteBuffer的数据解码成字符序列
        System.out.println("\n" + cnDecoder.decode(bbuff));



    }
}
