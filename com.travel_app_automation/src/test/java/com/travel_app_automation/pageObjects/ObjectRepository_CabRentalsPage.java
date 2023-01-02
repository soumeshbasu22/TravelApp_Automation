package com.travel_app_automation.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public static By search_button_cal=By.xpath("//div[text()='Search']");
	public static By pickup_loc=By.xpath("(//input[@class='CSwnlidw0FXxs7YV9qLF'])[1]");
	public static By drop_loc=By.xpath("(//input[@class='CSwnlidw0FXxs7YV9qLF'])[2]");
	public static By pickup_loc_2=By.xpath("(//input[@class='CSwnlidw0FXxs7YV9qLF'])[3]");
	public static By drop_loc_2=By.xpath("(//input[@class='CSwnlidw0FXxs7YV9qLF'])[4]");
	public static By proceed_button=By.xpath("//div[text()='Proceed']");
	public By cal_dt(int date) {
		return By.xpath("//div//button//span//p[text()='"+date+"']");
	}
	public By cal_mnth(String month) {
		return By.xpath("//div//div//p[text()='"+month+"']");
	}
	public static By pickup_dt=By.cssSelector("div.p2seMhZLGjzfKK2OxnZS");
	public static By drop_dt=By.xpath("(//label[text()='Pickup Date & Time']//following::input)[2]");
	public static By clock_hr(String hour) {
		return By.xpath("(//span[@class='MuiTypography-root MuiPickersClockNumber-clockNumber MuiTypography-body1'])["+hour+"]");
	}
	public static By clock_min(String minute) {
		return By.xpath("(//span[@class='MuiTypography-root MuiPickersClockNumber-clockNumber MuiTypography-body1'])["+minute+"]");
	}
	public static By ok_button_in_cal=By.xpath("//span[text()='OK']");
	public static By search_button=By.xpath("//div[text()='Search']");
	public static By search_result_page_tripInfo=By.cssSelector("div.luDkBul8XMY1VQpgrCUx");
	public static By customer_name=By.xpath("//input[@id='customer_name']");
	public static By customer_email=By.xpath("//input[@id='customer_email']");
	public static By customer_mobile=By.xpath("//input[@id='customer_mobile']");
	public static By view_vehile_button=By.xpath("//*[text()='View Vehicles']");
	public static By error_msg_changeDropoff=By.xpath("//*[contains(text(),'Change drop')]");
	public static By error_msg_timeRequired=By.xpath("//div[contains(text(),'Day')]");
	public static By change_dropoff_button=By.xpath("//*[contains(text(),'change Drop')]");
	public static By cab_capacity=By.xpath("(//div[@class='llEWHIN0JBjiJgjIcuyh'])[2]");
	public static By bus_capacity=By.xpath("(//div[@class='llEWHIN0JBjiJgjIcuyh'])[4]");
	public static By number_of_people_bus=By.xpath("//input[@type='number']");
	
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
		driver.findElement(pickup_loc_2).clear();
		driver.findElement(pickup_loc_2).sendKeys(pickUp);
		Thread.sleep(4000);
		try {
			List<WebElement>pickup_options=driver.findElements(By.cssSelector("div.XD54l47wdPEWA3XX5tcS"));
			pickup_options.get(0).click();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		Thread.sleep(4000);
		//driver.findElement(pickup_loc_2).clear();
		driver.findElement(drop_loc_2).sendKeys(Keys.chord(Keys.CONTROL,"A"));
		Thread.sleep(2000);
		driver.findElement(drop_loc_2).sendKeys(Keys.BACK_SPACE);
		driver.findElement(drop_loc_2).sendKeys(Drop);
		Thread.sleep(4000);
		try {
		List<WebElement>drop_options=driver.findElements(By.cssSelector("div.XD54l47wdPEWA3XX5tcS"));
		drop_options.get(0).click();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		driver.findElement(proceed_button).click();
	}
	public void cabrentals_pick_dt(String month_year,int date,String clock_hr_selection,String clock_min_selection) throws Exception {
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(pickup_dt));
			//((JavascriptExecutor)driver).executeScript("arguments[0].click()",driver.findElement(pickup_dt));
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='p2seMhZLGjzfKK2OxnZS']")));
			element.click();
		}catch (NoSuchElementException e) {
			driver.findElement(By.cssSelector("div.EMpsgVDccVk4TDSAFF2N")).click();    //For one way trip
		}
		for(int i=0;i<60;i++) {
			
			try {
			if(driver.findElement(cal_mnth(month_year)).isDisplayed()) {
				Thread.sleep(2000);
				driver.findElement(cal_dt(date)).click();
				break;
			}
			}catch (Exception e) {
				driver.findElement(By.xpath("(//*[@class='MuiSvgIcon-root'])[2]")).click();
			}
			
		}
		Thread.sleep(6000);
		//driver.findElement(clock_hr(clock_hr_selection)).click();
		//driver.findElement(clock_min(clock_min_selection)).click();
		driver.findElement(ok_button_in_cal).click();
	}
public void cabrentals_drp_dt(String month_year,int date,String clock_hr_selection,String clock_min_selection) throws Exception {
		
		driver.findElement(drop_dt).click();
		for(int i=0;i<60;i++) {
			try {
			if(driver.findElement(cal_mnth(month_year)).isDisplayed()) {
				Thread.sleep(2000);
				driver.findElement(cal_dt(date)).click();
				break;
			}
			}catch (Exception e) {
				driver.findElement(By.xpath("(//*[@class='MuiSvgIcon-root'])[2]")).click();
			}
			
		}
		Thread.sleep(6000);
		//driver.findElement(clock_hr(clock_hr_selection)).click();
		//driver.findElement(clock_min(clock_min_selection)).click();
		driver.findElement(ok_button_in_cal).click();
		//driver.findElement(search_button).click();
	}
public void cab_search_page(String uname,String email,String mobile) {
	driver.findElement(customer_name).sendKeys(uname);
	driver.findElement(customer_email).sendKeys(email);
	driver.findElement(customer_mobile).sendKeys(mobile);
	driver.findElement(view_vehile_button).click();
}
public int cab_or_bus_option(String carOption) {
	int capacity=0;
	driver.findElement(ryde_button).click();
	driver.findElement(outstation_ride).click();
	driver.switchTo().frame(driver.findElement(By.xpath("//object[@class='Bk0St1vF8dHt6obq7lwo']")));
	if(carOption=="Cab") {
	String message=driver.findElement(cab_capacity).getText();
	capacity=Integer.parseInt(message.substring(9,10));
	}
	else if(carOption=="Bus") {
		String message2=driver.findElement(bus_capacity).getText();
		capacity=Integer.parseInt(message2.substring(4,5));
	}
	System.out.println(capacity);
	return capacity;
}
}
