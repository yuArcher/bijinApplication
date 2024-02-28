package com.lantu.utils;

import cn.afterturn.easypoi.excel.annotation.Excel;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;
import com.lantu.sys.entity.ExcelEntity;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class TestExcel {
    public static void setFieldValueByFieldName(ExcelEntity excelEntity, String fieldName, List<Object> val){
        try {
            Field[] fields = excelEntity.getClass().getDeclaredFields();
            int length = fields.length;
            for (int i=0;i<fields.length;i++){
                Field field = fields[i];
                Excel anotation = field.getAnnotation(Excel.class);
                field.setAccessible(true);
                if (anotation == null) {
                    if (fieldName.equals(field.getName())){
//                        if (field.getType() == Integer.class){
//                            field.set(object, Integer.valueOf(val.toString()));
//                        } else if (field.getType() == Long.class){
//                            field.set(object,Long.valueOf(val.toString()));
//                        }else {
                            field.set(excelEntity,val.toString());
//                        }
//                        return;
                    }
                }else {
                    String name = field.getName();
//                    if (fieldName.equals(field.getName())){
//                        if (field.getType() == Integer.class){
//                            field.set(object, Integer.valueOf(val.toString()));
//                        } else if (field.getType() == Long.class){
//                            field.set(object,Long.valueOf(val.toString()));
//                        }else {
//                            field.set(entity,val.toString());
                    switch (field.getName()) {
                        case "id":
                            excelEntity.setId(val.get(i).toString());
                            break;
                        case "code":
                            excelEntity.setCode(val.get(i).toString());
                            break;
                        case "shipname":
                            excelEntity.setShipname(val.get(i).toString());
                            break;
                        case "shipnumber":
                            excelEntity.setShipnumber(val.get(i).toString());
                            break;
                        case "oceanfield":
                            excelEntity.setOceanfield(val.get(i).toString());
                            break;
                        case "stand":
                            excelEntity.setStand(val.get(i).toString());
                            break;
                        case "xcoordinate":
                            excelEntity.setXcoordinate(val.get(i).toString());
                            break;
                        case "ycoordinate":
                            excelEntity.setYcoordinate(val.get(i).toString());
                            break;
                        case "address":
                            excelEntity.setAddress(val.get(i).toString());
                            break;
                        case "depth":
                            excelEntity.setDepth(val.get(i).toString());
                            break;
                        case "xinlength":
                            excelEntity.setXinlength(val.get(i).toString());
                            break;
                        case "ataddress":
                            excelEntity.setAtaddress(val.get(i).toString());
                            break;
                        case "status":
                            excelEntity.setStatus(val.get(i).toString());
                            break;
                        case "mark":
                            excelEntity.setMark(val.get(i).toString());
                            break;
                    }
//                        }
//                        return;
//                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
