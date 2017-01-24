package jk.controller.outproudct;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jk.controller.BaseController;
import jk.domain.Contract;
import jk.service.ContractService;
import jk.service.OutProductService;
import jk.vo.ContractVO;
import jk.vo.OutProductVO;

@Controller
public class OutProductController extends BaseController {

	@Resource
	OutProductService outProductService;
	
	@RequestMapping("/cargo/outproduct/toedit.action")
	public String toedit(){
		return "/cargo/outproduct/jOutProduct";
	}
	
	@RequestMapping("/cargo/outproduct/print.action")
	public void print(String inputDate){

		/**
		 * POI 实现Excel表格答应
		 * 1.标题，修饰
		 * 2.内容，修饰
		 * */
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row row = null;//创建一行对象
		Cell nCell = null;
		int rowNo = 0;
		int cellNo = 1;
		
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 8));
		row = sheet.createRow(rowNo++);
		row.setHeightInPoints(36);
		nCell=row.createCell(1);
		nCell.setCellStyle(titleStyle(wb));
		inputDate = "2017-1";
		System.out.println("replaceFirst:"+inputDate.replaceFirst("-0", "-"));
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年")+"月份出货表");
		rowNo++;
		row = sheet.createRow(rowNo++);
		String[] titles = new String[]{"客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};
		for (int i = 0; i < titles.length; i++) {
			nCell = row.createCell(i+1);
			nCell.setCellValue(titles[i]);
		}
		
		List<OutProductVO> dataList = outProductService.find(inputDate);
		System.out.println(dataList.size());
	
		for (int i = 0; i < dataList.size(); i++) {
			OutProductVO vo=dataList.get(i);
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
		
		OutputStream os =null;
		try {
		 os = new FileOutputStream("/Users/tyler/Desktop/OutProduct.xls");

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
	
	private CellStyle titleStyle(Workbook wb){
		CellStyle cellStyle = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("宋体");
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)12);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return cellStyle;
	}

}
