package com.cc.bootstrap.common.config.datasource;

import java.util.Collection;

/**
 * @Description: TODO
 * @author: ChenChen
 * @date: 2022/4/20 9:28
 */
public interface Context {
    Object getAttribute(String var1);

    void setAttribute(String var1, Object var2);

    void removeAttribute(String var1);

    Collection<String> getAttributeNames();

    void clear();
}
