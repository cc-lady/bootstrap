package com.cc.bootstrap.common.config;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.common.enums.GlobalExceptionEnum;
import com.cc.bootstrap.page.api.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName ExceptionAdvice
 * @Description 全局异常处理
 * @createTime 2021年12月30日 11:17:00
 */
@ControllerAdvice
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
    private static Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    ResponseResult<String> bindExceptionHandler(Exception exception) {
        String errorMessage = exception.getMessage();
        LOGGER.error("发生Exception.class异常，errorMessage = {} ", errorMessage, exception);
        return ResponseResult.fail(GlobalExceptionEnum.EXCEPTION_ENUM);
    }
}
