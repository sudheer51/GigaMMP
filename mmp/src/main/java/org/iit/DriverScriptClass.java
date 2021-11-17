package org.iit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverScriptClass {
	
	protected WebDriver driver;
	protected Properties prop;
    	
	@BeforeClass
	@Parameters({"path"})
	public void loadPropertiesFile(String path) throws IOException
	 {
	    String absolutepath = System.getProperty("user.dir")+path;
	    File f = new File(absolutepath);
	    FileInputStream fis = new FileInputStream(f);
	    prop =  new Properties();
	    prop.load(fis);
	    System.out.println("Property  file loaded");
	 }	
	
	public void initSetUp()
	{
	String browser = prop.getProperty("browser");

	 if(browser.equalsIgnoreCase("chrome".trim()))
	{
		
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	}
	 else if(browser.equalsIgnoreCase("edge".trim()))
	{
		
	  WebDriverManager.edgedriver().setup();
	  driver = new EdgeDriver();
	}
	 else if(browser.equalsIgnoreCase("firefox".trim()))
	{
		 
	  WebDriverManager.firefoxdriver().setup();
	  driver = new FirefoxDriver();
	}
	  driver.manage().window().maximize();	 
   
  }
   
}
