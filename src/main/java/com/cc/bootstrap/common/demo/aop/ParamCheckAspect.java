package com.cc.bootstrap.common.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.bouncycastle.operator.RuntimeOperatorException;
import org.springframework.stereotype.Component;

/**
 * @Description: 在请求前对参数进行校验，必须符合规范，例如：不能包含../，不能包含<script>或</script>
 * within(cn.spring21.aop.target..*) 指定cn.spring21.aop.target以及其子包下所有类的所有方法 ----也可以指定包下所有本包和子包的所有类和所有方法
 * @author: ChenChen
 * @date: 2022/11/8 15:15
 */
@Aspect
@Component
public class ParamCheckAspect {

    /**
     * @Description 对请求参数进行参数检查
     * @param joinPoint
     * @author ChenChen
     * @return void
     * @date 2022/11/8 15:28
     */
//    @Before(value = "execution( * * (..))")//execution( * * (..)) 所有类的所有方法
    @Before(value = "within(com.cc.bootstrap.page.api.*)")//com.cc.bootstrap.page.api包下所有类的所有方法
    public void checkParam(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        if (null == params) {
            return;
        }

        for (Object param : params) {
            if(!String.class.equals(param.getClass())) {
                continue;
            }

            String value = ((String) param).toLowerCase();
            if(value.contains("../") || value.contains("<script>") || value.contains("</script>")) {
                throw new RuntimeOperatorException("参数异常！不可包含../或<script>或</script>！");
            }
        }
    }
}
