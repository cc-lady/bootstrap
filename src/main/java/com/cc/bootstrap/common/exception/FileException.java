package com.cc.bootstrap.common.exception;

/**
 * @Description: 文件异常类
 * @author: ChenChen
 * @date: 2022/3/2 17:39
 */
public class FileException extends RuntimeException{
    public FileException(String msg) {super(msg);}
    public FileException(Exception exception) {super(exception);}
    public FileException(String msg, Exception exception) {super(msg, exception);}
}
