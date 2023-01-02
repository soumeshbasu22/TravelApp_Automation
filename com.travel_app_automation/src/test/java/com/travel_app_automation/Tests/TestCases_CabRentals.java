package com.travel_app_automation.Tests;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArraySorter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.travel_app_automation.pageObjects.BaseClass;
import com.travel_app_automation.pageObjects.ObjectRepository_CabRentalsPage;
import com.travel_app_automation.utilities.ExcelDataProvider2;
import com.travel_app_automation.utilities.edp_configuration;

public class TestCases_CabRentals extends BaseClass {
	ObjectRepository_CabRentalsPage or3;
	edp_configuration edpc;
	ExcelDataProvider2 edp2;
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
	@Test(priority=9,groups="Cab Rentals",enabled=false)
	public void outstation_cabRentalsPage_invalidDuration() throws Exception {
		test=report.createTest("Cab Rentals Page");
		int days_req=0;
		String hour_val="";
		int hour_val_digit=0;
		or3=new ObjectRepository_CabRentalsPage(driver);
		edpc=new edp_configuration();
		edp2=new ExcelDataProvider2();
		Assert.assertEquals(or3.cabrentals_outstation_chooseCar("Round Trip","Cab"), true);
		or3.cabrentals_outstation_chooseLoc(edpc.pickup_loc(),edpc.drop_loc());
		Thread.sleep(3000);
		
		or3.cabrentals_pick_dt("December 2022",19,"1","15");
		boolean AMSelected=driver.findElement(By.xpath("//h6[text()='AM']")).isSelected();
		boolean PMSelected=driver.findElement(By.xpath("//h6[text()='PM']")).isSelected();
		System.out.println(AMSelected);
		or3.cabrentals_drp_dt("December 2022", 19, "1", "15");
		Thread.sleep(4000);
		Assert.assertEquals(driver.findElement(or3.error_msg_changeDropoff).isDisplayed(), true);
		String msg=driver.findElement(or3.error_msg_timeRequired).getText();
		String[] msg_arr=driver.findElement(or3.error_msg_timeRequired).getText().split(" ");
		System.out.println(msg_arr[0]);
		days_req=Integer.parseInt(msg_arr[0]);
		hour_val_digit=Integer.parseInt(msg_arr[2]);
		int total_hr=(days_req*24)+hour_val_digit;
		System.out.println(days_req);
		System.out.println("Total hour required is:"+total_hr);
		Thread.sleep(2000);
		try {
			driver.findElement(By.cssSelector("div.z1MPAJlvYE_YTNk89cmb")).click();
		}catch (Exception e) {
			
		}
		Thread.sleep(2000);
		String selected_dt=driver.findElement(By.xpath("//button[contains(@class,'daySelected')]")).getText();
		System.out.println("Selected date is:"+ selected_dt);
		if(AMSelected) {
			Assert.assertEquals(selected_dt, 19+(total_hr/24));
		}
		else if(PMSelected) {
			Assert.assertEquals(selected_dt, 19+1+(total_hr/24));
		}
	}
	@Test(groups="Cab Rentals",enabled=false)
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
	@Test(groups="Cab Rentals")
	public void cab_or_bus_option() {
		test=report.createTest("Validate if bus option is for 7 people max and cab is for 8 people");
		or3=new ObjectRepository_CabRentalsPage(driver);
		Assert.assertEquals(or3.cab_or_bus_option("Bus"), 8);
	}

}
