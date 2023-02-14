package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class MainPage extends BasePage{
	public MainPage(WebDriver driver) {
		super(driver);
	}
	
	//Creating object of an Actions class
	Actions action = new Actions(driver);
	
    @FindBy(css =".vector-icon.hanukkah-popup__exit-icon" )
	    private WebElement X_Button;	 
    @FindBy(css =".nav-right__toggle>button" )
	    private WebElement globusIcon; 
    @FindBy(css=".locale-settings-list__item:nth-child(1)")
        private WebElement changeLanguageToHebrew;  
    @FindBy(css =".nav-right__user" )
         private WebElement sign_In;
    @FindBy(css=".btn-blue-outlined")
         private WebElement BuildYourOwnTrip;  
    @FindBy(css=".home-hero__button-container>a:nth-child(2)>span")
         private WebElement ShichorSlogen;
            
   public WebElement getShichorSlogen() {
	    wait(2000);
		return ShichorSlogen;
	}

    public void removePopup(){
	   click(X_Button); 
   }
   public void changeLanguage() {
	  click(globusIcon);
	  click(changeLanguageToHebrew);
   }
   public void sign_In() {
		  click(sign_In);
	   }
   public void startPlanning() {
	   wait(5000);
	   click(BuildYourOwnTrip);	   
   }
}