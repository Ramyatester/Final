package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginScreenPageFactoryObjects {

//	public static final String menuSearch_xpath = "(//*[@id=\"menuSearch\"])[1]";
//	public static final String searchBar_xpath = "//*[@id='autoComplete']";
	//constructor
	
	
	
	public static final String LoginOrRegisterButton ="//a[normalize-space()='Login or register']";
	public static final String userNameTextBox ="//input[@id='loginFrm_loginname']";
	public static final String passWordTextBox ="//input[@id='loginFrm_password']";
	public static final String loginButton= "//button[normalize-space()='Login']";
	public static final String loginFailMSG = "//div[@class='alert alert-error alert-danger']";

	
}
