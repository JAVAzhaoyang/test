package com.zl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户,角色关系表
 */
@Data
@TableName("t_user_role")
public class UserRole {

    private String userId;//登录用户id (非用户id,  用户表与登录信息表关联,为了减少繁琐查询步骤,使用登录id,非原人员id)

    private String roleId;//角色id

}
