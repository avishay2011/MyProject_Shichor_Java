package tests;

import org.openqa.selenium.StaleElementReferenceException;
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

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import utils.UtilsConfiguration;
import utils.UtilsReadingFromXML_File;

@Listeners(utils.Listeners.class)
public class Test_02_Sanity extends BaseTest {
	
	@Test
	public  void test1_Sanity()  {	
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
        ctDestination.selectDestination(UtilsReadingFromXML_File.getData("CityEnglish"),UtilsReadingFromXML_File.getData("CityHebrew")); 
        ctDates.flexibleDates();
        tov.cancelPopup();
        tov.selectFlight();
        sf.acceptCookies();
        pd.fillName(UtilsReadingFromXML_File.getData("passenger_first_name"), UtilsReadingFromXML_File.getData("passenger_family_name"));
        pd.fillNationalityAndGender(UtilsReadingFromXML_File.getData("nationality"), UtilsReadingFromXML_File.getData("gender"));
        pd.fillDateOfBirth(UtilsReadingFromXML_File.getData("birth_date_day"), UtilsReadingFromXML_File.getData("birth_date_month"), UtilsReadingFromXML_File.getData("birth_date_year"));
        pd.fillPassportOrId(UtilsReadingFromXML_File.getData("PassportOrId"));
        pd.fillExpirationDate(UtilsReadingFromXML_File.getData("Expiration_date_day"), UtilsReadingFromXML_File.getData("Expiration_date_month"), UtilsReadingFromXML_File.getData("Expiration_date_year"));
        pd.fillContactDetails(UtilsReadingFromXML_File.getData("email"), UtilsReadingFromXML_File.getData("phoneNumber"));
        pd.checkBoxes();
        pd.Continue();
        tf.continueStandardTicked();
        tf.selectService();
        kg.selectTransferProtection();
   //     seating.Continue();
   //     seating.skipSeatSelection();
	}

}
