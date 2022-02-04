package com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks;

import lombok.Data;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 创建一个错误对象，其 中包含一个堆栈跟踪元素数组和一个错误代码字符串。
 * @createTime 2022年01月25日 16:25:00
 */
@Data
public class Error {
    private StackTraceElement[] trace;
    private String errorCode;
    //Getters and setters are ignored for brevity
}
