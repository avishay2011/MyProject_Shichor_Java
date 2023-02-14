package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Kiwi_Guarantee extends BasePage {
	public  Kiwi_Guarantee(WebDriver driver) {
        	super(driver);
	}
	
	@FindBy (css =".ZKJdy:nth-child(2)>div>div:nth-child(1)")
            private WebElement GuaranteeTransferProtection;	
	@FindBy(css="button[data-test=\"StepControls-AdditionalService-next\"]")
	        private WebElement Continue;
	
	public void selectTransferProtection() {
		click(GuaranteeTransferProtection);
		click(Continue);
	}
	}

