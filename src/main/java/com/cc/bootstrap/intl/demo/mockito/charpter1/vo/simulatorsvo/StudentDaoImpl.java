package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.simulatorsvo;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description StudentDaoImpl
 * @createTime 2022年01月06日 16:12:00
 */
public class StudentDaoImpl implements StudentDao {
    public StudentDaoImpl() {
    }

    /*
     * @Description 批更新方法创建两个列表；一个用于新学生，另一个用于现有学生。它在学生 列表中循环，并填充插入列表和提示列表方法，
     * 这取决于角色编号属性。如果 角色号为空，则意味着有一个新学生。它为每个学生创建一个SQL参数映射，
     * 并调用JdbcSupprt类，最后，检查数据库更新计数。
     */
    @Override
    public void batchUpdate(List<Student> students) {
        List<Student> insertList = new ArrayList<>();
        List<Student> updateList = new ArrayList<>();
        for (Student student : students) {
            if (student.getRoleNumber() == null) {
                insertList.add(student);
            } else {
                updateList.add(student);
            }
        }
        int rowsInserted = 0;
        int rowsUpdated = 0;
        if (!insertList.isEmpty()) {
            List<Map<String, Object>> paramList = new
                    ArrayList<>();
            for (Student std : insertList) {
                Map<String, Object> param = new HashMap<>();
                param.put("name", std.getName());
                paramList.add(param);
            }
            int[] rowCount = update("insert", paramList);
            rowsInserted = sum(rowCount);
        }
        if (!updateList.isEmpty()) {
            List<Map<String, Object>> paramList = new
                    ArrayList<>();
            for (Student std : updateList) {
                Map<String, Object> param = new HashMap<>();
                param.put("roleId", std.getRoleNumber());
                param.put("name", std.getName());
                paramList.add(param);
            }
            int[] rowCount = update("update", paramList);
            rowsUpdated = sum(rowCount);
        }
        if (students.size() != (rowsInserted + rowsUpdated)) {
            throw new IllegalStateException("Database update error, expected "
                    + students.size() + " updates but actual "
                    + (rowsInserted + rowsUpdated));
        }
    }

    // 调用JdbcSupport批量更新
    public int[] update(String sql, List<Map<String,
            Object>> params) {
        return new JdbcSupport().batchUpdate(sql, params);
    }

    // 求和
    private int sum(int[] rows) {
        int sum = 0;
        for (int val : rows) {
            sum += val;

            return sum;
        }
        return sum;
    }
}
