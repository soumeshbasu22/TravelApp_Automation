package com.travel_app_automation.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class edp_configuration {
	
	Properties prop;
	String filepath="C:\\Users\\soumesh\\eclipse-workspace\\com.travel_app_automation\\src\\test\\resources\\Cab_Data\\cab_timing.txt";
	public edp_configuration() {
		try {
			FileInputStream fis=new FileInputStream(filepath);
			prop=new Properties();
			prop.load(fis);
			prop.get("pickup");
		}catch(Exception e) {
			
		}
	}
	public String pickup_loc() {
		return prop.getProperty("pickup");
	}
	public String drop_loc() {
		return prop.getProperty("Drop");
	}

}
