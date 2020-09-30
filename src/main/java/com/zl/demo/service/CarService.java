package com.zl.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.demo.entity.Car;
import com.zl.demo.mapper.CarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarService extends ServiceImpl<CarMapper, Car> {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private CarMapper mapper;

    public Car findUserByName(String name){
        return mapper.selectOne(new QueryWrapper<Car>().lambda().eq(Car::getName,name));
    }

    public List<Car> findByCarList(String name,String licenseno){
        return mapper.selectList(new QueryWrapper<Car>().lambda()
             /*   .eq(Car::getName,name)
                .eq(Car::getLicenseno,licenseno)*/
                .eq(Car::getDeleteflag,0));
    }


    @Transactional(readOnly = false)
    public int carDelete(String id){
        try {
            Car car = mapper.selectById(id);
            car.setDeleteflag(1);
            updateById(car);
            logger.info("删除人员,人员信息{}", car.getName());
            return 0;
        } catch (Exception e) {
            logger.error("删除人员失败,失败原因{}", e.getMessage());
            return 1;
        }
    }

}
