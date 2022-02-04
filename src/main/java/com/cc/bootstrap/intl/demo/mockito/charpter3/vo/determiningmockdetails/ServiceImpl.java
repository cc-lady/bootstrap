package com.cc.bootstrap.intl.demo.mockito.charpter3.vo.determiningmockdetails;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 用来测试时模拟对象还是间谍对象
 * @createTime 2022年02月04日 14:15:00
 */
public class ServiceImpl {
    private final Dependency1 dependency1;
    private final Dependency2 dependency2;
    public ServiceImpl(Dependency1 dependency1, Dependency2
            dependency2) {
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }
    public Dependency1 getDependency1() {
        return dependency1;
    }
    public Dependency2 getDependency2() {
        return dependency2;
    }
}
