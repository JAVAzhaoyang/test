package com.zl.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zl.demo.entity.RolePermission;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    //查询各个角色所拥有的权限
    @Select(" SELECT a2.`code`,a3.`name` FROM t_role_permission a1\n" +
            "LEFT JOIN t_permission a2 ON a1.permission_id=a2.id\n" +
            "LEFT JOIN t_role a3 ON a1.role_id=a3.id ")
    List<Map<String, Object>> findInfo();

}
