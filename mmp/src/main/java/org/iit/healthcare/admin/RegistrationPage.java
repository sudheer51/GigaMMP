package org.iit.healthcare.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.iit.Utility;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	WebDriver driver;
	
	
	public  RegistrationPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public HashMap<String, String> registerUser(String firstName,String userName, String passWord,String SSN){
		
		HashMap<String,String> regisHMap = new HashMap<String,String>();
		
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		regisHMap.put("fname",driver.findElement(By.id("firstname")).getAttribute("value"));
		
		driver.findElement(By.id("lastname")).sendKeys("Chelsy");
		regisHMap.put("lname",driver.findElement(By.id("lastname")).getAttribute("value"));
		
		driver.findElement(By.name("dob")).sendKeys("13/1/1999");
		regisHMap.put("dob",driver.findElement(By.name("dob")).getAttribute("value"));
		
		
		driver.findElement(By.id("license")).sendKeys("1"+ Utility.randomInt(7));
		regisHMap.put("licnumber",driver.findElement(By.id("license")).getAttribute("value"));
		
		driver.findElement(By.id("ssn")).sendKeys(SSN);
		regisHMap.put("ssnumber",driver.findElement(By.id("ssn")).getAttribute("value"));
	
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ssnstatus")));
		
		
		driver.findElement(By.id("state")).sendKeys(Utility.getRandomState());
		regisHMap.put("state",driver.findElement(By.id("state")).getAttribute("value"));
		
		
		driver.findElement(By.id("city")).sendKeys(Utility.getRandomString(7));
		regisHMap.put("city",driver.findElement(By.id("city")).getAttribute("value"));
		
		WebElement address=driver.findElement(By.id("address"));
		int  addrNum=Utility.randomInt(4);
		String stName=Utility.getRandomString(7);
		address.sendKeys(addrNum +" "+ stName + " st" );
		regisHMap.put("address",address.getAttribute("value"));
		
		driver.findElement(By.id("zipcode")).sendKeys(String.valueOf(Utility.randomInt(5)));
		regisHMap.put("zip",driver.findElement(By.id("zipcode")).getAttribute("value"));
		
		Random r = new Random();
		driver.findElement(By.id("age")).sendKeys(String.valueOf (r.nextInt((100-18)+1) + 18));
		regisHMap.put("age",driver.findElement(By.id("age")).getAttribute("value"));
		
		driver.findElement(By.id("height")).sendKeys(String.valueOf(r.nextInt((201-122)+1) + 122));
		regisHMap.put("height",driver.findElement(By.id("height")).getAttribute("value"));
		
		
		driver.findElement(By.id("weight")).sendKeys(String.valueOf( r.nextInt((110-40)+1) + 40));
		regisHMap.put("weight",driver.findElement(By.id("weight")).getAttribute("value"));
		
		 driver.findElement(By.id("pharmacy")).sendKeys(Utility.getRandomString(7));
		regisHMap.put("pharmacy",driver.findElement(By.id("pharmacy")).getAttribute("value"));
		
		WebElement pharmacyAdd=driver.findElement(By.id("pharma_adress"));
		int  pharNum=Utility.randomInt(4);
		String pharstName=Utility.getRandomString(7);
		pharmacyAdd.sendKeys(pharNum +" "+ pharstName + " st" );
		regisHMap.put("pharmacyAdd",pharmacyAdd.getAttribute("value"));
		
		driver.findElement(By.id("email")).sendKeys(Utility.getRandomString(7)+"@gmail.com");
		regisHMap.put("eMail",driver.findElement(By.id("email")).getAttribute("value"));
		
		driver.findElement(By.id("password")).sendKeys(passWord);
		regisHMap.put("pword",driver.findElement(By.id("password")).getAttribute("value"));
		
		driver.findElement(By.id("username")).sendKeys(userName);
		regisHMap.put("username",driver.findElement(By.id("username")).getAttribute("value"));
		
		driver.findElement(By.id("confirmpassword")).sendKeys(driver.findElement(By.id("password")).getAttribute("value"));
		regisHMap.put("conpword",driver.findElement(By.id("confirmpassword")).getAttribute("value"));
		
		Select drpSecurity=new Select(driver.findElement(By.id("security")));
		 List<WebElement> selectOp = drpSecurity.getOptions();
		 int size = selectOp.size();
		
		 int randnMumber = r.nextInt((size-1)+1)+1;
		 selectOp.get(randnMumber).click();
		 
		 driver.findElement(By.id("answer")).sendKeys(Utility.getRandomString(7));
		regisHMap.put("answer",driver.findElement(By.id("answer")).getAttribute("value"));
		
		driver.findElement(By.name("register")).click();
		alertMessage();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		return regisHMap;
		
	}
	private boolean alertMessage() {
		Alert alert = driver.switchTo().alert();
		String alertMessage=alert.getText();
		assertTrue(alertMessage.contains("Thank you for registering with MMP."));
		
		return true;
		
	}
	public void approvalPending() {
		
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		assertTrue(alertMessage.contains("Admin Approval is pending."));
		alert.accept();
		return;
	}
	
	private void assertTrue(boolean contains) {
		// TODO Auto-generated method stub
		
	}
	
	
}
