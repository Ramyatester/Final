package systemTests;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.ElementNotInteractableException;
import org.testng.annotations.Test;
import base.BaseClass;
import base.LoginScreenPageFactoryObjects;

public class UserSession extends BaseClass {
	@Test(priority = 1)
	  public void testusersession() throws Throwable {
		  	boolean res;
		  	String lgngurl = LoginScreenPageFactoryObjects.LoggingUrl;
			String lgedurl = LoginScreenPageFactoryObjects.LoggedUrl;
			
				buttonClick(LoginScreenPageFactoryObjects.LoginOrRegisterButton);
				enterText(LoginScreenPageFactoryObjects.userNameTextBox, "Anandh");
				enterText(LoginScreenPageFactoryObjects.passWordTextBox,"Anandh@123" );     
				buttonClick(LoginScreenPageFactoryObjects.loginButton);
				pause(300);
				navigateback();
				navigateforward();
				pause(300);
				assertEquals(lgedurl,lgngurl);
			
			} 
	  }

