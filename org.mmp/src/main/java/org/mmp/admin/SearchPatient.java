package org.mmp.admin;

import java.util.HashMap;

import org.iit.mmp.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchPatient {
	

	protected WebDriver driver;
	public SearchPatient(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public HashMap<String,String>CreateVisit(String doctorName,String Sym)
	{
		HashMap<String,String>visitHMap  = new HashMap<String,String>();
		 driver.findElement(By.xpath("//h4[contains(text(),'Dr."+doctorName+"')]/ancestor::ul/following-sibling::button")).click();
		 visitHMap.put("name", doctorName);
		 WebElement frameEle = driver.findElement(By.id("myframe"));
			driver.switchTo().frame(frameEle);
			driver.findElement(By.id("datepicker")).click();
			
			String dateofAppointment=Utilities.selectFutureDate(20);
			String[] date = dateofAppointment.split("/");
			String ExpDay = date[0];
			String ExpMonth = date[1];
			String ExpYear = date[2];
			WebDriverWait wait1=new WebDriverWait(driver,30);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-datepicker-year']")));
			  String calYear= driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
			  while(!calYear.equals(ExpYear)) 
				{
					
					driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
					calYear =driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
					System.out.println("Printing year::"+ calYear);
				}
				
				String calMonth=driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
				while(!calMonth.equals(ExpMonth)) 
				{
					
					driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
					calMonth =driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
					System.out.println("Printing Month  ::"+ calMonth);
				}
				driver.findElement(By.linkText(ExpDay)).click();
				System.out.println("The date selected in the datePicker:" + driver.findElement(By.id("datepicker")).getAttribute("value"));
	
				new Select(driver.findElement(By.id("time"))).selectByVisibleText("10Am");
				visitHMap.put("time","10Am");
				
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='status']")));

				driver.findElement(By.id("ChangeHeatName")).click();

				driver.findElement(By.name("sym")).clear();
				driver.findElement(By.name("sym")).sendKeys("fever");
				visitHMap.put("symp", "fever");
				driver.findElement(By.xpath("//input[@value='Submit']")).click();
				return visitHMap;
				
	}
	public HashMap<String, String> currentAppointment()
	{
		HashMap<String,String> CappHMap = new HashMap<String,String>();
		WebDriverWait wt=new WebDriverWait(driver,30);
		String ActualProvidername = driver.findElement(By.xpath("//a[contains(text(),'Provider')]")).getText();
		String[] Actname = ActualProvidername.split(":");
		String ActDocName = Actname[1];
		CappHMap.put("name",ActDocName);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains (text(),'Time')]")));
		String Actualtime = driver.findElement(By.xpath("//a[contains (text(),'Time')]")).getText();
		String[] Acttime = Actualtime.split(":");
		String ActAppTime = Acttime[1].trim();
		CappHMap.put("time",ActAppTime);
		
		String ActualSymptom = driver.findElement(By.xpath("//a[contains(text(),'Symptoms')]")).getText();
		String[] ActSym = ActualSymptom.split(":");
		String ActSymp = ActSym[1];
		CappHMap.put("symp",ActSymp);
		//System.out.println(ActAppTime);
		//System.out.println(ActSymp);
		//System.out.println(ActDocName);
		return CappHMap;
		
	}
}
