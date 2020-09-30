package com.zl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色,权限信息关系表
 */
@Data
@TableName("t_role_permission")
public class RolePermission {

    private String permissionId;//操作权限id

    private String roleId;//角色信息id
}
