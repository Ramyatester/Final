package systemTests;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.BaseClass;
import base.LocType;
import base.LoginScreenPageFactoryObjects;
import base.RegistrationPageFactoryObjects;

public class RegistrationScreen extends BaseClass{
	
@Test
public void newUserRegistration() throws IOException, Throwable {
	
	String newRegisterSuccessMsg = "Your Account Has Been Created!";
	buttonClick(LoginScreenPageFactoryObjects.LoginOrRegisterButton);
	buttonClick(RegistrationPageFactoryObjects.continueButton);
	enterText(RegistrationPageFactoryObjects.firstNameText, "admin");
	enterText(RegistrationPageFactoryObjects.lastNameText, "Tester");
	enterText(RegistrationPageFactoryObjects.emailEnterText, "emai809@gmail.com");
	enterText(RegistrationPageFactoryObjects.addressOneField, "No4");
	enterText(RegistrationPageFactoryObjects.cityField, "Chennai");
	select_Dropdown_Value(LocType.xpath,RegistrationPageFactoryObjects.countryField,"United Kingdom" );
	pause(5000);
	select_Dropdown_Value(LocType.xpath,RegistrationPageFactoryObjects.regionOrStateField,"Angus" );
	enterText(RegistrationPageFactoryObjects.zipCodeField, "600034");
	
	enterText(RegistrationPageFactoryObjects.loginNameField,
			passRandomString(LocType.xpath,RegistrationPageFactoryObjects.loginNameField));
	enterText(RegistrationPageFactoryObjects.setPasswordField,"password123");
			
	enterText(RegistrationPageFactoryObjects.passwordConfirmField,"password123");
			
	buttonClick(RegistrationPageFactoryObjects.subScribeNoOptionbotton);
	buttonClick(RegistrationPageFactoryObjects.privacyPolicyCheckBox);
	buttonClick(RegistrationPageFactoryObjects.continueButtonRegisterForm);
//String expectedSuccessMSG=	getAttribute(RegistrationPageFactoryObjects.accountCreatedSuccessfullText,"");
pause(5000);
String expectedSuccessMSG= getText(LocType.xpath,RegistrationPageFactoryObjects.accountCreatedSuccessfullText);
System.out.println("Acconut creation text :"+expectedSuccessMSG);
	Assert.assertTrue(expectedSuccessMSG.contains("YOUR ACCOUNT HAS BEEN CREATED!"));
	
//	String actualTextValue	=driver.findElement(By.xpath(RegistrationPageFactoryObjects.accountCreatedSuccessfullText)).getAttribute("innerHTML");
//     System.out.println(actualTextValue);
//     Assert.assertEquals(newRegisterSuccessMsg, expectedSuccessMSG);
	
	
}
@Test
public void root() {
	
	System.out.println("");
}

}


