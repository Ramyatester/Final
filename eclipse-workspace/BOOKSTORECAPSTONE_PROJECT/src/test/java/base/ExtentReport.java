package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	static WebDriver driver;
	
	@Test
	public void reports() {
		
		ExtentSparkReporter reports = new ExtentSparkReporter("extentreport.html");
		
		ExtentReports extent = new ExtentReports();
		
		extent.attachReporter(reports);
		ExtentTest test1 = extent.createTest("");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\eclipse-workspace\\RedBus\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		test1.log(Status.INFO, "Starting the Tc") ;
		driver.get("https://www.redbus.in/");
		
	}
	

}
