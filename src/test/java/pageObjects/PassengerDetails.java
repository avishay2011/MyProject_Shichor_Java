package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class PassengerDetails extends BasePage {
	public PassengerDetails(WebDriver driver) {
		   super(driver);
	}
	@FindBy(css="input[name=\"firstname\"]")
	     private WebElement GivenName;
	@FindBy(css="input[name=\"lastname\"]")
         private WebElement SurName;
	@FindBy(css="select[data-test='ReservationPassenger-nationality']")
         private WebElement nationality;
	@FindBy(css=".PassengerForm__GenderWrapper-sc-bn2s74-4 select")
         private WebElement gender;
	@FindBy(css="input[name='birthDay']")
         private WebElement DateOFBirth_Day;
	@FindBy(css="select[name='birthMonth']")
         private WebElement DateOfBirth_Month;
	@FindBy(css="input[name='birthYear']")
         private WebElement DateOfBirthYear;
	@FindBy(css="input[name='idNumber']")
         private WebElement passportOrIdNumber;
	@FindBy(css="input[name='idExpirationDay']")
         private WebElement ExpirationDate_Day;
	@FindBy(css="select[name='idExpirationMonth']")
         private WebElement ExpirationDate_Month;
	@FindBy(css="input[name='idExpirationYear']")
         private WebElement ExpirationDate_Year;
	@FindBy(css="input[name='email']")
         private WebElement email;
    @FindBy(css="input[name='phone']")
         private WebElement phone;
    @FindBy(css="div[data-test=\"Baggage-Option-1\"]>div>div:nth-child(2)>div")
         private WebElement checkBox_1_PersonalItem;
    @FindBy(css=".ReservationPassengerBags.CommonBags>div:nth-child(2)>div:nth-child(4)>div div:nth-child(2)")
         private WebElement NoCheckedBaggage;     
    @FindBy(css=".SelectInsurance__MiddleCell-sc-e77vu2-1:nth-child(3)>div>label>div:nth-child(2)")
         private WebElement CheckBox_NoInsurance;
    @FindBy(css="button[data-test='StepControls-passengers-next']")
         private WebElement Continue;
    @FindBy(css=".WizardStep___StyledDiv-sc-v1tvdj-7.hMuHhx")
         private List<WebElement> progeress_Steps;
    @FindBy(css="div[aria-live=\"polite\"]")
         private WebElement GivenName_errorMessage;
    @FindBy(css="div[aria-live=\"polite\"]")
         private WebElement SurName_errorMessage;
    @FindBy(css="div[aria-live=\"polite\"]")
         private WebElement Nationality_errorMessage;
    @FindBy(css="div[aria-live=\"polite\"]")
         private WebElement Gender_errorMessage;
    @FindBy(css="div[aria-live=\"polite\"]")
         private WebElement DateOfBirth_errorMessage;
    @FindBy(css="div[aria-live=\"polite\"]")
         private WebElement Passport_Or_Id_Num_errorMessage;
    @FindBy(css="div[aria-live=\"polite\"]")
         private WebElement ExpirationDate_errorMessage;
    @FindBy(css="div[aria-live=\"polite\"]>div:nth-child(2)")
         private WebElement email_errorMessage;
    @FindBy(css="div[aria-live=\"polite\"]>div:nth-child(2)")
         private WebElement Phone_errorMessage;

    public WebElement getGivenName_errorMessage() {
		return GivenName_errorMessage;
	}
	public WebElement getSurName_errorMessage() {
		return SurName_errorMessage;
	}
	public WebElement getNationality_errorMessage() {
		return Nationality_errorMessage;
	}
	public WebElement getGender_errorMessage() {
		return Gender_errorMessage;
	}
	public WebElement getDateOfBirth_errorMessage() {
		return DateOfBirth_errorMessage;
	}
	public WebElement getPassport_Or_Id_Num_errorMessage() {
		return Passport_Or_Id_Num_errorMessage;
	}
	public WebElement getExpirationDate_errorMessage() {
		return ExpirationDate_errorMessage;
	}
	public WebElement getEmail_errorMessage() {
		wait(1500);
		return email_errorMessage;
	}
	public WebElement getPhone_errorMessage() {
		wait(1500);
		return Phone_errorMessage;
	}
    
	public WebElement getGivenName() {
		return GivenName;
	}
	public WebElement getSurName() {
		return SurName;
	}
	public WebElement getDateOFBirth_Day() {
		return DateOFBirth_Day;
	}
	public WebElement getDateOfBirthYear() {
		return DateOfBirthYear;
	}
	public WebElement getExpirationDate_Day() {
		return ExpirationDate_Day;
	}
	public WebElement getExpirationDate_Year() {
		return ExpirationDate_Year;
	}
	public WebElement getEmail() {
		return email;
	}
	public List<WebElement> getProgeress_Steps() {
		return progeress_Steps;
	}
	
	public WebElement getPhone() {
		return phone;
	}
	public WebElement getNationality() {
		return nationality;
	}
	public WebElement getGender() {
		return gender;
	}
	public WebElement getPassportOrIdNumber() {
		return passportOrIdNumber;
	}
	
	public void fillName(String FirstName,String FamilyName) {
		fillText(GivenName, FirstName);
		fillText(SurName, FamilyName);
	}
	public void fillNationalityAndGender(String Nantionality, String Gender) {
		waitUntil.until(ExpectedConditions.elementToBeClickable(nationality));
		Select selectNationality=new Select(nationality);
		selectNationality.selectByVisibleText(Nantionality);
		Select selectGender=new Select(gender);
		selectGender.selectByVisibleText(Gender);	
	}	
	public void fillDateOfBirth(String dateOfBirth_Day,String dateOfBirth_Month, String dateOfBirth_Year) {
		fillText(DateOFBirth_Day, dateOfBirth_Day);
		fillText(DateOfBirthYear, dateOfBirth_Year);
		Select selectBirthDayMonth=new Select(DateOfBirth_Month);
		selectBirthDayMonth.selectByVisibleText(dateOfBirth_Month);		
	}
	public void fillPassportOrId(String passportOrIdNumber) {
		fillText(this.passportOrIdNumber, passportOrIdNumber);
	}
	public void fillExpirationDate(String expirationDate_Day,String expirationDate_Month,String expirationDate_Year) {
		fillText(this.ExpirationDate_Day, expirationDate_Day);
		fillText(this.ExpirationDate_Year, expirationDate_Year);
		Select selectExpirationMonth=new Select(this.ExpirationDate_Month);
		selectExpirationMonth.selectByVisibleText(expirationDate_Month);		
    }
	public void fillContactDetails(String email,String phone) {
		fillText(this.email, email);
		fillText(this.phone, phone);
	}
	public void checkBoxes() {
		click(checkBox_1_PersonalItem);
		click(NoCheckedBaggage);
		click(CheckBox_NoInsurance);
	}
	public void Continue() {
		click(Continue);
	}	
}
