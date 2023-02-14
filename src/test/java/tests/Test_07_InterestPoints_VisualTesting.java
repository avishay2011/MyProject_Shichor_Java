package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.UtilsConfiguration;
import utils.UtilsReadingFromXML_File;
import java.awt.Desktop.Action;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.guide.ClickableWindow;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.CreateTrip_Dates;
import pageObjects.CreateTrip_Destination;
import pageObjects.Kiwi_Guarantee;
import pageObjects.MainPage;
import pageObjects.PassengerDetails;
import pageObjects.SearchFlight;
import pageObjects.Seating;
import pageObjects.Sign_In_Page;
import pageObjects.TicketFare;
import pageObjects.TripOverviewPage;

@Listeners(utils.Listeners.class)
public class Test_07_InterestPoints_VisualTesting extends BaseTest
{   
	Screen screen;
    String path = "C:/Users/avish/eclipse-workspace/Automation_Shichor/PicsSikuli/";
    JavascriptExecutor js;

    @Test 
    public void Test1_GoToRoutesList() throws InterruptedException, FindFailed
    {   
    	js = (JavascriptExecutor) driver;
    	MainPage MP=new MainPage(driver);
        Sign_In_Page signIn=new Sign_In_Page(driver);
        CreateTrip_Destination ct=new CreateTrip_Destination(driver);
        CreateTrip_Dates ctDates= new CreateTrip_Dates(driver);
        TripOverviewPage tov=new TripOverviewPage(driver);
        SearchFlight sf=new SearchFlight(driver);
        PassengerDetails pd=new PassengerDetails(driver);
        TicketFare tf=new TicketFare(driver);
        Kiwi_Guarantee kg=new Kiwi_Guarantee(driver);
        Seating seating=new Seating(driver);
        screen = new Screen();
        signIn.LogIn(UtilsConfiguration.readProperty("email"),UtilsConfiguration.readProperty("password"));
        MP.startPlanning();
        ct.startPlanning();
        ct.selectDestination(UtilsReadingFromXML_File.getData("CityEnglish"),UtilsReadingFromXML_File.getData("CityHebrew")); 
        ctDates.flexibleDates();
        tov.cancelPopup();
        js.executeScript("window.scrollBy(0,550)", "");
        tov.clickOnImage("PrestigeousHistoryRoute");
        tov.clickOnImage("routeForward");
        tov.dragAndDrop("DragBegin", "DragEnd");
    }
    
    @Test (dataProvider = "getParameters") 
    public void Test2_ViewInterestPointsOnMap(String interestPlaceIcon,String interestPlaceReview) throws FindFailed {   
      	TripOverviewPage tov=new TripOverviewPage(driver);    
        tov.TestInterestPlaces(interestPlaceIcon,interestPlaceReview);
    }
    
   @DataProvider
	public Object[][] getParameters() {
		Object[][]myParameters= {
				{"UnescoIcon","UnescoReview"},
				{"eifelicon","eiffelReview"},
				{"arcTheRriumpghIcon","archtheTriumphReview"},
				{"LidonIcon","LidoReview"},
		};
		return myParameters;		
	}
}

