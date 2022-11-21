package com.travel_app_automation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	public XSSFWorkbook wb;
	public XSSFSheet sh;
	
	public ExcelDataProvider() throws Exception {
		
		String filepath="C:\\Users\\soumesh\\eclipse-workspace\\com.travel_app_automation\\Test_Data\\Bus_Vendors.xlsx";
		FileInputStream fis=new FileInputStream(filepath);
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet("Sheet1");
	}
	
	public String bus_vendors(int i) {
		
		return sh.getRow(i).getCell(0).getStringCellValue();
		
	}
	public String fromDest(int i) {
			
		return sh.getRow(i).getCell(1).getStringCellValue();
			
	}
	public String toDest(int i) {
			
		return sh.getRow(i).getCell(2).getStringCellValue();
			
	}
	public String boardingPoint(int i) {
		
		return sh.getRow(i).getCell(3).getStringCellValue();
	}
	public String droppingPoint(int i) {
		
		return sh.getRow(i).getCell(4).getStringCellValue();
	}
}
