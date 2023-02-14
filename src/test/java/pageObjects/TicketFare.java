package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class  TicketFare extends BasePage {
	
	public TicketFare(WebDriver driver) {
		super(driver);
	}
	@FindBy(css ="button[data-test=\"fareTypesStandardButton\"]")
           private WebElement continueWithStandard;
	@FindBy(css="button[data-test=\"servicePackagesPlusButton\"]")
	       private WebElement plusServices;
	
	public void continueStandardTicked() {
		click(continueWithStandard);
	}
	public void selectService() {
		click(plusServices);
	}
	}
