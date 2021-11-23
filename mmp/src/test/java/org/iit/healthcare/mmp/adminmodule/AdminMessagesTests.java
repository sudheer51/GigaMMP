package org.iit.healthcare.mmp.adminmodule;

import java.util.HashMap;

import org.apache.poi.util.SystemOutLogger;
import org.iit.TestBaseClass;
import org.iit.healthcare.mmp.HelperClass;
import org.iit.healthcare.mmp.adminmodule.pages.AdminMessagesPage;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * 1. Login Patient Module
2. Click on Tab ->Messages
3. Click Send after entering contact reason and Subject
4. Click 'ok' in the pop-up window
5. Login Admin Module
6. Click on Tab -> Messages
7. Validate the data  displayed in the Messages page.


p1: convert into a method
p2: parameterize the method
p3: return the outcome for validation

 *
 */
public class AdminMessagesTests extends TestBaseClass{
	
	String expectedmsg = "Messages Successfully sent.";
	String actualmsg="";
	
	
	 
	@Test	
	public void validatePatientAdminMessage()
		{
			AdminMessagesPage adminMessagePage = new AdminMessagesPage(driver);
			HelperClass helper = new HelperClass(driver);
			HashMap<String,String> patientHMap = new HashMap<String,String>();
		 	
		 	helper.launchApplicationURL(prop.getProperty("patienturl"));
			helper.login(prop.getProperty("patientUser"),prop.getProperty("patientPassword"));
			helper.navigateToAModule("Messages");
			
			patientHMap = adminMessagePage.patientsendMessages(prop.getProperty("patientreason"), prop.getProperty("patientmessage"));
						
			actualmsg = adminMessagePage.readAlertValidation();
			Assert.assertEquals(expectedmsg, actualmsg);
									
			/*for(HashMap.Entry<String, String> entry : patientHMap.entrySet())
			{
				System.out.println("Patient Hashmap Retrieve  :  "+ entry.getKey()+ "  :   "+ entry.getValue());
			}*/
			
		 	helper.launchApplicationURL(prop.getProperty("urlAdmin"));
			helper.adminlogin(prop.getProperty("adminUser"),prop.getProperty("adminPassword"));
			helper.navigateToAModule("Messages");
			
			HashMap <String, String> adminHMap = adminMessagePage.fetchPatientData_inAdminMessage();
			
			/*for(HashMap.Entry<String, String> entry : adminHMap.entrySet())
			{
				System.out.println("Admin Hashmap Retrieve  "+ entry.getKey()+ "  :   "+ entry.getValue());
			}*/
			Assert.assertTrue(patientHMap.equals(adminHMap));
			System.out.println(" 2 Hashmaps successfully matched true.");
			helper.closeDriver();
			
	}
	 
	
		

}
