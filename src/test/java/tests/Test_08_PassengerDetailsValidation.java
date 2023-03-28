package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.UtilsConfiguration;
import utils.UtilsReadingFromXML_File;

import java.awt.Desktop.Action;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.guide.ClickableWindow;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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

@Listeners(utils.Listeners.class)
public class Test_08_PassengerDetailsValidation extends BaseTest
{   
	Screen screen;
    String path = "C:/Users/avish/eclipse-workspace/Automation_Shichor/PicsSikuli/";
    JavascriptExecutor js;
    
    @Test (priority =1 )
    public void Test1_PassengerDetailsValidation() throws InterruptedException, FindFailed
    {   
    	js = (JavascriptExecutor) driver;
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
        pd.fillName(UtilsReadingFromXML_File.getData("passenger_first_name"), UtilsReadingFromXML_File.getData("passenger_family_name"));
        pd.fillNationalityAndGender(UtilsReadingFromXML_File.getData("nationality"), UtilsReadingFromXML_File.getData("gender"));
        pd.fillDateOfBirth(UtilsReadingFromXML_File.getData("birth_date_day"), UtilsReadingFromXML_File.getData("birth_date_month"), UtilsReadingFromXML_File.getData("birth_date_year"));
        pd.fillPassportOrId(UtilsReadingFromXML_File.getData("PassportOrId"));
        pd.fillExpirationDate(UtilsReadingFromXML_File.getData("Expiration_date_day"), UtilsReadingFromXML_File.getData("Expiration_date_month"), UtilsReadingFromXML_File.getData("Expiration_date_year"));
        pd.fillContactDetails(UtilsReadingFromXML_File.getData("email"), UtilsReadingFromXML_File.getData("phoneNumber"));            
    }
    
    @Test(priority =2 )
    public void Test2_VerifyReservetionProgres_Steps() { 	
    	PassengerDetails pd=new PassengerDetails(driver); 	
        for (WebElement el : pd.getProgeress_Steps()) {   ///  print all the elements on list just for ensure which elements  we are test 
        }
    	Verifications.visibility_Of_Element_On_List(pd.getProgeress_Steps()); ///call to verification method that test that all the required elements from the web elements list are visible on webPage
    }
    
    @Test(priority =3 )
    public void Test3_givenName_empty_Field() {
    	PassengerDetails pd=new PassengerDetails(driver); 	
    	pd.clear(pd.getGivenName());
    	pd.Continue();
    	pd.printText(pd.getGivenName_errorMessage());
     	Verifications.verifyTextEquals(pd.getGivenName_errorMessage(), "Required field");  	
    }
    @Test(priority =4 )
    public void Test4_givenName_Not_Latin_Characters() {
    	PassengerDetails pd=new PassengerDetails(driver); 
    	pd.fillText(pd.getGivenName(), "ישראל");
    	pd.Continue();
    	pd.printText(pd.getGivenName_errorMessage());
     	Verifications.verifyTextEquals(pd.getGivenName_errorMessage(), "Use Latin characters only.");  
     	pd.clear(pd.getGivenName());
    	pd.fillText(pd.getGivenName(), "Avishay");
    }
    @Test(priority =5 )
    public void Test5_surName_empty_Field() {
    	PassengerDetails pd=new PassengerDetails(driver); 	
    	pd.clear(pd.getSurName());
    	pd.Continue();
    	pd.printText(pd.getSurName_errorMessage());
     	Verifications.verifyTextEquals(pd.getSurName_errorMessage(), "Required field"); 	
     	
    }
    @Test(priority =6 )
    public void Test6_surnName_Not_Latin_Characters() {
    	PassengerDetails pd=new PassengerDetails(driver); 
    	pd.fillText(pd.getSurName(), "ישראלי");
    	pd.Continue();
    	pd.printText(pd.getSurName_errorMessage());
     	Verifications.verifyTextEquals(pd.getSurName_errorMessage(), "Use Latin characters only.");  
     	pd.clear(pd.getSurName());
    	pd.fillText(pd.getSurName(), "avraham");
    }
    @Test(priority =7 )
    public void Test7_passport_or_Id_Number_Missing() {
	    PassengerDetails pd=new PassengerDetails(driver); 	
   	    pd.clear(pd.getPassportOrIdNumber());
   	    pd.Continue();
        pd.printText(pd.getPassport_Or_Id_Num_errorMessage());
        Verifications.verifyTextEquals(pd.getPassport_Or_Id_Num_errorMessage(), "Required field"); 	
   }
    
   @Test(priority =8 )
   public void Test8_passport_or_Id_Number_More_Than_20_Characters() {
   	   PassengerDetails pd=new PassengerDetails(driver); 
       pd.fillText(pd.getPassportOrIdNumber(), "123456789101234567891");
       pd.Continue();
       pd.printText(pd.getPassport_Or_Id_Num_errorMessage());
       Verifications.verifyTextEquals(pd.getPassport_Or_Id_Num_errorMessage(), "Your passport/ID number must be 20 characters or less.");  
       pd.clear(pd.getPassportOrIdNumber());
       pd.fillText(pd.getPassportOrIdNumber(), "061204343");
    }
    
   @Test(priority =9 )
    public void Test9_dateOfBirth_missing() {
       PassengerDetails pd=new PassengerDetails(driver); 	
       pd.clear(pd.getDateOFBirth_Day());
       pd.Continue();
       pd.printText(pd.getDateOfBirth_errorMessage());
       Verifications.verifyTextEquals(pd.getDateOfBirth_errorMessage(), "Required field"); 
     	
    }
    
   @Test(priority =11 )
   public void Test10_dateOfBirth_dayBiggerThan_31() {
	  PassengerDetails pd=new PassengerDetails(driver); 
      pd.fillText(pd.getDateOFBirth_Day(), "32");
      pd.Continue();
   	  pd.printText(pd.getDateOfBirth_errorMessage());
      Verifications.verifyTextEquals(pd.getDateOfBirth_errorMessage(), "Day must be in the range 1 – 30.");  
      pd.clear(pd.getDateOFBirth_Day());
   	  pd.fillText(pd.getDateOFBirth_Day(), "25");
    	
   }
  @Test(priority =12 )
  public void Test11_dateOfBirth_YearIsHigherThen_2023() {
	  PassengerDetails pd=new PassengerDetails(driver); 
	  pd.clear(pd.getDateOfBirthYear());
      pd.fillText(pd.getDateOfBirthYear(), "2024");
      pd.Continue();
   	  pd.printText(pd.getDateOfBirth_errorMessage());
      Verifications.verifyTextEquals(pd.getDateOfBirth_errorMessage(), "Year must be in the range 1900 – 2023.");  
      pd.clear(pd.getDateOfBirthYear());
   	  pd.fillText(pd.getDateOfBirthYear(), "2000"); 	
  }
  
  @Test(priority =13 )
  public void Test12_ExpirationDateDay_Missing() {
	  PassengerDetails pd=new PassengerDetails(driver); 	
      pd.clear(pd.getExpirationDate_Day());
      pd.Continue();
      pd.printText(pd.getExpirationDate_errorMessage());
      Verifications.verifyTextEquals(pd.getExpirationDate_errorMessage(), "Required field");
  } 
  
  @Test(priority =14 )
  public void Test13_Expiration_date_dayBiggerThan_31() {
	 PassengerDetails pd=new PassengerDetails(driver); 
     pd.fillText(pd.getExpirationDate_Day(), "32");
     pd.Continue();
  	 pd.printText(pd.getExpirationDate_errorMessage());
     Verifications.verifyTextEquals(pd.getExpirationDate_errorMessage(), "Day must be in the range 1 – 30.");  
     pd.clear(pd.getExpirationDate_Day());
  	 pd.fillText(pd.getExpirationDate_Day(), "25");
   	
  }
  
  @Test(priority =15 )
  public void Test14_Expiration_date_yearLowerThen_2023() {
	  PassengerDetails pd=new PassengerDetails(driver); 
	  pd.clear(pd.getExpirationDate_Year());
      pd.fillText(pd.getExpirationDate_Year(), "2021");
      pd.Continue();
   	  pd.printText(pd.getExpirationDate_errorMessage());
      Verifications.verifyTextEquals(pd.getExpirationDate_errorMessage(), "Year must be in the range 2023 – 2100.");  
      pd.clear(pd.getExpirationDate_Year());
   	  pd.fillText(pd.getExpirationDate_Year(), "2025"); 	
  }
  
  

    @Test(priority =16 )
    public void Test15_email_missing() throws InterruptedException {
    	PassengerDetails pd=new PassengerDetails(driver); 	
    	pd.checkBoxes();
    	pd.clear(pd.getEmail());
    	pd.Continue();
    	pd.printText(pd.getEmail_errorMessage());
     	Verifications.verifyTextEquals(pd.getEmail_errorMessage(), "Required for your tickets");
    }
   @Test(priority =17 )
   public void Test16_email_wrongFormat() {
	   PassengerDetails pd=new PassengerDetails(driver); 
   	   pd.fillText(pd.getEmail(), "John1966gmail.com");
       pd.Continue();
   	   pd.printText(pd.getEmail_errorMessage());
       Verifications.verifyTextEquals(pd.getEmail_errorMessage(), "Please use this format: your@email.com");  
       pd.clear(pd.getEmail());
   	   pd.fillText(pd.getEmail(), "John1966@gmail.com");
   }
   
    
    @Test(priority =18 )
    public void Test_17_PhoneNumber_missing() {
    	PassengerDetails pd=new PassengerDetails(driver); 	
    	pd.clear(pd.getPhone());
    	pd.Continue();
    	pd.printText(pd.getPhone_errorMessage());
     	Verifications.verifyTextEquals(pd.getPhone_errorMessage(), "Required field");
    }   
    
}