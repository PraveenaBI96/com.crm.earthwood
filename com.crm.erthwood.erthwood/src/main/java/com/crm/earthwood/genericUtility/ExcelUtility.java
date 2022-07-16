package com.crm.earthwood.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *  It's developed using Apache-Poi Libraries, it's used to handle Microsoft Excel
 *  @author Praveen
 */
public class ExcelUtility {
/**
 * It's used to read the data from Excel sheets based on below arguments 
 * @param SheetName
 * @param RowNum
 * @param CellNum
 * @return data
 * @throws Throwable 
 * @throws Throwable
 */
	public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./data/vTigerdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row=sh.getRow(rowNum);
		DataFormatter format = new DataFormatter();
		String data= format.formatCellValue(row.getCell(cellNum));
		wb.close();
		return data;
	}
	/**
	 * It's used to get the Last used Row number on specified Sheet
	 * @return rowCount
	 * @throws Throwable 
	 * 
	 */
	public int getRowCount(String sheetName) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./data/vTigerdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount=sh.getLastRowNum();
		wb.close();
		return rowCount;
	}
	/**
	 * It's used to get the last used cell number of Specified row on specified sheet
	 * @throws Throwable 
	 */
	public int getCellCount(String sheetName,int rowNum,int cellNum) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./data/vTigerdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int lastCell=sh.getRow(rowNum).getLastCellNum();
		wb.close();
		return lastCell;
	}
	/**
	 * It's used to set the data to cell on specified row and Sheet
	 * @throws Throwable 
	 * 
	 */
	public void setDataExcel(String sheetName,int rowNum,int cellNum,String data) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./data/vTigerdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row=sh.getRow(rowNum);
		Cell cell = row.createCell(cellNum);
		cell.setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./data/vTigerdata.xlsx");
		wb.write(fos);
		wb.close();
    }

}