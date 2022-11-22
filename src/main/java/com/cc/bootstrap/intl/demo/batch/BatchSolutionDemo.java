package com.cc.bootstrap.intl.demo.batch;

/**
 * @Description: 分页处理数据发生的数据遗漏现象 -- 批处理思路解决
 * 用批处理的方式解决
 *
 * 详情：我们分页查询后，每页查询的list数据进行处理，最后事务增删改，会影响下一次循环时分页查询的结果
 * 比如：对用户表初始0新老用户标记进行处理，查询有300页数据
 * count = searchZeroUserCount();//300
 * for(int i = 1;i<=count;i++){
 *     // 查询当前页数据
 *     list = searchZeroPage(i,500);//i=2，即第二页时发现，此时的page数据发生了变化，这样到最后会发现总数处理不对，漏数据了
 *     // 处理。。。。。。
 *     // 将此部分处理后的数据，刷为1-老用户
 *     update(list);
 * }
 *
 * 解决：使用批处理思路
 * for(int i = 1;i<=count;i++){
 *       // 查询当前页数据
 *      list = searchZeroPage(1,500);//i=2，也查第一页数据，即排除事务对查询的影响，一批一批地处理数据
 *       // 处理。。。。。。
 *       // 将此部分处理后的数据，刷为1-老用户
 *      update(list);
 *   }
 * @author: ChenChen
 * @date: 2022/11/22 14:10
 */
public class BatchSolutionDemo {
}
