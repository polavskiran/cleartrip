package pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.Util;

public class HomePage extends BaseClass {

	// static String baseURL = "https://www.cleartrip.com/";
	// static WebDriver driver = null;
	static String depTime = "05:05";
	private Util util = new Util();

	public static void main(String[] args) throws ParseException, InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "E:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		// driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		enterOriginStation("Delhi");
		enterDestination("Bangalore");

		enterDate("10/12/2020");
		Thread.sleep(5000);

		driver.findElement(By.id("SearchBtn")).click();

		bookFlight(depTime);
	}

	public static void enterOriginStation(String originCity) {
		WebElement fromCity = driver.findElement(By.id("FromTag"));
		fromCity.click();
		fromCity.clear();
		fromCity.sendKeys(originCity);

		WebElement listCities = driver.findElement(By.cssSelector("ul#ui-id-1"));
		List<WebElement> Cities = listCities.findElements(By.tagName("li"));

		/*
		 * for (int i = 0; i < Cities.size(); i++) {
		 * System.out.println(Cities.get(i).getText()); }
		 */

		Cities.get(0).click();
	}

	public static void enterDestination(String destCity) {
		WebElement toCity = driver.findElement(By.id("ToTag"));
		toCity.click();
		toCity.clear();
		toCity.sendKeys(destCity);

		WebElement listCities = driver.findElement(By.cssSelector("ul#ui-id-2"));
		List<WebElement> Cities = listCities.findElements(By.tagName("li"));

		/*
		 * for (int i = 0; i < Cities.size(); i++) {
		 * System.out.println(Cities.get(i).getText()); }
		 */

		Cities.get(0).click();
	}

	public static void enterDate(String date) throws ParseException {

		WebElement dateEle = driver.findElement(By.id("DepartDate"));
		dateEle.click();
		String strDate;

		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
		strDate = sdf.format(fromDate);
		System.out.println("Date Format with dd MMMM yyyy : " + strDate);

		String[] dates = strDate.split(" ");
		String monthday = dates[0];
		String month = dates[1].trim();
		// String year = dates[2];

		WebElement monthVal = driver
				.findElement(By.xpath("//div[@id='ui-datepicker-div']//span[@class='ui-datepicker-month']"));

		System.out.println("Month Value is: " + monthVal.getText());

		while (!driver.findElement(By.xpath("//div[@id='ui-datepicker-div']//span[@class='ui-datepicker-month']"))
				.getText().equals(month)) {
			driver.findElement(By.xpath("//div[@class='monthBlock last']//a[@class='nextMonth ']")).click();
		}

		List<WebElement> allDays = driver.findElements(By.xpath("//table[@class='calendar']//td"));

		for (WebElement day : allDays) {
			String sDate = day.getText();

			if (sDate.contains(monthday)) {
				day.click();
				break;
			}
		}
	}
	
	public void searchFlight() throws ParseException {
		util.launchBrowser(browserName);
		enterOriginStation("New Delhi");
		enterDestination("Bangalore");
		enterDate("10/12/2020");
		
		driver.findElement(By.id("SearchBtn")).click();
	}

	public static void bookFlight(String strTime) {

		List<WebElement> allTimings = driver.findElements(By.xpath(
				"//div[contains(@class,'details ms-grid-row-2')]//div[contains(@class,'ms-grid-column-1 ms-grid-row-1')]"));

		for (WebElement time : allTimings) {

			System.out.println(time.getText());

			if (time.getText().equals(depTime)) {
				System.out.println(time.getText() + "--->" + depTime);
				driver.findElement(By.xpath(
						"//div[contains(@class,'details ms-grid-row-2')]//div[contains(@class,'ms-grid-column-4')]"))
						.click();
			}
		}
	}
}