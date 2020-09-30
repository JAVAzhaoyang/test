package com.zl.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zl.demo.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    // 查询用户上次登录时间
    @Select(" SELECT * FROM t_login_log WHERE deleteflag=0 AND user_id=#{uid} ORDER BY createdtime DESC limit 1 ")
    LoginLog getLastLoginTime(String uid);
}
