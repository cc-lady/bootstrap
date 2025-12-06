package com.cc.bootstrap.intl.demo.design_pattern.delegate;

/**
 * @Description: 老板下达命令
 * @author: ChenChen
 * @date: 2025-10-13 16:31
 */
public class Boss {

    public void command(String command, Leader leader) {
        leader.doing(command);
    }
}
