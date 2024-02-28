package com.lantu.utils;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.Types.BOOLEAN;
import static java.sql.Types.NUMERIC;
import static javax.xml.xpath.XPathConstants.STRING;

@Component
public class ExcelUtils {
    private final static String excel2003L = ".xls";
    private final static String excel2007U = ".xlsx";

    public static List<List<Object>> getListByExcel(InputStream in,String fileName) throws Exception {
        List<List<Object>> list = null;

        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作簿为空");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<Object>>();
        for (int i = 0;i<work.getNumberOfSheets();i++){
            sheet = work.getSheetAt(i);
            if (sheet==null){continue;}

            for (int j=sheet.getFirstRowNum();j<=sheet.getLastRowNum();j++){
                row = sheet.getRow(j);
                if (row==null){continue;}

                List<Object> li=new ArrayList<Object>();
                for (int y = row.getFirstCellNum();y< row.getLastCellNum();y++){
                    cell = row.getCell(y);
                    li.add(getValue(cell));
                }
                list.add(li);
            }
        }
        return list;
    }

    public static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
//        if (excel2003L.equals(fileType)){
//            wb = new HSSFWorkbook(inStr);
//        } else if (excel2007U.equals(fileType)){
//            wb = new XSSFWorkbook(inStr);
//        } else {
//            throw new Exception("解析的文件格式有误");
//        }
        wb = WorkbookFactory.create(inStr);
        return wb;
    }

    public static String getValue(Cell cell){
        String value = "";
        cell.setCellType(Cell.CELL_TYPE_STRING);
        if (null == cell) {
            return value;
        }
        switch (cell.getCellType()){
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)){
                    Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    value = format.format(date);
                } else {
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    if (null != value && !"".equals(value.trim())){
                        String[] item = value.split("[.]");
                        if (1<item.length&&"0".equals(item[1])){
                            value=item[0];
                        }
                    }
                }
                break;
//            case STRING:
//                value = cell.getStringCellValue();
//                break;
            case BOOLEAN:
                value = " "+cell.getBooleanCellValue();
                break;
            default:
                value = cell.getStringCellValue();
        }
        if ("null".endsWith(value.trim())){
            value = "";
        }
        return value;
    }
}
