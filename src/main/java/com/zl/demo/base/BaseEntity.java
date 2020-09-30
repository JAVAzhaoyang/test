package com.zl.demo.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @description:基本属性
 * @author: zhaoyang
 * @date: 2020
 */
@Data
public class BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdtime;


    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedtime;

    /**
     * 是否有效
     */
    private Integer deleteflag = 0;

}
