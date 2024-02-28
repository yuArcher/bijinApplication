package com.lantu.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("exceldata")
public class ExcelEntity {

    @Excel(name = "序号")
    private String id;

    @Excel(name = "样品编号")
    private String code;

    @Excel(name = "调查船")
    private String  shipname;

    @Excel(name = "航次")
    private String shipnumber;

    @Excel(name = "海域")
    private String oceanfield;

    @Excel(name = "站位")
    private String stand;

    @Excel(name = "X坐标")
    private String xcoordinate;

    @Excel(name = "Y坐标")
    private String ycoordinate;

    @Excel(name = "详细地址")
    private String address;

    @Excel(name = "结束深度")
    private String depth;

    @Excel(name = "心长")
    private String xinlength;

    @Excel(name = "存放位置")
    private String ataddress;

    @Excel(name = "保存状况")
    private String status;

    @Excel(name = "备注")
    private String mark;

}
