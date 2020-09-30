package com.zl.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.demo.entity.UserRole;
import com.zl.demo.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {

    @Autowired
    private  UserRoleMapper mapper;
}
