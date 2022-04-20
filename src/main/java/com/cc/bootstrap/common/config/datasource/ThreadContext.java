package com.cc.bootstrap.common.config.datasource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description: TODO
 * @author: ChenChen
 * @date: 2022/4/20 9:28
 */
public class ThreadContext extends CommonContext{
    private static ThreadLocal<ThreadContext> context = new ThreadLocal<ThreadContext>() {
        protected synchronized ThreadContext initialValue() {return new ThreadContext(); }
    };

    private ThreadContext() {}

    public static ThreadContext getContext() {
        return context.get();
    }

    public static String print() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(getContext().getAttributeNames());
    }
}
