package com.cc.bootstrap.common.config;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.common.enums.GlobalExceptionEnum;
import com.cc.bootstrap.common.exception.FileException;
import com.cc.bootstrap.common.exception.api.base.AbstractApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName ExceptionAdvice
 * @Description 全局异常处理
 * @createTime 2021年12月30日 11:17:00
 */
@RestControllerAdvice
@Slf4j
/*
 * @ControllerAdvice是在类上声明的注解，其用法主要有三点：
结合方法型注解@ExceptionHandler，用于捕获Controller中抛出的指定类型的异常，从而达到不同类型的异常区别处理的目的；
结合方法型注解@InitBinder，用于request中自定义参数解析方式进行注册，从而达到自定义指定格式参数的目的；
结合方法型注解@ModelAttribute，表示其标注的方法将会在目标Controller方法执行之前执行。
————————————————
版权声明：本文为CSDN博主「Java晋升」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/m0_37607679/article/details/103949069
 */
public class ExceptionAdvice {

    /**
     * @Description 业务异常
     * @param exception
     * @param response
     * @author ChenChen
     * @return com.cc.bootstrap.common.base.restful.ResponseResult<java.lang.String>
     * @date 2025-07-31 16:30
     */
    @ExceptionHandler(AbstractApiException.class)
    public ResponseResult<String> bindExceptionHandler(AbstractApiException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("业务处理异常，AbstractApiException = {} ", errorMessage, exception);
        response.setStatus(exception.getCode());
        return ResponseResult.failWithMessage(exception.getServerExceptionCode(), errorMessage);
    }

    /**
     * @Description 文件校验异常
     * @param exception
     * @param response
     * @author ChenChen
     * @return com.cc.bootstrap.common.base.restful.ResponseResult<java.lang.String>
     * @date 2025-07-31 16:42
     */
    @ExceptionHandler(FileException.class)
    public ResponseResult<String> bindExceptionHandler(FileException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("文件异常，FileException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.FILE_VALID_EXCEPTION, errorMessage);
    }

    /**
     * @Description 请求不支持HttpRequestMethod异常
     * @param exception
     * @param response
     * @author ChenChen
     * @return com.cc.bootstrap.common.base.restful.ResponseResult<java.lang.String>
     * @date 2025-07-31 16:43
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult<String> bindExceptionHandler(HttpRequestMethodNotSupportedException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("请求不支持HttpRequestMethod异常，HttpRequestMethodNotSupportedException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.REQUEST_METHOD,
                String.format("当前请求不支持%s；仅支持：%s", exception.getMethod(), Arrays.toString(exception.getSupportedMethods())));
    }

    /**
     * @Description 非法参数异常
     * @param exception
     * @param response
     * @author ChenChen
     * @return com.cc.bootstrap.common.base.restful.ResponseResult<java.lang.String>
     * @date 2025-07-31 16:45
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult<String> bindExceptionHandler(IllegalArgumentException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        if (StringUtils.isNotBlank(errorMessage) && errorMessage.contains("URLDecoder")) {
            errorMessage = "请求数据中含有非法内容，操作失败";
        }
        log.error("非法参数异常，IllegalArgumentException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.ILLEGAL_ARGUMENT, errorMessage);
    }

    /**
     * @Description 非法状态异常
     * @param exception
     * @param response
     * @author ChenChen
     * @return com.cc.bootstrap.common.base.restful.ResponseResult<java.lang.String>
     * @date 2025-07-31 16:47
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseResult<String> bindExceptionHandler(IllegalStateException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("非法状态异常，IllegalStateException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.ILLEGAL_STATE, "访问服务异常，内容暂时不可用");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult<String> bindExceptionHandler(HttpMessageNotReadableException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("请求内容解析异常，HttpMessageNotReadableException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.MESSAGE_NOT_READABLE, "访问服务异常，请求内容解析失败");
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseResult<String> bindExceptionHandler(SQLSyntaxErrorException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("SQL语句执行异常，SQLSyntaxErrorException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.SQL_SYNTAX_ERROR, "SQL语句执行存在错误");
    }

    @ExceptionHandler({DuplicateKeyException.class, SQLIntegrityConstraintViolationException.class})
    public ResponseResult<String> bindExceptionHandler(Exception exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("主键冲突，DuplicateKeyException|SQLIntegrityConstraintViolationException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.DUPLICATE_KEY, String.format("违反唯一约束条件：%s", exception.getCause()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseResult<String> bindExceptionHandler(BindException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("参数绑定失败，BindException = {} ", errorMessage, exception);
        response.setStatus(500);
        List<FieldError> fieldErrors = exception.getFieldErrors();
        return this.error(GlobalExceptionEnum.DUPLICATE_KEY, String.format("参数绑定失败：%s", this.getMessage(fieldErrors)));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult<String> bindExceptionHandler(MissingServletRequestParameterException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("缺少请求参数，MissingServletRequestParameterException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.MISSING_SERVLET_REQUEST_PARAMETER, String.format("请求参数不能为空，请检查：%s", exception.getParameterName()));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseResult<String> bindExceptionHandler(JsonProcessingException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("json转换异常，JsonProcessingException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.JSON_PROCESS_EXCEPTION, String.format("json转换异常，请检查：%s", errorMessage));
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseResult<String> bindExceptionHandler(HttpMediaTypeNotAcceptableException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("HttpMediaType不支持异常，JsonProcessingException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.HTTP_MEDIATYPE_NOTSUPPORTED, errorMessage);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseResult<String> bindExceptionHandler(NumberFormatException exception, HttpServletResponse response) {
        String errorMessage = exception.getMessage();
        log.error("数值转换异常，NumberFormatException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.NUMBER_FORMAT, "数值转换异常");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<String> bindExceptionHandler(MethodArgumentNotValidException exception, HttpServletResponse response) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String errorMessage = this.getBindErrorMessage(fieldErrors);
        log.error("参数校验失败，MethodArgumentNotValidException = {} ", errorMessage, exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.CONSTRAINT_VIOLATION, errorMessage);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseResult<String> bindExceptionHandler(Throwable exception, HttpServletResponse response) {
        log.error("未知异常，Throwable = {} ", exception.getMessage(), exception);
        response.setStatus(500);
        return this.error(GlobalExceptionEnum.UNKNOWN, "未知的异常");
    }

    private String getBindErrorMessage(List<FieldError> fieldErrors) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("参数校验失败！");
        Map<String, List<FieldError>> errorMap = fieldErrors.stream().collect(Collectors.groupingBy(FieldError::getField));
        for (Map.Entry<String, List<FieldError>> entry : errorMap.entrySet()) {
            String field = entry.getKey();
            stringBuilder.append(String.format("参数 [%s]，", field));
            List<FieldError> fieldErrorList = entry.getValue();
            stringBuilder.append(String.format("参数值 [%s]，", fieldErrorList.get(0).getRejectedValue()));
            stringBuilder.append(String.format("错误信息 [%s]。", fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("；"))));

        }
        return stringBuilder.toString();
    }

    private String getMessage(List<FieldError> fieldErrors) {
        String message = fieldErrors.stream()
                .map(fieldError ->  String.format("请检查字段：%s，值：%s", fieldError.getField(), fieldError.getRejectedValue()))
                .collect(Collectors.joining(";"));
        return message;
    }

    private ResponseResult<String> error(GlobalExceptionEnum fileValidException, String errorMessage) {
        return ResponseResult.fail(fileValidException, errorMessage);
    }
}
