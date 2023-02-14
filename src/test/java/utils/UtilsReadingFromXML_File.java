package utils;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UtilsReadingFromXML_File {

    public static String getData (String nodeName)
	{
		DocumentBuilder dBuilder;
		Document doc = null;
		File fXmlFile = new File("./src/test/resources/data/testingParameters.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try 
		{
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		}
		catch(Exception e) 
		{
			System.out.println("Exception in reading XML file: " + e);
		}
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(nodeName).item(0).getTextContent();
	}
}

