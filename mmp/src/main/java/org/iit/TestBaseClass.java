package org.iit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBaseClass {

	protected WebDriver driver;
	protected static Properties prop = new Properties();
	
	
	@BeforeTest
	public void loadProperties() throws IOException
	{
		String absolutePath = System.getProperty("user.dir")+"//config//mmp.properties";
		System.out.println("Absolute path =  " + absolutePath);
		File f = new File(absolutePath);
		FileInputStream fis = new FileInputStream(f);
		prop.load(fis);
		System.out.println("Adding the comment in the load");
		
	}
	@BeforeClass
	public void instantiateDriver() throws IOException
	{
		System.out.println("First line of the instantiate method by Student1");
		 System.out.println("PRoperties  : "+ prop);
		String browser = prop.getProperty("browser");
		System.out.println(" Browser   :  "+ browser);
		if(browser.equalsIgnoreCase("chrome")){
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")){	
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}
		driver.manage().window().maximize();
		System.out.println("LastLine of the instatiate method of TestBase");
	}
	
	/**
	 * @AfterMethod
	 
	public void tearDown(){
		driver.close();
	}
	 **/
}
