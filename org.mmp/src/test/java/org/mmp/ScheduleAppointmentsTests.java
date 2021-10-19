package org.mmp;

import java.util.HashMap;

import org.mmp.HelperClass;
import org.mmp.ScheduleAppointmentsPage;
import org.mmp.TestBaseClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class ScheduleAppointmentsTests extends TestBaseClass {
@Test
	
	public void validateScheduleAppoinment()
	{
		HelperClass helper = new HelperClass(driver);
		helper.LauchApplication(prop.getProperty("patienturl"));
		helper.login(prop.getProperty("patientUser"),prop.getProperty("patientPassword"));
		helper.navigateModule("Schedule Appointment");
		ScheduleAppointmentsPage sPage = new ScheduleAppointmentsPage(driver);
		HashMap<String,String>exHMap=sPage.bookappointment("Smith", "Fever");
		HashMap<String,String>actualHMap=sPage.fetchPatientPortalData();
		System.out.println(exHMap);
		System.out.println(actualHMap);
		Assert.assertTrue(exHMap.equals(actualHMap));
		helper.teardown();
	}
}
