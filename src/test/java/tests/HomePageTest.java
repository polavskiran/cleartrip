package tests;

import java.text.ParseException;

import org.testng.annotations.Test;

import pages.BaseClass;
import pages.HomePage;

public class HomePageTest extends BaseClass {

	@Test
	public void homePageTest() {

		try {
			test = extent.createTest("Homepage Test");
			new HomePage().searchFlight();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}