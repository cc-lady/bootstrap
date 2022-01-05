package com.cc.bootstrap.common.util;

import com.cc.bootstrap.common.base.restful.IBaseEnum;
import com.cc.bootstrap.common.enums.GlobalExceptionEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName EnumUtils
 * @Description 枚举工具类
 * @createTime 2021年12月30日 16:04:00
 */
public class EnumUtils {

    /**
     * @Description 将枚举转换成Map对象方便取值 - 仅为了提供思路
     * @author ChenChen
     * @date 2021/12/30
     *
     * @return
     */
    public static Map<String, String> convertToMap() {
        Map<String, String> mapResult = new HashMap<>();

        Arrays.stream(GlobalExceptionEnum.values()).forEach(globalExceptionEnum -> {
            mapResult.put(globalExceptionEnum.getCode(), globalExceptionEnum.getMessage());
        });

        return mapResult;
    }

    /**
     * @Description 反射获取枚举值-仅供了解
     * @author ChenChen
     * @date 2022/1/5
     * @param enumClass
     * @return
     */
    private static Stream<IBaseEnum> generateReflectDataForEnum(Class<? extends IBaseEnum> enumClass)
            throws IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("======================fields===============================");
        Field[] fields = enumClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("======================" + field.getName() + "======================");
            field.setAccessible(true);
            System.out.println("field.getName()" + field.getName());
            /* 类似这样的
             * EXCEPTION_ENUM("EC_0001", "未知异常，请联系项目管理员！"),
             * field.isEnumConstant()为 true
             */
            System.out.println("field.isEnumConstant()" + field.isEnumConstant());
            // 字段打印如下（自定义的枚举是字段）
//            ======================EXCEPTION_ENUM======================
//            field.getName()EXCEPTION_ENUM
//            field.isEnumConstant()true
//                    ======================RUNTIME_EXCEPTION_ENUM======================
//            field.getName()RUNTIME_EXCEPTION_ENUM
//            field.isEnumConstant()true
//                    ======================code======================
//            field.getName()code
//            field.isEnumConstant()false
//                    ======================message======================
//            field.getName()message
//            field.isEnumConstant()false
//                    ======================$VALUES======================
//            field.getName()$VALUES
//            field.isEnumConstant()false
        }

        System.out.println("======================methods===============================");
        Method[] methods = enumClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method.getName()" + method.getName());
            // 取枚举的values方法
            if("values".equals(method.getName())) {

            }
            // 方法打印如下
//            ======================methods===============================
//            method.getName()values
//            method.getName()valueOf
//            method.getName()getMessage
//            method.getName()getCode
        }

        return null;
    }


    /**
     * @Description 真正的工具类 - 将枚举转为Map使用，只要参数 clazz 继承了IbaseEnum
     * @author ChenChen
     * @date 2022/1/5
     * @param clazz
     * @return
     */
    public static Map<String, String> generateConvertToMap(Class<? extends IBaseEnum> clazz) {
        Map<String, String> mapResult = new HashMap<>();

        if(!clazz.isEnum()) {
            throw new UnsupportedOperationException("参数不合法：非枚举类，不支持转换，请检查程序是否有误！");
        }

        // 通过class.getEnumConstants();获取所有的枚举字段和值
        IBaseEnum[] iBaseEnums = clazz.getEnumConstants();
        for (IBaseEnum iBaseEnum : iBaseEnums) {
            mapResult.put(iBaseEnum.getCode(), iBaseEnum.getMessage());
        }
        return mapResult;

    }

    // 测试使用
    public static void main(String[] args) throws InstantiationException,
            IllegalAccessException, InvocationTargetException {
        Map<String, String> mapResult1 = EnumUtils.convertToMap();
        System.out.println(mapResult1);
        // 这里是真正工具调用的地方
        Map<String, String> mapResult2 = EnumUtils.generateConvertToMap(GlobalExceptionEnum.class);
        System.out.println(mapResult2);

        EnumUtils.generateReflectDataForEnum(GlobalExceptionEnum.class);
    }
}
