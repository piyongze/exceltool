package com.pyz.testtool.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pyz.testtool.service.ExcelService;
import com.pyz.testtool.tool.ExcelTool;
import com.pyz.testtool.tool.HibernateUtil;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//write();
		//read();
		System.out.println("begin");
		long begin=System.currentTimeMillis();
		ExcelTool.setWorkbook("e:/xslx/test.xlsx");
		ExcelTool.ergodic(new ExcelService());
		ExcelTool.closeFile();
		HibernateUtil.closeSessionFactory();
		System.out.println("end");
		long end=System.currentTimeMillis();
		System.out.println("用时："+(end-begin)/1000+"s");
	}
	
	public static void write() throws Exception{
		//创建工作簿
				Workbook wb=new XSSFWorkbook();
				//创建sheet
				Sheet sheet1=wb.createSheet("new sheet");
				Sheet sheet2=wb.createSheet("second sheet");
				
				CreationHelper createHelper=wb.getCreationHelper();
				
				//创建行
				Row row=sheet1.createRow(0);
				//创建cell
				row.createCell(0).setCellValue(1);
				row.createCell(1).setCellValue(1.2);
				row.createCell(2).setCellValue(createHelper.createRichTextString("i am test"));
				row.createCell(3).setCellValue(true);
				
				//创建cell风格
				CellStyle cellStyle = wb.createCellStyle();
			    cellStyle.setDataFormat(
			        createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
				Cell cell=row.createCell(4);
				cell.setCellValue(new Date());
				cell.setCellStyle(cellStyle);
				
				FileOutputStream fileOut;

				fileOut = new FileOutputStream("E:/xslx/test.xlsx");
				//写入文件
				wb.write(fileOut);
				fileOut.close();
				System.out.println("执行结束");
	}
	
	public static void read() throws Exception{
		 	
			InputStream inp = new FileInputStream("E:/xslx/test.xlsx");

		    Workbook wb = WorkbookFactory.create(inp);
		    Sheet sheet = wb.getSheetAt(0);
		    Row row = sheet.getRow(2);
		    Cell cell = row.getCell(3);
		    if (cell == null)
		        cell = row.createCell(3);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    cell.setCellValue("a test");

		    // Write the output to a file
		    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
		    wb.write(fileOut);
		    fileOut.close();
	}

}
