package com.xibu.tickets.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.List;
import java.util.Map;


/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月21日 下午12:00:03
*/
public class DBToExcel {
	/**
	 * 将数据库数据导出成excel表
	 * @param list	要写入excel表的数据
	 * @param filePath	文件路径
	 * @param sheetName		表名
	 * @param sheetID		第几张表，表的页号
	 */
	public static int exportExcel (List<Map<String, Object>> list , String filePath , String sheetName , int sheetID) {
//		JdbcTemplate template = new JdbcTemplate( DBHelper_Mysql.getDs());
		int result = 0 ;
		try {
			//得到工作簿
			File file ;
			
			filePath = filePath.endsWith(".xls") ? filePath : filePath + ".xls" ;

//			System.out.println( filePath );
			file = new File( filePath ) ;
			
			if ( !file.exists() ) {
				file.createNewFile() ;
			}

			if ( list.size() <= 0 ) {
				return result;
			}

			String colName[] = {"车次名称", "营业总额"};

			// 创建工作簿对象
			HSSFWorkbook workbook = new HSSFWorkbook();

			// 创建sheet对象
			HSSFSheet sheet = workbook.createSheet();

			// 创建第1行
			HSSFRow row0 = sheet.createRow(0);
			// 设置行高
			row0.setHeightInPoints((short) 24);
			// 设置列宽
//        sheet.setColumnWidth(0,20*256);


			// 创建字体对象
			HSSFFont font0 = workbook.createFont();
			// 设置字体
			font0.setFontName("宋体");
			// 设置字体是否加粗
			font0.setBold(true);
			// 设置字体颜色
			font0.setColor(Font.COLOR_RED);
			// 设置字体高度
			font0.setFontHeightInPoints((short) 14);

			// 创建单元格样式
			HSSFCellStyle cellStyle0 = workbook.createCellStyle();
			// 设置水平方向上的文本对齐方式
			cellStyle0.setAlignment(HorizontalAlignment.CENTER);
			// 设置竖直方向上的文本对齐方式
			cellStyle0.setVerticalAlignment(VerticalAlignment.CENTER);
			// 设置单元格顶部边框使用的边框类型
			cellStyle0.setBorderTop(BorderStyle.THIN);
			// 设置单元格底部边框使用的边框类型
			cellStyle0.setBorderBottom(BorderStyle.THIN);
			// 设置单元格左边框使用的边框类型
			cellStyle0.setBorderLeft(BorderStyle.THIN);
			// 设置单元格右边框使用的边框类型
			cellStyle0.setBorderRight(BorderStyle.THIN);
			// 设置此样式的字体
			cellStyle0.setFont(font0);
			// 设置单元格文本是否换行
			cellStyle0.setWrapText(false);
			// 设置单元格前景填充颜色 【注意：确保前景色设置在背景色之前。】
			cellStyle0.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			// 设置单元格背景填充颜色
			cellStyle0.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
			// 设置一个充满了前景色的单元格.
			cellStyle0.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			HSSFCellStyle cellStyle1 = workbook.createCellStyle();
			cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);


			HSSFRow row = sheet.createRow(0);
			row.setHeightInPoints(24);
			row.setRowStyle(cellStyle1);
			for (int cell0i = 0; cell0i < colName.length; cell0i++) {
				HSSFCell cell = row.createCell(cell0i);
				cell.setCellValue(colName[cell0i]);
				cell.setCellStyle(cellStyle1);
			}

			for (int j = 0; j < list.size(); j++) {
				Map<String, Object> map = list.get(j);
				System.out.println( map );
				// 为每一条记录创建一个行对象
				HSSFRow row1 = sheet.createRow(j + 1);
				row1.setHeightInPoints(24);
				row1.setRowStyle(cellStyle1);

				HSSFCell cell0 = row1.createCell(0);
				cell0.setCellValue(map.get("车次名称").toString());
				cell0.setCellStyle(cellStyle1);


				HSSFCell cell1 = row1.createCell(1);
				cell1.setCellValue(map.get("营业总额").toString());
				cell1.setCellStyle(cellStyle1);

			}
			result = 1 ;

			workbook.write( file );
			workbook.close();
		} catch (Exception e) {
			throw new RuntimeException( e.getMessage() ) ;
		}
		
		return result ;
	} 
}
