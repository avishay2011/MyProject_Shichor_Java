package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Kiwi_Guarantee extends BasePage {
	public  Kiwi_Guarantee(WebDriver driver) {
        	super(driver);
	}
	
	@FindBy (css =".Radio__StyledIconContainer-sc-1e6hy4x-1")
            private WebElement TakeTheRisk;	
	@FindBy(css="button[data-test=\"StepControls-AdditionalService-next\"]")
	        private WebElement Continue;
	
	public void selectTransferProtection() {
		click(TakeTheRisk);
		click(Continue);
	}
	}

