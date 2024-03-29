package com.cc.bootstrap.intl.demo.web.charset;

/**
 * @Description: Java中需要编码的场景
 * @author: ChenChen
 * @date: 2023/5/17 15:26
 */
public class Demo1_info {
    public static void main(String[] args) {
        /*
            在I/O操作中存在的编码
        我们知道涉及编码的地方一般都在从字符到字节或者从字节到字符的转换上，而需要这种转换的场景主要是I/O，这个I/O包括磁盘I/O和网络I/O，网络I/O部分在后面将主要以Web应用为例进行介绍。
        在我们的应用程序中涉及I/O操作时，只要注意指定统一的编解码Charset字符集，一般不会出现乱码问题。对有些应用程序如果不注意指定字符编码，则在中文环境中会使用操作系统默认编码。
        如果编解码都在中文环境中，通常也没有问题，但还是强烈建议不要使用操作系统的默认编码，因为这样会使你的应用程序的编码格式和运行环境绑定起来，在跨环境时很可能出现乱码问题。
            在内存操作中的编码

         */


        //在Java中如何编解码
//        这里将用实际例子介绍在Java中如何实现编码及解码。我们以“I am君山”这个字符串为例介绍在Java中如何把它以ISO-8859-1、GB-2312、GBK、UTF-16、UTF-8编码格式进行编码。

    }
}
