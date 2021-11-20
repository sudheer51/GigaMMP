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
	
	public  void launchApplicationURL(String url)	{
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public void navigateToAModule(String mName)
	{
		driver.findElement(By.xpath("//span[normalize-space()='"+mName+"']")).click();
	}
	public String login(String uname,String pword)
	{
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pword);
		driver.findElement(By.name("submit")).click();
		String actual = driver.findElement(By.tagName("h3")).getText();
		return actual;
	}
	public String adminlogin(String uname,String pword)
	{
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pword);
		driver.findElement(By.name("admin")).click();
		String actual = driver.findElement(By.tagName("h3")).getText();
		return actual;
	}
	public void closeDriver() {
        driver.close();
}
	
	
}
