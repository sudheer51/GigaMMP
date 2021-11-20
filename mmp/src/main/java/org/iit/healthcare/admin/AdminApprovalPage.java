package org.iit.healthcare.admin;

import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.iit.healthcare.mmp.HelperClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminApprovalPage {
	WebDriver driver;
	HelperClass helper = new HelperClass(driver);
	
	public  AdminApprovalPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void adminApproval(String userName, String SSN) throws InterruptedException{
		  
		
		driver.findElement(By.xpath("//span[normalize-space()='Users']")).click();
		Select drpStatus= new Select(driver.findElement(By.id("search")));
		drpStatus.selectByValue("0");
		System.out.println(SSN);
		Thread.sleep(2000);
		List<WebElement> pLis = driver.findElements(By.xpath("//div[@id='show']/descendant::table/tbody/tr/td/a"));
		System.out.println(pLis.size());
		
		//WebElement patientName = driver.findElement(By.xpath("//a[normalize-space()='"+userName+"']"));
		//WebElement pateintSSN=driver.findElement(By.xpath("//td[normalize-space()='"+SSN+"']"));
		for(int i=1;i<(pLis.size())+1;i++)
		{
			
			
			System.out.println(i);
			String s1 = driver.findElement(By.xpath("//div[@id='show']/descendant::table/tbody/tr["+i+"]/td[2]")).getText();
			String s2= driver.findElement(By.xpath("//div[@id='show']//tbody/tr["+i+"]/td[1]/a[1]")).getText();
			System.out.println(s1);
			System.out.println(s2);
			if((s2.contains(userName))){
				
				if(s1.contains(SSN)){
					
				System.out.println("Inside if condition");
				System.out.println(s2);
				System.out.println(s1);
				
				driver.findElement(By.xpath("//a[normalize-space()='"+s2+"']")).click();
				break;
				}
			}
			
		}
		
		
		
		Select drpapproval= new Select(driver.findElement(By.id("sapproval")));
	
		drpapproval.selectByValue("1");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Alert alert = driver.switchTo().alert();
		String alertMessage=alert.getText();
		alertMessage.contains("USER has been updated.");
		alert.accept();
		
		
		
		
	}
	
}
