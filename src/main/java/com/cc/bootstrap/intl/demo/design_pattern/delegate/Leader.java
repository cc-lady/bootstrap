package com.cc.bootstrap.intl.demo.design_pattern.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 项目经理
 * @author: ChenChen
 * @date: 2025-10-13 16:28
 */
public class Leader implements IEmployee{
    private Map<String, IEmployee> targets = new HashMap<>();

    public Leader () {
        targets.put("登录", new EmployeeA());
        targets.put("加密", new EmployeeB());
    }

    // 经理自己不干活
    @Override
    public void doing(String command) {
        targets.get(command).doing(command);
    }
}
