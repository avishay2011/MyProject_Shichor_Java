package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.testng.annotations.DataProvider;

public class ManageDDT {
	
	String filePath="./src/test/resources/dataDrivenTestingFiles/destinations.csv";
	@DataProvider(name="Data_Provider_destinations")
	public Object [][] getDataObject(){
		return getDataFromCSV(filePath);
	}
	
	public static List<String> readCSV(String csvFile){
		List<String> lines=null;
		File file=new File(csvFile);
		try {
			 lines=Files.readAllLines(file.toPath(),StandardCharsets.UTF_8);			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;		
	}	
	
	public static Object [][] getDataFromCSV(String filePath){
		Object[][] data=new Object[11][2];
		List <String> csvData=readCSV(filePath);
		for (int i = 0; i < csvData.size(); i++) {
			data[i][0]=csvData.get(i).split(",")[0];
			data[i][1]=csvData.get(i).split(",")[1];		
		}
		return data;		
	}
}
