package com.travel_app_automation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Helper {
	String filepath="C:\\Users\\soumesh\\eclipse-workspace\\com.travel_app_automation\\TestCases\\RedBus_TestApp.xlsx";
	XSSFWorkbook WB;
	XSSFSheet SH;
	public Helper() throws Exception{
		FileInputStream fi=new FileInputStream(filepath);
		WB=new XSSFWorkbook(fi);
		SH=WB.getSheet("Sheet1");
	}
	public void write_excel(int a,int b,String msg) throws Exception {
		
		Row newrow=SH.getRow(a);
		Cell newcell=newrow.createCell(b);
		newcell.setCellValue(msg);
		FileOutputStream fo=new FileOutputStream(filepath);
		WB.write(fo);
	}

}
