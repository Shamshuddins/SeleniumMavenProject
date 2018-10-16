package com.onlinesalesforce;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.utils.TwfException;

import jxl.read.biff.BiffException;

public class OnlineSalesForce extends WebPage{
	
	
	WebDriver driver;
	
	
	public void chooseShippingDate(String k) throws TwfException, BiffException, IOException, InterruptedException{
		
		
		driver=DriverFactory.getDriver();
		
		SimpleDateFormat date=new SimpleDateFormat("d");
		
		Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			String increment= date.format(cal.getTime());
			int dateNo=Integer.parseInt(increment);
			System.out.println(increment);
		

			//if date is less than 23 always choose the first one
			
			if(dateNo<23){
				
				System.out.println("----> "+dateNo);
				driver.findElement(By.xpath("//table[@class='dp_daypicker']//tbody//tr//td[text()='"+increment+"']")).click();
				
				Thread.sleep(5000);
				
			}else{
				
				
				List<WebElement> ele=driver.findElements(By.xpath("//table[@class='dp_daypicker']//tbody//tr//td[text()='"+increment+"']"));
					
				//if more than one element than choose the second one....	
				if(ele.size()>1){
						
						ele.get(1).click();
						
						
						
					}else{
						
						
						driver.findElement(By.xpath("//table[@class='dp_daypicker']//tbody//tr//td[text()='"+increment+"']")).click();
						
					}
				
				
				
			}
		
		
		
		
	}
	
	
	
	
	public void proceedToCheckOut(String k) throws TwfException, InterruptedException{
		
		driver=DriverFactory.getDriver();
		
		
		Actions action=new Actions(driver);
		
		action.moveToElement(driver.findElement(By.xpath("//i[@class='icon-icons_cart']")));
		action.build().perform();
		
		Thread.sleep(2000);
		
		action.click(driver.findElement(By.xpath("//strong[contains(text(),'Cart 1')]")));
		
		action.build().perform();
		
		
		
	}
	
	
	public void clearCart(String k) throws TwfException, InterruptedException, BiffException, IOException{
		
		driver=DriverFactory.getDriver();
		
		Actions action=new Actions(driver);
		
		action.moveToElement(driver.findElement(By.xpath("//i[@class='icon-icons_cart']")));
		action.build().perform();
		
        action.click(driver.findElement(By.xpath("//strong[contains(text(),'Cart 1')]")));
		
		action.build().perform();
		
		Thread.sleep(2000);
		
		
		waitForElement(getElementByUsing("Checkout"), 30);
		
		Thread.sleep(2000);
		
		action.moveToElement(driver.findElement(By.xpath("//i[@class='icon-icons_cart']")));
		action.build().perform();
		
		Thread.sleep(2000);
		action.click(driver.findElement(By.xpath("//button[text()='Clear Cart(s)']")));
		
		action.build().perform();
		
		
		
		action.click(getElementByUsing("Remove"));
		action.build().perform();
		
		
		//getElementByUsing("Remove").click();
		
		
		
		
	}
	
	
	

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}

}
