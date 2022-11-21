package com.travel_app_automation.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.travel_app_automation.pageObjects.BaseClass;
import com.travel_app_automation.pageObjects.ObjectRepository_CabRentalsPage;
import com.travel_app_automation.utilities.edp_configuration;

public class TestCases_CabRentals extends BaseClass {
	ObjectRepository_CabRentalsPage or3;
	edp_configuration edpc;
	@Test(groups="Cab Rentals")
	public void outstation_cabRentalsPage() throws Exception {
		test=report.createTest("Cab Rentals Page");
		or3=new ObjectRepository_CabRentalsPage(driver);
		edpc=new edp_configuration();
		//or3.cabrentals_outstation("Round Trip","Cab");
		Assert.assertEquals(or3.cabrentals_outstation_chooseCar("Round Trip","Cab"), true);
		or3.cabrentals_outstation_chooseLoc(edpc.pickup_loc(),edpc.drop_loc());
	}

}
