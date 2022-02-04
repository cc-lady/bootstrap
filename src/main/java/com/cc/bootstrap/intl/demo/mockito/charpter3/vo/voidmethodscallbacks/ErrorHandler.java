package com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 错误处理程序接口获取错误Error对象，将堆栈元素映射方法映射到错误代码字符串 ，
 * 并将代码设置回错误对象。
 * @createTime 2022年01月25日 16:27:00
 */
public interface ErrorHandler {
    void mapTo(Error error);
}
