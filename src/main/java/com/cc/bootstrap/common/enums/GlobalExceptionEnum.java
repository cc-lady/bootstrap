package com.cc.bootstrap.common.enums;

import com.cc.bootstrap.common.base.restful.IBaseEnum;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 全局异常枚举信息
 * @createTime 2021年12月30日 14:11:00
 */
public enum GlobalExceptionEnum implements IBaseEnum {

    EXCEPTION_ENUM("EC_0001", "未知异常，请联系项目管理员！"),
    RUNTIME_EXCEPTION_ENUM("EC_0002", "运行期间发生异常，请联系项目管理员！"),//测试枚举工具类使用
    ;

    private String code;
    private String message;

    GlobalExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
