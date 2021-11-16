package org.mmp;

import java.util.HashMap;
import java.util.List;

import org.iit.mmp.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ViewReportsPage {
    protected WebDriver driver;
    HelperClass helper;
	public ViewReportsPage(WebDriver driver)
	{
		this.driver=driver;
	}
	public void Loginvalidation()
	{
		String actual = driver.findElement(By.xpath("//h3[contains(text(),'ria')]")).getText();
		String expected = "ria1";
		if(actual.contains(expected))
		{
			System.out.println("Valid login user :"+actual);
		}
		else
		{
			System.out.println("Invalid user");
		}
	}
	public HashMap<String,String>  viewreports() 
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
			String keyval = "titlename" + String.valueOf(i);
			hMap.put(keyval, title);
		
		}
	
		System.out.println(hMap);
		return hMap;
	}


}
