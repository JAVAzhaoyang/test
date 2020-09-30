package com.zl.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.demo.entity.Role;
import com.zl.demo.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    @Autowired
    private RoleMapper mapper;

    public List<Role> getCurrentRole(Map map){
        return mapper.getAll(map);
    }

    public Role getRole(int type){
        String roleName = Role.getRoleName(type);
        Role role = mapper.selectOne(new QueryWrapper<Role>().lambda()
                .eq(Role::getDeleteflag,0)
                .eq(Role::getName,roleName));
        return role;
    }


}
