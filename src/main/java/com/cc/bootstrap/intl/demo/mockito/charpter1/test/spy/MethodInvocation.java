package com.cc.bootstrap.intl.demo.mockito.charpter1.test.spy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName MethodInvocation 方法调用类
 * @Description 方法调用类表示一个方法调用：方法名称、一个参数列表和一个返回值。
 * 假设一个和()方法调用两个数字和方法返回两个数字的和，然后方法调用类将包含 一个方法名称和，
 * 一个参数列表将包括两个数字，和一个返回值将包含两个数 字的总和。
 * @createTime 2022年01月06日 15:20:00
 */
public class MethodInvocation {
    private List<Object> params = new ArrayList<>();
    private Object returnedValue = null;
    private String method;
    public List<Object> getParams() {
        return params;
    }
    public MethodInvocation addParam(Object parm){
        getParams().add(parm);
        return this;
    }
    public Object getReturnedValue() {
        return returnedValue;
    }
    public MethodInvocation setReturnedValue(Object returnedValue) {
        this.returnedValue = returnedValue;
        return this;
    }
    public String getMethod() {
        return method;
    }
    public MethodInvocation setMethod(String method) {
        this.method = method;
        return this;
    }
}
