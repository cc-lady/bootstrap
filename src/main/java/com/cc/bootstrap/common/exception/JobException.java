package com.cc.bootstrap.common.exception;

import com.cc.bootstrap.common.enums.GlobalExceptionEnum;
import com.cc.bootstrap.common.exception.api.base.AbstractApiException;

/**
 * @Description: Job服务异常
 * @author: ChenChen
 * @date: 2025-07-31 15:57
 */
public class JobException extends AbstractApiException {
    private static final String BASE_ERROR = "Job服务请求失败！";

    public JobException(Integer code, String serverExceptionCode) {
        super(code, serverExceptionCode, BASE_ERROR);
    }

    public JobException(String serverExceptionCode, String message) {
        super(serverExceptionCode, message);
    }

    public JobException(Integer code, String serverExceptionCode, String message) {
        super(code, serverExceptionCode, message);
    }

    public JobException(Integer code, GlobalExceptionEnum globalExceptionEnum) {
        super(code, globalExceptionEnum.getCode(), globalExceptionEnum.getMessage());
    }

    public JobException(Integer code, GlobalExceptionEnum globalExceptionEnum, String message) {
        super(code, globalExceptionEnum.getCode(), message);
    }
}
