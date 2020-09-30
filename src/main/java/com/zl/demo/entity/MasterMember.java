package com.zl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zl.demo.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 人员组织架构关联表
 */
@Data
@TableName("t_master_member")
public class MasterMember extends BaseEntity implements Serializable, Cloneable {

    private int type;

    private String createId;//使用添加权限的用户id

    private String memberId;//用户id

    private String orgId;//公司组织架构
}
