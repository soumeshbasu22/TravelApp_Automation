package com.travel_app_automation.Tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArraySorter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.travel_app_automation.pageObjects.BaseClass;
import com.travel_app_automation.pageObjects.ObjectRepository_CabRentalsPage;
import com.travel_app_automation.utilities.ExcelDataProvider2;
import com.travel_app_automation.utilities.ExcelDataProvider_RydeModule;
import com.travel_app_automation.utilities.edp_configuration;

public class TestCases_CabRentals extends BaseClass {
	ObjectRepository_CabRentalsPage or3;
	edp_configuration edpc;
	ExcelDataProvider2 edp2;
	ExcelDataProvider_RydeModule edp_r;
	@Test(priority=8,groups="Cab Rentals",enabled=false)
	public void outstation_cabRentalsPage() throws Exception {
		test=report.createTest("Cab Rentals Page");
		or3=new ObjectRepository_CabRentalsPage(driver);
		edpc=new edp_configuration();
		edp2=new ExcelDataProvider2();
		Assert.assertEquals(or3.cabrentals_outstation_chooseCar("Round Trip","Cab"), true);
		or3.cabrentals_outstation_chooseLoc(edpc.pickup_loc(),edpc.drop_loc());
		Thread.sleep(3000);
		or3.cabrentals_pick_dt("December 2022", 14,"1","15");
		
		or3.cabrentals_drp_dt("December 2022", 17, "1", "15");
		driver.findElement(or3.search_button).click();
		Thread.sleep(10000);
		or3.cab_search_page("Soumesh",edp2.username(1),edp2.username(5));
		WebDriverWait wait=new WebDriverWait(driver, 10000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(or3.search_result_page_tripInfo));
		Assert.assertEquals(driver.findElement(or3.search_result_page_tripInfo).isDisplayed(), true);
	} 
	@Test(priority=9,groups="Cab Rentals",enabled=false)
	public void cab_rentals_without_drop_loc() throws Exception {
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		or3=new ObjectRepository_CabRentalsPage(driver);
		edpc=new edp_configuration();
		test=report.createTest("Error when pick up location is not given");
		Thread.sleep(3000);
		or3.cabrentals_outstation_chooseCar("Round Trip", "Cab");
		or3.cabrentals_outstation_chooseLoc(edpc.pickup_loc(), " ");
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@style,'border-color')]")).isDisplayed(), true);
	}
	@Test(priority=9,groups="Cab Rentals",enabled=true)
	public void outstation_cabRentalsPage_invalidDuration() throws Exception {
		test=report.createTest("Cab Rentals Page");
		or3=new ObjectRepository_CabRentalsPage(driver);
		edpc=new edp_configuration();
		edp2=new ExcelDataProvider2();
		edp_r=new ExcelDataProvider_RydeModule();
		Assert.assertEquals(or3.cabrentals_outstation_chooseCar(edp_r.tripType(7),edp_r.rideType(7)), true);
		or3.cabrentals_outstation_chooseLoc(edpc.pickup_loc(),edpc.drop_loc());
		Thread.sleep(3000);
		
		or3.cabrentals_pick_dt(edp_r.monthOfTravel(7),Integer.parseInt(edp_r.StartDt(7)),"1","15");
		boolean AMSelected=driver.findElement(By.xpath("//h6[text()='AM']")).isSelected();
		boolean PMSelected=driver.findElement(By.xpath("//h6[text()='PM']")).isSelected();
		System.out.println(AMSelected);
		or3.cabrentals_drp_dt(edp_r.monthOfTravel(7),Integer.parseInt(edp_r.endDate(7)), "1", "15");
		driver.findElement(or3.search_button).click();
		Thread.sleep(4000);
		Assert.assertEquals(driver.findElement(or3.error_msg_changeDropoff).isDisplayed(), true);
		or3.invalid_timelineLogic(AMSelected, PMSelected);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//img[@src='/bushire/static/data/icons/close1.svg']")).click();
	}
	@Test(priority=10,groups="Cab Rentals",enabled=false)
	public void outstation_cabRentals_SortedByprice() throws Exception {
		test=report.createTest("Validate if price details displayed is already sorted");
		boolean priceSorted = false;
		List<Integer>sortedPriceList=new ArrayList<>();
		or3=new ObjectRepository_CabRentalsPage(driver);
		edpc=new edp_configuration();
		or3.cabrentals_outstation_chooseCar("One Way Trip", "Cab");
		or3.cabrentals_outstation_chooseLoc(edpc.pickup_loc(), edpc.drop_loc());
		or3.cabrentals_pick_dt("December 2022", 21, "1", "15");
		driver.findElement(or3.search_button).click();
		Thread.sleep(10000);
		List<WebElement>ListOfPrices=driver.findElements(By.cssSelector("span.hS_1nZZo18c1AToVHGBL"));
		for(int i=0;i<ListOfPrices.size();i++) {
			String price=ListOfPrices.get(i).getText().substring(2);
			int int_price=Integer.parseInt(price);
			sortedPriceList.add(int_price);
			System.out.println(int_price);
			
		}
		System.out.println(sortedPriceList);
		for(int j=0;j<sortedPriceList.size()-1;j++) {
			if(sortedPriceList.get(j)<=sortedPriceList.get(j+1)) {
				priceSorted=true;
			}
		}
		Assert.assertEquals(priceSorted, true);
	}
	@Test(priority=11,groups="Cab Rentals")
	public void cab_or_bus_option() {
		test=report.createTest("Validate if bus option is for 7 people max and cab is for 8 people");
		or3=new ObjectRepository_CabRentalsPage(driver);
		Assert.assertEquals(or3.cab_or_bus_option("Bus"), 8);
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("img.FudHijrUGCygBSqIUOu6")).click();
		driver.findElement(By.xpath("//img[@src='/bushire/static/webv2/home/logo-rb.svg']")).click();
	}
	@Test(priority=12,groups="Cab Rentals")
	public void bus_booking() throws Exception {
		test=report.createTest("Validate if number is buses are displayed after filling correct details");
		or3=new ObjectRepository_CabRentalsPage(driver);
		edpc=new edp_configuration();
		edp2=new ExcelDataProvider2();
		edp_r=new ExcelDataProvider_RydeModule();
		or3.cabrentals_outstation_chooseCar(edp_r.tripType(3), edp_r.rideType(3));
		or3.cabrentals_outstation_chooseLoc(edpc.pickup_loc(), edpc.drop_loc());
		
		or3.cabrentals_pick_dt(edp_r.monthOfTravel(3), Integer.parseInt(edp_r.StartDt(3)), "1", "23");
		boolean AMSelected=driver.findElement(By.xpath("//h6[text()='AM']")).isSelected();
		boolean PMSelected=driver.findElement(By.xpath("//h6[text()='PM']")).isSelected();
		
		or3.cabrentals_drp_dt("January 2023", 23, "1", "23");
		
		driver.findElement(or3.number_of_people_bus).sendKeys("10");
		driver.findElement(or3.search_button).click();
		SoftAssert sa=new SoftAssert();
		
			
			
		try {
			driver.findElement(or3.change_dropoff_button).isDisplayed();
			or3.invalid_timelineLogic(AMSelected,PMSelected);
			driver.findElement(By.xpath("//span[text()='OK']")).click();
			driver.findElement(or3.search_button).click();
		}catch (NoSuchElementException e) {
			// TODO: handle exception
		}finally {
			for(int i=0;i<2;i++) {
				try {
					WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(30));
					wait1.until(ExpectedConditions.visibilityOfElementLocated(or3.customer_name));
					or3.cab_search_page("Test", edp2.username(1), edp2.username(5));
					break;
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
					
				}}
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
			boolean isVisible_searchR=wait.until(ExpectedConditions.visibilityOfElementLocated(or3.search_result_page_tripInfo)).isDisplayed();
			sa.assertEquals(isVisible_searchR, true);
			
		}	
		
	}
		

}
