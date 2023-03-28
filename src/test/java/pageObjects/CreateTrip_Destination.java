package pageObjects;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class CreateTrip_Destination extends BasePage {

	public CreateTrip_Destination(WebDriver driver) {
		super(driver);
	}	
	@FindBy(css =".flows__item:nth-child(1)>.flows__item-btn>.btn-blue")
	    private WebElement StartPlanning;			
	@FindBy(css=".destination-form__list-item")
	    private List<WebElement> destination;
	@FindBy(css=".hide-xs.show-md.destination-form__select-desktop")
	    private WebElement SearchField;
	@FindBy(css=".destination-form-place-option>span")
	    private List<WebElement> SearchResults;
	 	
    public List<WebElement> getSearchResults() {
			return SearchResults;
		}
    public void startPlanning() {
    	click(StartPlanning);   	   	
    }
    
    public void searchDestination(String cityForSearch) {
	  hoverToElementAndClick(SearchField);
	  hoverToElementAndFillText(SearchField,cityForSearch);
	  wait(1000);
    }
    
	public void selectDestination(String cityInEnglish,String cityInHebrew) {
    	for (WebElement el : destination) {
    		if(el.getText().equalsIgnoreCase(cityInEnglish)||el.getText().equalsIgnoreCase(cityInHebrew)) {
			click(el);
			break;
		}  	
    	}
   	}
	
	
    }
    
   
    
    
    


