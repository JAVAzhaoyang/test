package com.zl.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.demo.entity.Permission;
import com.zl.demo.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> {

    @Autowired
    private PermissionMapper mapper;

    public List<Permission> getAll(String id){
        return mapper.selectList(new QueryWrapper<Permission>().lambda().eq(Permission::getId,id));
    }

}
