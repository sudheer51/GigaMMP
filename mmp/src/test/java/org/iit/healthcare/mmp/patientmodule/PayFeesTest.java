package org.iit.healthcare.mmp.patientmodule;

import org.iit.healthcare.mmp.HelperClass;
import org.iit.healthcare.mmp.patientmodule.pages.PayFees;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PayFeesTest extends BaseTest{

	@Test(priority=2)
	public void fees() throws Exception{

		logger=reports.createTest("PayFees");
		HelperClass helper = new HelperClass(driver);
		helper.launchApplication(prop.getProperty("patienturl"));		  	   
		helper.loginCredentials(prop.getProperty("patientUser"),prop.getProperty("patientPassword")); 		
		logger.pass("Login Success");
		
		helper.navigatetoModule("  Fees ");		
	    logger.info("Navigated to Fees Page");
		PayFees pFees = new PayFees(driver);		
		
		pFees.amountToBePaid("2904");
		pFees.CCardDetails("Sri K'hari","2","2345678923456789","03","16","346");			
		
		//Validating for Customer Name
		boolean nameChk = pFees.CustName("Sri K'hari");
		Assert.assertTrue(nameChk);
		logger.pass("Checking for Vaild Customer Name");	
		
		//Validating for Credit Card Number.
		boolean result= pFees.creditCardNoCheck("2345678923456789");
		Assert.assertTrue(result);
		logger.pass("Credit Card Validity Check");
	
	}

}
