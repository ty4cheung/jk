package jk.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class TestHSSF {

	@Test
	public void testHSSF() {
		/*
		 * 1.创建一个工作簿 2.创建一个工作表 3.创建一个行对象 4.创建一个单元格对象指定它的列； 5.给单元格对象设置内容 6.样式进行修饰
		 * 7.保存 8.写文件。关闭对象
		 */
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(7);
		Cell nCell = row.createCell(4);
		nCell.setCellValue("给单元格对象设置内容");
		OutputStream os =null;
		try {
		 os = new FileOutputStream("/Users/tyler/Desktop/TestPoi.xls");

			wb.write(os);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	

	@Test
	public void testHSSF2() {
		/*
		 * 1.创建一个工作簿 2.创建一个工作表 3.创建一个行对象 4.创建一个单元格对象指定它的列； 5.给单元格对象设置内容 6.样式进行修饰
		 * 7.保存 8.写文件。关闭对象
		 */
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(8);
		Cell nCell = row.createCell(4);
		CellStyle titleStyle =wb.createCellStyle();
		Font titleFont = wb.createFont();
		titleFont.setFontName("黑体");
		titleFont.setFontHeightInPoints((short)72);
		titleStyle.setFont(titleFont);
		nCell.setCellStyle(titleStyle);
		nCell.setCellValue("给单元格对象设置内容");
		
		OutputStream os =null;
		try {
		 os = new FileOutputStream("/Users/tyler/Desktop/TestPoi2.xls");

			wb.write(os);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
