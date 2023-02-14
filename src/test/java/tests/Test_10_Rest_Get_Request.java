package tests;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.RestAssured.*;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners(utils.Listeners.class)
public class Test_10_Rest_Get_Request  {
    
	@BeforeMethod
	public void beforeEveryRestTest() {
	/// Those code lines are used for all the rest API tests 
	RestAssured.baseURI = "https://shichor.kiwi.com/en/";
    RestAssured.useRelaxedHTTPSValidation();   ///  the initiated route of the URL address for all tests cases 
    RestAssured.requestSpecification = new RequestSpecBuilder()  ///ignore from not valid certification failures
            .addHeader(CONTENT_TYPE, APPLICATION_JSON) // content type need to be json file 
            .build();      
	}
		
	@Test(description = "GET Kiwi_Flights_SearchEngine return 200")
	public void StatusCode200_Kiwi() {		
		{
			given()
					.log()
					.all()
					.when()
					.get("?destination=anywhere&origin=ben-gurion-tel-aviv-israel")
					.then()
					.statusCode(HttpStatus.SC_OK);
			given()
					.log()
					.ifValidationFails()
					.when()
					.get("?destination=anywhere&origin=ben-gurion-tel-aviv-israel")
					.then()
					.statusCode(HttpStatus.SC_OK);
		}
	}
}