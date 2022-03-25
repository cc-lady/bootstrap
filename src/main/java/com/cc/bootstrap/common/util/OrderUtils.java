package com.cc.bootstrap.common.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cc.bootstrap.common.base.entity.AbstractEntity;
import com.cc.bootstrap.common.schema.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @Description: 排序工具类
 * @author: ChenChen
 * @date: 2022/3/25 16:09
 */
public class OrderUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(OrderUtils.class);

    /**
     * @Description 根据实体类字段名获取字段上@TableField注解值，即数据库字段名。
     * @param orderField 与实体类字段属性一致。
     * @param clazz
     * @author ChenChen
     * @return java.lang.String
     * @date 2022/3/25 16:16
     */
    public static String getTableFieldAnnotationValue(String orderField, Class<? extends AbstractEntity> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.getName().equals(orderField))
                .map(field -> {
                    TableField annotation = field.getAnnotation(TableField.class);
                    if(null != annotation && annotation.exist()) {
                        return annotation.value();
                    }
                    return null;
                }).findFirst().get();
    }

    public static void main(String[] args) {
        // 正确示范
        String orderField = "userName";
        Class<? extends AbstractEntity> clazz = User.class;
        String userName_tableField = OrderUtils.getTableFieldAnnotationValue(orderField, clazz);
        LOGGER.info("实体类 User 的属性 [{}] 对应的数据库字段名为 [{}]", orderField, userName_tableField);

        // 错误示范
        String orderField_error = "userName1";
        try {
            String userName_tableField1 = OrderUtils.getTableFieldAnnotationValue(orderField_error, clazz);
            LOGGER.info("实体类 User 的属性 [{}] 对应的数据库字段名为 [{}]", orderField, userName_tableField1);
        } catch (NoSuchElementException noSuchElementException) {
            LOGGER.error("实体类 User 的属性 [{}] 无法获取到对应的数据库字段名！", orderField_error, noSuchElementException);
        }
    }
}
