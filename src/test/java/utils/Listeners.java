package utils;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.reflect.Method;

import org.monte.media.Registry;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener  {
	
	public void onStart (ITestContext execution){
		System.out.println("----------StartingExecution-----------");
    }
    public void onFinish (ITestContext execution){
    	System.out.println("----------EndingExecution-------------");
    }
    public void onTestFailedButWithSuccessPercentage (ITestResult test){
	///To Do implement this method
    }
    public void onTestSkipped (ITestResult test){
    	System.out.println("----------Skipping Test: " + test.getName() + "---------------") ;
}
    public void onTestStart (ITestResult test){
    	System.out.println("----------Start Test: " + test.getName() + "---------------") ;
    }
    
    public void onTestSuccess (ITestResult test){
    	System.out.println("----------Test: " + test.getName() + " passed:-----------------");
    //	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
    	//// Stop recording and delete the file    	
        /// On the try /catch is the stop record method
    	try { 
			MonteScreenRecorderUtil.stopRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	///  Delete recorded file 
    	File file= new File("./test-recordings/" + test.getName() + ".avi");
    	if(file.delete())  /// הפקודה למחוק ותנאי של משתנה בוליאני שבודק האם באמת הקובץ נמחק 
    	   System.out.println("File deleted successfully");
    	else
    		System.out.println("Failed to deleted file");
    }
    public void onTestFailure (ITestResult test){
    	System.out.println("----------Test: " + test.getName() + " failed:-----------------");
    	try {
			MonteScreenRecorderUtil.stopRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	
	
	
	
}

