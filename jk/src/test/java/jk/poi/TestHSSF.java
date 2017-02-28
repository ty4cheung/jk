package jk.poi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.junit.Test;

import jk.util.DownloadUtil;
import jk.util.UtilFuns;
import jk.vo.ContractProductVO;
import jk.vo.ContractVO;

public class TestHSSF {

	@Test
	public void test(){
		int[] a ={9,1,2,9,13,5};
		for(int i=0;i<a.length;i++){
			for(int j=a.length-1;j>=0;j--){
				if((a[i]+a[j])==14){
					a[i]=0;
					a[j]=0;
				}
			}
		}
		List list=new ArrayList();
		for (int i = 0; i < a.length; i++) {
			if(a[i]!=0){
				list.add(a[i]);
			}
		}
		System.out.println(list);
	}
	
	
	@Test
	public void cloneSheet() throws FileNotFoundException, IOException, ParseException{
		String path ="/Users/tyler/WorkSpace/java/apache-tomcat-8.0.35/wtpwebapps/jk/";
	
		Workbook wb = new HSSFWorkbook(new FileInputStream(new File(path+"make/xlsprint/tCONTRACTVO.xls")));
		Map pageMap = null;
		List<Map> pageList = new ArrayList<Map>();
		ContractVO contract=null;
		ContractProductVO cp = null;
		UtilFuns utilFuns = new UtilFuns();
		String _stars = "";
		for(int i=0;i<3;i++){
			_stars += "★";
		}
		for(int i=0;i<1;i++){
			pageMap = new HashMap<String,String>();
			cp = new ContractProductVO();
			contract = new ContractVO();
			
			pageMap.put("Offeror", "收 购 方："+contract.getOfferor());
			pageMap.put("ContractNo", "合 同 号："+contract.getContractNo());
			pageMap.put("SigningDate", "签单日期："+UtilFuns.formatDateTimeCN(UtilFuns.dateTimeFormat(contract.getSigningDate())));
			
			pageMap.put("Factory", "生产工厂："+UtilFuns.convertNull(cp.getFactory().getFullName()));
			pageMap.put("Contacts", "联 系 人："+cp.getFactory().getContacts());
			pageMap.put("Phone", "电    话："+cp.getFactory().getPhone());
			
			pageMap.put("InputBy", "制单："+contract.getInputBy());
			pageMap.put("CheckBy", "审单："+utilFuns.fixSpaceStr(contract.getCheckBy(), 26) + "验货员："+contract.getInspector());
			
			pageMap.put("Crequest", contract.getCrequest());
			
			
			
			pageMap.put("ProductDescTitle", _stars + " 货物描述");
			
			pageMap.put("ProductImage", cp.getProductImage());
			pageMap.put("ProductDesc", cp.getProductDesc());
			pageMap.put("Cnumber", cp.getCnumber().toString());
			if(cp.getPackingUnit().equals("PCS")){
				pageMap.put("PackingUnit", "只");
			}else if(cp.getPackingUnit().equals("SETS")){
				pageMap.put("PackingUnit", "套");
			}
			pageMap.put("Price", cp.getPrice().toString());
			pageMap.put("ProductNo", cp.getProductNo());
			
			String fullName = cp.getFactory().getFullName();
			if(contract.getPrintStyle().equals("2")){		//按两款货物打印，才做
				//处理第二款货物
				i++;
				if(i<1){			//判断第二款货物是否有
					cp = new ContractProductVO();
					if(cp.getFactory().getFullName().equals(fullName)){
						pageMap.put("ProductImage2", cp.getProductImage());
						pageMap.put("ProductDesc2", cp.getProductDesc());
						pageMap.put("Cnumber2", cp.getCnumber().toString());
						if(cp.getPackingUnit().equals("PCS")){
							pageMap.put("PackingUnit2", "只");
						}else if(cp.getPackingUnit().equals("SETS")){
							pageMap.put("PackingUnit2", "套");
						}
						pageMap.put("Price2", cp.getPrice().toString());
						pageMap.put("ProductNo2", cp.getProductNo());
					}
				}else{
					i--;						//如果第二款货物厂家不同，则必须新起一页
				}
			}
			pageList.add(pageMap);			//存储一页数据
		}
//		Workbook wb = new HSSFWorkbook(new FileInputStream(new File(path+"make/xlsprint/tCONTRACTVO.xls")));
		
		for(int i=0;i<pageList.size();i++){
			Sheet cloneSheet = wb.cloneSheet(i);//复制工作簿	
			wb.setSheetName(i+1, "B"+(i+1)+"");//设置工作簿名称			
		}
		
		//设置相同内容
		for(int i=0;i<pageList.size();i++){
			int rowNo = 6;
			int colNo = 0;
			Row nRow = null;
			Cell nCell = null;
			Map<String,String> printMap = pageList.get(i);
			
			Sheet sheet = wb.getSheetAt(i);						//定位到当前工作表
			sheet.setForceFormulaRecalculation(true);				//强制公式自动计算，利用模板时，模板中的公式不会因值发生变化而自动计算。
			
			nRow = sheet.getRow(rowNo++);
			nCell = getCell(sheet, colNo, nRow,1);
			nCell.setCellValue(printMap.get("Offeror"));
			nCell = getCell(sheet, colNo, nRow,5);
			nCell.setCellValue(printMap.get("Factory"));
			
			nRow = sheet.getRow(rowNo++);
			nCell = getCell(sheet, colNo, nRow,1);
			nCell.setCellValue(printMap.get("ContractNo"));
			nCell = getCell(sheet, colNo, nRow,5);
			nCell.setCellValue(printMap.get("Contacts"));
			
			nRow = sheet.getRow(rowNo++);
			nCell = getCell(sheet, colNo, nRow,1);
			nCell.setCellValue(printMap.get("SigningDate"));
			nCell = getCell(sheet, colNo, nRow,5);
			nCell.setCellValue(printMap.get("Phone"));
			
			nRow = sheet.getRow(rowNo++);
			nCell =getCell(sheet, colNo, nRow,4);
			nCell.setCellValue(printMap.get("ProductDescTitle"));
			
			nRow = sheet.getRow(rowNo++);
			nCell = getCell(sheet, colNo, nRow,1);
			nCell.setCellValue(printMap.get("ProductImage"));
			if(UtilFuns.isNotEmpty(printMap.get("ProductImage"))){
				this.setPicture(path+"/ufiles/jquery/"+printMap.get("ProductImage"), sheet, rowNo-1, 1, rowNo, 3);		//插入产品图片
			}			
			
			nCell =getCell(sheet, colNo, nRow,4);
			nCell.setCellValue(printMap.get("ProductDesc"));
			nCell = getCell(sheet, colNo, nRow,5);
			nCell.setCellValue(Integer.parseInt(printMap.get("Cnumber")));
			nCell = getCell(sheet, colNo, nRow,6);
			nCell.setCellValue(printMap.get("PackingUnit"));
			nCell = getCell(sheet, colNo, nRow,7);
			nCell.setCellValue(Double.parseDouble(printMap.get("Price")));
			
			nRow = sheet.getRow(rowNo++);
			nCell = getCell(sheet, colNo, nRow,1);
			nCell.setCellValue(printMap.get("ProductNo"));
			
			
			if(printMap.get("ProductNo2")!=null){			//第二款货不存在
				nRow = sheet.getRow(rowNo++);
				nCell = getCell(sheet, colNo, nRow,1);
				nCell.setCellValue(printMap.get("ProductImage2"));
				if(UtilFuns.isNotEmpty(printMap.get("ProductImage2"))){
					this.setPicture(path+"/ufiles/jquery/"+printMap.get("ProductImage2"), sheet, rowNo-1, 1, rowNo, 3);		//插入产品图片
				}
				
				nCell =getCell(sheet, colNo, nRow,4);
				nCell.setCellValue(printMap.get("ProductDesc2"));
				nCell = getCell(sheet, colNo, nRow,5);
				nCell.setCellValue(printMap.get("Cnumber2"));
				nCell = getCell(sheet, colNo, nRow,6);
				nCell.setCellValue(printMap.get("PackingUnit2"));
				nCell = getCell(sheet, colNo, nRow,7);
				nCell.setCellValue(printMap.get("Price2"));
				
				nRow = sheet.getRow(rowNo++);
				nCell = getCell(sheet, colNo, nRow,1);
				nCell.setCellValue(printMap.get("ProductNo2"));
			
			}else{				//没有第二款货物时空着
				rowNo++;
				rowNo++;
			}
			
			nRow = sheet.getRow(rowNo++);
			nCell = getCell(sheet, colNo, nRow,1);
			nCell.setCellValue(printMap.get("InputBy"));			
			nCell =getCell(sheet, colNo, nRow,4);
			nCell.setCellValue(printMap.get("CheckBy"));	
			
			rowNo++;
			nRow = sheet.getRow(rowNo);
			nCell = getCell(sheet, colNo, nRow,1);
			nCell.setCellValue("  "+printMap.get("Crequest"));
		}
		
		wb.removeSheetAt(0);					//删除模板sheet

		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		wb.write(bos);
		bos.close();
		
		du.download(bos, null, "购销合同.xls");
		wb.setFirstVisibleTab(1);
	}
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
	
	public Cell getCell(Sheet sheet,int rowNo,Row row,int index){
		Cell cell=null;
		try {
			
			 cell = row.getCell(index);
			if(cell == null){
				cell = row.createCell(index);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			row = sheet.createRow(rowNo);
			cell = row.createCell(index);
		}
		
		return cell;
	}
	
	//处理图片，excel中图片是单独对象存放
		public void setPicture(String pic, Sheet sheet, int startRow, int startCol, int stopRow, int stopCol) throws IOException{
			File imageFile = new File(pic);
			if(imageFile.exists()){
				InputStream is = new FileInputStream(new File(pic));
				byte[] bytes = IOUtils.toByteArray(is);
				int pictureIdx = sheet.getWorkbook().addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);		//扩展名可为.jpg/.jpeg/.png
				is.close();
				
				Drawing drawing = sheet.createDrawingPatriarch();	// Create the drawing patriarch.  This is the top level container for all shapes.
				//前面四个参数是图片偏移量
				ClientAnchor anchor = new HSSFClientAnchor(20, 1, 1020, 0, (short)startCol, startRow, (short)stopCol, stopRow);	//add a picture shape
				anchor.setRow1(startRow);							//set position corner of the picture		
				anchor.setCol1(startCol);
				anchor.setRow2(stopRow);
				anchor.setCol2(stopCol);
				
				drawing.createPicture(anchor, pictureIdx);
			}
		}
		
}
