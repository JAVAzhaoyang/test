package com.zl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zl.demo.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色信息表
 */
@Data
@TableName("t_role")
public class Role extends BaseEntity implements Serializable, Cloneable{

    private String name;//角色名称

    private Integer insertsign = 1;//添加权限标识 0代表拥有权限;1代表未拥有权限

    private Integer deletesign = 1;//删除权限标识

    private Integer updatesign = 1;//修改权限标识



    public static final String getRoleName(int type) {
        String name = "";
        if (type == 0) {
            name = "系统管理员";
        } else if (type == 1) {
            name = "业务岗";
        } else if (type == 2) {
            name = "运维岗";
        }
        return name;
    }
}
