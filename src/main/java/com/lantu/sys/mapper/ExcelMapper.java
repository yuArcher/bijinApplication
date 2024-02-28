package com.lantu.sys.mapper;

import com.github.pagehelper.Page;
import com.lantu.sys.entity.ExcelEntity;

import java.util.List;
import java.util.Map;

public interface ExcelMapper {
    void insertDate(ExcelEntity excelEntity);

    Page<ExcelEntity> getExcelList(Map<String, Object> map);
}
