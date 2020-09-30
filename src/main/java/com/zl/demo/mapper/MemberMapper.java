package com.zl.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zl.demo.entity.Member;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberMapper extends BaseMapper<Member> {

    //根据姓名或人员代码查询用户信息
    @Select(" SELECT * FROM t_member WHERE (name LIKE #{name} OR comcode=#{comcode}) AND deleteflag=0 ")
    List<Member> findByNameLikeOrCodeAndDeleteFlag(String name, String comcode);

}
