package com.zl.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.demo.entity.Org;
import com.zl.demo.mapper.OrgMapper;
import net.sf.ehcache.search.expression.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgService extends ServiceImpl<OrgMapper, Org> {

    @Autowired
    private OrgMapper mapper;

    public Org selectByComcode(String comcode){
        return mapper.selectOne(new QueryWrapper<Org>().lambda()
                .eq(Org::getComcode,comcode)
                .eq(Org::getDeleteflag,0));
    }

    //查询机构最高等级的省公司
    public Org selectLevelTop(){
        return mapper.selectOne(new QueryWrapper<Org>().lambda().eq(Org::getLevel,0).eq(Org::getDeleteflag,0));
    }

    //查询公司下的所有子公司
    public List<Org> getSubset(String pcode){
        return mapper.selectList(new QueryWrapper<Org>().lambda()
                .eq(Org::getDeleteflag,0).eq(Org::getPcode,pcode).orderByAsc(Org::getComcode));
    }


}
