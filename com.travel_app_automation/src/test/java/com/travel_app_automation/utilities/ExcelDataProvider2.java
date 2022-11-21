package com.travel_app_automation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider2 {
	
	public XSSFSheet sh1;
	public XSSFWorkbook wb1;
	
	public ExcelDataProvider2() throws Exception {
		
		String filepath1="C:\\Users\\soumesh\\eclipse-workspace\\com.travel_app_automation\\Test_Data\\UserCred.xlsx";
		FileInputStream fis1=new FileInputStream(filepath1);
		wb1=new XSSFWorkbook(fis1);
		sh1=wb1.getSheet("Sheet1");
	}
	
	public String username(int i) {
		
		return sh1.getRow(i).getCell(0).getStringCellValue();
	}

}
