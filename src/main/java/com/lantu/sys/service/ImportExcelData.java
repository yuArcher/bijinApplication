package com.lantu.sys.service;

import com.lantu.sys.entity.ExcelEntity;

import java.util.List;
import java.util.Map;

public interface ImportExcelData {
    void insertDate(ExcelEntity excelEntity);

    Map<String, Object> getExcelList(Map<String, Object> map);
}
