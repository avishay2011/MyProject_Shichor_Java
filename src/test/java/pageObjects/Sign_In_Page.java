package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Sign_In_Page extends BasePage {
	
	public Sign_In_Page(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="#email")
	        private WebElement email;	
	@FindBy(css=".input__field>#password")
        	private WebElement password;	
	@FindBy(css=".btn-blue.btn-fluid.btn")
	       private WebElement login;
	@FindBy(css=".form-error-message")
	       private WebElement formError;
	@FindBy(css=".modal-v2__close-button")
	       private WebElement X_Button;
	 
	
	public WebElement getFormError() {
		return formError;
	}

	public void LogIn(String email,String password) {
		this.email.sendKeys(Keys.CONTROL+"a");
		this.email.sendKeys(Keys.BACK_SPACE);
		fillText(this.email, email);
		this.password.sendKeys(Keys.CONTROL+"a");
		this.password.sendKeys(Keys.BACK_SPACE);
		fillText(this.password, password);
		click(login);	
	}
	
	public void Click_X() {
		click(X_Button);
	}
}
