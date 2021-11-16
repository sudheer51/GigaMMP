package org.mmp.admin;

import java.util.HashMap;

import org.iit.mmp.HelperClass;
import org.iit.mmp.TestBaseClass;
import org.testng.annotations.Test;

public class ReportsTestpg extends TestBaseClass{

	@Test
	public void ValidateReports() throws InterruptedException
	{
		HelperClass helper = new HelperClass(driver);
		helper.LauchApplication(prop.getProperty("urlAdmin"));
		helper.adminlogin(prop.getProperty("adminUser"), prop.getProperty("adminPassword"));
		helper.navigateAdminModule("Patients");
		Reports rpt = new Reports(driver);
		rpt.searchPatient(prop.getProperty("SSN"));
		rpt.navigatemenu("Reports");
		rpt.SelectReportDetails("PCR Report","10/10/2020"," Covid report for Ria1 as on the date");
		helper.LauchApplication(prop.getProperty("patienturl"));
		helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		//rpt.validateReports();
		helper.navigateModule("Profile");
         HashMap<String,String>ExpHMap=rpt.validateReports();
		
		if(ExpHMap.containsValue("PCR Report"))
		{
			System.out.println("Test case passed");
		}
		else
		{System.out.println("Testcase failed");}
		helper.teardown();
	}
}
