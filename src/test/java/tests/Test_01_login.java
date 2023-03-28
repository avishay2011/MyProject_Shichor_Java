package tests;

import org.testng.annotations.Test;

import commonOps.Verifications;
import pageObjects.MainPage;
import pageObjects.Sign_In_Page;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utils.UtilsConfiguration;

@Listeners(utils.Listeners.class)
public class Test_01_login extends BaseTest {

	@Test(dataProvider = "getData")
	public  void test1_login_errorMessage1(String email,String password )  {	 
        Sign_In_Page signIn=new Sign_In_Page(driver);
        signIn.logIn(email,password);   
      //This String is used for check that we get the right message 
      	String expectedString = "Please enter a valid email address";
      	Verifications.verifyTextEquals(signIn.getFormError(), expectedString);
	}
	@DataProvider
	public Object[][] getData() {
		Object[][]myData= {
				{"user1","123"},
				{"gal","123"},
				{"yonit","1#444"},
				{"gal","123456878"},
		};
		return myData;		
	}
	
	@Test
	public void test2_SuccesfullyLogIn() {
		Sign_In_Page signIn=new Sign_In_Page(driver);
        signIn.logIn(UtilsConfiguration.readProperty("email"),UtilsConfiguration.readProperty("password"));   
        
	}
}
