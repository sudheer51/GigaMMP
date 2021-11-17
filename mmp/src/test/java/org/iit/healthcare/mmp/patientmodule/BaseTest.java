package org.iit.healthcare.mmp.patientmodule;

import org.iit.DriverScriptClass;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTest extends DriverScriptClass {

	 public static ExtentHtmlReporter extent;
	 public static ExtentReports reports;
	 public static ExtentTest logger;
	
	
	@BeforeSuite
	public void setupReports() {
		extent = new ExtentHtmlReporter("./mmpReports/reports.html");
		reports = new ExtentReports();
		reports.attachReporter(extent);
	}

	
	@BeforeClass
	public void initDriver()
	{
		initSetUp();		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.fail("Failed here");
		}
		driver.close();
	}
	
	@AfterSuite
	public void flushReports()
	{
		
		reports.flush();
		
	}
	
}
