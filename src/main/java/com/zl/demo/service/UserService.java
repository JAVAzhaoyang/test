package com.zl.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.zl.demo.entity.User;
import com.zl.demo.mapper.UserMapper;
import com.zl.demo.utils.Digests;
import com.zl.demo.utils.Encodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService extends ServiceImpl<UserMapper, User> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    @Autowired
    private UserMapper mapper;

    //通过用户昵称查询信息
    public User findUserByLoginName(String loginName){
        return mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getLoginName,loginName));
    }

    public int userDelete(String id){
        try {
            User user = mapper.selectById(id);
            user.setDeleteflag(1);
            updateById(user);
            logger.info("删除人员,人员信息{}", user.getName());
            return 0;
        } catch (Exception e) {
            logger.error("删除人员失败,失败原因{}", e.getMessage());
            return 1;
        }
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    public void register(User user) {
        entryptPassword(user);
        save(user);
    }


}
