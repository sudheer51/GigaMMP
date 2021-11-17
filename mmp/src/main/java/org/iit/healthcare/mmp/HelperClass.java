package org.iit.healthcare.mmp;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperClass  {	
	
	 WebDriver driver;
	 	 
	public HelperClass(WebDriver driver)
	{
		this.driver=driver;
	}
	public void launchApplication(String url)
	{
		   driver.get(url);
		   driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);  
	}
	
	public void loginCredentials(String uname,String password)
	{
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Sign In']")).click();
		
	}
	public void navigatetoModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();
	}
		
	/*
	public void tearDown()
	{
		driver.close();
	}
*/
	
}
