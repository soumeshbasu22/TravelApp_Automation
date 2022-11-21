package com.travel_app_automation.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserFactory {
	
	@SuppressWarnings("deprecation")
	public static WebDriver browser(String browsername,WebDriver driver,String url) {
		
		if(browsername=="Chrome") {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\soumesh\\eclipse-workspace\\com.travel_app_automation\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browsername=="Headless") {
			ChromeOptions co=new ChromeOptions();
			co.setHeadless(true);
			driver=new ChromeDriver(co);
		}
		else if(browsername=="Edge") {
			
			System.setProperty("webdriver.edge.driver", "C:\\Users\\soumesh\\eclipse-workspace\\com.travel_app_automation\\msedgedriver.exe");
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		driver.get(url);
		return driver;
	}

}
