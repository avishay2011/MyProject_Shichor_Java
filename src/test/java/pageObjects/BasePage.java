package pageObjects;

import java.io.File;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public class BasePage {
	WebDriver driver;
	JavascriptExecutor js=(JavascriptExecutor)driver; 
	Actions action;
	WebDriverWait waitUntil;
	Screen screen;
	String path = "C:/Users/avish/eclipse-workspace/Automation_Shichor/PicsSikuli/";

	
	public BasePage (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		action=new Actions(driver);
		waitUntil=new WebDriverWait(driver, Duration.ofSeconds(15));
		screen = new Screen();			
	}

	
///	String main=driver.getWindowHandle(); /// Main page of shichor   - gt the main page of Shichor 
	public void moveToNewWindow() {
    Set<String> list=driver.getWindowHandles();                                 /// Move to the new window that opens 
    for (String win: list) {
 	   driver.switchTo().window(win);
    }
    }
	
	/// this method is used just for check if the element is the required element  that needed for the test
	protected void highlightElement(WebElement element) {
		js = (JavascriptExecutor) driver;	
		waitUntil.until((ExpectedConditions.visibilityOf(element)));
		js.executeScript("arguments[0].setAttribute('style','background-color:yellow; border: 1px solid blue;');", element);
		

	}
	
	
	protected void highlightElementForShorTime(WebElement element, String BorderColor,String BackGroundColor) {
		//keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = " background-color:"+BackGroundColor +"; border: 1px solid " + BorderColor + ";" + originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// Change the style 
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
				element);

		// Change the style back after few miliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},600);", element);
	}

	public void fillText(WebElement el, String text) {
		waitUntil.until((ExpectedConditions.visibilityOf(el)));
		highlightElementForShorTime(el, "pink","green");
		el.clear();
		el.sendKeys(text);
	}

	public void click(WebElement el) {
		waitUntil.until(ExpectedConditions.elementToBeClickable(el));
		highlightElementForShorTime(el, "pink","yellow");
		el.click();
	}
	
	public void clear(WebElement el) {
		waitUntil.until(ExpectedConditions.elementToBeClickable(el));
		highlightElementForShorTime(el, "pink","yellow");
		el.sendKeys(Keys.CONTROL+"a");
		el.sendKeys(Keys.BACK_SPACE);
	}

	public void printText(WebElement el) {
		waitUntil.until(ExpectedConditions.visibilityOf(el));
		System.out.println(getText(el));
	}

	public void wait(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (Exception e) {
		}
	}
	
	
	
	public void hoverToElementAndClick(WebElement el) {
		waitUntil.until(ExpectedConditions.visibilityOf(el));
		action.moveToElement(el).click().build().perform();
		wait(1500);
	}
	
	public void hoverToElementAndFillText(WebElement el,String cityToSearch) {
		waitUntil.until(ExpectedConditions.visibilityOf(el));
		action.moveToElement(el).click().sendKeys(cityToSearch).perform();
		wait(1500);	
	}
	
	public void clickOnImage(String fileName) throws FindFailed {
		wait(2000);
		screen.click(path + fileName);
		wait(2500);
	}
	
	public void doesTheImageExist(String fileName){
		screen.exists(path + fileName);
	}
	
	public void dragAndDrop(String drag,String drop) throws FindFailed {
		wait(2500);
		screen.dragDrop(path + drag, path + drop );
	}
	
	public String getText(WebElement el) {
		waitUntil.until(ExpectedConditions.visibilityOf(el));
		return el.getText();
	}
	
	
	
	
	}




