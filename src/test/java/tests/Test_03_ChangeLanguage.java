package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonOps.Verifications;
import pageObjects.MainPage;
import pageObjects.Sign_In_Page;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

@Listeners(utils.Listeners.class)
public class Test_03_ChangeLanguage extends BaseTest {
	@Test
	public  void test1_changeLanguage( )  {	 
		MainPage MP=new MainPage(driver);
		Sign_In_Page signIn=new Sign_In_Page(driver);
		signIn.click_X();
		MP.changeLanguage();
		MP.printText(MP.getShichorSlogen());
		String Expected="הרכיבו טיול בעצמכם";
	 	Verifications.verifyTextEquals(MP.getShichorSlogen(), Expected);
}

}
