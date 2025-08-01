package com.cc.bootstrap.common.exception.api.base;

/**
 * @Description: 业务抽象异常类
 * @author: ChenChen
 * @date: 2025-07-31 15:52
 */
public abstract class AbstractApiException extends Exception {
    /**
     * @Description http状态码
     *
     * @author ChenChen
     * @return
     * @date 2025-07-31 15:53
     */
    protected Integer code = 500;//默认为500
    /**
     * @Description 业务异常编码
     *
     * @author ChenChen
     * @return
     * @date 2025-07-31 15:53
     */
    protected String serverExceptionCode;


    protected AbstractApiException(Integer code, String serverExceptionCode, String message) {
        super(message);
        this.code = code;
        this.serverExceptionCode = serverExceptionCode;
    }

    protected AbstractApiException(String serverExceptionCode, String message) {
        super(message);
        this.serverExceptionCode = serverExceptionCode;
    }

    public Integer getCode() {
        return code;
    }

    public String getServerExceptionCode() {
        return serverExceptionCode;
    }
}
