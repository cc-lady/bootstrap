package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.dummyvo;

import java.math.BigDecimal;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 学生的分数
 * @createTime 2022年01月06日 14:08:00
 */
public class Marks {
    private final Student student;
    private final String subjectId;
    private final BigDecimal marks;
    public Marks(Student student, String subjectId,
                 BigDecimal marks) {
        this.student = student;
        this.subjectId = subjectId;
        this.marks = marks;
    }

    public Student getStudent() {
        return student;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public BigDecimal getMarks() {
        return marks;
    }
}
