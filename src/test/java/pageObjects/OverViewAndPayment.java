package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OverViewAndPayment extends BasePage{
	public OverViewAndPayment(WebDriver driver) {
		super(driver);		
}
	@FindBy(css="button[data-test=\"StepControls-payment-previous\"]>div")
	private WebElement Back;
	@FindBy(css=".Stack__StyledStack-sc-53pobq-0.bjXQpv>div>div:nth-child(2)>p")
	private WebElement seat_Outbound_Flight;
	@FindBy(css=".Stack__StyledStack-sc-53pobq-0.dZEykh>div:nth-child(2)>div:nth-child(2)>p")
	private WebElement seat_Return_Flight;

	public WebElement getSeat_Outbound_Flight() {
		return seat_Outbound_Flight;
	}
	public WebElement getSeat_Return_Flight() {
		return seat_Return_Flight;
	}

	public void backToPreviousPage() {
		click(Back);
	}
}  
