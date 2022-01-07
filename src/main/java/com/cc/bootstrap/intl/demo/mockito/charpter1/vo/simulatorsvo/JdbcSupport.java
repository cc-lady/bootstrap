package com.cc.bootstrap.intl.demo.mockito.charpter1.vo.simulatorsvo;

import java.util.List;
import java.util.Map;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description JdbcSupport - jdbc支持
 * @createTime 2022年01月06日 16:09:00
 */
public class JdbcSupport {
    /*
     * 批量更新
     * 它返回 一个整数数组。每个数组索引都包含0或1。如果返回的值为1，则表示数 据库更新成功，0表示没有更新。
     * 因此，如果我们只传递一个学生对象进行 更新，并且如果更新成功，则数组将只包含一个整数为1；
     * 但是，如果失败 ，则数组将包含0。
     * @return
     */
    public int[] batchUpdate(String sql, List<Map<String, Object>> params){
        //original db access code is hidden
        return null;
    }
}
