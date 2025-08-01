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

    UNKNOWN("EC_0001", "访问服务请求异常，未知的异常！"),
    REQUEST_METHOD("EC_0002", "不支持的请求类型！"),
    ILLEGAL_ARGUMENT("EC_0003", "非法参数！"),
    NUMBER_FORMAT("EC_0004", "格式转换异常！"),
    ILLEGAL_STATE("EC_0005", "非法状态！"),
    MESSAGE_NOT_READABLE("EC_0006", "内容解析异常！"),
    SQL_SYNTAX_ERROR("EC_0007", "SQL语句解析异常！"),
    DUPLICATE_KEY("EC_0008", "主键冲突！"),
    MESSAGE_ARGUMENT_NOT_VALID("EC_0009", "方法参数格式异常！"),
    CONSTRAINT_VIOLATION("EC_0010", "参数校验发生错误！"),
    BIND_EXCEPTION("EC_0011", "绑定异常！"),
    MISSING_SERVLET_REQUEST_PARAMETER("EC_0012", "缺少请求参数！"),
    JSON_PROCESS_EXCEPTION("EC_0013", "json转换异常！"),
    FILE_VALID_EXCEPTION("EC_0014", "非法文件异常！"),
    XSS_INJECTION_EXCEPTION("EC_0015", "XSS漏洞校验异常！"),
    HTTP_MEDIATYPE_NOTSUPPORTED("EC_0016", "MediaType不支持异常！"),
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
