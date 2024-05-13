package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {

public 	static	WebDriver driver;
      protected Map<String,String> data =null;
	  static String TestName;
	  static  ExtentReports reports;
	 // static string reports;
	  protected static ExtentTest test;
	  public static String testName;
	  public static List<String> expRe;
      	
	

    @BeforeSuite

	public  static void openBrowser() throws MalformedURLException{
		
		 
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\eclipse-workspace\\BOOKSTORECAPSTONE_PROJECT\\Driver\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		driver.manage().deleteAllCookies();
		driver.get("https://automationteststore.com/index.php?rt=product/category&path=65");
		reports = new ExtentReports();
		reports.attachReporter(new ExtentSparkReporter(System.getProperty("user.dir")+"\\BOOKSTORECAPSTONE_PROJECT\\ExtentReportsResults.html"));
		
		

    }
		
    @BeforeMethod
    public void setUP(Method method) {
    
    TestName  = method.getName();
    System.out.println("Current executed Testcase is :"+TestName);
    test = reports.createTest(TestName);
    
    } 
    @AfterMethod
    public void afterExecution(ITestResult iTestResult) throws IOException {
    	
    	
    	testName =iTestResult.getName();
    	System.out.println("Executed TestName :"+TestName);
    	if (iTestResult.getStatus()==iTestResult.SUCCESS) {
    		System.out.println("TestCase is Passed"+ TestName);
    	//	test.log(LogStatus.PASS, "Testcase is passed"+TestName);
    		test.log(Status.PASS, "Testcase is passed"+TestName);
    		
    		test.addScreenCaptureFromBase64String(captureScreen());
    		
    	}
    	else if(iTestResult.getStatus()==iTestResult.FAILURE){
    		System.out.println("TestCase is Failed"+ testName);
    		captureScreen(testName);
    		test.log(Status.FAIL, "TestCase is Failed"+testName);
    		
    		}
    	else if(iTestResult.getStatus()==iTestResult.SKIP){
    		System.out.println("TestCase is Skipped"+ testName);
    		captureScreen(testName);
    		test.log(Status.SKIP, test.addScreenCaptureFromBase64String(captureScreen())+testName);
    	
    	}
    	
    }
    	
    	

		public String captureScreen(String name) throws IOException {
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		String encodedBase64 =null;
    		
    		FileInputStream fileInputStreamReader =null;
    		try {
				fileInputStreamReader = new FileInputStream(scrFile);
				byte[] bytes = new byte[(int)scrFile.length()];
				fileInputStreamReader.read(bytes);
				encodedBase64 = new String(Base64.encodeBase64(bytes));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return "data:image/png;base64,"+encodedBase64;
    	}
		
		
		public String captureScreen() throws IOException {
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		String encodedBase64 =null;
    		
    		FileInputStream fileInputStreamReader =null;
    		try {
				fileInputStreamReader = new FileInputStream(scrFile);
				byte[] bytes = new byte[(int)scrFile.length()];
				fileInputStreamReader.read(bytes);
				encodedBase64 = new String(Base64.encodeBase64(bytes));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return "data:image/png;base64,"+encodedBase64;
    	}
    
		@AfterClass
		public void end() {
			
			System.out.println("Browser closed successfully");
			reports.removeTest(test);
			reports.flush();
		}
}
