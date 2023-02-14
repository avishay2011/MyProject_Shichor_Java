package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;

public class Seating extends BasePage{
    JavascriptExecutor js=(JavascriptExecutor) driver;
    
	public Seating(WebDriver driver) {
		super(driver);		
	}

	@FindBy(css=".ButtonPrimitive__StyledButtonPrimitive-sc-1lbd19y-0.jxYHvZ")
	private WebElement skipSeatSelection;
	@FindBy(css=".Stack__StyledStack-sc-53pobq-0.lkDEal")
	private List<WebElement> flight_To_Destination;
	@FindBy(css="div[data-test=\"SeatingResponsiveSeat-1-A\"]")
	private WebElement seat;
	@FindBy(css=".ContentWrapper__StyledActions-sc-4br52r-1>div>button:nth-child(2)>div>div")
	private WebElement confirmSeat;
	@FindBy(css="button[data-test=\"Seating-AccordionFooter-button-next\"]>div>div")
	private WebElement nextSeat;
	@FindBy(css="button[data-test=\"StepControls-SeatingStep-next\"]>div>div")
	private WebElement ContinueToNextPage;
	
	public void select_Outbound_flight_Seat(String row, String locationOnRow) throws FindFailed {
		wait(10000);
		highlightElement(flight_To_Destination.get(0).findElement(By.cssSelector("[data-test=\"SeatingDesktopSeat-"+row+"-"+locationOnRow+"\"]")));
		js.executeScript("window.scrollBy(0,1300)", "");
		Pattern selectedSeat=new Pattern(path+ "Seat").similar(0.80);
		screen.click(selectedSeat);
    	click(confirmSeat);
    	click(ContinueToNextPage);
	}
	public void select_Return_Flight_Seat(String row, String locationOnRow) throws FindFailed {
		click(nextSeat);
		highlightElement(flight_To_Destination.get(1).findElement(By.cssSelector("[data-test=\"SeatingDesktopSeat-"+row+"-"+locationOnRow+"\"]")));
		wait(2000);
		js.executeScript("window.scrollBy(0,1300)", "");
		Pattern selectedSeat=new Pattern(path+ "Seat").similar(0.80);
		screen.click(selectedSeat);
    	click(confirmSeat);
    	click(ContinueToNextPage);
	}

}
