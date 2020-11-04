package pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {

	ExtentSparkReporter sparkRepoter;
	protected ExtentReports extent;
	protected static WebDriver driver = null;
	protected ExtentTest test;
	protected static String baseURL = "https://www.cleartrip.com/";
	protected static String browserName;
	protected static Properties configFile = new Properties();

	public BaseClass() {
		try {
			File file = new File(
					System.getProperty("user.dir") + "//src//main//resources//propertyFile//config.properties");
			FileInputStream inputStream = new FileInputStream(file);

			configFile.load(inputStream);
			browserName = configFile.getProperty("browser");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void setUp() {

		String reportPath = System.getProperty("user.dir") + "//Reports//index.html";
		sparkRepoter = new ExtentSparkReporter(reportPath);
		sparkRepoter.config().setReportName("Sample Report");
		sparkRepoter.config().setDocumentTitle("::Test Results::");

		extent = new ExtentReports();
		extent.attachReporter(sparkRepoter);
		extent.setSystemInfo("Test", "Kiran Kumar");
	}

	@AfterTest
	public void tearDown() {
		extent.flush();
		driver.quit();
	}
}