package org.iit.healthcare.mmp.patientmodule;

import java.util.HashMap;

import org.iit.TestBaseClass;
import org.iit.healthcare.mmp.HelperClass;
import org.iit.healthcare.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 1. Login
2. Click on Tab ->Schedule Appointment
3. Click on Create NEW Appointment
4. Click on Book Appointment Btn
5. Select Date and Time and continue
6. Enter the symptoms and click on continue.
7. Validate the data is displayed in the home page.


p1: convert into a method
p2: parameterize the method
p3: return the outcome for validation

 *
 */


public class ScheduleAppointmentTests extends TestBaseClass  {
	 @Test
	public void validateScheduleAppointment()
	{
	 
	 	 
	 	HelperClass helper = new HelperClass(driver);
	 	helper.launchApplicationURL(prop.getProperty("patienturl"));
		helper.login(prop.getProperty("patientUser"),prop.getProperty("patientPassword"));
		helper.navigateToAModule("Schedule Appointment");
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		HashMap<String,String> expHMap = sPage.bookAppointment("Smith","Fever");
		HashMap<String,String> actualHMap =sPage.fetchPatientPortalData();
		Assert.assertTrue(expHMap.equals(actualHMap));
	}
 
	 
	 
	 

}
