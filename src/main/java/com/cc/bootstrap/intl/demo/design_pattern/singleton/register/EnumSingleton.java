package com.cc.bootstrap.intl.demo.design_pattern.singleton.register;

/**
 * @Description: 枚举式单例：利用枚举的语法特殊性（无法通过构造函数创建实例等）
 * 1，在readObject0（）中调用了readEnum（）方法，来看readEnum（）方法的代码实现：
 * 枚举类型其实通过类名和类对象类找到一个唯一的枚举对象。因此，枚举对象不可能被类加载器加载多次。（序列化不会破坏枚举式单例）
 * 2.在 newInstance（）方法中做了强制性的判断，如果修饰符是Modifier.ENUM枚举类型，则直接抛出异常。（反射不会破坏枚举式单例）
 *
 * 注册式单例：1.枚举式单例；2.容器式单例
 * @author: ChenChen
 * @date: 2025-10-10 15:31
 */
public enum EnumSingleton {
    INSTANCE;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    // 提供全局唯一访问点
    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
