 package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateTrip_Dates extends BasePage {

		public CreateTrip_Dates(WebDriver driver) {
			super(driver);
		}	
		
		@FindBy(css=".flatpickr-days>.dayContainer:nth-child(2)>.flatpickr-day")
	    private List<WebElement> Day;
		
		@FindBy(css=".search-dates-menu__flexible:nth-child(2)")
	    private WebElement FlexiblDates;
		
		@FindBy(css=".flatpickr-next-month")
	    private WebElement nextMonthButton;
		
		@FindBy(css=".flatpickr-months>div>div>.cur-month")
	    private WebElement currentMonth;
		
		@FindBy(css=".dates-form__submit>.btn-blue.btn")
	    private WebElement plan_a_Trip_Button;
			 				   
	    public void flexibleDates() {
	    	click(FlexiblDates);
	    }
	    
	    public void plan_A_Trip_Button(){
	    	click(plan_a_Trip_Button);
	    }
	  
	    public void selectDates(String departureMonth,String departureday,String returnMonth,String returnDay) {
	    	wait(2000);
	    	for (int i = 0; i <= 13; i++) {
				if(currentMonth.getText().equalsIgnoreCase(departureMonth))
					break;
				else {
					nextMonthButton.click();
				}			
			}	
	    	click(driver.findElement(By.cssSelector(".flatpickr-day[aria-label=\""+departureMonth+" "+departureday+", 2023\"]")));	
	    	wait(2000);
	    	for (int i = 0; i <= 13; i++) {
				if(currentMonth.getText().equalsIgnoreCase(returnMonth))
					break;
				else {
					nextMonthButton.click();
				}			
			}
	   
	    	click(driver.findElement(By.cssSelector(".flatpickr-day[aria-label=\""+returnMonth+" "+returnDay+", 2023\"]")));  	
	    	}  
	    }
	    
	   