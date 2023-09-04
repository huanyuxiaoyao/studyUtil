package com.cashway.excelutil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhangpengyu
 */
public class DownloadToExcel {

    private static DownloadToExcel downloadToExcel;

    private DownloadToExcel() {

    }

    private static DownloadToExcel getInstance() {
        if (downloadToExcel == null) {
            synchronized (DownloadToExcel.class) {
                if (downloadToExcel == null) {
                    downloadToExcel = new DownloadToExcel();
                }
            }
        }
        return downloadToExcel;
    }

    public void download(Context context, String path, List<String> headDate, List<MyBean> list) {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = generateSheet(wb, headDate);

        //=================================定义表头属性===============================================
        HSSFFont font = wb.createFont(); // 生成字体格式设置对象
        font.setFontName("黑体"); // 设置字体黑体
//        font.setBold(true); // 字体加粗
        font.setFontHeightInPoints((short) 16); // 设置字体大小
        font.setColor(HSSFFont.COLOR_NORMAL);//字体颜色

        HSSFCellStyle cellStyle = wb.createCellStyle(); // 生成行格式设置对象
        cellStyle.setBorderBottom(BorderStyle.THIN);// 下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);// 左边框
        cellStyle.setBorderRight(BorderStyle.THIN);// 右边框
        cellStyle.setBorderTop(BorderStyle.THIN);// 上边框
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 横向居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 纵向居中对齐
        cellStyle.setFont(font);

        //=================================定义内容属性===============================================
        HSSFFont txtContent = wb.createFont(); // 生成字体格式设置对象
        txtContent.setFontName("黑体"); // 设置字体黑体
//        txtContent.setBold(false); // 字体加粗
        txtContent.setFontHeightInPoints((short) 12); // 设置字体大小
        txtContent.setColor(HSSFFont.COLOR_NORMAL);//字体颜色

        HSSFCellStyle cellStyleContent = wb.createCellStyle(); // 生成行格式设置对象
        cellStyleContent.setBorderBottom(BorderStyle.THIN);// 下边框
        cellStyleContent.setBorderLeft(BorderStyle.THIN);// 左边框
        cellStyleContent.setBorderRight(BorderStyle.THIN);// 右边框
        cellStyleContent.setBorderTop(BorderStyle.THIN);// 上边框
        cellStyleContent.setAlignment(HorizontalAlignment.CENTER); // 横向居中对齐
        cellStyleContent.setVerticalAlignment(VerticalAlignment.CENTER); // 纵向居中对齐
        cellStyleContent.setFont(txtContent);

        //====================================写入数据===============================================
        for (int k = 0; k < list.size() + 1; k++) {
            HSSFRow row = sheet.createRow(k);

            if (k == 0) {
                HSSFCell cell0 = row.createCell(0);
                HSSFCell cell1 = row.createCell(1);
                HSSFCell cell2 = row.createCell(2);
                HSSFCell cell3 = row.createCell(3);
                cell0.setCellStyle(cellStyle);
                cell1.setCellStyle(cellStyle);
                cell2.setCellStyle(cellStyle);
                cell3.setCellStyle(cellStyle);

                row.setHeight((short) 500);
                cell0.setCellValue("序号");
                cell1.setCellValue("大象体重");
                cell2.setCellValue("时间");
                cell3.setCellValue("日期");
            } else {

                HSSFCell cell0 = row.createCell(0);
                HSSFCell cell1 = row.createCell(1);
                HSSFCell cell2 = row.createCell(2);
                HSSFCell cell3 = row.createCell(3);
                cell0.setCellStyle(cellStyleContent);
                cell1.setCellStyle(cellStyleContent);
                cell2.setCellStyle(cellStyleContent);
                cell3.setCellStyle(cellStyleContent);

                row.setHeight((short) 500);
                cell0.setCellValue(list.get(k - 1).getId());
                cell1.setCellValue(list.get(k - 1).getWeighValue());
                cell2.setCellValue(list.get(k - 1).getTime());
                cell3.setCellValue(list.get(k - 1).getDate());
            }
        }

        writeToFile(path, wb);

    }

    private String getNowTime() {
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(time);
    }


    /**
     * 设置表格列宽
     *
     * @param wb       工作表
     * @param dataList 表头数据
     * @return HSSFSheet
     */
    private HSSFSheet generateSheet(HSSFWorkbook wb, List<String> dataList) {
        HSSFSheet sheet = wb.createSheet();
        for (int i = 0; i < dataList.size(); i++) {
            sheet.setColumnWidth(i, 7000);
        }

        return sheet;
    }

    private boolean writeToFile(String path, HSSFWorkbook wb) {

        //检查文件是否存在 存在重建 未存在则新建
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                if (file.delete()) {
                    file.createNewFile();
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error", e + "");
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            wb.write(fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.e("error", "Download Fail FileNotFoundException" + e);
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("error", "Download Fail IOException" + e);
            e.printStackTrace();
        }
        return false;
    }
}

