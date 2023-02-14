package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
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
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import utils.UtilsConfiguration;
import utils.UtilsReadingFromXML_File;

@Listeners(utils.Listeners.class)
public class Test_04_SearchDestinationManually extends BaseTest {
	
	@Test(priority =1)
	public  void test1_SearchDestinationManually() throws InterruptedException  {	
        MainPage MP=new MainPage(driver);
        Sign_In_Page signIn=new Sign_In_Page(driver);
        CreateTrip_Destination ctDestination=new CreateTrip_Destination(driver);
        CreateTrip_Dates ctDates=new CreateTrip_Dates(driver);
        TripOverviewPage tov=new TripOverviewPage(driver);
        SearchFlight sf=new SearchFlight(driver);
        PassengerDetails pd=new PassengerDetails(driver);
        TicketFare tf=new TicketFare(driver);
        Kiwi_Guarantee kg=new Kiwi_Guarantee(driver);
        Seating seating=new Seating(driver);
        signIn.LogIn(UtilsConfiguration.readProperty("email"),UtilsConfiguration.readProperty("password"));
        MP.startPlanning();
        ctDestination.startPlanning();
        ctDestination.searchDestination(UtilsReadingFromXML_File.getData("destintation_for_Searching"));
        Verifications.assertTheTextContains_SpecificWords(ctDestination.getSearchResults().get(0), "buenos");
        }
	
	@Test(priority=2,dataProvider = "Data_Provider_destinations",dataProviderClass =utils.ManageDDT.class)
	public void test2_searchAndVerifyDestination_Exist_Or_Not(String destination,String shouldExist) {
		CreateTrip_Destination ctDestination=new CreateTrip_Destination(driver);
		ctDestination.searchDestination(destination);
		//If on csv file  the data parameter is exist will be made a verification that the city is exist. otherwise will be made verification that is not exist
		if (shouldExist.equalsIgnoreCase("exist"))
			Verifications.existanceOfElement(ctDestination.getSearchResults());
		else if (shouldExist.equalsIgnoreCase("notExist"))
			Verifications.NonExistanceOfElement(ctDestination.getSearchResults());
		else 
			throw new RuntimeException("Invalid expected input in data driven testing , please select exist or notExist only ");
	}
	}