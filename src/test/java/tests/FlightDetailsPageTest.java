package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.BaseClass;
import pages.FlightDetailsPage;
import pages.HomePage;

public class FlightDetailsPageTest extends BaseClass {

	@Test
	public void showNonStopFlights() {
		try {
			test = extent.createTest("Homepage Test");
			new HomePage().searchFlight();

			// Filter Non-stop flights
			new FlightDetailsPage().filterByStops("Non-stop");			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to filter flights");
		}
	}
}