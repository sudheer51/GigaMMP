package org.iit.healthcare.admin;

import java.util.HashMap;

import org.iit.TestBaseClass;
import org.iit.healthcare.mmp.HelperClass;

import org.testng.annotations.Test;

public class RegistrationTest extends TestBaseClass {
	
	@Test
	public void validateUserRegistration() throws InterruptedException
	{
	 
	 	 
	 	HelperClass helper = new HelperClass(driver);
	 	helper.launchApplicationURL(prop.getProperty("NAMTGURL"));
	 RegistrationPage regisPage = new RegistrationPage(driver);
	 	HashMap<String,String> expHMap = regisPage.registerUser("randel","Chamer14","PjCharmers01","889765435");
	 	
	 	//helper.login("Chamer04","PjCharmers01");
	 	//regisPage.approvalPending();
	 	helper.launchApplicationURL(prop.getProperty("urlAdmin"));
	 	helper.adminlogin(prop.getProperty("adminUser"),prop.getProperty("adminPassword"));
	 	AdminApprovalPage adminPage= new AdminApprovalPage(driver);
	 	adminPage.adminApproval("randel","889765435");
	 	helper.launchApplicationURL(prop.getProperty("patienturl"));
	 	helper.login("Chamer14","PjCharmers01");
	 	
		
	}

}
