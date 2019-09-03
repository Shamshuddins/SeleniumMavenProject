package com.GAP.WS1_0;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.GAP.WS1_0.Helper.Helper;

import com.GAP.WS1_0.Constant.Constant;
import com.GAP.WS1_0.Constant.Constant;

import com.GAP.WS1_0.Constant.Constant;

import com.GAP.WS1_0.Constant.Global;








import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;

public class Chrome_Create_New_Opp extends BaseClass
{
	String className = this.getClass().getSimpleName();
	String dataPath = Helper.getProjectConfigPath(className);
	long timestamp = new Date().getTime();

	public long getTimestamp() {
		long temp = new Date().getTime() - timestamp;
		this.setTimestamp(new Date().getTime());
		return temp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Parameters({ "masterFileName" })
	@Test
	public void booth_rent_stage_on_payment(String masterFileName) throws Exception
	{
		logger = extent.createTest(className);
		System.out.println("======================="+className +" method execution started===========================");
		String url = Global.siteUrl;
		String dataInput = dataPath + Global.dataInputFileName;
		String sampleFile = Global.sampleFile;
		String masterFile = Global.masterFilePath + masterFileName;
		String userNameAdmin = Global.adminUNM;
		String passwordAdmin = Global.adminPass;
		/** Read CSV file */
		BufferedReader br = null;
		int recordNumber = 0;// use for skipping header from CSV
		String line = "", cvsSplitBy = ",", defaultValue = null, random = new Random().nextInt(10000) + "";

		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {

			String masterArray[] = new BufferedReader(new FileReader(masterFile)).readLine().split(",");
			String automationOutputStep1 = Global.masterFilePath + masterArray[1] + ".txt";
			String automationInputStep1 = Global.masterFilePath + masterArray[0] + ".txt";
			br = new BufferedReader(new FileReader(dataInput));

			if ((line = br.readLine()) != null) {}

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] record = line.split(cvsSplitBy);




				/** Read Record from input */
				
				
				String eventEdition = 0 < record.length ? record[0] : defaultValue;
				
				driver.get(url);
				Thread.sleep(2000);

				if (Helper.hasXPath(driver, "//*[@id=\"login_form\"]")) {
					Helper.sendKeys(driver, Constant.userNameField, userNameAdmin, 0);// Enter userName
					Helper.sendKeys(driver, Constant.passwordField, passwordAdmin, 0);// Enter password
					Helper.click(driver, Constant.loginButton, 9000);// Click on the Login Button
				}

				/** Switch to Lightning Experience UI from Classic */
				/*if (driver.getPageSource().contains("Switch to Lightning Experience")) {
					Helper.linkText(driver, Constant.SwitchToLightningExperience, 5000);
				}*/

				//Helper.linkText(driver, "Home", 3000);

				
				Thread.sleep(10000);
				driver.get("https://informage--uat.cs70.my.salesforce.com/home/home.jsp?source=lex");
				
				driver.findElement(By.linkText("Accounts")).click(); // Click on Accounts Tab
				
				driver.findElement(By.linkText("6435Charlton Company")).click();
				Helper.click(driver, Constant.Create_New_Opportunity_clickCreateNewOppButton, 0); // Click on Create New Opportunity Button

				Helper.click(driver, Constant.Create_New_Opportunity_clickEventEditionLookUp, 3000); // Click on Event Edition LookUp
				Helper.sendKeys(driver, Constant.Create_New_Opportunity_sendEventEdition1, eventEdition, 3000);// Enter Event Edition
				Helper.click(driver, Constant.Create_New_Opportunity_clickEventEdition, 0); // Click on Select Event

				Helper.sendKeys(driver, Constant.Create_New_Opportunity_sendOpportunityName, "testing" + random + "opp", 0);// Enter Opportunity name
				String oppName17 = Helper.getAttribute(driver, Constant.Create_New_Opportunity_getOpportunityName, "value");
				System.out.println(oppName17);

				Helper.click(driver, Constant.Create_New_Opportunity_clickOppContactLookUp, 3000); // Click on Opportunity Contact LookUp
				Helper.sendKeys(driver, Constant.Create_New_Opportunity_sendOpportunityContact1, "Charlton"+" " , 3000);// Enter Opportunity Contact
				Helper.click(driver, Constant.Create_New_Opportunity_clickOppContact, 0); // Click on Select Contact
				//====================add billing contact
				Date d17 = new Date();
				Helper.sendKeys(driver, Constant.Create_New_Opportunity_selectDate, d17.getDate() + "/" + (d17.getMonth() + 1) + "/" + (d17.getYear() + 1900), 0);// Select Date
				Helper.sendKeys(driver, Constant.Create_New_Opportunity_selectDate, Keys.TAB, 3000);

				Helper.click(driver, Constant.Create_New_Opportunity_saveButton, 20000); // Click on Save Button
				driver.navigate().refresh();
				Thread.sleep(15000);
				/*driver.switchTo().frame(driver.findElement(By.xpath("(//*[@class='slds-template_iframe slds-card iframe-parent']/iframe)"))); // Switch to iFrame
				//Scroll in Page
				JavascriptExecutor cgender3573 = (JavascriptExecutor) driver;
				WebElement gender3573 = driver.findElement(By.xpath("(//*[@class='nx-basicfieldeditor-item-label nx-layout-above'])[35]/div[1]"));
				cgender3573.executeScript("arguments[0].scrollIntoView();", gender3573);
				String exhibitorvalue7 = driver.findElement(By.xpath("(//*[@class='nx-field nx-layout-above'])[39]/div")).getText();
				System.out.println("Exhibitor selected is : "+exhibitorvalue7);
				
				Helper.click(driver, Constant.Verify_Quote_Functionality_clickNewQuote, 5000); // Click on Create New Quote Button
                Helper.click(driver, Constant.Verify_Quote_Functionality_clickSaveButton, 10000); // Click on Save Button
                
                Helper.writeIntoFile(automationOutputStep1, oppName17,  true);*/ // Write into File
			//==========================================================
				/*driver.switchTo().defaultContent();
				Helper.click(driver, Booth_Test_Contstant.floorPlan, 10000);// Click floorPlan Menu
				driver.switchTo().defaultContent();									
				Thread.sleep(10000);
				int size7 = driver.findElements(By.tagName("iframe")).size();
			    driver.switchTo().frame(--size7);
				Thread.sleep(10000);
				driver.switchTo().frame(0);
				Thread.sleep(30000);
				
				
				Thread.sleep(20000);
				
				driver.findElement(By.xpath("//*[@id='btnAv']")).click();
				Thread.sleep(20000);
				driver.findElement(By.xpath("//*[@id='nav1']//*[@id='boothSearch']")).click();
				driver.findElement(By.xpath("//*[@id='nav1']//*[@id='boothSearch']")).sendKeys("B148");
				Thread.sleep(10000);
				driver.findElement(By.cssSelector("#boothGrid > div.slick-viewport > div > div:nth-child(1) > div.slick-cell.l2.r2.cell-text")).click();
				Thread.sleep(16000);
				Actions ac7t= new Actions(driver);
				ac7t.moveByOffset(1000, 280).click().build().perform();
				Thread.sleep(6000);
				driver.switchTo().defaultContent();
				Thread.sleep(6000);
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='windowViewMode-normal oneContent active lafPageHost']//*[iframe]/iframe")));
				Helper.click(driver, Verify_Booth_Details_Constant.addBoothButton, 10000);// Click floorPlan Menu
				
				driver.switchTo().defaultContent();
				Thread.sleep(15000);
				
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='windowViewMode-normal oneContent active lafPageHost']//*[iframe]/iframe")));
				Thread.sleep(10000);
				Helper.click(driver, Matching_Product_Name_Constant.save, 15000);// Click on the save
				driver.switchTo().defaultContent(); 
				
				Thread.sleep(10000);
				driver.navigate().refresh();
				Thread.sleep(10000);
				Helper.click(driver, Constant.Booth_Rent_Stage_OpportunityLink, 15000);// Click on the save
				driver.switchTo().defaultContent();
				Thread.sleep(5000);
				
				Helper.switchFrame(driver, Constant.swtichFrame, 5000, 10000); // Switch to iFrame
				
				JavascriptExecutor cgender353a74 = (JavascriptExecutor) driver;
				WebElement gender353a74 = driver.findElement(By.xpath("(//*[@class='nx-basicfieldeditor-item-label nx-layout-above'])[47]"));
				cgender353a74.executeScript("arguments[0].scrollIntoView();", gender353a74);	
				String paystatus7 = Helper.getText(driver, Constant.Booth_Rent_Stage_paymentStatus);// Get first name
				System.out.println("Payment Status : "+paystatus7);
				Thread.sleep(3000);
				Helper.click(driver, Booth_Test_Contstant.quote, 12000);// Click quote Menu
				driver.switchTo().defaultContent();
				Thread.sleep(3000);
				
				//Click Generate Manual Contract option display under Send DocumentSign PickList field
				WebElement dropdown17 = driver.findElement(By.xpath("//ul[@class='branding-actions slds-button-group slds-m-left--xx-small oneActionsRibbon forceActionsContainer']/li[@class='slds-button slds-button--icon-border-filled oneActionsDropDown']"));			 
				Actions action17 = new Actions(driver);	 
				action17.moveToElement(dropdown17).build().perform();	        
				Thread.sleep(5000);
				Helper.click(driver, Matching_Product_Name_Constant.dropdown, 8000);// Click on the dropdown
				Helper.linkText(driver, "Generate Manual Contract", 3000);// Click Generate Manual Contract
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='windowViewMode-normal oneContent active lafPageHost']//*[iframe]/iframe")));
				Thread.sleep(20000);
				if(driver.findElements(By.xpath("//*[@class='modal-footer slds-text-heading_medium slds-p-vertical_medium']/center/p/b")).size() !=0)
				{
					System.out.println(driver.findElement(By.xpath("//*[@class='modal-footer slds-text-heading_medium slds-p-vertical_medium']/center/p/b")).getText());
					//driver.close();
				}else {
					System.out.println("product available");
				}
				//Select primary account by clicking radio button and than click 'continue' button
				if(driver.findElements(By.xpath("(//*[@type='radio'])[1]")).size() != 0)			
				{

					driver.findElement(By.xpath("(//*[@type='radio'])[1]")).click();

					Thread.sleep(3000);

					driver.findElement(By.xpath("//input[@value='Continue']")).click();

					driver.switchTo().defaultContent();
				}
				else
				{
					driver.findElement(By.xpath("//input[@value='Continue']")).click();

					driver.switchTo().defaultContent();
				}

				//Click on 'Return to opportunity' button
				WebDriverWait returnButton11 = new WebDriverWait(driver, 50);
				returnButton11.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Return to Opportunity']")));
				driver.findElement(By.xpath("//input[@value='Return to Opportunity']")).click();

				Thread.sleep(20000);
				driver.navigate().refresh();
				Thread.sleep(10000);
				
				//===============================
				if (driver.getPageSource().contains("Switch to Lightning Experience")) {
					Helper.linkText(driver, Matching_Product_Name_Constant.SwitchToLightningExperience, 5000);
				}
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='slds-template_iframe slds-card']/iframe")));// Switch to iFrame
				Thread.sleep(10000);
				
				//Verify Attach agreement button display
				String attachAgreement11 = Helper.getText(driver, Matching_Product_Name_Constant.attachAgreement);
				System.out.println("Agreement button is displaying :"+attachAgreement11);
				Thread.sleep(3000);

				Helper.click(driver, Matching_Product_Name_Constant.attachAgreement, 8000);// Click on the attachAgreement 

				Thread.sleep(6000);
				driver.switchTo().defaultContent();
				driver.navigate().refresh();
				Thread.sleep(6000);

				//Select the Frame
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='windowViewMode-normal oneContent active lafPageHost']//*[iframe]/iframe")));	        	        
				Thread.sleep(3000);	              

				//Click 'choose file' button and select file
				driver.findElement(By.xpath("//*[@id=\"j_id0:j_id6:file_File\"]")).sendKeys("C:\\Rajesh Gupta\\sample.pdf");	        	      

				//Click the "Attach Agreement" button
				driver.findElement(By.xpath("//button[contains(text(), 'Attach Agreement')]")).click();
				Thread.sleep(6000);
				driver.switchTo().defaultContent();
				Thread.sleep(10000);

				
			
				
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='slds-template_iframe slds-card']/iframe")));
				Thread.sleep(20000);
				//Scroll in Page
				JavascriptExecutor cgender14 = (JavascriptExecutor) driver;
				WebElement gender14 = driver.findElement(By.xpath("(//*[@class='nx-basicfieldeditor-item-label nx-layout-above'])[12]"));
				cgender14.executeScript("arguments[0].scrollIntoView();", gender14);
				String stage14= Helper.getText(driver, Resubmit_Accounting_Approval_Constant.stage2);
				System.out.println("Opportunity Stage is : "+stage14);
				String status4= Helper.getText(driver, Resubmit_Accounting_Approval_Constant.status2);
				System.out.println("Opportunity Status is : "+status4);
								
				Helper.click(driver, Matching_Product_Name_Constant.boothTab, 8000);// Click on the boothTab
				Helper.click(driver, Matching_Product_Name_Constant.rentedBooth, 18000);// Click on the rentedBooth
				
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='nx-template']/iframe")));
				Thread.sleep(2000);
				
				
				String BoothStatus5 = driver.findElement(By.xpath("//*[@class='slds-table slds-scrollable--y slds-table--bordered slds-max-medium-table--stacked slds-no-row-hover dataTable no-footer']/tbody/tr[1]/td[6]")).getText();
				System.out.println("Booth Status is : "+BoothStatus5);
				Thread.sleep(2000);*/
                String content = className+","+"Pass\n";
				bw.write(content);
				bw.flush();
				bw.close();
                driver.close();
			}


		} catch (Exception e) {
			class Local {};		
			Random objGenerator = new Random();
			int randomNumber = objGenerator.nextInt(100);
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(FILENAME, true));
			String content = className+","+"Fail\n"+","+"Fail Reason : "+e+"\n\n";
			bw1.write(content);
			bw1.flush();
			bw1.close();
			bw1.close();
			String methodName = Local.class.getEnclosingMethod().getName();
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir") +"\\Failure Screenshot\\"+methodName+randomNumber+".jpg"));
			//driver.close();
			Assert.fail("==========================Test case Failed===================",e);
		}  finally {

		}		
	}	
}
