package org.iit.healthcare.mmp.adminmodule.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.iit.Utility;
import org.iit.healthcare.mmp.HelperClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminMessagesPage {
	
	WebDriver driver;
	Alert alert;
	HelperClass helper;
	public AdminMessagesPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public HashMap<String, String> patientsendMessages(String reason, String message){
		HashMap<String,String> appHMap = new HashMap<String,String>();
		
		driver.findElement(By.xpath("//input[@id='subject']")).sendKeys(reason);
		appHMap.put("Reason", driver.findElement(By.xpath("//input[@id='subject']")).getAttribute("value"));
		
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys(message);
		appHMap.put("Message", driver.findElement(By.xpath("//textarea[@id='message']")).getAttribute("value"));
		
		String todaydate = getTodayDate(0);
		//appHMap.put("TodayDate", todaydate);
		
		driver.findElement(By.xpath("//input[@value = 'Send']")).click();
		return appHMap;
	}
	
		
	public String readAlertValidation()
	{
		String alert_msg;
		try{
			alert = driver.switchTo().alert();
			alert_msg = alert.getText();
			alert.accept();
		}catch(Exception e){
			alert_msg = e.getMessage();
			System.out.println("Alert Exception caught  : "+alert_msg);
		}
		return alert_msg;
	}
	
	public String getTodayDate(int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		String date = sdf.format(d);
		return date;
	}
	
	public HashMap<String, String> fetchPatientData_inAdminMessage()
	{
		 
		HashMap<String,String> patHMap = new HashMap<String,String>();
		
		patHMap.put("Reason",driver.findElement(By.xpath("//table[@class='table']//tr[2]/td[2]")).getText());
		patHMap.put("Message", driver.findElement(By.xpath("//table[@class='table']//tr[3]/td[2]")).getText() );
		//patHMap.put("TodayDate", driver.findElement(By.xpath("//table[@class='table']//tr[2]/td[3]")).getText());
		System.out.println(" All patient data has been pushedinto the patient hashmap.");
		return patHMap;
	}

	public boolean validateMessageAdminModule(HashMap<String, String> hMap, String name, String subject, String description){

		boolean result = false;
		if(hMap.get("Subject").equals(subject) && hMap.get("Description").equals(description) &&hMap.get("Name").equals(name))
		{
			System.out.println("Passed");
			result = true;

		}
		return result;
	}
}


