package com.cc.bootstrap.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.cc.bootstrap.common.base.entity.Schema;

/**
 * ObjectFactory
 * 对象转换等操作工具类
 */
public class ObjectFactory {

    /**
     * 将实体类List为VOList
     * @param schemaList
     * @param voClass
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> List<T> toVOList(List<? extends Schema> schemaList, Class<T> voClass) throws InstantiationException, IllegalAccessException {
        List<T> returnList = new ArrayList<>();
        if (null != schemaList){
            Iterator<Schema> iterator = (Iterator<Schema>)schemaList.iterator();
            while(iterator.hasNext()){
                Schema schema = iterator.next();
                returnList.add(toVo(schema, voClass));
            }
        }

        return returnList;
    }

    /**
     * 将实体类转换为Vo
     * @param schema
     * @param voClass
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T toVo(Schema schema,  Class<T> voClass) throws IllegalAccessException, InstantiationException {
        T t = voClass.newInstance();
        BeanUtils.copyProperties(schema, t);
        return t;
    }
}
