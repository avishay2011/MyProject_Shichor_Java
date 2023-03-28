package tests;

import org.sikuli.script.Screen;
import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
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

@Listeners(utils.Listeners.class)
public class Test_05_SelectDestination extends BaseTest{
		 
	@Test
	public void test1_selectDestination	() 
	{
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
        signIn.logIn(UtilsConfiguration.readProperty("email"),UtilsConfiguration.readProperty("password"));
        MP.startPlanning();
        ct.startPlanning();
        ct.selectDestination(UtilsReadingFromXML_File.getData("CityEnglish"),UtilsReadingFromXML_File.getData("CityHebrew")); 
        ctDates.flexibleDates();
        tov.cancelPopup();
        Verifications.verifyTextEquals(tov.getWhere(), UtilsReadingFromXML_File.getData("CityEnglish"));  
	}	
	
	@Test
	public void test2_PictureOfDetination() {
		TripOverviewPage tov=new TripOverviewPage(driver);
        tov.doesTheImageExist("BigPictureParis");
	}	
}
