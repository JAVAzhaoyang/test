package com.zl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zl.demo.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限信息表
 */
@Data
@TableName("t_permission")
public class Permission extends BaseEntity implements Serializable, Cloneable,org.apache.shiro.authz.Permission{

    private String name;//操作权限名称

    private String code;//操作权限代码

    @Override
    public boolean implies(org.apache.shiro.authz.Permission permission) {
        return false;
    }
}
