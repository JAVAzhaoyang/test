package com.zl.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zl.demo.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getAll(Map map);

}
