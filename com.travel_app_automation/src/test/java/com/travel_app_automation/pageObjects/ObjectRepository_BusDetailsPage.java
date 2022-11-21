package com.travel_app_automation.pageObjects;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.travel_app_automation.Tests.TestCases;
import com.travel_app_automation.utilities.ExcelDataProvider2;
import com.travel_app_automation.utilities.Helper;


public class ObjectRepository_BusDetailsPage {
	
	WebDriver driver;
	String parentwin;
	Helper help;
	public BaseClass bc;
	public TestCases tc;
	int count=0;
	public String marker;
	public String marker1;
	public String marker2;
	public String marker5;
	public String marker6;
	ExcelDataProvider2 edp2;
	public static By invalid_route_msg=By.xpath("//div[contains(text(),'No')]");
	public static By vend_name=By.xpath("//*[contains(text(),'ROYAL CRUISER')]");
	public static By sign_in_google=By.xpath("//*[@class='nsm7Bb-HzV7m-LgbsSe-BPrWId'][contains(text(),'Sign in with Google')]");
	public static By bootstrap_popup=By.xpath("//*[@class='modalFrame']");
	public static By google_uname=By.xpath("//*[@autocomplete='username']");
	public static By popup_close=By.xpath("//*[contains(@class,'close closepopupbtn')]");
	public static By mobile_no_textbox=By.xpath("//input[contains(@id,'mobileNoInp')]");
	public static By error_msg=By.xpath("//span[contains(text(),'Please enter valid mobile number')]");
	public static By otp_button=By.xpath("//span[contains(text(),'GENERATE OTP')]");
	public static By verify_otp=By.xpath("//*[@id='verifyUser']");
	public static By dropping_pt=By.xpath("//span[contains(text(),'DROPPING')]");
	public static By continue_button_booking=By.xpath("//button[contains(text(),'Proceed')]");
	public static By passenger_info_page=By.xpath("//span[text()='Passenger Information']");
	public static By time_filter=By.xpath("//label[contains(@for,'dt12 pm')][@class='custom-checkbox']");
	public static By all_bus_list=By.xpath("//li[@class='row-sec clearfix']");
	public static By boarding_pt_filter=By.xpath("//input[@placeholder='BOARDING POINT']");
	public static By boarding_pt_popup=By.xpath("//input[@name='inpFilter']");
	public static By airport_bp=By.xpath("(//label[@for='bp_Airport'])[1]");
	public static By apply_filter_btn=By.xpath("//div[@class='button btn-apply bp-apply']");
	public WebElement boarding_pt(String bp) {
		 
		return driver.findElement(By.xpath("//span[contains(text(),'"+bp+"')][contains(@class,'loc')]"));

	}
	public String view_seats_button(int j,String vendorInput) {
		
		return "(//*[contains(text(),'"+vendorInput+"')]//following::div[@class='button view-seats fr'])[1]";
			
	}
	public String hide_seats_button(int k,String vendorInput) {
		
		return "//*[contains(text(),'"+vendorInput+"')]//following::div[@class='button hide-seats fr']["+k+"]";
			
	}
	public static String seater(int r) {
		return "(//label[@class='custom-checkbox'])["+r+"]";
	}
	public ObjectRepository_BusDetailsPage(WebDriver driver){
		
		this.driver=driver;
		
	}
	public void ticket_booking(String vendor) throws Exception {
		
		help=new Helper();
			
			try {
					String bus_vendor;
					List<WebElement>vendors=driver.findElements(By.xpath("//*[contains(text(),'"+vendor+"')]"));
					System.out.println(vendors);
					if(vendors.size()<2) {
						bus_vendor=driver.findElement(By.xpath("//*[contains(text(),'"+vendor+"')]")).getText();
					}
					else {
						bus_vendor=vendors.get(1).getText();
						System.out.println(bus_vendor);
					}
					System.out.println("Vendor name is"+bus_vendor);
					if(bus_vendor.contains(vendor)&& !bus_vendor.isEmpty()) {
						
						for(int j=1;j<=330;j++) {
							
						System.out.println(j);
						String vs_xpath=view_seats_button(j,vendor);
						System.out.println(vs_xpath);
						String seat=driver.findElement(By.xpath(vs_xpath)).getText();
						System.out.println(seat);
						if(seat.equalsIgnoreCase("View Seats")){
						Thread.sleep(2000);
						if(driver.findElement(By.xpath(vs_xpath)).isDisplayed()) {
							count+=1;
							
							System.out.println("p");
							System.out.println(count);
							//if(count>0) {
								Actions a=new Actions(driver);
								a.moveToElement(driver.findElement(By.xpath(vs_xpath))).click().build().perform();
								//help.write_excel(4, 4, "P");
								marker5="Pass";
							//}
							/*else {
								//help.write_excel(4, 4, "F");
								marker5="Fail";
							}*/
						}
						
						if(driver.findElement(bootstrap_popup).isDisplayed()) {
							System.out.println("POP UP!!!");
							parentwin=driver.getWindowHandle();
							Thread.sleep(3000);
							driver.switchTo().frame("restStopIframe");
							System.out.println("in frame 1");
							//driver.findElement(By.xpath("//input[contains(@id,'mobileNoInp')]")).sendKeys("9898");
							driver.switchTo().frame(1);
							System.out.println("in frame 2");
							driver.findElement(sign_in_google).click();
							Thread.sleep(2000);
							sign_in_google(1);
							}
						Thread.sleep(2000);
						//driver.navigate().back();
						break;
						}
						
						}
					}
				
			}catch(Exception e) {
				System.out.println("jschsjchkas");
				System.out.println(e.getMessage());
			}finally {
				System.out.println("the count is"+count);
				if(count==1) {
					driver.findElement(By.xpath(hide_seats_button(count, vendor))).click();
				}
				else {
					driver.findElement(By.xpath(hide_seats_button(count-1, vendor))).click();
				}
			}
			try {
				
			}catch(Exception e) {
				 
			}
		}
	public void sign_in_google(int i) throws Exception {
		edp2=new ExcelDataProvider2();
		Set<String>windows=driver.getWindowHandles();
		System.out.println(windows.size());
		for(String m:windows) {
			
			driver.switchTo().window(m);
			Thread.sleep(3000);
			String title=driver.getTitle();
			if(title.contains("Sign")) {
				driver.findElement(google_uname).sendKeys(edp2.username(i));
				Thread.sleep(3000);
			}
			
			driver.switchTo().window(parentwin);
			System.out.println(driver.getTitle());
		}
		//driver.switchTo().frame("restStopIframe");
		sign_in_phone();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//input[contains(@id,'mobileNoInp')]")).sendKeys("9898");
		driver.switchTo().parentFrame();
		driver.findElement(popup_close).click();
		Thread.sleep(3000);
		
	}
	
	public void sign_in_phone() throws Exception {
		tc=new TestCases();
		if(driver.findElement(bootstrap_popup).isDisplayed()) {
			driver.switchTo().frame("restStopIframe");
			int rownum=edp2.sh1.getLastRowNum()-edp2.sh1.getFirstRowNum();
			for(int j=1;j<=rownum;j++) {
				if(!edp2.username(j).contains("@")) {
					try {
					driver.findElement(mobile_no_textbox).sendKeys(edp2.username(j));
					marker1="Pass";
					//help.write_excel(5, 4, "P");
					//tc.childtest.log(Status.PASS, "Mobile no textbox is displayed");
					if(edp2.username(j).length()<10) {
						
						try {
						driver.findElement(error_msg).isDisplayed();
						//help.write_excel(6, 4, "P");
						System.out.println(edp2.username(j)+" is the input");
						marker="Pass";
						
					}catch (NoSuchElementException e) {
						//help.write_excel(6, 4, "F");
						System.out.println(edp2.username(j)+" is the input");
						marker="Fail";
					}
					}
					}catch (Exception e) {
						System.out.println("in catch block");
						//help.write_excel(5, 4, "F");
						marker1="Fail";
						
					}
					Thread.sleep(18000);
					try {
					driver.findElement(otp_button).click();
					Thread.sleep(25000);
					driver.findElement(verify_otp).click();
					//help.write_excel(7, 4, "P");
					marker2="Pass";
					}catch (Exception e) {
						//help.write_excel(7, 4, "F");
						marker2="Fail";
					}
						
				}Thread.sleep(6000);
				 driver.findElement(mobile_no_textbox).clear();
					
			}
		}
	}
	
	public void book_ticket(String bp,String dp,String vendor) throws Exception {
		
		try {
			Thread.sleep(5000);
			//driver.navigate().refresh();
			System.out.println(view_seats_button(1,vendor));
			Thread.sleep(10000);
			System.out.println(driver.manage().window().getSize());
			
			//driver.findElement(By.xpath("//div[@class='details']//ul//li//span[contains(text(),'Live Tracking')]")).sendKeys(Keys.chord(Keys.CONTROL+"-"+"-"));
			//((JavascriptExecutor)driver).executeScript("scroll(0,1000)");
			Actions a=new Actions(driver);
			a.moveToElement(driver.findElement(By.xpath(view_seats_button(1,vendor)))).build().perform();
			Thread.sleep(2000);
			a.click(driver.findElement(By.xpath(view_seats_button(1,vendor)))).build().perform();;
			
			Thread.sleep(5000);
			boarding_pt(bp).click();
			driver.findElement(dropping_pt).click();
			boarding_pt(dp).click();
			driver.findElement(continue_button_booking).click();
			try {
				if(driver.findElement(passenger_info_page).isDisplayed()) {
					marker6="Pass";
				}
					
			}catch (Exception e) {
				marker6="Fail";
				System.out.println(e.getMessage());
			}
			}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean time_filter() throws InterruptedException {
		boolean filter_result=false;
		int initial_size=driver.findElements(all_bus_list).size();
		driver.findElement(time_filter).click();
		int final_size=driver.findElements(all_bus_list).size();
		if(initial_size>final_size) {
			filter_result=true;
		}
		//driver.findElement(time_filter).click();
		Thread.sleep(3000);
		
		return filter_result;
	}
	public boolean bus_type_filter() throws InterruptedException {
		boolean filter_result=false;
		driver.navigate().refresh();
		Thread.sleep(6000);
		String no_of_buses=driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
		String initial_buses=no_of_buses.substring(0, 2);
		
		int filtered_buses=Integer.parseInt(initial_buses);
		
		for(int i=5;i<8;i++) {
			String final_b="";
			driver.navigate().refresh();
			driver.findElement(By.xpath(seater(i))).click();
			Thread.sleep(6000);
			String final_no_of_buses=driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
			String final_buses=final_no_of_buses.substring(0, 2);
			if(final_buses.endsWith(" ")) {
				final_b=final_buses.trim();
				
			}
			else {
				final_b=final_buses;
			}
			int final_filtered_buses=Integer.parseInt(final_b);
			
			if(filtered_buses>final_filtered_buses) {
				filter_result=true;
				
			}
			
		}
		return filter_result;
	}
	public boolean filter_boarding_pt() throws Exception {
		driver.navigate().refresh();
		String final_b="";
		boolean filter_result=false;
		String no_of_buses=driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
		String initial_buses=no_of_buses.substring(0, 2);
		
		int filtered_buses=Integer.parseInt(initial_buses);
		Actions a=new Actions(driver);
		a.moveToElement(driver.findElement(boarding_pt_filter)).build().perform();
		Thread.sleep(3000);
		driver.findElement(boarding_pt_filter).click();
		driver.findElement(boarding_pt_popup).sendKeys("Airport");
		driver.findElement(airport_bp).click();
		driver.findElement(apply_filter_btn).click();
		String final_no_of_buses=driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
		String final_buses=final_no_of_buses.substring(0, 2);
		if(final_buses.endsWith(" ")) {
			final_b=final_buses.trim();
			
		}
		else {
			final_b=final_buses;
		}
		int final_filtered_buses=Integer.parseInt(final_b);
		
		if(filtered_buses>final_filtered_buses) {
			filter_result=true;
			
		}
		return filter_result;
	}
	}
