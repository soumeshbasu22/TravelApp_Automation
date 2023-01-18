package com.travel_app_automation.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	//@SuppressWarnings("deprecation")
	public static WebDriver browser(String browsername,WebDriver driver,String url) {
		
		if(browsername.equals("Chrome")) {
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\soumesh\\git\\TravelApp_Automation\\com.travel_app_automation\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browsername.equals("Headless")) {
			ChromeOptions co=new ChromeOptions();
			co.setHeadless(true);
			driver=new ChromeDriver(co);
		}
		else if(browsername.equals("Edge")) {
			
			System.setProperty("webdriver.edge.driver", "C:\\Users\\soumesh\\git\\TravelApp_Automation\\com.travel_app_automation\\msedgedriver.exe");
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		driver.get(url);
		return driver;
	}

}
