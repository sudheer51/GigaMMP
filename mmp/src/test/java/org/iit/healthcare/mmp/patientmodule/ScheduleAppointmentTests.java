package org.iit.healthcare.mmp.patientmodule;

import java.util.HashMap;
import org.iit.healthcare.mmp.HelperClass;
import org.iit.healthcare.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScheduleAppointmentTests extends BaseTest {
		
	@Test(priority=1)
	   public void scheduleAppointment() throws Exception
	   {
		
		   logger=reports.createTest("Book Appointment");
		   
		   HelperClass helper = new HelperClass(driver);		   
		   helper.launchApplication(prop.getProperty("patienturl"));	
		   logger.info("opened patient url");
		   helper.loginCredentials(prop.getProperty("patientUser"),prop.getProperty("patientPassword")); 	
		   logger.pass("Login Successful");
		   helper.navigatetoModule("Schedule Appointment");		   		
		   ScheduleAppointmentPage s_appointment = new ScheduleAppointmentPage(driver);
		   
		   //Calling method that takes all details as input
		   //s_appointment.bookAppointments("Dr.Beth","October","3","2022","10Am","Fever and cough");	
		   
		   //Calling method that takes appointment date in date format
		   //boolean result = s_appointment.dateCheck("Dr.Beth","10/25/2022","10Am","Fever");
		   //Assert.assertTrue(result);		   
		   
		   //Calling the hashMap method
		   HashMap<String,String> expHashMap = s_appointment.dateCheck1("Beth","Fever","12/25/2021","10Am");
		   System.out.println("input given:"+expHashMap);
		   HashMap<String,String> actualHashMap = s_appointment.patientPortal();
		   System.out.println("Chk if input entered:"+actualHashMap);	
		   logger.pass("Booking Appointment Successful");
		   Assert.assertTrue(expHashMap.equals(actualHashMap));		  		      
	   
	   }       

}
