package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.simulatorsvo;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Student;

import java.util.List;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description StudentDao
 * @createTime 2022年01月06日 16:11:00
 */
public interface StudentDao {
    public void batchUpdate(List<Student> students);
}
