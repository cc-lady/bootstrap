package com.cc.bootstrap.page.enums;

import com.cc.bootstrap.common.base.restful.IBaseEnum;

/**
 * @Description: Job服务业务码枚举
 * @author: ChenChen
 * @date: 2025-07-31 16:02
 */
public enum JobServiceEnum implements IBaseEnum {
    SUCCESS("JOB_000", "临时作业服务请求成功！"),
    ;

    private String code;
    private String message;

    JobServiceEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
