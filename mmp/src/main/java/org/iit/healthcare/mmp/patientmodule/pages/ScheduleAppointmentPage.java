package org.iit.healthcare.mmp.patientmodule.pages;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleAppointmentPage  {
	
	WebDriver driver;
	WebElement calendarFrame,time_dropDown,desc_symptom;
	String monthTitle,year;
	Select s;	
	WebDriverWait wait;
	
	public ScheduleAppointmentPage(WebDriver driver)
	{
		this.driver=driver;
	}
	  
   //DatePicker Method 1 that take date in date format and check if it is less than or same as current Date
	//without HASHMAP
   public boolean dateCheck(String doctorName,String dateAppointment,String Time,String symptom) throws Exception
   {	   	   
	    driver.findElement(By.xpath("//span[contains(text(),'Schedule Appointment')]")).click();
		 driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		 driver.findElement(By.xpath("//h4[contains(text(),'"+doctorName+"')]/ancestor::ul/"
		 		+ "following-sibling::button[contains(text(),'Book Appointment')]")).click();
		 
		 calendarFrame=driver.findElement(By.xpath("//iframe[@id='myframe']"));
	     driver.switchTo().frame(calendarFrame);	     
		 driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		   
		   //to get current date
		   DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		   LocalDate currentDate = LocalDate.parse(dateAppointment,dateformat);	   
		   int appoint_dt = currentDate.getDayOfMonth();
		   int appoint_mth = currentDate.getMonthValue();
		   System.out.println("month number give:"+appoint_mth);
		   int appoint_yr = currentDate.getYear();		   
		  
		   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		   Date currentDate1 = new Date();
		   Date todayDate = sdf.parse(dateAppointment);
		   System.out.println("today date is :"+currentDate1);
		   System.out.println("given date:"+todayDate);  
		 
		   if(todayDate.compareTo(currentDate1) <= 0)
		   {
			   System.out.println("Please choose a date greater than current date");
			   
		   }
		 Month month_name = Month.of(appoint_mth);
		 System.out.println("month given:"+month_name);
		
		 monthTitle = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		 System.out.println("month shown:"+monthTitle);
		 Month name = Month.valueOf(monthTitle.toUpperCase());
		 
		 
		 String cal_string = name.toString();
		 String given_string = month_name.toString();
		 year = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		 
		 
		 
		 while(!(cal_string.equals(given_string) && year.equalsIgnoreCase(Integer.toString(appoint_yr))))
		 {

			 System.out.println("inside while loop");
		 wait=new WebDriverWait(driver,20);	
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")));
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']"))).click();
		 monthTitle = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		 
		 name = Month.valueOf(monthTitle.toUpperCase());
		 month_name = Month.of(appoint_mth);
		 given_string = month_name.toString();		 
		 cal_string = name.toString();

		 year = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

		 }	
					 
		 driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr/td/child::a[contains(text(),'"+appoint_dt+"')]")).click();		
		 
		 time_dropDown = driver.findElement(By.xpath("//select[@name='time']"));
		 s = new Select(time_dropDown);
		 
		 List<WebElement> timeOptions = s.getOptions();
		 for (WebElement t : timeOptions) {		 
			 if(t.getAttribute("value").equalsIgnoreCase(Time))
			 {
				 s.selectByValue(Time);
			 }		
		  }
		 Thread.sleep(2000);	 
		 driver.findElement(By.xpath("//button[@id='ChangeHeatName']")).click();		 		 
		 driver.findElement(By.xpath("//textarea[@name='sym']")).sendKeys(symptom);		 
		 if(symptom.isEmpty())
		 {
			 System.out.println("Please provide your symptoms");
		 }	 		
		 driver.findElement(By.xpath("//input[@value='Submit']")).click();
		 return true;
 }   		  
   
   //DatePicker method with HASHMAP  
   public HashMap<String,String> dateCheck1(String doctorName,String symptom,String dateAppointment,String Time) throws Exception
   {
	   HashMap<String,String> appHashMap = new HashMap<String,String>();
	   driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
	   driver.findElement(By.xpath("//h4[contains(text(),'Dr."+doctorName+"')]/ancestor::ul/"
		 		+ "following-sibling::button[contains(text(),'Book Appointment')]")).click();
	   appHashMap.put("doctor", doctorName);
	   
	   calendarFrame=driver.findElement(By.xpath("//iframe[@id='myframe']"));
	     driver.switchTo().frame(calendarFrame);	     
		 driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		   
		   //to get current date
		   DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		   LocalDate currentDate = LocalDate.parse(dateAppointment,dateformat);	   
		   int appoint_dt = currentDate.getDayOfMonth();
		   int appoint_mth = currentDate.getMonthValue();
		   System.out.println("month number give:"+appoint_mth);
		   int appoint_yr = currentDate.getYear();		   
		  
		   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		   Date currentDate1 = new Date();
		   Date todayDate = sdf.parse(dateAppointment);
		   System.out.println("today date is :"+currentDate1);
		   System.out.println("given date:"+todayDate);  
		 		   
		   
		 Month month_name = Month.of(appoint_mth);
		 System.out.println("month given:"+month_name);
		
		 monthTitle = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		 System.out.println("month shown:"+monthTitle);
		 Month name = Month.valueOf(monthTitle.toUpperCase());
		 
		 
		 String cal_string = name.toString();
		 String given_string = month_name.toString();
		 year = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		 			 	 
		 while(!(cal_string.equals(given_string) && year.equalsIgnoreCase(Integer.toString(appoint_yr))))
		 {
			
		 wait=new WebDriverWait(driver,20);	
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")));
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']"))).click();
		 monthTitle = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		 
		 name = Month.valueOf(monthTitle.toUpperCase());
		 month_name = Month.of(appoint_mth);
		 given_string = month_name.toString();		 
		 cal_string = name.toString();

		 year = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();

		 }	
		
		
	   driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr/td/child::a[contains(text(),'"+appoint_dt+"')]")).click();
	   appHashMap.put("dateAppointment",driver.findElement(By.id("datepicker")).getAttribute("value"));
	   //appHashMap.put("Date",Integer.toString(appoint_dt));
	   
	   time_dropDown = driver.findElement(By.xpath("//select[@name='time']"));
		 s = new Select(time_dropDown);
		 
		 List<WebElement> timeOptions = s.getOptions();
		 for (WebElement t : timeOptions) {		 
			 if(t.getAttribute("value").equalsIgnoreCase(Time))
			 {
				 s.selectByValue(Time);
			 }		
		  }
		 appHashMap.put("time",Time);
		 
		 driver.findElement(By.xpath("//button[@id='ChangeHeatName']")).click();
		 driver.findElement(By.xpath("//textarea[@name='sym']")).sendKeys(symptom);		 
		 if(symptom.isEmpty())
		 {
			 System.out.println("Please provide your symptoms");
		 }	 		
	   
		 appHashMap.put("symptoms", symptom);
		 driver.findElement(By.xpath("//input[@value='Submit']")).click();
	   return appHashMap;
		 
	   
   }

   
   public HashMap<String,String> patientPortal()
   {
	   
		 HashMap<String,String> pHashMap = new HashMap<String,String>();		 
		 
		 pHashMap.put("doctor",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText());
		 pHashMap.put("dateAppointment", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText());
		 pHashMap.put("time",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText());
		 pHashMap.put("symptoms",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText());		 
		 		 		 		 		 		 		 		 		 		 
		 return pHashMap;	   
   }
   
   //DatePicker Method 2 that takes date parts as arguments
   public void bookAppointments(String drName,String month,String date,String Year,String Time,String symptom) throws Exception
   {   
        	 
	 driver.findElement(By.xpath("//span[contains(text(),'Schedule Appointment')]")).click();
	 driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
	 driver.findElement(By.xpath("//h4[contains(text(),'"+drName+"')]/ancestor::ul/"
	 		+ "following-sibling::button[contains(text(),'Book Appointment')]")).click();
	 
	 calendarFrame=driver.findElement(By.xpath("//iframe[@id='myframe']"));
     driver.switchTo().frame(calendarFrame);     
	 driver.findElement(By.xpath("//input[@id='datepicker']")).click();
	 
	 String monthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
	 String monthName = monthYear.split(" ")[0].trim();
	 String yearValue = monthYear.split(" ")[1].trim();
	 
	 while(!(monthName.equalsIgnoreCase(month) && yearValue.equalsIgnoreCase(Year)))
	 {
		 wait=new WebDriverWait(driver,20);	
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")));
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']"))).click(); 
		 monthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
		 monthName = monthYear.split(" ")[0].trim();
		 yearValue = monthYear.split(" ")[1].trim();	 
	 }	 
		
	 driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr/td/child::a[contains(text(),'"+date+"')]")).click();	
	 
	 Thread.sleep(2000);	 
	 time_dropDown = driver.findElement(By.xpath("//select[@name='time']"));
	 s = new Select(time_dropDown);	 
	
	 //Checking if given option is present
	 List<WebElement> timeOptions = s.getOptions();
	 for (WebElement t : timeOptions) {
		 
		 if(t.getAttribute("value").equalsIgnoreCase(Time))
		 {
			 s.selectByValue(Time);
		 }		
	  }
	 Thread.sleep(2000);	 
	 driver.findElement(By.xpath("//button[@id='ChangeHeatName']")).click();	 	 
	 driver.findElement(By.xpath("//textarea[@name='sym']")).sendKeys(symptom);
	 
	 if(symptom.isEmpty())
	 {
		 System.out.println("Please provide your symptoms");
	 }	 
	
	 driver.findElement(By.xpath("//input[@value='Submit']")).click();
   } 
      
}