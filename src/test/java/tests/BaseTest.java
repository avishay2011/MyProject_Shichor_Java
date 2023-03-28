package tests;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pageObjects.BasePage;
import pageObjects.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import utils.MonteScreenRecorderUtil;
import utils.UtilsConfiguration;

public class BaseTest {
    WebDriver driver;
	
	@BeforeClass
	public void setup() {
		
		/// Those code lines are used for all the rest API tests 
		RestAssured.baseURI = "https://shichor.kiwi.com/en/";
        RestAssured.useRelaxedHTTPSValidation();   ///  the initiated route of the URL address for all tests cases 
        RestAssured.requestSpecification = new RequestSpecBuilder()  ///ignore from not valid certification failures
                .addHeader(CONTENT_TYPE, APPLICATION_JSON) // content type need to be json file 
                .build();      
        /// Those code lines are used for Web UI tests
		initBrowser("chrome");
		driver.manage().window().maximize();
		driver.get(UtilsConfiguration.readProperty("url"));
		MainPage MP=new MainPage(driver);
		MP.removePopup();
        MP.sign_In();
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		try {
			 MonteScreenRecorderUtil.startRecord(method.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void failedTest(ITestResult result) {
	  //check if the test failed
		if (result.getStatus() == ITestResult.FAILURE ){        /////// נשמר קובץ בספריה רק אם התסריט נכשל
			TakesScreenshot ts = (TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);   /// יצירת אוביקט מסוג קובץ ושמירה של הקובץ עם השם של הטסט  
			try {
				FileUtils.copyFile(srcFile, new File("./ScreenShots/"+result.getName()+".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//result.getname() method will give you current test case name. 
			//./ScreenShots/ tell you that, in your current directory, create folder ScreenShots. dot represents current directory
		}
	}
	
	
	
    public void initBrowser(String BrowserName) {
 	    if (BrowserName.equalsIgnoreCase("chrome")) 
		    driver=initChromeDriver();
    	else if (BrowserName.equalsIgnoreCase("edge"))
	     	driver=initEdgeDriver();
        else 
        	throw new RuntimeException("Invalid browser type- Please select Chrome or edge only");
    }

    public WebDriver initChromeDriver() {
	    WebDriverManager.chromedriver().setup();         
	    ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(chromeOptions);

	    return driver;
    }

    public WebDriver initEdgeDriver() {
	    WebDriverManager.edgedriver().setup();
    	driver=new EdgeDriver();
	    return driver;
    }
	
 //	@AfterClass
 //	public void tearDown() {
	// 	driver.quit();
	  
 	//}
}
