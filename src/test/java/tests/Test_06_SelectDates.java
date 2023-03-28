package tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonOps.Verifications;
import pageObjects.CreateTrip_Dates;
import pageObjects.CreateTrip_Destination;
import pageObjects.MainPage;
import pageObjects.PassengerDetails;
import pageObjects.SearchFlight;
import pageObjects.Sign_In_Page;
import pageObjects.TicketFare;
import pageObjects.TripOverviewPage;
import io.qameta.allure.testng.TestInstanceParameter;
import utils.UtilsConfiguration;
import utils.UtilsReadingFromXML_File;

@Listeners(utils.Listeners.class)
public class Test_06_SelectDates extends BaseTest{
	 
	@Test
	public void test1_selectDates 
	() {
		MainPage MP=new MainPage(driver);
        Sign_In_Page signIn=new Sign_In_Page(driver);
        CreateTrip_Destination ctDestination=new CreateTrip_Destination(driver);
        CreateTrip_Dates ctDates=new CreateTrip_Dates(driver);
        TripOverviewPage tov=new TripOverviewPage(driver);
        SearchFlight sf=new SearchFlight(driver);
        PassengerDetails pd=new PassengerDetails(driver);
        TicketFare tf=new TicketFare(driver);
        signIn.logIn(UtilsConfiguration.readProperty("email"),UtilsConfiguration.readProperty("password"));
        MP.startPlanning();
        ctDestination.startPlanning();
        ctDestination.selectDestination(UtilsReadingFromXML_File.getData("CityEnglish"),(UtilsReadingFromXML_File.getData("CityHebrew"))); 
        ctDates.selectDates(UtilsReadingFromXML_File.getData("departureMonth"),UtilsReadingFromXML_File.getData("departureDay"),UtilsReadingFromXML_File.getData("returnMonth"),UtilsReadingFromXML_File.getData("returnDay"));
        ctDates.plan_A_Trip_Button();
        tov.cancelPopup();    
        String departureDayExpected=UtilsReadingFromXML_File.getData("departureDay");
        String departureMonthExpected=UtilsReadingFromXML_File.getData("departureMonth").substring(0,3);
        String LandingDayExpected=UtilsReadingFromXML_File.getData("returnDay");
        String LandingMonthExpected=UtilsReadingFromXML_File.getData("returnMonth").substring(0,3);
        String expected_Selected_Dates= departureDayExpected+" "+departureMonthExpected+" - "+LandingDayExpected+ " "+ LandingMonthExpected;
        tov.printText(tov.getSelectedDates());
        System.out.println(expected_Selected_Dates);      
        Verifications.verifyTextEquals(tov.getSelectedDates(), expected_Selected_Dates);
	}
}
