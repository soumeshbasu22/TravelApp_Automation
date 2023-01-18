package com.travel_app_automation.pageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.travel_app_automation.utilities.BrowserFactory;

public class BaseClass {
	
	public WebDriver driver;
	public ExtentReports report;
	public ExtentTest test;
	public ExtentTest childtest;
	public ExtentTest childtest1;
	public ExtentTest childtest2;
	public ExtentTest childtest3;
	public ExtentTest childtest4;
	public ExtentTest childtest5;
	public ExtentTest childtest6;
	public ExtentTest childtest7;
	public ExtentTest childtest8;
	Properties prop;
	@BeforeClass(groups="Smoke Test",alwaysRun = true)
	public  WebDriver setUp() {
		String config_filepath="C:\\Users\\soumesh\\git\\TravelApp_Automation\\com.travel_app_automation\\src\\test\\resources\\Browser_Data\\browser_config.properties";
		
		
		try {
			FileInputStream fis=new FileInputStream(config_filepath);
			prop=new Properties();
			prop.load(fis);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filepath="C:\\Users\\soumesh\\git\\TravelApp_Automation\\com.travel_app_automation\\Reports\\ExtentReport.html";
		ExtentHtmlReporter html=new ExtentHtmlReporter(filepath);
		report=new ExtentReports();
		report.attachReporter(html);
		driver=BrowserFactory.browser(prop.getProperty("browsername"),driver, prop.getProperty("url"));
		return driver;
		
	}
	/*@AfterClass
	public void tearDown() {
		
		driver.quit();
	}*/
	
	@AfterMethod(groups={"Smoke Test","Cab Rentals"},alwaysRun = true)
	public void reports(ITestResult result) {
		
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "TestCase id Passed");
		}
		else {
			test.log(Status.FAIL, "TestCase Failed");
		}
		report.flush();
	}

}
