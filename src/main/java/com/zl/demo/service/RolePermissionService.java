package com.zl.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.demo.entity.RolePermission;
import com.zl.demo.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RolePermissionService extends ServiceImpl<RolePermissionMapper, RolePermission> {

    @Autowired
    private RolePermissionMapper mapper;

    public List<RolePermission> findList(String roleId){
        return mapper.selectList(new QueryWrapper<RolePermission>().lambda().eq(RolePermission::getRoleId,roleId));
    }

    public List<Map<String, Object>> findInfo(){
        return mapper.findInfo();
    }
}
