package com.zl.demo.utils.easypoi.entity.export;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhaoyang on 2020. 车辆信息下载
 */
@Data
public class CarExport {

    @Excel(name = "姓名", isImportField = "true",width=15)
    private String name;

    @Excel(name = "年龄", isImportField = "true",width=15)
    private int age;

    @Excel(name = "开始时间", isImportField = "true",width=30)
    private String startdate;

    @Excel(name = "结束时间", isImportField = "true",width=30)
    private String enddate;

    @Excel(name = "车牌号", isImportField = "true",width=15)
    private String licenseno;

    @Excel(name = "交易金额", isImportField = "true",width=15)
    private BigDecimal cost;
}
