package com.cc.bootstrap.common.config.datasource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @author: ChenChen
 * @date: 2022/4/20 9:28
 */
public class CommonContext implements Context{
    private Map<String, Object> map = new HashMap<>();

    public CommonContext(){}

    @Override
    public Object getAttribute(String var1) {
        return this.map.get(var1);
    }

    @Override
    public void setAttribute(String var1, Object var2) {
        this.map.put(var1, var2);
    }

    @Override
    public void removeAttribute(String var1) {
        this.map.remove(var1);
    }

    @Override
    public Collection<String> getAttributeNames() {
        return this.map.keySet();
    }

    @Override
    public void clear() {
        this.map.clear();
    }
}
