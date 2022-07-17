package com.disillusion.demo;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.junit.jupiter.api.Test;

public class PoiTest {

    // @Test
    // public void test01() {
    //     // 处理HSSF
    //     String path = "D:\JavaSource\ash-prc-parent\easyexcel-demo\学生信息表xls.xls";
    //     // 处理XSSF
    //     // String path = "extend\\file\\poi_test_01.xls";

    //     // 创建工作簿
    //     boolean flag = path.endsWith(".xlsx");
    //     Workbook wb = WorkbookFactory.create(flag ? true : false);
    //     // Workbook wb = new SXSSFWorkbook(100);//内存仅保留100行数据，可避免OOM

    //     // 创建工作表
    //     Sheet sheet = wb.createSheet(WorkbookUtil.createSafeSheetName("MySheet001"));
    //     // 设置列宽
    //     sheet.setColumnWidth(0, 26 * 256);

    //     // 创建行(索引从0开始)
    //     Row row = sheet.createRow(0);
    //     // 设置行高
    //     row.setHeightInPoints(20.25f);

    //     // 创建单元格样式对象
    //     CellStyle style = wb.createCellStyle();
    //     // 设置样式
    //     style.setAlignment(HorizontalAlignment.CENTER); // 横向居中
    //     style.setVerticalAlignment(VerticalAlignment.CENTER);// 纵向居中
    //     style.setBorderBottom(BorderStyle.THIN);
    //     style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
    //     style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    //     // 创建单元格、设置样式和内容
    //     CellUtil.createCell(row, 0, "测试", style);

    //     // 保存到本地目录
    //     OutputStream out = new FileOutputStream(new File(path));
    //     wb.write(out);

    //     // 释放资源
    //     out.close();
    //     wb.close();

    // }

}
