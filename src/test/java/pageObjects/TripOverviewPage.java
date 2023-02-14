package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sikuli.script.FindFailed;
 
public class TripOverviewPage extends BasePage {	
	JavascriptExecutor js=(JavascriptExecutor) driver;
		
	public TripOverviewPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(css =".react-joyride__overlay")
           private WebElement desktopBar;	
	@FindBy (css="._hj-kWRoL__styles__openStateToggle:nth-child(1)")
	       private WebElement toggleIcon;
	@FindBy (css=".flights__slider-item>a")
	       private WebElement flightCard;
	@FindBy (css=".sb-desktop .step-value")
           private WebElement where;
	@FindBy (css=".sb-desktop__bar-items>.sb-desktop__bar-item:nth-child(2)>button>div:nth-child(2)")
	       private WebElement selectedDates;
	@FindBy (css=".flights__view-all--desktop")
           private WebElement viewAllFlights;
		
	public WebElement getWhere() {
		return where;
	}
	
	public WebElement getSelectedDates() {
		return selectedDates;
	}

	public void cancelPopup() {
		hoverToElement(desktopBar);
	}
	public void selectFlight() {
		click(flightCard);
	}	
	public void open_FlightsSearch_Engine() {
		hoverToElement(viewAllFlights);
		js.executeScript("window.scrollBy(0,550)", "");
		wait(5000);
		click(viewAllFlights);
	}
	public void TestInterestPlaces(String InterestPlaceIcon,String InterestPlaceReview) throws FindFailed {
		clickOnImage(InterestPlaceIcon);
		doesTheImageExist(InterestPlaceReview);
	}	
	
}
