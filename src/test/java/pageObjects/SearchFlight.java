package pageObjects;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchFlight extends BasePage{
       public SearchFlight(WebDriver driver) {
		      super(driver);	
	} 
       @FindBy(css="#cookies_accept")
              private WebElement AccepctCookies;
       
       public void acceptCookies() {
    	   moveToNewWindow();
    	   click(AccepctCookies);
       }
}     
       
