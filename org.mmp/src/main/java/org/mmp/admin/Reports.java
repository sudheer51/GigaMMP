package org.mmp.admin;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Reports {

	protected WebDriver driver;
	public Reports(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void searchPatient(String SSN)
	{
	driver.findElement(By.xpath("//input[@id='search']")).sendKeys(SSN);
	driver.findElement(By.xpath("//input[@type='button']")).click();
	driver.findElement(By.xpath("//div[@id='show']/table/tbody/tr/td/a")).click();
	String actual = driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'Ria')]")).getText();
	String[] actname = actual.split(":");
	String aname = actname[1];
	System.out.println(aname);
	String expected = "RiaKumar";
	if(aname.equals(expected))
	{
		System.out.println("Valid Patient");
	}
	else
	{
		System.out.println("Invalid patient");
	}
		
	}
	
	public void navigatemenu(String menuname)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		WebElement ele = driver.findElement(By.xpath("//input[@value='"+menuname+"']"));
		js.executeScript("arguments[0].scrollIntoView(true);",ele);
		ele.click();
	}
	public void SelectReportDetails(String repname,String repdate,String repdesc)
	{
		WebElement selname = driver.findElement(By.xpath("//select[@id='app_date']"));
		Select sel=new Select(selname);
		sel.selectByVisibleText(repdate);
		driver.findElement(By.xpath("//input[@name='report_name']")).sendKeys(repname);
		WebElement elem = driver.findElement(By.xpath("//input[@id='file']"));
		elem.sendKeys("C:/Users/sowmy/OneDrive/Desktop/ECG report.txt");
		driver.findElement(By.xpath("//textarea[@name=\"report_desc\"]")).sendKeys(repdesc);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}
	
	
		public HashMap<String,String>  validateReports() 
		{
			HashMap<String, String> hMap = new HashMap<String,String>();
			driver.findElement(By.xpath("//a[@href='viewreports.php']")).click();
			String viewPgHeader = driver.findElement(By.xpath("//h3[@class='panel-title']")).getText();
			System.out.println("viewpgheader is"+viewPgHeader);
			String ExpHeader="View Reports";
			System.out.println("ExpHeader is"+ExpHeader);
			if(ExpHeader.equalsIgnoreCase(viewPgHeader))
			{
				System.out.println("Page title match");
			}
			else
			{
				System.out.println("Page title doesn't match");
			}
			List<WebElement> list = driver.findElements(By.xpath("//table[@class='table']/tbody/tr/td"));
			System.out.println("The total reports are :"+list.size());
			for(int i=0;i<list.size();i++)
			{
				String title = driver.findElement(By.xpath("(//tbody/tr/td)["+(i+1)+"]//a//li//h4")).getText();
				//System.out.println(title);
				String keyval = "Report titlename number is " + String.valueOf(i);
				hMap.put(keyval, title);
				
			}
			System.out.println("The reports are "+hMap);
			return hMap;
		}


	

	}
	
	

