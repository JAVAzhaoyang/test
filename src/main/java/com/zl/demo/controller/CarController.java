package com.zl.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.zl.demo.base.BaseController;
import com.zl.demo.entity.Car;
import com.zl.demo.mapper.CarMapper;
import com.zl.demo.service.CarService;
import com.zl.demo.utils.DateUtils;
import com.zl.demo.utils.MathUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CarController extends BaseController {

    public static final String CURRENT_USER = "CURRENT_USER";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CarService carService;

    /**
     * 查询显示
     * @param pageNumber
     * @param pageSize
     * @param name
     * @return
     */
    @RequestMapping("/select/carInfo")
    @ResponseBody
    public Map<Object, Object> carInfo(@RequestParam(defaultValue = "1") int pageNumber,
                                       @RequestParam(defaultValue = "20") int pageSize,
                                       @RequestParam(required = false) String name){

        Page page = new Page<Car>(pageNumber, pageSize);
        LambdaQueryWrapper<Car> queryWrapper = new QueryWrapper<Car>().lambda();
        queryWrapper.eq(Car::getDeleteflag,0).orderByDesc(Car::getCreatedtime);
        if(!Strings.isNullOrEmpty(name)){
            queryWrapper.eq(Car::getName, name);
        }
        IPage iPage = carService.page(page, queryWrapper);
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("total", iPage.getTotal());
        map.put("totalPages", iPage.getPages());
        map.put("currentPage", pageNumber);
        map.put("rows", iPage.getRecords());
        return map;
    }

    @RequestMapping(value = "/insert/car")
    @ResponseBody
    @RequiresPermissions(value = {"insert","*"},logical = Logical.OR)//权限管理;
    public Map<String, Object> carInsert(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String licenseno,
            @RequestParam(required = false) String cost,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String startdate,
            @RequestParam(required = false) String enddate) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            Car car = new Car();
            car.setCreatedtime(new Date());
            car.setUpdatedtime(new Date());
            car.setName(name);
            car.setPhoneNumber(phoneNumber);
            car.setLicenseno(licenseno);
            car.setCost(MathUtils.StrToBigDecimal(cost));
            car.setAge(MathUtils.StrToInteger(age));
            if(!Strings.isNullOrEmpty(startdate)){
                car.setStartdate(DateUtils.StrToDate(startdate));
            }
            if(!Strings.isNullOrEmpty(enddate)){
                car.setEnddate(DateUtils.StrToDate(enddate));
            }

            logger.info("当前操作人: {}，添加人员，姓名：{}", getCurrentUser().getName(), car.getName());
            carService.save(car);
        } catch (Exception e) {
            logger.error("添加人员出错：{}【{}】,错误原因：{}", name,e.getMessage());
            map.put("retCode", "1");
            map.put("retMessage", e.getMessage());
            return map;
        }
        map.put("retCode", "0");
        map.put("retMessage", "添加成功");
        return map;
    }


    /**
     * 删除信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/car")
    @ResponseBody
    @RequiresPermissions(value = {"delete","*"},logical = Logical.OR)//权限管理;
    public int carDelete(@RequestParam String id) {
        logger.info("【当前操作人：{}】删除人员,人员ID{}", getCurrentUser().getName(), id);
        int status = carService.carDelete(id);
        return status;
    }


    @RequestMapping(value = "/view/car")
    @ResponseBody
    public Map<String, Object> carView(@RequestParam String id) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            Car car = carService.getById(id);
            logger.info("【当前操作人：{}】查看人员信息,人员ID{}", getCurrentUser().getName(), id);
            map.put("id", car.getId());
            map.put("name", car.getName());
            map.put("age", car.getAge());
            map.put("licenseno", car.getLicenseno());
            map.put("cost", car.getCost());
            map.put("phoneNumber", car.getPhoneNumber());
            map.put("startdate", DateUtils.formatDate(car.getStartdate()));
            map.put("enddate", DateUtils.formatDate(car.getEnddate()));
        } catch (Exception e) {
            logger.error("【当前操作人：{}】查看人员信息失败,人员ID{}，失败原因{}", getCurrentUser().getName(), id, e.getMessage());
            System.out.println(e.getMessage());
            return map;
        }
        return map;
    }


    /**
     * 根据多个值设置权限
     * @RequiresPermissions(value = {"elevator:view", "onlineMonitoring:view"}, logical = Logical.OR)
     *
     * Logical.OR是指value 中的权限任选其一
     *
     * Logical.AND是指value 中的权限都要有，默认为and
     */

    /**
     * 信息修改
     * @param id
     * @param name
     * @param age
     * @param licenseno
     * @param cost
     * @param phoneNumber
     * @param startdate
     * @param enddate
     * @return
     */
    @RequestMapping(value = "/update/car")
    @ResponseBody
    @RequiresPermissions(value = {"update","*"},logical = Logical.OR)//权限管理;
    public Map<String, Object> carUpdate(@RequestParam(required = false) String id,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) String age,
                                               @RequestParam(required = false) String licenseno,
                                               @RequestParam(required = false) String cost,
                                               @RequestParam(required = false) String phoneNumber,
                                               @RequestParam(required = false) String startdate,
                                               @RequestParam(required = false) String enddate
                                             ) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            Car car = carService.getById(id);
            logger.info("【当前操作人: {}】编辑人员名单信息,原来的名单,姓名{}", getCurrentUser().getName(), car.getName());
            car.setName(name);
            car.setAge(MathUtils.StrToInteger(age));
            car.setPhoneNumber(phoneNumber);
            car.setLicenseno(licenseno);
            car.setCost(MathUtils.StrToBigDecimal(cost));
            car.setStartdate(DateUtils.StrToDate(startdate));
            car.setEnddate(DateUtils.StrToDate(enddate));
            carService.updateById(car);
            logger.info("【当前操作人: {}】编辑人员名单信息,更新后的人员名单信息,姓名{}，代码{}", getCurrentUser().getName(), car.getName());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改人员名单信息出错：{},错误原因：{}", name, e.getMessage());
            map.put("retCode", "1");
            map.put("retMessage", e.getMessage());
            return map;
        }
        map.put("retCode", "0");
        map.put("retMessage", "信息修改成功");
        return map;
    }
}
