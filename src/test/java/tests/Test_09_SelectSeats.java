package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonOps.Verifications;
import pageObjects.CreateTrip_Dates;
import pageObjects.CreateTrip_Destination;
import pageObjects.Kiwi_Guarantee;
import pageObjects.MainPage;
import pageObjects.OverViewAndPayment;
import pageObjects.PassengerDetails;
import pageObjects.SearchFlight;
import pageObjects.Seating;
import pageObjects.Sign_In_Page;
import pageObjects.TicketFare;
import pageObjects.TripOverviewPage;
import utils.UtilsConfiguration;
import utils.UtilsReadingFromXML_File;

@Listeners(utils.Listeners.class)
public class Test_09_SelectSeats extends BaseTest {
	
	Screen screen;
	String path = "C:/Users/avish/eclipse-workspace/Automation_Shichor/PicsSikuli/";
   
    
	@Test
	public  void test1__Outbound_flight_Seat() throws FindFailed, InterruptedException  {	
	
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
        OverViewAndPayment ovp=new OverViewAndPayment(driver);
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
    //    seating.Continue();
     //   seating.ContinueWithSeatSelection();
       seating.select_Outbound_flight_Seat(UtilsReadingFromXML_File.getData("outBoundFlight_Row"),UtilsReadingFromXML_File.getData("outBoundFlight_LocationOnRow"));
        String Expected_outbound_Flight_Seat=UtilsReadingFromXML_File.getData("outBoundFlight_Row")+"-"+UtilsReadingFromXML_File.getData("outBoundFlight_LocationOnRow");
        String Actual_outBound_Flight_Seat= ovp.GetText(ovp.getSeat_Outbound_Flight()).substring(12, 16);
        AssertJUnit.assertEquals(Expected_outbound_Flight_Seat, Actual_outBound_Flight_Seat);    
        ovp.backToPreviousPage();
	} 
	
   @Test
    public void test2_Select_Return_flight_seat() throws FindFailed {
    	Seating seating=new Seating(driver);
    	OverViewAndPayment ovp=new OverViewAndPayment(driver);
    	seating.select_Return_Flight_Seat(UtilsReadingFromXML_File.getData("ReturnFlight_Row"),UtilsReadingFromXML_File.getData("ReturnFlight_LocationOnRow"));
    	String Expected_Return_Flight_Seat=UtilsReadingFromXML_File.getData("ReturnFlight_Row")+"-"+UtilsReadingFromXML_File.getData("ReturnFlight_LocationOnRow");
        String Actual_Return_Flight_Seat= ovp.GetText(ovp.getSeat_Return_Flight()).substring(12, 16);
        AssertJUnit.assertEquals(Expected_Return_Flight_Seat, Actual_Return_Flight_Seat);    
	    }
    
    

}
