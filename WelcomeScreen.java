package systemTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import base.LocType;
import base.LoginScreenPageFactoryObjects;
import base.WelcomeScreenPageFactoryObjects;

public class WelcomeScreen extends BaseClass {
     
	@Test(priority=4)
	public void validateWelcomeText() throws Throwable{
		String ExpectedText = "Welcome back Ramya";
		LoginScreen n= new LoginScreen();
		
		try {
			n.abilityToLoginSuccessfully();
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
}
