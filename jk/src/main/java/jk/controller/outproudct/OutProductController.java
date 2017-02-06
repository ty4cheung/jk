package jk.controller.outproudct;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jk.controller.BaseController;
import jk.domain.Contract;
import jk.service.ContractService;
import jk.service.OutProductService;
import jk.util.DownloadUtil;
import jk.vo.ContractVO;
import jk.vo.OutProductVO;

@Controller
public class OutProductController extends BaseController {

	@Resource
	OutProductService outProductService;

	@RequestMapping("/cargo/outproduct/toedit.action")
	public String toedit() {
		return "/cargo/outproduct/jOutProduct";
	}

	@RequestMapping("/cargo/outproduct/printHSSF.action")
	public void printHSSF(String inputDate, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// linux下jdk1.8 方法获取时，不会拼接自己写的目录
		String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "tOUTPRODUCT.xls"));
		// 2003
		Workbook wb = new HSSFWorkbook(is); // 打开一个模板文件，工作簿
		Sheet sheet = wb.getSheetAt(0); // 获取到第一个工作表

		Row nRow = null;
		Cell nCell = null;
		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板上的单元格样式
		nRow = sheet.getRow(2);

		// 客户的样式
		nCell = nRow.getCell(1);
		CellStyle customStyle = nCell.getCellStyle();

		// 订单号的样式
		nCell = nRow.getCell(2);
		CellStyle contractNoStyle = nCell.getCellStyle();

		// 货号的样式
		nCell = nRow.getCell(3);
		CellStyle productNoStyle = nCell.getCellStyle();

		// 数量的样式
		nCell = nRow.getCell(4);
		CellStyle numStyle = nCell.getCellStyle();

		// 生产厂家的样式
		nCell = nRow.getCell(5);
		CellStyle factoryStyle = nCell.getCellStyle();

		// 日期的样式
		nCell = nRow.getCell(6);
		CellStyle dateStyle = nCell.getCellStyle();

		// 贸易条款的样式
		nCell = nRow.getCell(8);
		CellStyle tradeStyle = nCell.getCellStyle();

		// 处理大标题
		nRow = sheet.getRow(rowNo++); // 获取一个行对象
		nCell = nRow.getCell(colNo); // 获取一个单元格对象
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表"); // yyyy-MM

		rowNo++; // 跳过静态表格头
		// 处理内容
		List<OutProductVO> dataList = outProductService.find(inputDate);
		for (int j = 0; j < dataList.size(); j++) {
			colNo = 1; // 初始化
			OutProductVO op = dataList.get(j);

			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getCustomName());
			nCell.setCellStyle(customStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getContractNo());
			nCell.setCellStyle(contractNoStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getProductNo());
			nCell.setCellStyle(productNoStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getCnumber());
			nCell.setCellStyle(numStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getFactoryName());
			nCell.setCellStyle(factoryStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getDeliveryPeriod());
			nCell.setCellStyle(dateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getShipTime());
			nCell.setCellStyle(dateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getTradeTerms());
			nCell.setCellStyle(tradeStyle);
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);

		DownloadUtil downloadUtil = new DownloadUtil(); // 直接弹出下载框，用户可以打开，可以保存
		downloadUtil.download(os, response, "出货表.xls");

		os.flush();
		os.close();
	}

	@RequestMapping("/cargo/outproduct/print.action")
	public void print(String inputDate, HttpServletRequest request, HttpServletResponse response) throws IOException{
		//linux下jdk1.8 方法获取时，不会拼接自己写的目录 
		String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
		
		InputStream is = new FileInputStream(new File(path + "tOUTPRODUCT.xlsx"));
		
		Workbook wb = new XSSFWorkbook(is);		//打开一个模板文件，工作簿 2007以上版本
		Sheet sheet = wb.getSheetAt(0);			//获取到第一个工作表
		
		Row nRow = null;
		Cell nCell = null;
		int rowNo = 0;							//行号
		int colNo = 1;							//列号
		
		//获取模板上的单元格样式
		nRow = sheet.getRow(2);
		
		//客户的样式
		nCell = nRow.getCell(1);
		CellStyle customStyle = nCell.getCellStyle();		
		
		//订单号的样式
		nCell = nRow.getCell(2);
		CellStyle contractNoStyle = nCell.getCellStyle();		
		
		//货号的样式
		nCell = nRow.getCell(3);
		CellStyle productNoStyle = nCell.getCellStyle();		
		
		//数量的样式
		nCell = nRow.getCell(4);
		CellStyle numStyle = nCell.getCellStyle();		
		
		//生产厂家的样式
		nCell = nRow.getCell(5);
		CellStyle factoryStyle = nCell.getCellStyle();		
		
		//日期的样式
		nCell = nRow.getCell(6);
		CellStyle dateStyle = nCell.getCellStyle();		
		
		//贸易条款的样式
		nCell = nRow.getCell(8);
		CellStyle tradeStyle = nCell.getCellStyle();		
		
		
		//处理大标题
		nRow = sheet.getRow(rowNo++);			//获取一个行对象
		nCell = nRow.getCell(colNo);			//获取一个单元格对象
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表");		//yyyy-MM
		
		rowNo++;								//跳过静态表格头
		
		//处理内容
		List<OutProductVO> dataList = outProductService.find(inputDate);
		for(int j=0;j<dataList.size();j++){
			colNo = 1;				//初始化
			OutProductVO op = dataList.get(j);
			
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getCustomName());
			nCell.setCellStyle(customStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getContractNo());
			nCell.setCellStyle(contractNoStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getProductNo());
			nCell.setCellStyle(productNoStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getCnumber());
			nCell.setCellStyle(numStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getFactoryName());
			nCell.setCellStyle(factoryStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getDeliveryPeriod());
			nCell.setCellStyle(dateStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getShipTime());
			nCell.setCellStyle(dateStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getTradeTerms());
			nCell.setCellStyle(tradeStyle);
		}
		
//		OutputStream os = new FileOutputStream("c:\\outproduct.xls");
//		wb.write(os);
//		
//		os.flush();
//		os.close();
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		
		DownloadUtil downloadUtil = new DownloadUtil();				//直接弹出下载框，用户可以打开，可以保存
		downloadUtil.download(os, response, "出货表.xlsx");
		
		os.flush();
		os.close();
	}
	
	@RequestMapping("/cargo/outproduct/printNotemplate.action")
	public void printNotemplate(String inputDate, HttpServletResponse response) throws IOException {

		/**
		 * POI 实现Excel表格答应 1.标题，修饰 2.内容，修饰
		 */

		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row row = null;// 创建一行对象
		Cell nCell = null;
		int rowNo = 0;
		int cellNo = 1;

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 8));
		row = sheet.createRow(rowNo++);
		row.setHeightInPoints(36);
		nCell = row.createCell(1);
		nCell.setCellStyle(titleStyle(wb));
		inputDate = "2017-1";
		System.out.println("replaceFirst:" + inputDate.replaceFirst("-0", "-"));
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表");
		rowNo++;
		row = sheet.createRow(rowNo++);
		String[] titles = new String[] { "客户", "订单号", "货号", "数量", "工厂", "工厂交期", "船期", "贸易条款" };
		for (int i = 0; i < titles.length; i++) {
			nCell = row.createCell(i + 1);
			nCell.setCellValue(titles[i]);
		}

		List<OutProductVO> dataList = outProductService.find(inputDate);
		System.out.println(dataList.size());

		for (int i = 0; i < dataList.size(); i++) {
			OutProductVO vo = dataList.get(i);
			row = sheet.createRow(rowNo++);
			nCell = row.createCell(cellNo++);
			nCell.setCellValue(vo.getCustomName());
			nCell = row.createCell(cellNo++);
			nCell.setCellValue(vo.getContractNo());
			nCell = row.createCell(cellNo++);
			nCell.setCellValue(vo.getProductNo());
			nCell = row.createCell(cellNo++);
			nCell.setCellValue(vo.getCnumber());
			nCell = row.createCell(cellNo++);
			nCell.setCellValue(vo.getFactoryName());
			nCell = row.createCell(cellNo++);
			nCell.setCellValue(vo.getShipTime());
			nCell = row.createCell(cellNo++);
			nCell.setCellValue(vo.getDeliveryPeriod());
			nCell = row.createCell(cellNo++);
			nCell.setCellValue(vo.getTradeTerms());

			cellNo = 1;
		}

		// OutputStream os =null;
		// try {
		// os = new FileOutputStream("/Users/tyler/Desktop/OutProduct.xls");
		//
		// wb.write(os);
		//
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } finally{
		// try {
		// os.flush();
		// os.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		wb.write(bos);
		DownloadUtil dl = new DownloadUtil();
		dl.download(bos, response, "出货表.xls");
		bos.flush();
		bos.close();
	}

	private CellStyle titleStyle(Workbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("宋体");
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 12);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return cellStyle;
	}

}
