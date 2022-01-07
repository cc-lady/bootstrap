package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.mockobjectsvo;

import com.cc.bootstrap.intl.demo.mockito.charpter1.test.spy.MethodInvocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 学生课程服务间谍对象
 * 注册ToCourse方法是一个空方法，不返回响应。为了验证是否使用一组特定的 参数调用了注册到课程的方法，
 * 我们可以创建一个间谍对象。该服务将写入 间谍日志，而间谍将作为一个间接的输出来进行验证。
 * 创建一个间谍对象来注册方法调用。
 * @createTime 2022年01月06日 15:23:00
 */
public class StudentServiceMockObject {
    private Map<String, List<MethodInvocation>> invocationMap = new HashMap<>();

    /**
     * 注册表调用方法接受一个方法调用对象，并将其放入其中地图
     */
    public void registerCall(MethodInvocation invocation) {
        List<MethodInvocation> list = invocationMap.get(invocation.getMethod());
        if (list == null) {
            list = new ArrayList<>();
        }
        if (!list.contains(invocation)) {
            list.add(invocation);
        }
        invocationMap.put(invocation.getMethod(), list);
    }

    /*
     * 查找方法调用次数
     */
    public int invocation(String methodName){
        List<MethodInvocation> list = invocationMap.get(methodName);
        if(list == null){
            return 0;
        }
        return list.size();
    }

    /*
     * 查找对应次数具体调用情况
     */
    public MethodInvocation arguments(String methodName, int invocationIndex){
        List<MethodInvocation> list = invocationMap.get(methodName);
        if(list == null || (invocationIndex > list.size())){
            return null;
        }
        return list.get(invocationIndex-1);
    }

    /*
     * 验证方法调用次数 - 增加此方法调用方法
     */
    public void verify(String methodName, int numberOfInvocation){
        int actual = invocation(methodName);
        if(actual != numberOfInvocation){
            throw new IllegalStateException(methodName
                    + " was expected [" + numberOfInvocation
                    + "] times but actuallyactaully invoked[" + actual + "] times");
        }
    }
}
