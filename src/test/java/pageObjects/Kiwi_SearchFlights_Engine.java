package pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;

import commonOps.Verifications;

public class Kiwi_SearchFlights_Engine extends BasePage{
	
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	public Kiwi_SearchFlights_Engine(WebDriver driver) {
		super(driver);	
	}
	@FindBy(css="button[id=\"cookies_accept\"]")
	private WebElement AcceptCookies;
	@FindBy(css="a[data-test=\"LandingSearchButton\"]")
	private WebElement SearchButton;
	@FindBy(css="strong[data-test=\"ResultCardPrice\"]")
	private List<WebElement> FlightPrice;
	@FindBy(css="div[data-test=\"SortBy-price\"]")
	private WebElement SortByPrice;
	@FindBy(css="div[data-test=\"FilterHeader-price\"]>div>div>div:nth-child(2)>button")
	private WebElement FilterPriceButton;
	@FindBy(css="div[data-test=\"SliderHandle-0\"]")
	private WebElement SlidePriceStart;
	@FindBy(css=".PriceFilterContentstyled__PriceFilterWrapper-sc-16vaz8s-0>p>span")
	private WebElement LowerPriceLimit;
	@FindBy(css="button[data-test=\"ClearButton\"]>div")
	private WebElement ClearButton;
	@FindBy(css="button[aria-controls=\"1-15val-slideID\"]>div")
	private WebElement FilterByDurationButton;
	@FindBy(css="div[data-test=\"FilterHeader-duration\"] div[data-test=\"SliderHandle-0\"]")
	private WebElement SlideUpperDuration;
	@FindBy(css=".BadgePrimitive__StyledBadgeContent-sc-1ibxlos-2.deIpMl>time")
	private List<WebElement> FlightDuration;
	@FindBy(css=".FilterContentSubPanelstyled__SubPanel-sc-kcg2u1-0>p")
	private WebElement UpperDurationLimit;
	@FindBy(css=".Radio__StyledIconContainer-sc-1e6hy4x-1.cQMXav")
	private WebElement radioButton_NonStop;
	@FindBy(css="div[data-test=\"StopCountBadge-0\"]>span>div>div")
	private List<WebElement> Stops;
	@FindBy(css=".fEwykE>div:nth-child(1)>div>div:nth-child(2)>div:nth-child(1)>div:nth-child(2)>strong:nth-child(1)>time")
	private List<WebElement> DepartureHours;
	@FindBy(css="a[data-test=\"HeaderLink\"]")
	private WebElement clearStopsFilter;
	@FindBy(css="button[aria-controls=\"1-14val-slideID\"]>div>div")
	private WebElement openTimesFilter;
	@FindBy(css="div[data-test=\"TimesFilterSlider-departure\"]>div>div:nth-child(2)")
	private WebElement SlideMinDepartureHour;
	@FindBy(css="div[data-test=\"TimesFilterSlider-departure\"]>div>div:nth-child(3)")
	private WebElement SlideMaxDepartureHour;
	@FindBy(css="a[data-test=\"HeaderLink\"]")
	private WebElement clearTimesFilter;
	@FindBy(css="div[data-test=\"TimesFilterSlider-arrival\"]>div>div:nth-child(2)")
	private WebElement SlideMinArrivalHour;
	@FindBy(css="div[data-test=\"TimesFilterSlider-arrival\"]>div>div:nth-child(3)")
	private WebElement SlideMaxArrivalHour;
	@FindBy(css=".fEwykE>div:nth-child(1)>div>div>div:nth-child(3)>div:nth-child(2)>strong>time")
	private List<WebElement> ArrivalHours;
	@FindBy(css="button[data-test=\"CabinClassFooter-cancel\"]")
	private WebElement cancelSuggestion;
	@FindBy(css="div[id=\"1-12val-labelID\"]")
	private WebElement openCarriersFilter;
	@FindBy(css="button[data-test=\"MultiSelect-SelectAllButton\"]>div>div")
	private WebElement DeselectAllCarriers;
	@FindBy(css=".MultiSelectFilterComponentstyled__ShowAll-sc-pqi52b-2>button>div>div")
	private WebElement ShowAllCarriers;
	@FindBy(css=".Checkbox__StyledTextContainer-sc-y66hzm-1>span>span>span")
	private List<WebElement> carriers;
	@FindBy(css=".CarrierLogo__StyledCarrierLogo-sc-1rhi78a-1>img")
	private List <WebElement> carriersLogo;
	@FindBy(css=".TooltipContent__StyledTooltipContent-sc-1s3rbui-3")
	private WebElement toolTipContent;
	
	public List<WebElement> getDepartureHours() {
		return DepartureHours;
	}	
	public List<WebElement> getStops() {
		return Stops;
	}
	public List<WebElement> getFlightDuration() {
		return FlightDuration;
	}		
	public WebElement getUpperDurationLimit() {
		return UpperDurationLimit;
	}
	public List<WebElement> getFlightPrice() {
		return FlightPrice;
	}
	public WebElement getLowerPriceLimit() {
		return LowerPriceLimit;
	}

	public List<WebElement> getArrivalHours() {
		return ArrivalHours;
	}	

	public void acceptCookies() {
		moveToNewWindow();
		click(AcceptCookies);
	}
	public void SearchButton() {
		click(SearchButton);
	}
	public void SortByPrice() {
		wait(5000);
		click(SortByPrice);
		js.executeScript("window.scrollBy(0,15000)", "");
		wait(5000);
	}
	public void FilterPrice() {
		waitUntil.until(ExpectedConditions.elementToBeClickable(FilterPriceButton));
		click(FilterPriceButton);
		hoverToElement(SlidePriceStart);
		action.dragAndDropBy(SlidePriceStart, 129, 0).build() .perform();
		wait(5500);
	}
	
	public void FilterByDuration() {
		click(FilterByDurationButton);
		hoverToElement(SlideUpperDuration);
		action.dragAndDropBy(SlideUpperDuration, -250, 0).build() .perform();	
		wait(5000);	
	}
	public void FilterNonStop() {
		wait(7000);
		js.executeScript("window.scrollBy(0,-500)", "");
		waitUntil.until(ExpectedConditions.elementToBeClickable(radioButton_NonStop));
		click(radioButton_NonStop);
		wait(3000);
	}
	
	public void FilterByDepartureHour() {
		click(clearStopsFilter);
		click(openTimesFilter);
		waitUntil.until(ExpectedConditions.elementToBeClickable(SlideMinDepartureHour));
		action.dragAndDropBy(SlideMinDepartureHour,   10, 0).build() .perform();
		waitUntil.until(ExpectedConditions.visibilityOf(SlideMaxDepartureHour));
		action.dragAndDropBy(SlideMaxDepartureHour, -180, 0).build() .perform();
		wait(6500);
	}
	
	public void FilterByArrivalHour() {
		wait(7000);
		hoverToElement(clearTimesFilter);
		waitUntil.until(ExpectedConditions.elementToBeClickable(SlideMinArrivalHour));
		action.dragAndDropBy(SlideMinArrivalHour,   160, 0).build() .perform();
		waitUntil.until(ExpectedConditions.visibilityOf(SlideMaxArrivalHour));
		action.dragAndDropBy(SlideMaxArrivalHour, -50, 0).build() .perform();		
		wait(6500);
		
	}
	
	public void FilterByAirlineCompany(String carrier1) {
		waitUntil.until(ExpectedConditions.elementToBeClickable(cancelSuggestion));
		js.executeScript("window.scrollBy(0,-650)", "");
		wait(8000);
		click(openCarriersFilter);
		wait(8000);
		click(DeselectAllCarriers);
		click(clearTimesFilter);
		wait(3000);
		for (WebElement el : carriers) {
			 if (el.getText().equalsIgnoreCase(carrier1))
				 click(el);
		}
		wait(10000);
		for (WebElement el : carriersLogo) {
			action.moveToElement(el).build().perform();
			wait(1500);
			printText(toolTipContent);
		    AssertJUnit.assertTrue("The list contains an airline company that not included in the filter terms", toolTipContent.getText().equalsIgnoreCase(carrier1));
    		
		    
		}
	}
	
	public void clearFilters() {
		waitUntil.until(ExpectedConditions.visibilityOf(ClearButton));
		wait(5000);
		click(ClearButton);
	}
	

	
}
