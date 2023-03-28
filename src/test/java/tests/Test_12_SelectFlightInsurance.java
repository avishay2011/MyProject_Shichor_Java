package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import commonOps.Verifications;
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
import utils.UtilsConfiguration;
import utils.UtilsReadingFromXML_File;

public class Test_12_SelectFlightInsurance extends BaseTest {
    
	@Test(priority = 1)
	public void Test1_TravelBasicInsurance() {    	
    	MainPage MP=new MainPage(driver);
        Sign_In_Page signIn=new Sign_In_Page(driver);
        CreateTrip_Destination ctDestination=new CreateTrip_Destination(driver);
        CreateTrip_Dates ctDates= new CreateTrip_Dates(driver);
        TripOverviewPage tov=new TripOverviewPage(driver);
        SearchFlight sf=new SearchFlight(driver);
        PassengerDetails pd=new PassengerDetails(driver);
        TicketFare tf=new TicketFare(driver);
        Kiwi_Guarantee kg=new Kiwi_Guarantee(driver);
        Seating seating=new Seating(driver);
        signIn.logIn(UtilsConfiguration.readProperty("email"),UtilsConfiguration.readProperty("password"));
        MP.startPlanning();
        ctDestination.startPlanning();
        ctDestination.selectDestination(UtilsReadingFromXML_File.getData("CityEnglish"),UtilsReadingFromXML_File.getData("CityHebrew")); 
        ctDates.flexibleDates();
        tov.cancelPopup();
        tov.selectFlight();
        sf.acceptCookies(); 
        pd.selectTravelBasic(); 
        AssertJUnit.assertEquals(pd.getTravelInsuranceBill().getText().substring(3,19), "Travel insurance");    
    }
	
	@Test(priority = 2)
	public void Test2_TravelPlusInsurance() {
		PassengerDetails pd=new PassengerDetails(driver);
		pd.selectTravelPlus();
		 AssertJUnit.assertEquals(pd.getTravelInsuranceBill().getText().substring(3,19), "Travel insurance");    
	}
}
