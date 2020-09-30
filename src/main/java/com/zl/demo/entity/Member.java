package com.zl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zl.demo.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 人员信息表
 */
@Data
@TableName("t_member")
public class Member extends BaseEntity implements Serializable, Cloneable {

    private String loginName;//帐号

    private String name;//名称（昵称或者真实姓名，不同系统不同定义）

    private String mobile;//手机号

    private String email;//邮箱

    private String city;//人员地址

    private String comcode;//用户归属机构代码

    private String orgId;//用户归属机构
}
