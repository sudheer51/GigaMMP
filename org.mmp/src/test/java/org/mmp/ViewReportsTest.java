package org.mmp;

import java.util.HashMap;

import org.mmp.HelperClass;
import org.mmp.TestBaseClass;
import org.mmp.ViewReportsPage;
import org.testng.annotations.Test;

public class ViewReportsTest extends TestBaseClass {

	@Test
	public void ValidateViewReports() throws InterruptedException
	{
		HelperClass helper = new HelperClass(driver);
		helper.LauchApplication(prop.getProperty("patienturl"));
		helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		ViewReportsPage viewpg = new ViewReportsPage(driver);
		viewpg.Loginvalidation();
		helper.navigateModule("Profile");
		HashMap<String,String>ExpHMap=viewpg.viewreports();
		
		if(ExpHMap.containsValue("ECG"))
		{
			System.out.println("Test case passed");
		}

		helper.teardown();

	}
}
