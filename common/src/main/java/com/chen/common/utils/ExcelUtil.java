package com.chen.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExcelUtil {

    /**
     * 导出表头必填字段
     * @param outputStream 输入流
     * @param dataList 导入数据
     * @param headList 表头列表
     * @param sheetName sheetname
     * @param cellWriteHandlers
     */
    public static void writeExcelWithModel(OutputStream outputStream, List<? extends Object> dataList, List<String> headList, String sheetName, CellWriteHandler... cellWriteHandlers) {
        List<List<String>> list = new ArrayList<>();
        if(headList != null){
            headList.forEach(h -> list.add(Collections.singletonList(h)));
        }

        // 头的策略
        WriteFont writeFont = new WriteFont();
        writeFont.setColor(IndexedColors.WHITE.index);
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setWriteFont(writeFont);
        // 单元格策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 初始化表格样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.write(outputStream).head(list).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy);
//        if(null != cellWriteHandlers && cellWriteHandlers.length>0){
//            for(int i = 0 ; i < cellWriteHandlers.length;i++){
//                excelWriterSheetBuilder.registerWriteHandler(cellWriteHandlers[i]);
//            }
//        }
        // 开始导出
        excelWriterSheetBuilder.doWrite(dataList);
    }
    /**
     * 导出表头必填字段标红色
     * @param outputStream 输入流
     * @param dataList 导入数据
     * @param headList 表头列表
     * @param sheetName sheetname
     * @param cellWriteHandlers
     */
    public static void writeExcelWithModel(OutputStream outputStream, List<? extends Object> dataList, Class<? extends Object> headList, String sheetName, CellWriteHandler... cellWriteHandlers) {

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 单元格策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 初始化表格样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.write(outputStream,headList).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy);
        if(null != cellWriteHandlers && cellWriteHandlers.length>0){
            for(int i = 0 ; i < cellWriteHandlers.length;i++){
                excelWriterSheetBuilder.registerWriteHandler(cellWriteHandlers[i]);
            }
        }
        // 开始导出
        excelWriterSheetBuilder.doWrite(dataList);
    }
}
