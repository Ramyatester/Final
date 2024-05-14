package systemTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import base.BaseClass;
import base.LocType;
import base.LoginScreenPageFactoryObjects;
import base.WelcomeScreenPageFactoryObjects;

/* Authour of Test :Ramya-194189*/

public class LoginScreen extends BaseClass {

	// Login unsuccessful with user name : Testerauto1 , Password : Google@123
	@Test(priority = 1) 
	public void verifyInvalidLogin_InvalidUserName() throws Throwable{
		
		
		try {
			
			buttonClick(LoginScreenPageFactoryObjects.LoginOrRegisterButton);
			enterText(LoginScreenPageFactoryObjects.userNameTextBox, "Testerauto1");
						enterText(LoginScreenPageFactoryObjects.passWordTextBox,"Google@123" );     
			buttonClick(LoginScreenPageFactoryObjects.loginButton);
			pause(3000);
			String actualTextValue	=driver.findElement(By.xpath(LoginScreenPageFactoryObjects.loginFailMSG)).getAttribute("innerHTML");
		     System.out.println(actualTextValue);
			Assert.assertTrue(actualTextValue.contains("Error: Incorrect login or password provided."));
			
//			String actualTextValue=	getAttribute(LoginScreenPageFactoryObjects.loginFailMSG);
			
			
} catch (Exception e) {
		
		e.printStackTrace();
	}
	}
	// Login unsuccessful with user name : Testerauto , Password : Google@12345
	@Test(priority = 2)
	public void verifyInvalidLogin_InvalidUserPassword() throws Throwable {

		try {
			buttonClick(LoginScreenPageFactoryObjects.LoginOrRegisterButton);
			enterText(LoginScreenPageFactoryObjects.userNameTextBox, "Testerauto");
			
			enterText(LoginScreenPageFactoryObjects.passWordTextBox,"Google@12345" );     
			buttonClick(LoginScreenPageFactoryObjects.loginButton);
			pause(3000);
			String actualTextValue	=driver.findElement(By.xpath(LoginScreenPageFactoryObjects.loginFailMSG)).getAttribute("innerHTML");
		     System.out.println(actualTextValue);
			Assert.assertTrue(actualTextValue.contains("Error: Incorrect login or password provided."));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	// Login succesfully with user name : Testerauto , Password : Google@123
	@Test(priority = 3)
	public void abilityToLoginSuccessfully() throws Throwable {
String ExpectedText = "Welcome back Ramya";
		try {
		buttonClick(LoginScreenPageFactoryObjects.LoginOrRegisterButton);
			enterText(LoginScreenPageFactoryObjects.userNameTextBox, "Testerauto");
			
			enterText(LoginScreenPageFactoryObjects.passWordTextBox,"Google@123" );     
			buttonClick(LoginScreenPageFactoryObjects.loginButton);
			pause(3000);
			captureScreen();
			pause(3000);
		String actualText=	getText(LocType.xpath,WelcomeScreenPageFactoryObjects.welcomeText);
			
			
			Assert.assertTrue(actualText.contains(ExpectedText));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Test (priority = 4)
	public void dummy() {
		
		System.out.println("none");
	}

}
