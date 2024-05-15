package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {

	public static WebDriver driver;
	protected Map<String, String> data = null;
	static String TestName;
	static ExtentReports reports;
	// static string reports;
	protected static ExtentTest test;
	public static String testName;
	public static List<String> expRe;
	public static WebDriverWait wait ;
	private static FileInputStream fileInputStream;
//	private static XSSFWorkbook workbook;
//	private static XSSFSheet sheet;
//	private static Cell cell;
	private static ProcessBuilder pb;
	private static String autoitExe;
    public static WebElement element;
	@BeforeSuite

	public static void openBrowser() throws MalformedURLException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Administrator\\Final\\eclipse-workspace\\BOOKSTORE_CAPSTONEPROJECT\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.manage().deleteAllCookies();
		driver.get("https://automationteststore.com/index.php?rt=product/category&path=65");
		reports = new ExtentReports();
		reports.attachReporter(new ExtentSparkReporter(
				System.getProperty("user.dir") + "\\BOOKSTORECAPSTONE_PROJECT\\ExtentReportsResults.html"));

	}

	@BeforeMethod
	public void setUP(Method method) {

		TestName = method.getName();
		System.out.println("Current executed Testcase is :" + TestName);
		test = reports.createTest(TestName);

	}

	@AfterMethod
	public void afterExecution(ITestResult iTestResult) throws IOException {

		testName = iTestResult.getName();
		System.out.println("Executed TestName :" + TestName);
		if (iTestResult.getStatus() == iTestResult.SUCCESS) {
			System.out.println("TestCase is Passed" + TestName);
			// test.log(LogStatus.PASS, "Testcase is passed"+TestName);
			test.log(Status.PASS, "Testcase is passed" + TestName);

			test.addScreenCaptureFromBase64String(captureScreen());

		} else if (iTestResult.getStatus() == iTestResult.FAILURE) {
			System.out.println("TestCase is Failed" + testName);
			captureScreen(testName);
			test.log(Status.FAIL, "TestCase is Failed" + testName);

		} else if (iTestResult.getStatus() == iTestResult.SKIP) {
			System.out.println("TestCase is Skipped" + testName);
			captureScreen(testName);
			test.log(Status.SKIP, test.addScreenCaptureFromBase64String(captureScreen()) + testName);

		}

	}

	public String captureScreen(String name) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String encodedBase64 = null;

		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(scrFile);
			byte[] bytes = new byte[(int) scrFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "data:image/png;base64," + encodedBase64;
	}

	public String captureScreen() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String encodedBase64 = null;

		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(scrFile);
			byte[] bytes = new byte[(int) scrFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "data:image/png;base64," + encodedBase64;
	}

	
	public void pause(Integer milliseconds) {
		
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@AfterClass
	public void end() {

		System.out.println("Browser closed successfully");
		reports.removeTest(test);
		reports.flush();
		driver.quit();
	}
	
	public static void buttonClick(String xpath) throws IOException , Throwable{
		
		boolean isPresent = true;
		
		try	{

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).click();
			System.out.println(xpath+"   clicked");
			isPresent = true;	
			test.log(Status.PASS,"Button Clicked in "+xpath);
		}
		catch(Exception e){
			isPresent = false;
			System.out.println(xpath+"  not found");
			test.log(Status.FAIL, xpath+ "Element not found");

		}	
		
	}
	
	public static void enterText(String xpath, String input)throws IOException {
		
		String[] namesplit = xpath.split("'");
		String sname= namesplit[namesplit.length - 2];
		boolean isPresent = true;

		try	{

			WebDriverWait wait=new WebDriverWait(driver,  Duration.ofSeconds(30));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			driver.findElement(By.xpath(xpath)).sendKeys(input);
			isPresent = true;
			System.out.println(xpath+"   found");
			test.log(Status.PASS,"Text Entered is "+input);
		}
		catch(Exception e){
			test.log(Status.FAIL, "+xpath+ Element not found");

		}
		
	}
	
	public static void getTitle() {
		driver.getTitle();
	}
	

	public static String getText(String locType,String locValue) {
		String text=null;
		element=identifyElement(locType, locValue);
		wait = new WebDriverWait (driver, Duration.ofSeconds(30)); 
		wait.until(ExpectedConditions.visibilityOf(element));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)element));
		text=element.getText();
		System.out.println(text);
		test.log(Status.INFO,  text);
		return text;
	}
	
	public static void assertion(String actual,String expected) {
		try {
			wait = new WebDriverWait (driver,  Duration.ofSeconds(30)); 
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static WebElement identifyElement(@Optional("xpath") String locType,String locValue) {
		if (locType.equalsIgnoreCase("id")) {
			element=driver.findElement(By.id(locValue));
		}
		else if (locType.equalsIgnoreCase("name")) {
			element=driver.findElement(By.name(locValue));
		}
		else if (locType.equalsIgnoreCase("className")) {
			element=driver.findElement(By.className(locValue));
		}
		else if (locType.equalsIgnoreCase("xpath")) {
			element=driver.findElement(By.xpath(locValue));
		}
		else if (locType.equalsIgnoreCase("cssSelector")) {
			element=driver.findElement(By.cssSelector(locValue));
		}
		else if (locType.equalsIgnoreCase("linkText")) {
			element=driver.findElement(By.linkText(locValue));
		}
		else if (locType.equalsIgnoreCase("partialLinkText")) {
			element=driver.findElement(By.partialLinkText(locValue));
		}
		else if (locType.equalsIgnoreCase("tagName")) {
			element=driver.findElement(By.tagName(locValue));
		}
		return element;
	}
	
	
	public static String getAttribute(String xpath,String text) {
		WebDriverWait wait=new WebDriverWait(driver,  Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).getAttribute("innerHTML");
		return text;
	}
}
