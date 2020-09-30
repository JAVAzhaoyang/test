package com.zl.demo.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zl.demo.base.BaseController;
import com.zl.demo.entity.Car;
import com.zl.demo.service.CarService;
import com.zl.demo.utils.DateUtils;
import com.zl.demo.utils.MathUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * excel导入
 * Created by zhaoyang on 2020.
 *
 */
@Controller
public class ImportController extends BaseController {

    private Logger bizLog = LoggerFactory.getLogger("BUSINESS");

    @Value("${file.uploadFolder}")
    private String filePath;

    @Autowired
    private CarService carService;


    @RequestMapping(value = "/car/import")
    @ResponseBody
    public Map<String, Object> carImport(HttpServletResponse response, ServletRequest request) throws Exception {
        bizLog.info("当前操作人{},开始导入渠道", getCurrentUser().getName());
        Map<String, Object> result = Maps.newHashMap();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        if (fileMap.keySet().size() == 0) {
            result.put("retCode", "1");
            result.put("retMsg", "请选择上传文件");
            return result;
        }
        List<Map<String, Object>> list = Lists.newArrayList();
        List<Map<String, Object>> error = Lists.newArrayList();
        int errNum=0;
        for (Iterator<String> iterator = fileMap.keySet().iterator(); iterator.hasNext();) {
            String k = iterator.next();
            MultipartFile file = fileMap.get(k);
            String fname = file.getOriginalFilename();
            String[] farr = fname.split("\\.");
            String suff = "";
            if (farr.length > 1) {
                suff = farr[farr.length - 1].toLowerCase();
            }
            String date = DateUtils.getYear(new Date()) + File.separator + DateUtils.getMoon(new Date())
                    + File.separator + DateUtils.getDay(new Date());// 获取上传的时间，并创建目录
            String path = filePath + date;// 存储路径
            File p = new File(path);
            if (!p.exists()) {
                p.mkdirs();
            }
            String fileName = UUID.randomUUID().toString() + (StringUtils.isEmpty(suff) ? "" : ("." + suff));
            File f = new File(path + File.separator + fileName);
            try {
                FileCopyUtils.copy(file.getBytes(), f);
            } catch (IOException e) {
                e.printStackTrace();
                result.put("retCode", "2");
                result.put("retMessage", "文件导入失败,请重新导入");
                return result;
            }
            String absolutePath = f.getAbsolutePath();

            // 读取excel
            if (!Strings.isNullOrEmpty(absolutePath)) {
                ImportParams params = new ImportParams();
                params.setTitleRows(0);
                params.setHeadRows(1);
                list = ExcelImportUtil.importExcel(new File(absolutePath), Map.class, params);
                if (!org.apache.commons.collections4.CollectionUtils.isEmpty(list)) {// excel不为空的话
                    bizLog.info("当前操作人{},渠道有{}条记录", getCurrentUser().getName(), list.size());
                    for (Map<String, Object> map : list) {
                        String name = MapUtils.getString(map, "姓名").trim();
                        String age = MapUtils.getString(map, "年龄").trim();
                        String phoneNumber = MapUtils.getString(map, "手机号码").trim();
                        String licenseno = MapUtils.getString(map, "车牌").trim();
                        String cost = MapUtils.getString(map, "维修金额").trim();

                        Car car = new Car();
                        car.setName(name);
                        car.setAge(MathUtils.StrToInteger(age));
                        car.setPhoneNumber(phoneNumber);
                        car.setLicenseno(licenseno);
                        car.setCost(MathUtils.StrToBigDecimal(cost));
                        carService.save(car);
                    }
                    if (!CollectionUtils.isEmpty(error)) {
                        // 如果有错误信息,将错误信息保存起来,返回出去
                        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
                        entity.add(new ExcelExportEntity("姓名", "姓名", 20));
                        entity.add(new ExcelExportEntity("年龄", "年龄", 20));
                        entity.add(new ExcelExportEntity("手机号码", "手机号码", 30));
                        entity.add(new ExcelExportEntity("车牌", "车牌", 30));
                        entity.add(new ExcelExportEntity("维修金额", "维修金额", 30));
                        entity.add(new ExcelExportEntity("错误信息", "错误信息", 30));
                        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entity, error);
                        String failName = System.currentTimeMillis() + "_error.xlsx";
                        String failPath = path + File.separator + failName;
                        File failFile = new File(failPath);
                        if (!failFile.exists()) {
                            failFile.createNewFile();
                        }
                        FileOutputStream fos = new FileOutputStream(failFile);
                        workbook.write(fos);
                        fos.close();
                        result.put("retCode", "1");
                        result.put("retData", failName);
                        result.put("retMessage",
                                "成功导入" + (list.size() - errNum) + "条,导入失败：" + errNum + "条。请下载清单查看失败原因");
                        return result;

                    }
                }else {
                    result.put("retCode", "2");
                    result.put("retMessage", "未读取到数据，请重新上传");
                    return result;
                }
            }
        }
        result.put("retCode", "0");
        result.put("retMessage", "成功导入" + list.size() + "条");
        return result;

    }


    @RequestMapping(value = "/error/export")
    public void errordownload(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String date = DateUtils.getYear(new Date()) + File.separator + DateUtils.getMoon(new Date()) + File.separator
                + DateUtils.getDay(new Date());// 获取上传的时间，并创建目录
        String path = filePath + date;// 存储路径
        String fPath = path + File.separator+id;
        String fileName = "导入失败清单.xls";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));

        FileInputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(fPath);
            out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException ex) {
            }
            try {
                if (out != null)
                    out.close();
            } catch (IOException ex) {
            }
        }
    }




}
