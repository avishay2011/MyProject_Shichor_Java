package commonOps;

import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.asserts.SoftAssert;

public class Verifications {
	public static void verifyTextInElememt(WebElement el,String Expected ) {
	AssertJUnit.assertEquals(el.getText(), Expected);    
    }
	
	public static void visibility_Of_Element_On_List(List<WebElement> elems) {
		SoftAssert softAssert = new SoftAssert();
		for (WebElement el : elems) {			 /// this method is used  to test whether each referenced element is displayed on the web page or not 
			softAssert.assertTrue(el.isDisplayed(), "Sorry, the "+ el.getText() + " is not displayed");
		}
		softAssert.assertAll("Some elements are not displayed on page");
	}
	
	public static void assertTheTextContains_SpecificWords(WebElement el,String string){
		AssertJUnit.assertTrue(el.getText().toLowerCase().contains(string.toLowerCase()));
	}
	
	public static void existanceOfElement(List<WebElement> elems) {
	AssertJUnit.assertTrue(elems.size()>0);
	}
	
	public static void NonExistanceOfElement(List<WebElement> elems) {
	AssertJUnit.assertFalse(elems.size()>0);
	}	
}
