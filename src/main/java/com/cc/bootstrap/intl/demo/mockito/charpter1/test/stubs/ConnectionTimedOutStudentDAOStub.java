package com.cc.bootstrap.intl.demo.mockito.charpter1.test.stubs;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.stubsvo.StudentDAO;

import java.sql.SQLException;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 如何测试SQL异常条件？创建一个存根对象，并抛出一个异常。
 *              无论何时调用创建方法 在存短的DAO上，DAO抛出一个异常。
 * --------这个类应该在测试源文件夹下创建，因为该类只在测试中使用。----------
 * @createTime 2022年01月06日 14:35:00
 */
public class ConnectionTimedOutStudentDAOStub implements StudentDAO {
    // 无论何时调用创建方法 在存短的DAO上，DAO抛出一个异常。
    public String create(String name, String className) throws SQLException {
        throw new SQLException("DB connection timed out");
    }
}
