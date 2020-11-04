package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;

import com.aventstack.extentreports.Status;

import pages.BaseClass;

public class Util extends BaseClass {

	/*
	 * 
	 */
	public void launchBrowser(String browserName) {
		System.out.println("baseURL = " + baseURL);
		switch (browserName.toLowerCase()) {
		case "chrome":
			ChromeOptions chOptions = new ChromeOptions();
			chOptions.addArguments("--incognito");
			chOptions.setAcceptInsecureCerts(true);
			// chOptions.setCapability(CapabilityType.BROWSER_NAME, "Google Chrome");
			chOptions.addArguments("start-maximized");

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver(chOptions);
			// driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(baseURL);
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(baseURL);
			break;

		case "edge":
			break;

		default:
			break;
		}
	}

	public WebElement getElement(By locator) {
		//test.log(locator);
		
		if(isWebElementPresent(locator)) {
			return driver.findElement(locator);
		}
		
		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		}catch(RuntimeException re) {
			//test.log(Status.FAIL, re);
			throw re;
		}
	}

	public boolean isWebElementPresent(By locator) {
		boolean flag = driver.findElements(locator).size() >= 1;

		return flag;
	}
}