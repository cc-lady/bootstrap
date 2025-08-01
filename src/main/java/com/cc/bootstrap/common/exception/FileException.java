package com.cc.bootstrap.common.exception;

/**
 * @Description: 文件异常类
 * @author: ChenChen
 * @date: 2022/3/2 17:39
 */
public class FileException extends RuntimeException{
    private final Integer code;
    private final String serverExceptinCode;

    public FileException(Integer code, String serverExceptinCode, String message) {
        super(message);
        this.code = code;
        this.serverExceptinCode = serverExceptinCode;
    }

    public FileException(Integer code, String serverExceptinCode) {
        this.code = code;
        this.serverExceptinCode = serverExceptinCode;
    }
}
