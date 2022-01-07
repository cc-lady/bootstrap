package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 教师-生成学生的分数
 * @createTime 2022年01月06日 14:10:00
 */
public class Teacher {
    public Grades generateGrade(List<Marks> marksList) {
        BigDecimal aggregate = BigDecimal.ZERO;
        for (Marks mark : marksList) {
            aggregate = aggregate.add(mark.getMarks());
        }
        BigDecimal percentage = calculatePercent(aggregate,
                marksList.size());
        if (percentage.compareTo(new BigDecimal("90.00")) > 0) {
            return Grades.Excellent;
        }
        if (percentage.compareTo(new BigDecimal("75.00")) > 0) {
            return Grades.VeryGood;
        }
        if (percentage.compareTo(new BigDecimal("60.00")) > 0) {
            return Grades.Good;
        }
        if (percentage.compareTo(new BigDecimal("40.00")) > 0) {
            return Grades.Average;
        }
        return Grades.Poor;
    }

    private BigDecimal calculatePercent(BigDecimal aggregate,
                                        int numberOfSubjects) {
        BigDecimal percent = new BigDecimal(aggregate.
                doubleValue() / numberOfSubjects);
        return percent;
    }
}
