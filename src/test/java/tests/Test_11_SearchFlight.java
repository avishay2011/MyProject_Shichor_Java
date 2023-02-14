package tests;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v104.media.model.Timestamp;
import org.sikuli.guide.ClickableWindow;
import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonOps.Verifications;
import io.restassured.internal.common.assertion.Assertion;
import pageObjects.CreateTrip_Dates;
import pageObjects.CreateTrip_Destination;
import pageObjects.Kiwi_Guarantee;
import pageObjects.Kiwi_SearchFlights_Engine;
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
public class Test_11_SearchFlight extends BaseTest {
	JavascriptExecutor js;

	@Test(priority = 1)
	public void test1_Open_KIWI_searchEngine() throws InterruptedException {
		MainPage MP = new MainPage(driver);
		js = (JavascriptExecutor) driver;
		Sign_In_Page signIn = new Sign_In_Page(driver);
		CreateTrip_Destination ctDestination = new CreateTrip_Destination(driver);
		CreateTrip_Dates ctDates = new CreateTrip_Dates(driver);
		TripOverviewPage tov = new TripOverviewPage(driver);
		SearchFlight sf = new SearchFlight(driver);
		PassengerDetails pd = new PassengerDetails(driver);
		TicketFare tf = new TicketFare(driver);
		Kiwi_Guarantee kg = new Kiwi_Guarantee(driver);
		Seating seating = new Seating(driver);
		Kiwi_SearchFlights_Engine ksf = new Kiwi_SearchFlights_Engine(driver);
		signIn.LogIn(UtilsConfiguration.readProperty("email"), UtilsConfiguration.readProperty("password"));
		MP.startPlanning();
		ctDestination.startPlanning();
		ctDestination.selectDestination(UtilsReadingFromXML_File.getData("CityEnglish"),
				UtilsReadingFromXML_File.getData("CityHebrew"));
		ctDates.flexibleDates();
		tov.cancelPopup();
		tov.open_FlightsSearch_Engine();
		ksf.acceptCookies();
		ksf.SearchButton();
		ksf.moveToNewWindow();
		// String DefaulatTestURL=driver.getCurrentUrl();

	}

	@Test(priority = 2)
	public void test2_Sort_Cheapest_First() throws InterruptedException {
		Kiwi_SearchFlights_Engine ksf = new Kiwi_SearchFlights_Engine(driver);
		ksf.SortByPrice();
		int i = 0;
		for (WebElement el : ksf.getFlightPrice()) {
			if (i < ksf.getFlightPrice().size() - 1) {
				int a = Integer.parseInt(ksf.getFlightPrice().get(i).getText().substring(2, 5));
				int b = Integer.parseInt(ksf.getFlightPrice().get(i + 1).getText().substring(2, 5));
				AssertJUnit.assertTrue("The list is not sorted correctly " + a + " is bigger than " + b, a <= b);
				i++;
			}
		}
	}

	@Test(priority = 3)
	public void test3_Filter_By_Price() {
		Kiwi_SearchFlights_Engine ksf = new Kiwi_SearchFlights_Engine(driver);
		ksf.FilterPrice();
		int i = 0;
		for (WebElement el : ksf.getFlightPrice()) {
			if (i < ksf.getFlightPrice().size() - 1) {
				int lowerPriceLimit = Integer.parseInt(ksf.getLowerPriceLimit().getText().substring(2, 5));
				int upperPriceLimit = Integer.parseInt("999999");
				int a = Integer.parseInt(ksf.getFlightPrice().get(i).getText().substring(2, 5));
				AssertJUnit.assertTrue("The list is not filtered correctly " + a + " a is not in the required range",
						a >= lowerPriceLimit && a <= upperPriceLimit);
				i++;
			}
		}
	}

	@Test(priority = 4)
	public void test4_Filter_By_MaxTravelTime() {
		Kiwi_SearchFlights_Engine ksf = new Kiwi_SearchFlights_Engine(driver);
		ksf.clearFilters();
		ksf.FilterByDuration();
		int Max_Flight_Duration = Integer.parseInt(ksf.getUpperDurationLimit().getText().substring(6, 7)) * 60;
		int flight_Duration_in_Minutes = 0;
		for (WebElement el : ksf.getFlightDuration()) {
			if (el.getText().length() > 2)
				flight_Duration_in_Minutes = Integer.parseInt(el.getText().substring(0, 1)) * 60
						+ Integer.parseInt(el.getText().substring(3, 5));
			else
				flight_Duration_in_Minutes = Integer.parseInt(el.getText().substring(0, 1)) * 60;
			AssertJUnit.assertTrue("The flight duration is longer then 5 hours",
					flight_Duration_in_Minutes <= Max_Flight_Duration);
			System.out.println(Integer.toString(flight_Duration_in_Minutes));
		}
		System.out.println(Integer.toString(Max_Flight_Duration));
	}

	@Test(priority = 5)
	public void test5_Filter_NonStop() {
		Kiwi_SearchFlights_Engine ksf = new Kiwi_SearchFlights_Engine(driver);
		ksf.clearFilters();
		ksf.FilterNonStop();
		for (WebElement el : ksf.getStops()) {
			Verifications.verifyTextInElememt(el, "Direct");
		}
	}

	public void test9_Filter_By_Airline_company() {

	}

	@Test(priority = 6)
	public void test6_Filter_By_departure_hour() {
		Kiwi_SearchFlights_Engine ksf = new Kiwi_SearchFlights_Engine(driver);
		ksf.FilterByDepartureHour();
		js.executeScript("window.scrollBy(0,15000)", "");
		for (WebElement el : ksf.getDepartureHours()) {
			java.sql.Timestamp ts = java.sql.Timestamp.valueOf("2000-01-01 " + (el.getText() + ":01"));
			AssertJUnit.assertTrue("The Departure hour is not in the required range",
					ts.after(java.sql.Timestamp.valueOf("2000-01-01 01:00:00.0")));
			AssertJUnit.assertTrue("The departure hour is not in the required range",
					ts.before(java.sql.Timestamp.valueOf("2000-01-01 07:00:01.0")));
			System.out.println(el.getText());
			System.out.println(ts.toString());
		}
	}

	@Test(priority = 7)
	public void test7_Filter_By_Arrival_Hour() {
		Kiwi_SearchFlights_Engine ksf = new Kiwi_SearchFlights_Engine(driver);
		ksf.FilterByArrivalHour();
		for (WebElement el : ksf.getArrivalHours()) {
			java.sql.Timestamp ts = java.sql.Timestamp.valueOf("2000-01-01 " + (el.getText() + ":01"));
			AssertJUnit.assertTrue("The Arrival hour is not in the required range",
					ts.after(java.sql.Timestamp.valueOf("2000-01-01 15:00:01.0")));
			AssertJUnit.assertTrue("The Arrival hour in not in the required range",
					ts.before(java.sql.Timestamp.valueOf("2000-01-01 19:00:01.0")));
			System.out.println(el.getText());
			System.out.println(ts.toString());
		}
	}

	@Test(priority = 8)
	public void test8_Filter_By_Airline_company() {
		Kiwi_SearchFlights_Engine ksf = new Kiwi_SearchFlights_Engine(driver);
		ksf.FilterByAirlineCompany("easyJet");
	}
}
