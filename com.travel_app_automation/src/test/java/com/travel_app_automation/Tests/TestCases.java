package com.travel_app_automation.Tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.travel_app_automation.pageObjects.BaseClass;
import com.travel_app_automation.pageObjects.ObjectRepository_BusDetailsPage;
import com.travel_app_automation.pageObjects.ObjectRepository_LandingPage;
import com.travel_app_automation.utilities.BrowserFactory;
import com.travel_app_automation.utilities.ExcelDataProvider;
import com.travel_app_automation.utilities.ExcelDataProvider2;
import com.travel_app_automation.utilities.Helper;

public class TestCases extends BaseClass {
	
	//WebDriver driver;
	ExcelDataProvider edp;
	ExcelDataProvider2 edp2;
	Helper help;
	ObjectRepository_BusDetailsPage orb;
	ObjectRepository_LandingPage orl;
	
	@Test(priority=2,enabled=true,groups="Smoke Test")
	public void SearchBus() throws Exception {
		
		test=report.createTest("Search Buses without logging in");
		childtest4=test.createNode("View Seats button");
		edp=new ExcelDataProvider();
		help=new Helper();
		driver.navigate().refresh();
		orb=new ObjectRepository_BusDetailsPage(driver);
		ObjectRepository_LandingPage or=new ObjectRepository_LandingPage(driver);
		
		for(int i=1;i<2;i++) {
			
			or.routeSearch(edp.fromDest(i),edp.toDest(i));
			or.calAutomate("Oct 2022","27");
			
			Thread.sleep(3000);
			if(driver.findElement(By.xpath("//*[contains(text(),'Kolkata')][@title='Kolkata']")).isDisplayed()) {
				
				//help.write_excel(3, 4, "P");
				test.log(Status.PASS, "Test case passed-Search successful");
			}
			else {
				//help.write_excel(3, 4, "F");
				test.log(Status.FAIL, "Test case failed-Search unsuccessful");
			}
			if(orb.marker5=="Pass") {
				childtest4.log(Status.PASS, "View seats button is clickable");
			}
			else if(orb.marker5=="Fail"){
				childtest4.log(Status.FAIL, "View seats button is not visible");
			}
			
		}
	}
	@Test(priority=4,enabled=false)
	public void invalidRouteSearch() throws Exception {
		
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		test=report.createTest("Invalid Route Search");
		ObjectRepository_LandingPage or=new ObjectRepository_LandingPage(driver);
		edp=new ExcelDataProvider();
		int rownum=edp.sh.getLastRowNum()-edp.sh.getFirstRowNum();
		for(int i=5;i<=rownum;i++) {
			or.routeSearch(edp.fromDest(i), edp.toDest(i));
			or.calAutomate("September", "27");
			try {
				Boolean inv_rt=driver.findElement(orb.invalid_route_msg).isDisplayed();
				test.log(Status.PASS, "'"+inv_rt+"'"+" message is displayed");
			}
			catch(NoSuchElementException e) {
				test.log(Status.FAIL, "Invalid route message is not displayed");
			}
		}
	}
	@Test(priority=3,enabled=false)
	public void otp_login_while_viewing_seats() throws Exception {	
		
		test=report.createTest("OTP Login while viewing seats");
		childtest=test.createNode("Error message check");
		childtest1=test.createNode("Mobile number textbox");
		childtest2=test.createNode("Verify OTP Button");
		orb=new ObjectRepository_BusDetailsPage(driver);
		edp=new ExcelDataProvider();
		try {
			int rownum=edp.sh.getLastRowNum()-edp.sh.getFirstRowNum();
			
			for(int i=1;i<=rownum;i++) {
				
				String vendor=edp.bus_vendors(i);
				
		
				orb.ticket_booking(vendor);
				if(orb.marker=="Fail") {
					childtest.log(Status.FAIL, "No error message displayed");
				}
				else if(orb.marker=="Pass")  {
					childtest.log(Status.PASS, "Error message displayed");
				}
				if(orb.marker1=="Pass") {
					childtest1.log(Status.PASS, "Mobile No checkbox is present");
				}
				else if (orb.marker1=="Fail") {
					childtest1.log(Status.FAIL, "mobile no textbox not present");
				}
				if(orb.marker2=="Pass") {
					childtest2.log(Status.PASS, "Verify OTP Button is working");
				}
				else if(orb.marker2=="Fail") {
					childtest2.log(Status.FAIL, "Verify OTP Button is not working");
				}
			}
		}catch(Exception e) {
			
			
		}edp.wb.close();
		
	}
	
	@Test(priority=1,enabled=false)
	public void home_page_login() throws Exception {
		edp2=new ExcelDataProvider2();
		test=report.createTest("Logging in from home page");
		childtest5=test.createNode("Login button in home page");
		childtest6=test.createNode("Login through mobile OTP");
		childtest7=test.createNode("Login attempt result");
		orl=new ObjectRepository_LandingPage(driver);
		int rownum=edp2.sh1.getLastRowNum()-edp2.sh1.getFirstRowNum();
		for(int i=1;i<=rownum;i++) {
			if(!edp2.username(i).contains("@")) {
				if(edp2.username(i).length()==10) {
				orl.login_homepage(edp2.username(i));
				if(orl.flag1==1) {
					childtest5.log(Status.PASS, "Login button clicked");
				}
				else {
					childtest5.log(Status.FAIL, "Login button not clicked");
				}
				if(orl.flag2==1) {
					childtest6.log(Status.PASS, "OTP Sent to mobile successfully");
				}
				else {
					childtest6.log(Status.FAIL, "OTP not Sent to mobile successfully");
				}
				orl.logout();
				if(orl.flag3==1) {
					childtest7.log(Status.PASS, "OTP Verified and logged in and logged out");
				}
				else {
					childtest7.log(Status.FAIL, "Login failure");
				}
			}
			}	
			
		}
	}
	
	@Test(priority=5,enabled=false)
	public void book_tickets() throws Exception {
		edp2=new ExcelDataProvider2();
		edp=new ExcelDataProvider();
		test=report.createTest("Book tickets end to end");
		childtest8=test.createNode("Booking proceeded till passenger details page");
		orl=new ObjectRepository_LandingPage(driver);
		orb=new ObjectRepository_BusDetailsPage(driver);
		int rownum=edp2.sh1.getLastRowNum()-edp2.sh1.getFirstRowNum();
		int rownum1=edp.sh.getLastRowNum()-edp2.sh1.getFirstRowNum();
		for(int i=1;i<=rownum;i++) {
			if(!edp2.username(i).contains("@") & edp2.username(i).length()==10) {
				orl.login_homepage(edp2.username(i));
				
				for(int j=1;j<=rownum1;j++) {
					orl.routeSearch(edp.fromDest(j),edp.toDest(j));
					orl.calAutomate("Oct 2022","27");
					orb.book_ticket(edp.boardingPoint(j), edp.droppingPoint(j), edp.bus_vendors(j));
					driver.navigate().back();
					if(orb.marker6.equals("Pass")) {
						childtest8.log(Status.PASS, "Passenger details page displayed");
					}
					else {
						childtest8.log(Status.FAIL, "passenger details page not displayed");
					}
				}
			}
		}
		
	}

}
