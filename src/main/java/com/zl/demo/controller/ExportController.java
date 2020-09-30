package com.zl.demo.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.zl.demo.entity.Car;
import com.zl.demo.service.CarService;
import com.zl.demo.utils.DateUtils;
import com.zl.demo.utils.easypoi.entity.export.CarExport;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * excel导出
 * Created by zhaoyang on 2020.
 *
 */
@Controller
@RequestMapping(value = "/export")
public class ExportController {

    private  static Logger bizLog = LoggerFactory.getLogger("BUSINESS");

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/carExporter")
    public void carExporter(@RequestParam(required = false) String name,
                            @RequestParam(required = false) String startDate,
                            @RequestParam(required = false) String endDate,
                            HttpServletResponse response,
                            ServletRequest request) throws IOException {

        List<Car> carList = carService.findByCarList(name,startDate);
        if(carList!=null){
            Workbook workbook = null;
            ExportParams params = new ExportParams("车辆信息", "车辆信息", ExcelType.XSSF);
            List<CarExport> list = new ArrayList<CarExport>();
            for (Car car:carList) {
                CarExport carExport = new CarExport();
                carExport.setName(car.getName());
                carExport.setAge(car.getAge());
                carExport.setStartdate(DateUtils.formatDateTime(car.getStartdate()));
                carExport.setEnddate(DateUtils.formatDateTime(car.getEnddate()));
                carExport.setLicenseno(car.getLicenseno());
                carExport.setCost(car.getCost());
                list.add(carExport);
            }
            workbook = ExcelExportUtil.exportExcel(params, CarExport.class, list);
            String fileName = "车辆信息.xlsx";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));

            OutputStream out = null;
            out = response.getOutputStream();
            workbook.write(out);
            out.close();

        }



    }
}
