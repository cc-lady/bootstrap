package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.stubsvo;

import java.sql.SQLException;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description StudentDAO
 * @createTime 2022年01月06日 14:28:00
 */
public interface StudentDAO {
    public String create(String name, String className) throws SQLException;
}
