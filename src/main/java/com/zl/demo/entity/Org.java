package com.zl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zl.demo.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 公司组织架构
 */
@Data
@TableName("t_org")
public class Org extends BaseEntity implements Serializable, Cloneable{

    private String name; //组织名称
    private String comcode;//组织代码
    private String pcode;//父类组织代码
    private int level; // 分为省-市-县支-团队四级 0：省 1：市区 2：县 3：团队


}
