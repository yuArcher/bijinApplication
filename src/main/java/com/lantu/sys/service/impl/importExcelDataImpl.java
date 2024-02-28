package com.lantu.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Page;
import com.lantu.sys.entity.ExcelEntity;
import com.lantu.sys.mapper.ExcelMapper;
import com.lantu.sys.service.ImportExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nieyb
 * @since 2023-08-14
 */
@Service
public class importExcelDataImpl  implements ImportExcelData {

    @Autowired
    ExcelMapper excelMapper;

    @Override
    public void insertDate(ExcelEntity excelEntity) {
        excelMapper.insertDate(excelEntity);
    }

    @Override
    public Map<String, Object> getExcelList(Map<String, Object> map) {

        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        map.replace("oceanfield","%"+map.get("oceanfield")+"%");
        map.replace("shipname","%"+map.get("shipname")+"%");
        map.replace("code","%"+map.get("code")+"%");
        PageHelper.startPage(pageNo, pageSize);
        Page<ExcelEntity> listPage = excelMapper.getExcelList(map);
        long total = listPage.getTotal();
        Map<String,Object> data=new HashMap<>();
        data.put("list",listPage.getResult());
        data.put("total",listPage.getTotal());
        return data;
    }
}
