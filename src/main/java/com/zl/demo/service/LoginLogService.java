package com.zl.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.demo.entity.LoginLog;
import com.zl.demo.entity.User;
import com.zl.demo.mapper.LoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LoginLogService extends ServiceImpl<LoginLogMapper, LoginLog> {

    @Autowired
    private LoginLogMapper mapper;

    // 查询用户上次登录时间
    public LoginLog getLastLoginTime(String userId){
        return mapper.getLastLoginTime(userId);
    }

    @Async
    public void addLoginLog(User user, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String agent = request.getHeader("User-Agent");
        LoginLog log = new LoginLog();
        log.setBrowser(agent);
        log.setIp(ip);
        log.setUserId(user.getId());
        log.setCreatedtime(new Date());
        log.setUpdatedtime(new Date());
        save(log);
    }
}
