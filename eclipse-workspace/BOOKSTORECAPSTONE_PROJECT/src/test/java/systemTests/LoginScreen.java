package systemTests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.BaseClass;

/* Authour of Test :Ramya-194189*/

public class LoginScreen extends BaseClass {
	
	//Login unsuccesful with user name : Testerauto1 , Password : Google@123
	@Test (priority=1)
	public void verifyInvalidLogin_InvalidUserName() {
		
		driver.findElement(By.xpath("//a[normalize-space()='Login or register']")).click();
		driver.findElement(By.xpath("//input[@id='loginFrm_loginname']")).sendKeys("Testerauto1");
		
		driver.findElement(By.xpath("//input[@id='loginFrm_password']")).sendKeys("Google@123");
		
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
	}
	//Login unsuccesful with user name : Testerauto , Password : Google@12345
	@Test (priority=2)
	public void verifyInvalidLogin_InvalidUserPassword() {
		
		driver.findElement(By.xpath("//a[normalize-space()='Login or register']")).click();
		driver.findElement(By.xpath("//input[@id='loginFrm_loginname']")).sendKeys("Testerauto");
		
		driver.findElement(By.xpath("//input[@id='loginFrm_password']")).sendKeys("Google@12345");
		
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
	}
	//Login succesfully with user name : Testerauto , Password : Google@123
	@Test(priority=3)
	public void abilityToLoginSuccessfully() {
		
		driver.findElement(By.xpath("//a[normalize-space()='Login or register']")).click();
		driver.findElement(By.xpath("//input[@id='loginFrm_loginname']")).sendKeys("Testerauto");
		
		driver.findElement(By.xpath("//input[@id='loginFrm_password']")).sendKeys("Google@123");
		
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
	}

	
}
