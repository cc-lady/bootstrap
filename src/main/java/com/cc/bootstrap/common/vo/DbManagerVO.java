package com.cc.bootstrap.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description: 分行数据库管理员信息VO类
 * @author: ChenChen
 * @date: 2023/8/4 16:17
 */
@Data
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DbManagerVO {
    //分行号
    private String department;

    //分行名称
    private String departmentName;

    //分行数据库管理员用户
    private String dbManagerUser;
}
