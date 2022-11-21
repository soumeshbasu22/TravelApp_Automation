package com.travel_app_automation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.travel_app_automation.utilities.ExcelDataProvider;

public class ObjectRepository_LandingPage {
	
	ExcelDataProvider edp;
	WebDriver driver;
	public static By FromDest=By.xpath("//*[text()='FROM']");
	public static By toDest=By.xpath("//*[text()='TO']");
	public static By travelDate=By.xpath("//*[text()='Date']");
	public static By searchButton=By.xpath("//*[contains(text(),'Search Buses')]");
	public static By login_button=By.xpath("//*[@id='i-icon-profile']");
	public static By signUp_shadow=By.xpath("//*[@id='signInLink']");
	public static By sign_out_link=By.xpath("//*[contains(@id,'signOutLink')]");
	public int flag1;
	public int flag2;
	public int flag3;

	
	public ObjectRepository_LandingPage(WebDriver driver){
		
		this.driver=driver;
		
	}
	
	public void calAutomate(String Month_Year,String date) {
		
		driver.findElement(travelDate).click();
		
		for(int k=0;k<=60;k++) {
		
		if(driver.findElement(By.xpath("//*[contains(@class,'first last')]//tr[1]//td[2]")).getText().equals(Month_Year)) {
		List<WebElement>cal=driver.findElements(By.xpath("//*[contains(@class,'first last')]//tr"));
		
		for(int i=4;i<cal.size();i++) {
			
			List<WebElement>cols=driver.findElements(By.xpath("//*[contains(@class,'first last')]//tr["+i+"]//td"));
			System.out.println(cols.size());
			
			
			for(int j=0;j<cols.size();j++) {
			
			if(cols.get(j).getText().equals(date)) {
				System.out.println(cols.get(j).getText());
				driver.findElement(By.xpath("//*[contains(@class,'first last')]//tr["+i+"]//td["+(j+1)+"]")).click();
				System.out.println(driver.findElement(By.xpath("//*[contains(@class,'first last')]//tr["+i+"]//td["+(j+1)+"]")).getAttribute("class"));
				break;
				}
			}
		}
		break;
		}
		else {
			driver.findElement(By.xpath("//*[contains(@class,'first last')]//tr[1]//td[3]")).click();
		}
		}
		driver.findElement(searchButton).click();
		
	}
	
	public void routeSearch(String fromPlace,String toPlace) throws Exception {
		
		Actions act=new Actions(driver);
		act.sendKeys(driver.findElement(FromDest), fromPlace)
		.sendKeys(Keys.TAB)
		.sendKeys(toPlace)
		.build().perform();
		List<WebElement>fplaces=driver.findElements(By.xpath("//*[contains(text(),"+fromPlace+")]"));
		fplaces.get(0).click();
		List<WebElement>tplaces=driver.findElements(By.xpath("//*[contains(text(),"+toPlace+")]"));
		tplaces.get(0).click();
	}
	
	public void login_homepage(String phone_nos) throws Exception {
		ObjectRepository_BusDetailsPage orb=new ObjectRepository_BusDetailsPage(driver);
		try {
			driver.findElement(login_button).click();
			driver.findElement(signUp_shadow).click();
			flag1=1;
		}
		catch(Exception e){
			flag1=2;
		}
		try {
			driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='modalIframe']")));
			driver.findElement(orb.mobile_no_textbox).sendKeys(phone_nos);
			Thread.sleep(10000);
			driver.findElement(orb.otp_button).click();
			flag2=1;
		}catch(Exception e) {
			flag2=2;
		}
		Thread.sleep(20000);
		try {
			driver.findElement(orb.verify_otp).click();
			Thread.sleep(2000);
			driver.findElement(login_button).click();
			if(driver.findElement(sign_out_link).isDisplayed()) {
				Thread.sleep(2000);
				flag3=1;
			}
		}catch (Exception e) {
			flag3=2;
		}
		
	}
	public void logout() throws Exception {
		
		try {
		
		driver.findElement(sign_out_link).click();
		
		}catch (Exception e) {
			
		}
	}
	
	}
