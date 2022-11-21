package com.travel_app_automation.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.travel_app_automation.pageObjects.BaseClass;
import com.travel_app_automation.pageObjects.ObjectRepository_BusDetailsPage;
import com.travel_app_automation.pageObjects.ObjectRepository_LandingPage;
import com.travel_app_automation.utilities.ExcelDataProvider;

public class TestCases_Set2 extends BaseClass {
	
	ObjectRepository_LandingPage or1;
	ObjectRepository_BusDetailsPage or2;
	ExcelDataProvider edp;
	
	@Test(priority=7,enabled=false)
	public void filters() throws Exception {
		
		test=report.createTest("Verify the filtered results");
		edp=new ExcelDataProvider();
		or1=new ObjectRepository_LandingPage(driver);
		or2=new ObjectRepository_BusDetailsPage(driver);
		or1.calAutomate("Nov 2022", "27");
		or1.routeSearch(edp.fromDest(2), edp.toDest(2));
		SoftAssert sa=new SoftAssert();
		Thread.sleep(5000);
		Assert.assertEquals(or2.time_filter(), true);
		sa.assertEquals(or2.bus_type_filter(),true);
		Assert.assertEquals(or2.filter_boarding_pt(), true);
	}

}
