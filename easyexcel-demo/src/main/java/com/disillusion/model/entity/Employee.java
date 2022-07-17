package com.disillusion.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import lombok.Data;

@Data
@HeadRowHeight(value = 35) // 表头行高
@ContentRowHeight(value = 25) // 内容行高
@ColumnWidth(value = 50) // 列宽
public class Employee implements Serializable{
    @ExcelProperty(value = {"学生信息","学生编号"},order = 1)
    // @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = {"学生信息","学生姓名"},order = 2)
    private String name;

    @ExcelProperty(value = {"学生薪水"},order = 4)
    private Double salary;

    @ExcelProperty(value = {"学生信息","学生生日"},order = 3)
    @DateTimeFormat("yyyy/MM/dd")
    private Date birthday;
}
