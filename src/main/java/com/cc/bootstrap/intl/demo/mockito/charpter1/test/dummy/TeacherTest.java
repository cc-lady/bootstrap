package com.cc.bootstrap.intl.demo.mockito.charpter1.test.dummy;

import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Grades;
import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Marks;
import com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo.Teacher;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 使用虚拟对象DummyStudent进行单元测试
 * ------虚拟对象不直接用于测试或测试代码， 但在创建测试代码中需要的另一个对象时需要虚拟对象。
 *  虚拟对象类似于空对象， 但被测试的代码不使用虚拟对象。
 *  空对象（如模式中）在测试代码中使用，并主动 与之交互，但它们只产生零行为。
 * ------如果没有使用它们，您只需使用一个实际的空值。
 * @createTime 2022年01月06日 14:17:00
 */
public class TeacherTest {
    @Test
    public void when_marks_above_seventy_five_percent_returns_very_good() {
        DummyStudent dummyStudent = new DummyStudent();
        Marks inEnglish = new Marks(dummyStudent, "English002", new BigDecimal("81.00"));
        Marks inMath = new Marks(dummyStudent, "Math005", new BigDecimal("97.00"));
        Marks inHistory = new Marks(dummyStudent, "History007", new BigDecimal("79.00"));

        List<Marks> marks = Arrays.asList(inHistory, inMath, inEnglish);
        Grades grade = new Teacher().generateGrade(marks);
        assertEquals(Grades.VeryGood, grade);
    }
}
