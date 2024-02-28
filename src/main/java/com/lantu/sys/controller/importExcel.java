package com.lantu.sys.controller;

import java.io.InputStream;
import java.util.*;

import com.lantu.common.vo.Result;
import com.lantu.sys.entity.ExcelEntity;
import com.lantu.sys.service.ImportExcelData;
import com.lantu.utils.TestExcel;
import com.lantu.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class importExcel {

    @Autowired
    TestExcel testExcel;

    @Autowired
    ImportExcelData importExcelData;

    @PostMapping("/import/excel")
    public Result<?> impportExcelFn(@RequestPart("file")MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        List<List<Object>> list = ExcelUtils.getListByExcel(inputStream,file.getOriginalFilename());
        log.info("list={}",list);
        log.info("file={}",file);

        List<Object> firstRows = new ArrayList<>();
        if (list!=null&&list.size()>0){
            List<Object> list1 = list.get(0);
            firstRows=list.get(1);
            for (int k=4;k<9;k++){
                firstRows.set(k,list.get(2).get(k));
            }
        }
        list.remove(0);
        list.remove(1);
        for (int i=1;i<list.size();i++){
            List<Object> rows = list.get(i);
            ExcelEntity excelEntity = new ExcelEntity();
            for (int j=0;j<rows.size();j++){
                String cellVal = (String) rows.get(j);
                if (!"".equals(rows.get(0)) && rows.get(0) != null) {
                    testExcel.setFieldValueByFieldName(excelEntity,firstRows.get(j).toString().trim(),rows);
                }
            }
        log.info("excelEntity={}",excelEntity);
        importExcelData.insertDate(excelEntity);
        }
        return Result.success(list);
    }

    @PostMapping("/excel/list")
    public Result<?> getRoleList(@RequestBody Map<String,Object> map){

        Map<String, Object> excelList = importExcelData.getExcelList(map);

        return Result.success(excelList);
    }
}