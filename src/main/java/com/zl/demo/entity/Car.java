package com.zl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zl.demo.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_car")
public class Car extends BaseEntity implements Serializable, Cloneable {

    private String name;

    private int age;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enddate;

    private String phoneNumber;//手机号码

    private String licenseno;//车牌号

    private BigDecimal cost;//交易金额

}
