package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

public class FlightDetailsPage extends BaseClass {

	Properties prop = new Properties();

	public FlightDetailsPage() throws IOException {
		String className = this.getClass().getName();

		try {
			File file = new File(System.getProperty("user.dir") + "//src//main//resources//propertyFile//" + className
					+ ".properties");
			FileInputStream inputStream = new FileInputStream(file);
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			test.log(Status.FAIL, "Unable to load properties file...");
		}
	}

	/*
	 * public void getFilterOptions() { List<WebElement> filterOptions =
	 * driver.findElements(By.xpath("//div[@class='pt-8']/div/p"));
	 * 
	 * for (int i = 0; i < filterOptions.size(); i++) { String option =
	 * filterOptions.get(i).getText();
	 * 
	 * switch (option) { case "Stops": filterByStops("Non-stop"); break;
	 * 
	 * case "Departure time": filterByDepTime(); break; } } }
	 */

	public void filterByStops(String stopType) {
		switch (stopType) {
		case "Non-stop":
			driver.findElement(By.xpath(prop.getProperty("nonstop"))).click();

			break;

		case "1 stop":
			driver.findElement(By.xpath(prop.getProperty("1stop"))).click();
			break;

		case "2 stops":
			driver.findElement(By.xpath(prop.getProperty("2stops"))).click();
			break;
		}
	}

	public void filterByDepTime() {

	}

	public void excludeAirlines() {

	}

	public void flightSelection() {

	}

}