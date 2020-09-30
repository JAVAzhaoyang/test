package com.zl.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zl.demo.entity.User;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

public interface UserMapper extends BaseMapper<User> {

}
