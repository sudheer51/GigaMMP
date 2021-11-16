package org.mmp.admin;

import java.util.HashMap;

import org.iit.mmp.HelperClass;
import org.iit.mmp.TestBaseClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

import org.mmp.ScheduleAppointmentsPage;

public class SearchPatientTest  extends TestBaseClass {

	@Test
	public void validatePatient()
	{
		HelperClass helper = new HelperClass(driver);
		helper.LauchApplication(prop.getProperty("urlAdmin"));
		helper.adminlogin(prop.getProperty("adminUser"), prop.getProperty("adminPassword"));
		helper.navigateAdminModule("Patients");
		Reports rpt = new Reports(driver);
		rpt.searchPatient(prop.getProperty("SSN"));
		rpt.navigatemenu("Create Visit");
		SearchPatient sp = new SearchPatient(driver);

		HashMap<String,String>actualHMap=sp.CreateVisit("Smith", "Fever");
		helper.LauchApplication(prop.getProperty("patienturl"));
		helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		helper.navigateModule("Schedule Appointment");

		HashMap<String,String>exHMap=sp.currentAppointment();
		System.out.println(exHMap);
		System.out.println(actualHMap);

		Assert.assertEquals(exHMap, actualHMap);
		helper.teardown();

	}
}
