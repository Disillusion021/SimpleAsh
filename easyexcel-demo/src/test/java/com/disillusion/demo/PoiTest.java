package com.disillusion.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

public class PoiTest {

    @Test
    public void simpleTestPOI() throws FileNotFoundException, IOException {
        // 处理XSSF
        String path = "D:\\JavaSource\\SimpleAsh\\easyexcel-demo\\poi_test_01.xlsx";
        // 处理HSSF
        // String path = "extend\\file\\poi_test_01.xls";

        // 创建工作簿
        boolean flag = path.endsWith(".xlsx");
        Workbook wb = WorkbookFactory.create(flag);
        // Workbook wb = new SXSSFWorkbook(100);//内存仅保留100行数据，可避免OOM

        // 创建工作表
        Sheet sheet = wb.createSheet(WorkbookUtil.createSafeSheetName("MySheet001"));
        // 设置列宽
        sheet.setColumnWidth(0, 26 * 256);

        // 创建行(索引从0开始)
        Row row = sheet.createRow(0);
        // 设置行高
        row.setHeightInPoints(20.25f);

        // 创建单元格样式对象
        CellStyle style = wb.createCellStyle();
        // 设置样式
        style.setAlignment(HorizontalAlignment.CENTER); // 横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 纵向居中
        style.setBorderBottom(BorderStyle.THIN);
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 创建单元格、设置样式和内容
        CellUtil.createCell(row, 0, "测试", style);

        // 保存到本地目录
        OutputStream out = new FileOutputStream(new File(path));
        wb.write(out);

        // 释放资源
        wb.close();
    }

    @Test
    public void testReadExcel() {
        String project_path = System.getProperty("user.dir");
        System.out.println(project_path); // D:\JavaSource\SimpleAsh\easyexcel-demo
        String file_path = project_path + "\\poi_test_02.xlsx";
        File file = new File(file_path);

        try (FileInputStream fileInputStream = new FileInputStream(file);
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);) {
            // 通过sheet的名字来获取数据
            // XSSFSheet xssfSheet = xssfWorkbook.getSheet("sheet01");
            // 通过sheet的下标来获取数据
            // 所有下标都是从0开始
            XSSFSheet xssfSheet0 = xssfWorkbook.getSheetAt(0);
            // 获取第一行的下标
            int firstRowNum = xssfSheet0.getFirstRowNum();
            // 获取最后一行的下标
            int lastRowNum = xssfSheet0.getLastRowNum();
            for (int i = firstRowNum; i <= lastRowNum; i++) {
                // 根据下标获取对应行的数据
                Row row = xssfSheet0.getRow(i);
                // 获取对应行的第一个Cell的下标
                int firstCellNum = row.getFirstCellNum();
                // 获取对应行的最后一个Cell的下标
                int lastCellNum = row.getLastCellNum();
                System.out.println(firstCellNum);
                System.out.println(lastCellNum);
                /**
                 * 注意：比如这一行有四个单元格，则firstCellNum=0，lastCellNum=4，注意=4！！！！！，并不是等于3
                 * 所以下面的for循环为<
                 * 之所以没有在循环外就确定这两个值，是因为你没有办法确定每一行的列数都一致
                 */
                // 新建一个List用来存放数据
                List<String> list = new ArrayList<>();
                // for (int j = firstCellNum; j < lastCellNum; j++) {
                // Cell cell = row.getCell(j);
                // System.out.println(cell.toString());
                // list.add(cell.toString());
                // }
                // 或
                for (Cell cell : row) {
                    System.out.println(cell.toString());
                    list.add(cell.toString());
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteExcel() {
        String project_path = System.getProperty("user.dir");
        System.out.println(project_path); // D:\JavaSource\SimpleAsh\easyexcel-demo
        String file_path = project_path + "\\poi_test_03.xlsx";
        File file = new File(file_path);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook();) {
            xssfWorkbook.write(fileOutputStream);
            XSSFSheet xssfSheet = xssfWorkbook.createSheet("sheet01");
            String[] title = new String[] { "姓名", "职业", "年龄" };
            Row row = xssfSheet.createRow(0);
            for (int i = 0; i < title.length; i++) {
                row.createCell(i).setCellValue(title[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
