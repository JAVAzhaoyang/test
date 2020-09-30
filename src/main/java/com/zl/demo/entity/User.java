package com.zl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zl.demo.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户账号登录信息表
 */
@Data
@TableName("t_user")
public class User extends BaseEntity implements Serializable, Cloneable{

    private String loginName;//帐号

    private String name;//名称（昵称或者真实姓名，不同系统不同定义）

    private String password;//密码

    private String salt;//加密密码的盐

    private String orgId;//用户归属机构

    private String memberId;//用户表管理id

    private int failTimes;//失败次数

    private Boolean locked = Boolean.FALSE;//是否锁定

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lockedtime;//被锁日期




}
