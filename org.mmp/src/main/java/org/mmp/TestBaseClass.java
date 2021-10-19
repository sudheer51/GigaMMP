package org.mmp;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBaseClass {
	protected WebDriver driver;
	protected Properties prop=new Properties();
	
	public TestBaseClass()
	{
		
			try
			{
				String AbsolutePath=System.getProperty("user.dir")+"/Config/mmp.properties";
				File file = new File(AbsolutePath);
				FileInputStream fis = new FileInputStream(file);
				
				prop.load(fis);
			
			}
			catch(Exception e)
			{
				System.out.println("unable to read the value from properties file please check "+e.getMessage());
			}
		}
		
		
	
	
	
	@BeforeClass
	public void instantiateDriver()
	{
		String browser=prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.out.println("Using Chrome Browser");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.out.println("Using Firefox browser");
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			System.out.println("Using Edge browser");
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	 
	
}


