package com.GAP.WS1_0.Helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.GAP.WS1_0.Constant.Global;

public class Helper 
{
static String projectDataPath = System.getProperty("user.dir") +  File.separator+ "data" + File.separator;
	
	public static String getProjectConfigPath(String className)
	{
		return projectDataPath + className +  File.separator;
	}
	public static boolean hasXPath(WebDriver driver, String xPath) throws InterruptedException 
	{
		return driver.findElements(By.xpath(xPath)).size() != 0;
	}

	public static WebElement getWebElementFromXPath(WebDriver driver, String xPath) throws InterruptedException 
	{
		return driver.findElement(By.xpath(xPath));
	}
	
	public static void actionOnWebElement(WebDriver driver, String xPath, int sleepTime) throws InterruptedException {
        WebElement we = driver.findElement(By.xpath(xPath));
        new Actions(driver).moveToElement(we).build().perform();
        if (sleepTime > 0)
            Thread.sleep(sleepTime);
    }

    public static void actionOnWebElementForLinkText(WebDriver driver, String linkText, int sleepTime)
            throws InterruptedException {
        WebElement we = driver.findElement(By.linkText(linkText));
        new Actions(driver).moveToElement(we).build().perform();
        if (sleepTime > 0)
            Thread.sleep(sleepTime);
    }

    public static void switchFrame(WebDriver driver, String xPath, int preSleepTime, int postSleepTime)
            throws InterruptedException {
        if (preSleepTime > 0)
            Thread.sleep(preSleepTime);
        driver.switchTo().frame(driver.findElement(By.xpath(xPath)));
        if (postSleepTime > 0)
            Thread.sleep(postSleepTime);
    }

	public static void sendKeys(WebDriver driver, String xPath, String value, int sleepTime) throws InterruptedException 
	{
		driver.findElement(By.xpath(xPath)).sendKeys(value);
		if (sleepTime > 0)
			Thread.sleep(sleepTime);
	}
	
	 public static void sendKeys(WebDriver driver, String xPath, Keys value, int sleepTime) throws InterruptedException {
	        driver.findElement(By.xpath(xPath)).sendKeys(value);
	        if (sleepTime > 0)
	            Thread.sleep(sleepTime);
	    }

	public static void click(WebDriver driver, String xPath, int sleepTime) throws InterruptedException 
	{
		driver.findElement(By.xpath(xPath)).click();
		if (sleepTime > 0)
			Thread.sleep(sleepTime);
	}
	
	public static void clear(WebDriver driver, String xPath, int sleepTime) throws InterruptedException 
	{
		driver.findElement(By.xpath(xPath)).clear();
		if (sleepTime > 0)
			Thread.sleep(sleepTime);
	}

	public static void linkText(WebDriver driver, String linkText, int sleepTime) throws InterruptedException 
	{
		driver.findElement(By.linkText(linkText)).click();
		if (sleepTime > 0)
			Thread.sleep(sleepTime);
	}
	
	public static boolean linkText(WebDriver driver, String linkText) throws InterruptedException {
        return driver.findElement(By.linkText(linkText)) != null;

    }

	public static String getAttribute(WebDriver driver, String xPath, String attrValue) throws InterruptedException 
	{
		return driver.findElement(By.xpath(xPath)).getAttribute(attrValue);
	}

	public static String getText(WebDriver driver, String xPath) throws InterruptedException 
	{
		return driver.findElement(By.xpath(xPath)).getText();
	}
	
	
	public static void scriptExecutor(WebDriver driver, String xPath, String script) throws InterruptedException 
	{
		JavascriptExecutor jsAssanyfield = (JavascriptExecutor) driver;
		WebElement elAssanyfield = driver.findElement(By.xpath(xPath));
		jsAssanyfield.executeScript(script, elAssanyfield);
	}
	
	public static void scriptExecutorForLinkText(WebDriver driver, String linkText, String script)
            throws InterruptedException {
        JavascriptExecutor jsAssanyfield = (JavascriptExecutor) driver;
        WebElement elAssanyfield = driver.findElement(By.linkText(linkText));
        jsAssanyfield.executeScript(script, elAssanyfield);
    }
	
	public static void logout(WebDriver driver) throws InterruptedException {
        driver.get(Global.logoutUrl);
        Thread.sleep(2000);
    }

    public static void selectDropDown(WebDriver driver, String xPath, String value, int sleepTime)
            throws InterruptedException {
        Select drpCountry = new Select(driver.findElement(By.xpath(xPath)));
        drpCountry.selectByVisibleText(value);
        if (sleepTime > 0)
            Thread.sleep(sleepTime);
    }

    public static void screenshotCapture(WebDriver driver, String className) throws IOException {
        Random objGenerator = new Random();
        int randomNumber = objGenerator.nextInt(100);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(projectDataPath + "temp" + File.separator + className + "_" + randomNumber + ".jpg"));
        Assert.fail("==========================Test case Failed===================");
    }

	
	// Write to Excel
	/*public static void writeIntoFile(String className, String dataPath, String condition, boolean b) 
	{
		FileWriter fw = null;
		BufferedWriter outputBr = null;
		try 
		{
			File output = new File(dataPath + "output\\" + condition + "-" + className + ".csv");
			if (output.exists()) 
			{
				fw = new FileWriter(output, true);
				outputBr = new BufferedWriter(fw);
				outputBr.write(b + "\n");
				outputBr.write(b+"1");
				outputBr.write(b+"3");
				outputBr.write(b+"2");
				// outputBr.write("RecordNumber," + recordNumber + ",AccountName," +
				// accountName+"\n");
				outputBr.close();
			} else 
				{
					fw = new FileWriter(output);
					fw.write(b + "\n");
					fw.write(b + "2");
					fw.write(b + "3");
					// 	fw.write("RecordNumber," + recordNumber + ",AccountName," +
					// accountName+"\n");
				}

		} catch (Exception e) 
		{
			e.printStackTrace();
		} finally 
		{
			if (fw != null) 
			{
				try 
				{
					fw.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			if (outputBr != null) 
			{
				try 
				{
					outputBr.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}*/
	
	public static void writeIntoFile(String filePath, String str,boolean forceNew) {
		  FileWriter fw = null;
		  BufferedWriter outputBr = null;
		  try {
		   File output = new File(filePath);
		   if (forceNew || !output.exists()) {
		    fw = new FileWriter(output);
		    fw.write(str);
		    Thread.sleep(100);
		   } else {
		    fw = new FileWriter(output, true);
		    outputBr = new BufferedWriter(fw);
		    outputBr.write(str);
		    outputBr.close();
		    Thread.sleep(100);
		   }

		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		   if (fw != null) {
		    try {
		     fw.close();
		    } catch (IOException e) {
		     e.printStackTrace();
		    }
		   }
		   if (outputBr != null) {
		    try {
		     outputBr.close();
		    } catch (IOException e) {
		     e.printStackTrace();
		    }
		   }
		  }
		 }
	
	public static String readFromFile(String masterFile) {
        try {
            return new BufferedReader(new FileReader(masterFile)).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return "";
        }
    }
}
