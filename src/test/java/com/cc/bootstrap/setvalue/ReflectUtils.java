package com.cc.bootstrap.setvalue;

import com.cc.bootstrap.intl.demo.file.upload.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;

/**
 * @Description: 反射为字段赋值
 * @author: ChenChen
 * @date: 2022/3/25 11:14
 */
public class ReflectUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);


    public static void setField(Object object, String fieldName, String value)
            throws NoSuchFieldException, IllegalAccessException {
        // 根据字段名获取反射字段对象
        Field field = object.getClass().getDeclaredField(fieldName);

        // 校验，对于有@Value的字段才可以修改（尽量使用模拟解决）
        if(!field.isAnnotationPresent(Value.class)) {
            LOGGER.error("仅支持@Value的注释的字段！请检查。 fieldName [{}]", fieldName);
            throw new UnsupportedOperationException("仅支持@Value的注释的字段！请检查。");
        }

        field.setAccessible(true);
        field.set(object, value);
    }

    // 查看对象的字段名
    private void testFieldValue() {
        FileUploadService fileUploadService = new FileUploadService();
        Field[] fields = fileUploadService.getClass().getDeclaredFields();
        for (Field field : fields) {
            LOGGER.info("field [{}]", field);
            field.setAccessible(true);
            String fieldName = field.getName();
            if(field.isAnnotationPresent(Value.class)) {
                LOGGER.info("field with annotation Value [{}]", fieldName);//fileUploadPath
            }
        }
    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // data
        String fieldName = "fileUploadPath";
        String value = "upload";
        // test
        FileUploadService fileUploadService = new FileUploadService();
        ReflectUtils.setField(fileUploadService, "fileUploadPath", "upload");
        LOGGER.info("reflectUtils.setField success.");//fileUploadPath
    }

}
