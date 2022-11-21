package com.travel_app_automation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.travel_app_automation.utilities.edp_configuration;

public class ObjectRepository_CabRentalsPage {
	
	WebDriver driver;
	public static By ryde_button=By.xpath("//a[@id='rYde']");
	public static By outstation_ride=By.xpath("(//div[text()='Outstation'])[1]");
	public static By trip_type(String tripType) {
		return By.xpath("//div[text()='"+tripType+"']");
	}
	public static By car_type(String carType) {
		return By.xpath("//div[text()='"+carType+"']"); 
	}
	public static By search_button=By.xpath("//div[text()='Search']");
	public static By pickup_loc=By.xpath("(//input[@class='CSwnlidw0FXxs7YV9qLF'])[1]");
	public static By drop_loc=By.xpath("(//input[@class='CSwnlidw0FXxs7YV9qLF'])[2]");
	public static By pickup_loc_2=By.xpath("(//input[@class='CSwnlidw0FXxs7YV9qLF'])[3]");
	public static By drop_loc_2=By.xpath("(//input[@class='CSwnlidw0FXxs7YV9qLF'])[4]");
	public ObjectRepository_CabRentalsPage(WebDriver driver) {
		this.driver=driver;
	}
	public boolean cabrentals_outstation_chooseCar(String tripType,String carType) throws Exception {
		driver.findElement(ryde_button).click();
		driver.findElement(outstation_ride).click();
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.cssSelector("object.Bk0St1vF8dHt6obq7lwo")));
		String text=driver.findElement(By.cssSelector("div.JaaftpM53LvIE7ppL0n1")).getText();
		System.out.println(text);
		driver.findElement(trip_type(tripType)).click();
		driver.findElement(car_type(carType)).click();
		return driver.findElement(search_button).isDisplayed();
	}
	public void cabrentals_outstation_chooseLoc(String pickUp,String Drop) throws Exception {
		edp_configuration edpc=new edp_configuration();
		System.out.println(edpc.pickup_loc());
		
		driver.findElement(pickup_loc).click();
		
		driver.findElement(pickup_loc_2).sendKeys(pickUp);
		Thread.sleep(4000);
		List<WebElement>pickup_options=driver.findElements(By.cssSelector("div.XD54l47wdPEWA3XX5tcS"));
		pickup_options.get(0).click();
		driver.findElement(drop_loc_2).sendKeys(Drop);
		Thread.sleep(4000);
		List<WebElement>drop_options=driver.findElements(By.cssSelector("div.XD54l47wdPEWA3XX5tcS"));
		drop_options.get(0).click();
	}
}
