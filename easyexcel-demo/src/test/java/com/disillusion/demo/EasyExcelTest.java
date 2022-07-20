package com.disillusion.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.disillusion.model.entity.Employee;

public class EasyExcelTest {

    public List<Employee> getData() {
        List<Employee> lists = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            Employee student = new Employee();
            student.setId(i + 1);
            student.setName("李四" + i);
            student.setBirthday(new Date());
            student.setSalary(1500.00D);
            lists.add(student);
        }
        return lists;
    }

    @Test
    // 测试导出excel，行高，列宽，表头，排序，日期格式，忽略类中的某个字段。
    void contextLoads1() {
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("学生信息表.xlsx", Employee.class);
        excelWriterBuilder.sheet(2).doWrite(getData());
    }

    @Test 
    // 测试动态忽略某些字段，导出特定字段。
    @SuppressWarnings("deprecation")
    void contextLoads2() {
        Set<String> exportColumns = new HashSet<>();
        exportColumns.add("id");
        exportColumns.add("name");
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("学生信息表.xlsx", Employee.class);
        excelWriterBuilder.includeColumnFiledNames(exportColumns)
        .sheet(2).doWrite(getData());
    }
}

