package com.cc.bootstrap.intl.demo.mockito.charpter1.test.simulators;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Student;
import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.simulatorsvo.StudentDaoImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description StudentDaoTest 测试更新学生单元测试类
 * 我们需要单元测试批处理更新行为，但是更新方法创建了一个Jdbc支持的新实 例，
 * 并调用数据库。所以，我们不能直接单元测试批量更新()方法；它需要 很长时间才能完成。
 * 我们的问题是更新()方法；我们将分离关注点，扩展学 生道impl类，并覆盖更新的()方法。
 * 如果我们在新对象上调用批处理更新() ，那么它将把更新()方法调用路由到新的被覆盖的更新()方法。
 * @createTime 2022年01月06日 16:17:00
 */
public class StudentDaoTest {
    private TestableStudentDao dao;
    private Map<String, Integer> sqlCount = null;
    
    @Before("")
    public void setup() {
        dao = new TestableStudentDao();
        sqlCount = new HashMap<String, Integer>();
    }

    @Test
    public void when_row_count_does_not_match_then_rollbacks_tarnsaction(){
        try {
            List<Student> students = new ArrayList<>();
            students.add(new Student(null, "Gautam Kohli"));
            int[] expect_update_fails_count = {0};
            dao.valuesToReturn = expect_update_fails_count;
            dao.batchUpdate(students);
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }


    class TestableStudentDao extends StudentDaoImpl {
        int[] valuesToReturn;
        public int[] update(String sql, List<Map<String, Object>>
                params) {
            Integer count = sqlCount.get(sql);
            if(count == null){
                sqlCount.put(sql, params.size());
            }else{
                sqlCount.put(sql, count+params.size());
            }
            if (valuesToReturn != null) {
                return valuesToReturn;
            }
            return valuesToReturn;
        }
    }

    // 值toRetern数组设置为{1}，学生对象使用空角色数属性创建。
    @Test
    public void when_new_student_then_creates_student(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(null, "Gautam Kohli"));
        int[] expect_update_success = {1};
        dao.valuesToReturn = expect_update_success;
        dao.batchUpdate(students);
        int actualInsertCount = sqlCount.get("insert");
        int expectedInsertCount = 1;
        assertEquals(expectedInsertCount, actualInsertCount);
    }

    @Test
    public void when_existing_student_then_updates_student_successfully(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("001", "Mark Leo"));
        int[] expect_update_success = {1};
        dao.valuesToReturn = expect_update_success;
        dao.batchUpdate(students);
        int actualUpdateCount = sqlCount.get("update");
        int expectedUpdate = 1;
        assertEquals(expectedUpdate, actualUpdateCount);
    }
}
