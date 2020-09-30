package com.zl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zl.demo.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录日志表
 *
 *
 */
@Data
@TableName("t_login_log")
public class LoginLog extends BaseEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = -2869730500629030430L;

    private String ip;//登录ip地址

    private String browser;//电脑型号

    private String userId;//登录用户id

}
