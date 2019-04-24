package com.example.demo.utils;


import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReadExcelUtils {


    public static List<Map<String, Object>> readExcel(InputStream is, String fileName) throws Exception {
        List<Map<String, Object>> result = new ArrayList();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        Workbook wb = null;
        try {
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                return result;
            }
            int sheetSize = wb.getNumberOfSheets();
            for (int i = 0; i < sheetSize; i++) {//遍历sheet页
                Sheet sheet = wb.getSheetAt(i);
                List sheetList = new ArrayList();//对应sheet页
                List titles = new ArrayList();//放置所有的标题
                int rowSize = sheet.getLastRowNum() + 1;
                for (int j = 0; j < rowSize; j++) {//遍历行
                    Row row = sheet.getRow(j);
                    if (row == null) {//略过空行
                        continue;
                    }
                    int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
                    if (j == 0) {//第一行是标题行
                        for (int k = 0; k < cellSize; k++) {
                            Cell cell = row.getCell(k);
                            titles.add(cell.toString());
                        }
                    } else {//其他行是数据行
                        Map rowMap = new HashMap();//对应一个数据行
                        for (int k = 0; k < cellSize; k++) {
                            Cell cell = row.getCell(k);
                            String key = titles.get(k).toString();
                            String value = null;
                            if (cell != null) {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                value = cell.toString();
                            }
                            rowMap.put(key, value);
                        }
                        result.add(rowMap);
                    }
                }
            }

        } finally {
            IOUtils.closeQuietly(is);
        }
        return result;
    }


    private static String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);
                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = cell.getStringCellValue();
                        System.out.println("--------- " + cellvalue);
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    public static void main(String[] args) throws Exception {
        String path = "C:/Users/beyond/Desktop/学生模版.xls";
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        List<Map<String, Object>> list = readExcel(fis, file.getName());
        System.out.println(list.get(0).get("姓名"));
    }
}
