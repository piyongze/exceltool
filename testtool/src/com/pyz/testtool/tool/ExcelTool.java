package com.pyz.testtool.tool;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

/**
 * 注意excel数据类型与对象属性类型的转换
 * @author PiYongze
 *
 */
public class ExcelTool {

	private static InputStream in;
	private static Workbook workbook;
	
	public static void setWorkbook(String path) throws Exception{
		in=new FileInputStream(path);
		workbook=WorkbookFactory.create(in);
	}
	
	public static void closeFile() throws Exception{
		in.close();
	}
	
	public static void ergodic(ExcelHandle handle) throws Exception{
		Sheet sheet=workbook.getSheet("sheet1");
		//获得行数 从0开始
		int rows=sheet.getLastRowNum();
		System.out.println("行数:"+rows);
		//获取列数  必须有行
		int columns=sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("列数:"+columns);
		Map domMap=Dom4jTool.handle();
		Map map;
		for (Row row : sheet) {
			map=new HashMap();
	        for (Cell cell : row) {
	            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            System.out.print(cellRef.formatAsString());
	            System.out.print(" - ");

	            switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_STRING:
	                    map.put(domMap.get(cell.getColumnIndex()+""), cell.getRichStringCellValue().getString());
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
		                    map.put(domMap.get(cell.getColumnIndex()+""), cell.getDateCellValue());
	                    } else {
		                    map.put(domMap.get(cell.getColumnIndex()+""), (int)cell.getNumericCellValue());
	                    }
	                    break;
	                case Cell.CELL_TYPE_BOOLEAN:
	                    map.put(domMap.get(cell.getColumnIndex()+""), cell.getBooleanCellValue());
	                    break;
	                case Cell.CELL_TYPE_FORMULA:
	                    map.put(domMap.get(cell.getColumnIndex()+""), cell.getCellFormula());
	                    break;
	                default:
	                    System.out.println("error");
	            }
	        }
	      //一行放入一个map 进行处理
			handle.handle(map);
	    }
	}
}
