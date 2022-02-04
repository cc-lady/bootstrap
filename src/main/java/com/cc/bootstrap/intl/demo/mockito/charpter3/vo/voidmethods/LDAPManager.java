package com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethods;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description LDAPManager - 验证用户名密码接口
 * @createTime 2022年01月22日 15:25:00
 */
public interface LDAPManager {
    /**
     * @Description 验证用户名密码
     * @author ChenChen
     * @date 2022/1/22
     * @paramuserName
     * @paramencrypterPassword
     * @return
     */
    boolean isValidUser(String userName, String encrypterPassword);
}
