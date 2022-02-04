package com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description LDAPManagerImpl - 验证用户名密码实现类
 * @createTime 2022年01月22日 15:27:00
 */
public class LDAPManagerImpl implements LDAPManager {
    Map<String, String> userMap = new HashMap<>();
    {
        userMap.put("admin", "admin111111");
    }

    @Override
    public boolean isValidUser(String userName, String encrypterPassword) {
        // 验证逻辑，此处随意写一下
        if(userMap.containsKey(userName) && userMap.get(userName).equals(encrypterPassword)) {
            return true;
        }
        return false;
    }
}
