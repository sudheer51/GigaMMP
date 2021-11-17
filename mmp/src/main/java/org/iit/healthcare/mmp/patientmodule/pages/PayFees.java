package org.iit.healthcare.mmp.patientmodule.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PayFees {
	
	WebDriver driver;
	WebElement cardType;
	Select cardType_dropdown,exp_Month,exp_Year;
	WebElement month,year;
	String cCardNo;
	int total = 0;
	String custName;
	
	public PayFees(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public boolean creditCardNoCheck(String cCardNo)
	 {
				 
		 if(cCardNo.length() < 15 || cCardNo.length() > 16)
		 {
			 System.out.println("Please check your card number");
		 }		 
		
		 // creating an integer array for storing the credit card no.
		 int [] cardnumberChk = new int[cCardNo.length()];
		 
		 for(int i=0;i<cCardNo.length();i++)
		 {
			 cardnumberChk[i] = Integer.parseInt(cCardNo.substring(i,i+1));
		 }
		 
		 //looping through each digit of the credit card number
		 for(int i=cardnumberChk.length-2; i>=0;i=i-2)
		 {
			 int temp = cardnumberChk[i];
			 temp= temp *2;
			 
			 if(temp >9)
			 {
				 temp= temp % 10 +1;
			 }
			 cardnumberChk[i]=temp;
		 }
		 
		 for(int j =0; j < cardnumberChk.length; j++)
		  {
		    
			total = total + cardnumberChk[j]; 
		  }
		 
		 if (total % 10==0)
		 {
			 return true;
		 }
		 else 
		 {
			 return false;
		 }	 
 }
		
	public  boolean CustName(String custName)
	{
					
		if(custName != null  && custName.matches("^[A-za-z \']*$"))
		{
		   System.out.println("valid name");
		   return true;
		}
	    return false;
	}	
	
	
	public void amountToBePaid(String optionValue)
	{		
		driver.findElement(By.xpath("//button[contains(text(),'Pay Now')]")).click();		
		WebElement amount = driver.findElement(By.xpath("//select[@name='amount']"));
		Select s = new Select(amount);			
		
		List<WebElement> amount_options = s.getOptions();
		   for (WebElement options : amount_options) 
		   {
			 if(options.getAttribute("value").equals(optionValue))
				{
				   s.selectByValue(optionValue);
				   
				}
			}
	     driver.findElement(By.xpath("//input[@value='Continue']")).click();		
	 }
	
	public void CCardDetails(String custName,String cCardTypeValue, String cCardNo, String expMonth, String expYear,String Ccard_CVV)
	 {
				
			 driver.findElement(By.xpath("//input[@placeholder='First  & Last Name']")).sendKeys(custName);
			 cardType=driver.findElement(By.xpath("//select[@name='Card_name']"));
			 cardType_dropdown= new Select(cardType);
			 cardType_dropdown.selectByValue(cCardTypeValue);						
			 driver.findElement(By.xpath("//input[@placeholder='Card Number']")).sendKeys(cCardNo); 
			 
			 month = driver.findElement(By.xpath("//select[@name='CCExpiresMonth']"));
			 exp_Month=new Select(month);			
			 exp_Month.selectByValue(expMonth);
			
			 year=driver.findElement(By.xpath("//select[@name='CCExpiresYear']"));
			 exp_Year=new Select(year);			 
			 exp_Year.selectByValue(expYear);	 
	 
			 driver.findElement(By.xpath("//input[@id='cvv']")).sendKeys(Ccard_CVV);
			 driver.findElement(By.xpath("//input[@value='submit']")).click();
		
	}	

}
