/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 *
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月19日 下午4:36:18
 */
package com.ink.balance.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.ink.balance.api.constants.SysParamConst;
import com.ink.balance.api.model.out.CheckBalanceOutput;
import com.ink.balance.api.model.out.CheckChannelOutput;
import com.ink.balance.api.model.out.CheckDifferenceOutput;
import com.ink.balance.api.model.out.PlatformDataOutput;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月19日 下午4:36:18
 */
public class ExcelUtils {
    /**
     * 创建excel文档，
     * @param list 数据
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<CheckDifferenceOutput> list, String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet("sheet1");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < columnNames.length; i++) {
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());


        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        if (CollectionUtils.isNotEmpty(list)) {
            for (short i = 1; i <= list.size(); i++) {
                // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                // 创建一行，在页sheet上
                Row row1 = sheet.createRow((short) i);
                // 在row行上创建一个方格
                Cell cell = null;
                CheckDifferenceOutput difference = list.get(i - 1);
                cell = row1.createCell(0);
                cell.setCellValue(difference.getChannelNo());
                cell.setCellStyle(cs2);

                cell = row1.createCell(1);
                cell.setCellValue(difference.getChannelMerchantNo());
                cell.setCellStyle(cs2);

                cell = row1.createCell(2);
                cell.setCellValue(getDifferenceSource(difference.getDifferenceSource()));
                cell.setCellStyle(cs2);

                cell = row1.createCell(3);
                cell.setCellValue(getDifferenceType(difference.getDifferenceType()));
                cell.setCellStyle(cs2);

                cell = row1.createCell(4);
                cell.setCellValue(difference.getPlatformOrderNo());
                cell.setCellStyle(cs2);

                cell = row1.createCell(5);
                cell.setCellValue(String.valueOf(difference.getAmount()));
                cell.setCellStyle(cs2);

                cell = row1.createCell(6);
                cell.setCellValue(getHandleStatus(difference.getHandleStatus()));
                cell.setCellStyle(cs2);

                cell = row1.createCell(7);
                cell.setCellValue(Date2String(difference.getCreateDate()));
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }
    /**
     * 导出对账状态信息
     * @param list
     * @param columnNames
     * @return
     */
    public static Workbook createCheckChanneWorkBook(List<CheckChannelOutput> list, String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet("CheckChanne");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < columnNames.length; i++) {
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());


        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        if (CollectionUtils.isNotEmpty(list)) {
            for (short i = 1; i <= list.size(); i++) {
                // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                // 创建一行，在页sheet上
                Row row1 = sheet.createRow((short) i);
                // 在row行上创建一个方格
                Cell cell = null;
                CheckChannelOutput channel = list.get(i - 1);
                cell = row1.createCell(0);
                cell.setCellValue(Long.toString(channel.getId()));
                cell.setCellStyle(cs2);

                cell = row1.createCell(1);
                cell.setCellValue(Date2String(channel.getCheckDate()));
                cell.setCellStyle(cs2);
                
                cell = row1.createCell(2);
                cell.setCellValue(Date2String(channel.getTradeDate()));
                cell.setCellStyle(cs2);
                
                cell = row1.createCell(3);
                cell.setCellValue(getChanneNo(channel.getChannelNo()));
                cell.setCellStyle(cs2);
                
                cell = row1.createCell(4);
                cell.setCellValue(getChannelMerchantNo(channel.getChannelMerchantNo()));
                cell.setCellStyle(cs2);

                cell = row1.createCell(5);
                cell.setCellValue(channel.getChannelAmount()==null?"0":channel.getChannelAmount().toString());
                cell.setCellStyle(cs2);
                
                cell = row1.createCell(6);
                cell.setCellValue(channel.getChannelCount());
                cell.setCellStyle(cs2);
                
                cell = row1.createCell(7);
                cell.setCellValue(channel.getPlatformAmount()==null?"0":channel.getPlatformAmount().toString());
                cell.setCellStyle(cs2);
                
                cell = row1.createCell(8);
                cell.setCellValue(channel.getPlatformCount());
                cell.setCellStyle(cs2);
                
                cell = row1.createCell(9);
                cell.setCellValue(channel.getPlatformSideCount());
                cell.setCellStyle(cs2);
                
                cell = row1.createCell(10);
                cell.setCellValue(channel.getChannelSideCount());
                cell.setCellStyle(cs2);
                
                cell = row1.createCell(11);
                cell.setCellValue(getCheckStatus(channel.getCheckStatus()));//对账结果
                cell.setCellStyle(cs2);
                
                cell = row1.createCell(12);
                cell.setCellValue(channel.getDifferenceCount());//差异总笔数
                cell.setCellStyle(cs2);

                cell = row1.createCell(13);
                cell.setCellValue(getAdjustStatus(channel.getAdjustStatus()));
                cell.setCellStyle(cs2);

                createColumn(cell,row1,14,channel.getUnhandleCount(),cs2);
                
                cell = row1.createCell(15);
                cell.setCellValue(Date2String(channel.getCreateDate()));
                cell.setCellStyle(cs2);

                cell = row1.createCell(16);
                cell.setCellValue(Date2String(channel.getUpdateDate()));
                cell.setCellStyle(cs2);

            }
        }
        return wb;
    }
    /**
     * 导出平台交易信息
     * @param list
     * @param columnNames
     * @return
     */
    public static Workbook createPlatformDataWorkBook(List<PlatformDataOutput> list, String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet("PlatformData");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < columnNames.length; i++) {
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();
        CellStyle csHeji = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();
        Font fH = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());
        //用于合计的字体
        fH.setFontHeightInPoints((short) 10);
        fH.setColor(IndexedColors.RED.getIndex());
        fH.setBoldweight(Font.BOLDWEIGHT_BOLD);

        csHeji.setFont(fH);

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        if (CollectionUtils.isNotEmpty(list)) {
        	BigDecimal amtCount =new BigDecimal(0);
        	BigDecimal arrivedAmtCount =new BigDecimal(0);
        	BigDecimal num =new BigDecimal(0);
        	Row row1 = null;
        	Cell cell = null;
            for (short i = 1; i <= list.size(); i++) {
                // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                // 创建一行，在页sheet上
                row1 = sheet.createRow((short) i);
                // 在row行上创建一个方格
                PlatformDataOutput platform = list.get(i - 1);
                
                createColumn(cell,row1,0,getChanneNo(platform.getChannelNo()),cs2);
                createColumn(cell,row1,1,getChannelMerchantNo(platform.getChannelMerchantNo()),cs2);
                createColumn(cell,row1,2,getChannelMerchantNo(platform.getPlatformOrderNo()),cs2);
                createColumn(cell,row1,3,platform.getAmt()==null?"0":platform.getAmt().toString(),cs2);
                //合计支付金额
                amtCount=amtCount.add(platform.getAmt()==null?num:platform.getAmt());
                
                createColumn(cell,row1,4,platform.getArrivedAmt()==null?"0":platform.getArrivedAmt().toString(),cs2);
                //合计到账金额
                arrivedAmtCount=arrivedAmtCount.add(platform.getArrivedAmt()==null?num:platform.getArrivedAmt());
                
                createColumn(cell,row1,5,Date2String(platform.getPayTime()),cs2);
                createColumn(cell,row1,6,Date2String(platform.getArrivedTime()),cs2);
                createColumn(cell,row1,7,getPayStatus(platform.getPayStatus()),cs2);
                createColumn(cell,row1,8,getPCheckStatus(platform.getCheckStatus()),cs2);
                createColumn(cell,row1,9,getResideFlag(platform.getResideFlag()),cs2);
                createColumn(cell,row1,10,Date2String(platform.getResideToDate()),cs2);
                createColumn(cell,row1,11,Date2String(platform.getCreateDate()),cs2);
                createColumn(cell,row1,12,Date2String(platform.getUpdateDate()),cs2);
            }
            sheet.groupRow(1, list.size());//分组
            row1 = sheet.createRow((short) list.size()+1);
            createColumn(cell,row1,2,"共"+(list.size())+"笔",csHeji);
            createColumn(cell,row1,3,"合计：￥"+amtCount,csHeji);
            createColumn(cell,row1,4,"合计：￥"+arrivedAmtCount,csHeji);
        }
        return wb;
    }
    /**
     * 导出平台交易信息
     * @param list
     * @param columnNames
     * @return
     */
    public static Workbook createCheckBalanceWorkBook(List<CheckBalanceOutput> list, String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet("CheckBalance");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < columnNames.length; i++) {
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());


        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        if (CollectionUtils.isNotEmpty(list)) {
            for (short i = 1; i <= list.size(); i++) {
                // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                // 创建一行，在页sheet上
                Row row1 = sheet.createRow((short) i);
                // 在row行上创建一个方格
                Cell cell = null;
                CheckBalanceOutput checkBalance = list.get(i - 1);
                
                
                createColumn(cell,row1,0,getDifferenceSource(checkBalance.getBalanceSource()),cs2);
                createColumn(cell,row1,1,getBalanceDirection(checkBalance.getBalanceDirection()),cs2);
                createColumn(cell,row1,2,checkBalance.getBalanceAmt()==null?"0":checkBalance.getBalanceAmt().toString(),cs2);
                createColumn(cell,row1,3,getChanneNo(checkBalance.getChannelNo()),cs2);
                createColumn(cell,row1,4,checkBalance.getPlatformOrderNo(),cs2);
                createColumn(cell,row1,5,getBalanceStatus(checkBalance.getBalanceStatus()),cs2);
                createColumn(cell,row1,6,Date2String(checkBalance.getCreateTime()),cs2);
                createColumn(cell,row1,7,Date2String(checkBalance.getUpdateTime()),cs2);
                createColumn(cell,row1,8,checkBalance.getBalanceNote(),cs2);

            }
        }
        return wb;
    }
    private static void createColumn(Cell cell,Row row,int num,String value,CellStyle style){
		cell = row.createCell(num);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
    private static void createColumn(Cell cell,Row row,int num,Integer value,CellStyle style){
		cell = row.createCell(num);
		if(value!=null){
			cell.setCellValue(value);
		}
		cell.setCellStyle(style);
	}
	private static void createColumn(Cell cell,Row row,int num,Double value,CellStyle style){
		cell = row.createCell(num);
		if(value!=null){
			cell.setCellValue(value);
		}
		cell.setCellStyle(style);
	}
    private static String getDifferenceSource(int value) {
        String name;
        switch (value) {
            case 1:
                name = "渠道";
                break;
            case 2:
                name = "平台";
                break;
            default:
                name =""+value;
                break;
        }
        return name;
    }
    private static String getPayStatus(int value) {
        String name;
        switch (value) {
            case 1:
                name = "待支付";
                break;
            case 2:
                name = "支付成功";
                break;
            case 3:
                name = "支付失败";
                break;
            default:
                name = ""+value;
                break;
        }
        return name;
    }
    private static String getDifferenceType(int value) {
        String name;
        switch (value) {
            case 1:
                name = "渠道单边";
                break;
            case 2:
                name = "平台单边";
                break;
            case 3:
                name = "差错";
                break;
            default:
                name = ""+value;
                break;
        }
        return name;
    }
    
    private static String getBalanceDirection(int value) {
        String name;
        switch (value) {
            case 1:
                name = "正";
                break;
            case 2:
                name = "负";
                break;
            default:
                name = ""+value;
                break;
        }
        return name;
    }
    private static String getBalanceStatus(int value) {
        String name;
        switch (value) {
            case 1:
                name = "调账成功";
                break;
            case 2:
                name = "调账失败";
                break;
            default:
                name = ""+value;
                break;
        }
        return name;
    }
    private static String getResideFlag(int value) {
        String name;
        switch (value) {
            case 0:
                name = "非驻留";
                break;
            case 1:
                name = "驻留";
                break;
            default:
                name = ""+value;
                break;
        }
        return name;
    }
    private static String getHandleStatus(int value) {
        String name;
        switch (value) {
            case 0:
                name = "待处理";
                break;
            case 1:
                name = "处理完成";
                break;
            case 2:
                name = "挂起";
                break;
            default:
                name =""+value;
                break;
        }
        return name;
    }
    
    private static String getCheckStatus(int value) {
        String name;
        switch (value) {
            case 1:
                name = "匹配";
                break;
            case 2:
                name = "未匹配";
                break;
            case 3:
                name = "数据异常";
                break;
            default:
                name = ""+value;
                break;
        }
        return name;
    }
    private static String getPCheckStatus(int value) {
        String name;
        switch (value) {
            case 1:
                name = "匹配";
                break;
            case 2:
                name = "未匹配";
                break;
            case 3:
                name = "差错";
                break;
            case 4:
                name = "金额调整后匹配";
                break;
            default:
                name = ""+value;
                break;
        }
        return name;
    }
    private static String getAdjustStatus(int value) {
        String name;
        switch (value) {
            case 0:
                name = "待处理";
                break;
            case 2:
                name = "处理完成";
                break;
            default:
                name = ""+value;
                break;
        }
        return name;
    }
    private static String getChanneNo(String value) {
        String name;
        if(value!=null&&value.equals("1")){
        	name="快钱";
        }else if(value!=null&&value.equals("2")){
        	name="快翼支付";	
		}else if(value!=null&&value.equals("10002")){
			name="民生";
		}else{
			name=value;
		}
        return name;
    }
    private static String getChannelMerchantNo(String value) {
        String name;
        if(value!=null&&value.equals(SysParamConst.CMBC_JLC_MERCHANT_NO)){
        	name="民生-简理财";
        }else{
			name=value;
		}
        return name;
    }
    private static String Date2String(Date date) {
    	if(date!=null){
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(date);
            return format;
    	}
        return null;
    }

}
