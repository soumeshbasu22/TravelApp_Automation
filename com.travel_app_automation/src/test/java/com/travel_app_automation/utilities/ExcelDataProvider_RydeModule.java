package com.travel_app_automation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider_RydeModule {
	
	public XSSFWorkbook wb;
	public XSSFSheet sh;
	
	public ExcelDataProvider_RydeModule() throws IOException {
		String filepath="C:\\Users\\soumesh\\git\\TravelApp_Automation\\com.travel_app_automation\\Test_Data\\Ryde_Data.xlsx";
		FileInputStream fis=new FileInputStream(filepath);
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet("Sheet1");
	}
	public String tripType(int i) {
		
		return sh.getRow(i).getCell(0).getStringCellValue();
	}
	public String rideType(int i) {
		
		return sh.getRow(i).getCell(1).getStringCellValue();
	}
	public String monthOfTravel(int i) {
		
		return sh.getRow(i).getCell(2).getStringCellValue();
	}
	public String StartDt(int i) {
		
		return sh.getRow(i).getCell(3).getStringCellValue();
	}
	public String endDate(int i) {
		
		return sh.getRow(i).getCell(4).getStringCellValue();
	}

}
