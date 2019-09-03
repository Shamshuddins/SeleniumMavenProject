
/**
 * Project:     GAP
 * Date:        13/12/2018
 * Created By:  Rajesh Gupta
 * Test Class:  Customer Center Home Page
 ************************************************************************
 * Description:  This class is used to test Customer Center Home Page
 ************************************************************************
 * History:
 */
package com.GAP.WS1_0;


import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.GAP.WS1_0.Helper.Helper;
import com.GAP.WS1_0.Constant.Constant;
import com.GAP.WS1_0.Constant.Global;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import jxl.read.biff.BiffException;

public class Create_Online_Form_CC extends BaseClass 
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
	public void create_online_form__cc(String masterFileName) throws InterruptedException, BiffException, IOException 
	{
		logger = extent.createTest(className);
		System.out.println("======================="+className+" method execution started===========================");
		String url = Global.siteUrlCCOpAdmin;
        String dataInput = dataPath + Global.dataInputFileName;
        String masterFile = Global.masterFilePath + masterFileName;
        String userNameOpsAdmin = Global.CCOpAdminUNM;
		String passwordOpsAdmin = Global.CCOpAdminPass;
		String FormName="";
		String Version="";
        
		
        /** Read CSV file */
        BufferedReader br = null;
        int recordNumber = 0;// use for skipping header from CSV
        String line = "", cvsSplitBy = ",", defaultValue = null, random = new Random().nextInt(10000) + "";

        //try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {

            String masterArray[] = new BufferedReader(new FileReader(masterFile)).readLine().split(",");
            String automationInputStep1 = Global.masterFilePath + masterArray[0] + ".txt";
            String automationOutputStep1 = Global.masterFilePath + masterArray[1] + ".txt";
				br = new BufferedReader(new FileReader(dataInput));

				if ((line = br.readLine()) != null) {}
				int lineNumber=1;
				while ((line = br.readLine()) != null) {
					lineNumber++;
					// use comma as separator
					String[] record = line.split(cvsSplitBy);
					if(lineNumber==2) {
					/** Read Record from input */
					
					String formName1 = 0 < record.length ? record[0] : defaultValue;
					String sectionName = 1 < record.length ? record[1] : defaultValue;
					String sectionColumn = 2 < record.length ? record[2] : defaultValue;
					String templateBuilder = 3 < record.length ? record[3] : defaultValue;
					String textCompose = 4 < record.length ? record[4] : defaultValue;
					String checkBox1 = 5 < record.length ? record[5] : defaultValue;
					String helpTextValue = 6 < record.length ? record[6] : defaultValue;
					String maxCharLimit = 7 < record.length ? record[7] : defaultValue;
					String checkBox2 = 8 < record.length ? record[8] : defaultValue;
					String checkBox3 = 9 < record.length ? record[9] : defaultValue;
					String checkBox4 = 10 < record.length ? record[10] : defaultValue;
					String checkBox5 = 11 < record.length ? record[11] : defaultValue;
					String checkBox6 = 12 < record.length ? record[12] : defaultValue;
					String Currency = 13 < record.length ? record[13] : defaultValue;
					String decimalLimit = 14 < record.length ? record[14] : defaultValue;
					String cloneTemplate = 15 < record.length ? record[15] : defaultValue;
					Version = 16 < record.length ? record[16] : defaultValue;
					String checkBox7 = 17 < record.length ? record[17] : defaultValue;
					
					
					/*String  = 12 < record.length ? record[12] : defaultValue;
					String  = 13 < record.length ? record[13] : defaultValue;
					String  = 14 < record.length ? record[14] : defaultValue;
					String  = 15 < record.length ? record[15] : defaultValue;
					String  = 16 < record.length ? record[16] : defaultValue;
					String  = 17 < record.length ? record[17] : defaultValue;
					String  = 18 < record.length ? record[18] : defaultValue;
					String  = 19 < record.length ? record[19] : defaultValue;*/

					/** Read Record from input */
					driver.get(url);
					Thread.sleep(2000);

					if (Helper.hasXPath(driver, "//*[@id=\"login_form\"]")) {
						Helper.sendKeys(driver, Constant.userNameField, userNameOpsAdmin, 0);// Enter userName
						Helper.sendKeys(driver, Constant.passwordField, passwordOpsAdmin, 0);// Enter password
						Helper.click(driver, Constant.loginButton, 8000);// Click on the Login Button
					}

					/** Switch to Lightning Experience UI from Classic */
					if (driver.getPageSource().contains("Switch to Lightning Experience")) {
						Helper.linkText(driver, Constant.SwitchToLightningExperience, 5000);
					}

					Helper.linkText(driver, "Home", 3000);
					Helper.linkText(driver, "Customer Centre", 5000);// Click Customer Centre menu
					driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='slds-template_iframe slds-card iframe-parent']/iframe")));
					Thread.sleep(10000);
					
					
					Helper.click(driver, Constant.Global_Form_Template_CC_formTemplate, 6000); // Click on FormTemplate
					driver.switchTo().defaultContent();
					Thread.sleep(3000);
					driver.switchTo().frame(driver.findElement(By.xpath("//*[@class='slds-template_iframe slds-card iframe-parent']/iframe")));
					Thread.sleep(10000);
					
					/*=======================================================================================================================
																		CREATE ONLINE FORM                 
					  =======================================================================================================================*/
					
					Helper.click(driver, Constant.Global_Form_Template_CC_createOnlineForm, 10000); // Click on createOnlineForm
					driver.switchTo().defaultContent();
					Thread.sleep(3000);
					
					Helper.click(driver, Constant.Global_Form_Template_CC_OnlineFormAddNewbtn, 10000); // Click on AddNewbtn
					Helper.sendKeys(driver, Constant.Global_Form_Template_CC_OnlineFormName, formName1+random, 3000);// Enter OnlineForm Name
					FormName = driver.findElement(By.xpath(Constant.Global_Form_Template_CC_OnlineFormName)).getAttribute("value");
					System.out.println("FormName is : "+FormName);
					Helper.click(driver, Constant.Global_Form_Template_CC_OnlineFormCreatebtn, 10000); // Click on Create btn
					
					
					/*//===========                      Edit Template            =============================================
					driver.findElement(By.xpath("//*[@class='slds-lookup__search-input slds-input input uiInput uiInputText uiInput--default uiInput--input']")).sendKeys(FormName);
					Thread.sleep(3000);
					driver.findElement(By.xpath("//table[@class='slds-table slds-table_bordered slds-border_left slds-border_right slds-table_resizable-cols slds-table_fixed-layout']/tbody/tr[1]/td[3]/button[3]")).click();
					Thread.sleep(8000);
					//===========                      Edit Template            =============================================
*/					
					/*Helper.click(driver, Constant.Global_Form_Template_CC_FormTemplateAddSectionbtn, 10000); // Click on AddSectionbtn
					Helper.sendKeys(driver, Constant.Global_Form_Template_CC_FormTemplateEnterSection, sectionName, 3000);// Enter OnlineForm Name
					Select docType1b= new Select(driver.findElement(By.xpath("(//*[@class='slds-select'])[2]")));
					docType1b.selectByValue(sectionColumn);
					driver.findElement(By.xpath("//*[@class='slds-button slds-button_neutral slds-button slds-button_brand']")).click();*/
					
					//===========          Drag & Drop       =================
					if(templateBuilder.equalsIgnoreCase("Number/Currency")||templateBuilder.equalsIgnoreCase("Signature")||templateBuilder.equalsIgnoreCase("GPS Location")||templateBuilder.equalsIgnoreCase("Media File")||templateBuilder.equalsIgnoreCase("Radio")||templateBuilder.equalsIgnoreCase("Picklist")) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0,1000)");
					JavascriptExecutor cgender = (JavascriptExecutor) driver;
					WebElement gender = driver.findElement(By.xpath("//*[@class='slds-scrollable slds-grow questionBuilder-left-box']/div[19]"));
					cgender.executeScript("arguments[0].scrollIntoView();", gender);}
					
					if(templateBuilder.equalsIgnoreCase("Header/Footer")||templateBuilder.equalsIgnoreCase("Text (Plain)")||templateBuilder.equalsIgnoreCase("Text (Rich)")||templateBuilder.equalsIgnoreCase("Address")||templateBuilder.equalsIgnoreCase("Email")||templateBuilder.equalsIgnoreCase("Phone")||templateBuilder.equalsIgnoreCase("Date")||templateBuilder.equalsIgnoreCase("DateTime")||templateBuilder.equalsIgnoreCase("URL")||templateBuilder.equalsIgnoreCase("Checkbox")) {
						//JavascriptExecutor js = (JavascriptExecutor) driver;
						//js.executeScript("window.scrollBy(0,0)");
						JavascriptExecutor cgender = (JavascriptExecutor) driver;
						WebElement gender = driver.findElement(By.xpath("//*[@class='slds-scrollable slds-grow questionBuilder-left-box']/div[1]"));
						cgender.executeScript("arguments[0].scrollIntoView();", gender);}
				
					WebElement From = driver.findElement(By.xpath("//*[@class='slds-media__body slds-border_left slds-p-around_small qf-QuestionBuilder-cursor']//h2[text()='"+templateBuilder+"'"+"]"));
					//header/footer, Text (plain), Text (Rich), Address, Email, Phone, Date, DateTime, URL, Checkbox, Number/Currency, Signature, GPS Location, Media File, Information/Instruction--------------------working    ||  Radio, Picklist, Switch, slider---------------pending
         		  	WebElement To = driver.findElement(By.xpath("(//*[@class='slds-scrollable slds-grow questionBuilder slds-is-relative sortableArea ui-sortable'])[1]"));
          		  	Actions builder = new Actions(driver);
                    Action dragAndDrop = builder.clickAndHold(From)
                    .moveToElement(To)
                    .release(To)
                    .build();
                    dragAndDrop.perform();
					Thread.sleep(8000);
					//Select Image Option
					if(driver.findElements(By.xpath("//*[@class='slds-form-element']//*[text()='Sellect Image']")).size()!=0)
					{
						driver.findElement(By.xpath("//*[@class='slds-form-element']//*[text()='Sellect Image']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@class='slds-modal__content slds-p-around_medium']/label[1]/img")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("(//*[@class='slds-button slds-button_brand'])[3]")).click();
						Thread.sleep(6000);
					}
					//Compose text Option 1
					if(driver.findElements(By.xpath(Constant.Global_Form_Template_CC_templateEditorComposeText)).size()!=0)
					{
						driver.findElement(By.xpath(Constant.Global_Form_Template_CC_templateEditorComposeText)).sendKeys(textCompose);
						Thread.sleep(3000);
					}
					
					if(driver.getPageSource().contains("Design header/footer using images")) {
					if(driver.findElements(By.xpath("//*[@class='ql-editor slds-rich-text-area__content slds-text-color_weak slds-grow']")).size()!=0)
					{
						driver.findElement(By.xpath("//*[@class='ql-editor slds-rich-text-area__content slds-text-color_weak slds-grow']")).sendKeys(textCompose);
						Thread.sleep(4000);
					}}
					
					//Help Text Check & Text box
					if(checkBox1.equalsIgnoreCase("1")) {
					if(driver.getPageSource().contains("Help Text")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox__label'])[1]/span[1]")).size()!=0) {
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[1]")).click();
						driver.findElement(By.xpath("//*[@placeholder='Help Text']")).sendKeys(helpTextValue);
						Thread.sleep(3000);
					}}}
					
					if(checkBox1.equalsIgnoreCase("0")) {
						if(driver.getPageSource().contains("Question Options")) {
							if(driver.findElements(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[4]/input")).size()!=0) {
								driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[4]/input")).sendKeys("Lorem Ipsum");
								Thread.sleep(5000);
						}}}
					
					if(!driver.getPageSource().contains("Help Text")) {
						if(driver.findElements(By.xpath("(//*[@class='slds-checkbox__label'])[1]/span[1]")).size()!=0) {
							driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[1]")).click();
						
							Thread.sleep(3000);
						}}
					
					
					//Max char Limit text box2-----remove when Help text is not checked
					/*if(!driver.getPageSource().contains("Text Color")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[3]/input")).size()!=0) {
						driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[3]/input")).sendKeys(maxCharLimit);
						Thread.sleep(4000);
					}}*/
					
					//Required Checkbox
					if(checkBox2.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[2]")).size()!=0) {
						
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[2]")).click();
					}}
					
					//=======if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[1]")).size()!=0) {
						
						//driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[1]")).click();
					//===========}
					
					//Allow Comment Checkbox
					if(checkBox3.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[3]")).size()!=0) {
						
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[3]")).click();
					}}
					
					
					
					//Allow Attachment Checkbox
					if(checkBox4.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[4]")).size()!=0) {
						
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[4]")).click();
					}}
					
					
					
					//Weight Checkbox
					if(checkBox5.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[5]")).size()!=0) {
						
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[5]")).click();
					}}
					
					if(driver.findElements(By.xpath("(//*[@class='slds-form-element__label'])[6]")).size()!=0) {
						String label=driver.findElement(By.xpath("(//*[@class='slds-form-element__label'])[6]")).getText();
						if(label.equalsIgnoreCase("Weight")) {
							driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[3]/input")).sendKeys("6.2");
						}
					}
					
					//Question Option==Radio
					//=============if(driver.getPageSource().contains("Question Options")) {
									//if(driver.findElements(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[2]/input")).size()!=0) {
									//driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[2]/input")).sendKeys("Question Option1");
									//Thread.sleep(4000);
					//==============	}}
					
					//Score check box
					if(checkBox6.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[6]")).size()!=0) {
						
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[6]")).click();
					}}
					
					if(checkBox7.equalsIgnoreCase("1")) {
						if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[6]")).size()!=0) {
							
							driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[6]")).click();
						}}
					
					//==============Background Color===not working
						//if(driver.findElements(By.xpath("(//*[@id='input-5'])")).size()!=0) {
						//driver.findElement(By.xpath("(//*[@id='input-5'])")).clear();
						//driver.findElement(By.xpath("(//*[@id='input-5'])")).sendKeys("#5ebbff");
					//}
					
					//Text Color====not working
						//if(driver.findElements(By.xpath("(//*[@id='input-6'])")).size()!=0) {
						//driver.findElement(By.xpath("(//*[@id='input-6'])")).click();
						//driver.findElement(By.xpath("(//*[@id='input-6'])")).clear();
						//driver.findElement(By.xpath("(//*[@id='input-6'])")).sendKeys("#bd35bd");
					//=============}
					if(checkBox5.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-select_container'])[2]/select")).size()!=0){
						driver.findElement(By.xpath("(//*[@class='slds-select_container'])[2]/select")).click();
					Select docType1a= new Select(driver.findElement(By.xpath("(//*[@class='slds-select_container'])[2]/select")));
					docType1a.selectByValue(Currency);
					}}
					
					
					
					if(driver.findElements(By.xpath("(//*[@class='slds-select_container'])[3]/select")).size()!=0){
						driver.findElement(By.xpath("(//*[@class='slds-select_container'])[3]/select")).click();
						Select docType2= new Select(driver.findElement(By.xpath("(//*[@class='slds-select_container'])[3]/select")));
						docType2.selectByValue(decimalLimit);
					}
					
					
					
					if(driver.findElements(By.xpath("(//*[@class='slds-form-element slds-m-top_small'])[1]/label")).size()!=0) {
						String element = driver.findElement(By.xpath("(//*[@class='slds-form-element slds-m-top_small'])[1]/label")).getText();
						if(element.equalsIgnoreCase("Background Color")) {
							driver.findElement(By.xpath("(//*[@class='slds-form-element__control'])[6]//input")).sendKeys("#0b7477");
						}
					}
					
					if(driver.findElements(By.xpath("(//*[@class='slds-form-element slds-m-top_small'])[2]/label")).size()!=0) {
						String element = driver.findElement(By.xpath("(//*[@class='slds-form-element slds-m-top_small'])[2]/label")).getText();
						if(element.equalsIgnoreCase("Text Color")) {
							driver.findElement(By.xpath("(//*[@class='slds-form-element__control'])[7]//input")).sendKeys("#0b7477");
						}
					}
					//Save
					if(driver.findElements(By.xpath("//*[@class='slds-button slds-button_neutral slds-button slds-button_brand']")).size()!=0){
						driver.findElement(By.xpath("//*[@class='slds-button slds-button_neutral slds-button slds-button_brand']")).click();
					}
					if(driver.findElements(By.xpath("(//*[@class='slds-button slds-button_brand'])[3]")).size()!=0){
						driver.findElement(By.xpath("(//*[@class='slds-button slds-button_brand'])[3]")).click();
						Thread.sleep(3000);}
					if(driver.findElements(By.xpath("(//*[@class='slds-button slds-button_brand'])")).size()!=0){
						driver.findElement(By.xpath("(//*[@class='slds-button slds-button_brand'])")).click();
						Thread.sleep(3000);}
					
					
					
					//=================================================================================================================================
					Helper.writeIntoFile(automationOutputStep1, FormName,  true); // Write into File
				}else {
					String templateBuilder = 3 < record.length ? record[3] : defaultValue;
					String textCompose = 4 < record.length ? record[4] : defaultValue;
					String checkBox1 = 5 < record.length ? record[5] : defaultValue;
					String helpTextValue = 6 < record.length ? record[6] : defaultValue;
					String maxCharLimit = 7 < record.length ? record[7] : defaultValue;
					String checkBox2 = 8 < record.length ? record[8] : defaultValue;
					String checkBox3 = 9 < record.length ? record[9] : defaultValue;
					String checkBox4 = 10 < record.length ? record[10] : defaultValue;
					String checkBox5 = 11 < record.length ? record[11] : defaultValue;
					String checkBox6 = 12 < record.length ? record[12] : defaultValue;
					String Currency = 13 < record.length ? record[13] : defaultValue;
					String decimalLimit = 14 < record.length ? record[14] : defaultValue;
					String cloneTemplate = 15 < record.length ? record[15] : defaultValue;
					Version = 16 < record.length ? record[16] : defaultValue;
					if(templateBuilder.equalsIgnoreCase("Number/Currency")||templateBuilder.equalsIgnoreCase("Signature")||templateBuilder.equalsIgnoreCase("GPS Location")||templateBuilder.equalsIgnoreCase("Media File")) {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(0,1000)");
						JavascriptExecutor cgender = (JavascriptExecutor) driver;
						WebElement gender = driver.findElement(By.xpath("//*[@class='slds-scrollable slds-grow questionBuilder-left-box']/div[19]"));
						cgender.executeScript("arguments[0].scrollIntoView();", gender);}
						
						if(templateBuilder.equalsIgnoreCase("Header/Footer")||templateBuilder.equalsIgnoreCase("Text (Plain)")||templateBuilder.equalsIgnoreCase("Text (Rich)")||templateBuilder.equalsIgnoreCase("Address")||templateBuilder.equalsIgnoreCase("Email")||templateBuilder.equalsIgnoreCase("Phone")||templateBuilder.equalsIgnoreCase("Date")||templateBuilder.equalsIgnoreCase("DateTime")||templateBuilder.equalsIgnoreCase("URL")||templateBuilder.equalsIgnoreCase("Checkbox")) {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("window.scrollBy(0,0)");
							driver.findElement(By.xpath("//span[text()='Template Builder']")).click();
							JavascriptExecutor cgender = (JavascriptExecutor) driver;
							WebElement gender = driver.findElement(By.xpath("//*[@class='slds-scrollable slds-grow questionBuilder-left-box']/div[1]"));
							cgender.executeScript("arguments[0].scrollIntoView();", gender);}
					WebElement From = driver.findElement(By.xpath("//*[@class='slds-media__body slds-border_left slds-p-around_small qf-QuestionBuilder-cursor']//h2[text()='"+templateBuilder+"'"+"]"));
					//header/footer, Text (plain), Text (Rich), Address, Email, Phone, Date, DateTime, URL, Checkbox, Number/Currency, Signature, GPS Location, Media File--------------------working    ||  Radio, Picklist, Switch, slider---------------pending
         		  	WebElement To = driver.findElement(By.xpath("(//*[@class='slds-scrollable slds-grow questionBuilder slds-is-relative sortableArea ui-sortable'])[1]"));
          		  	Actions builder = new Actions(driver);
                    Action dragAndDrop = builder.clickAndHold(From)
                    .moveToElement(To)
                    .release(To)
                    .build();
                    dragAndDrop.perform();
					Thread.sleep(8000);
					if(driver.findElements(By.xpath("//*[@class='slds-form-element']//*[text()='Sellect Image']")).size()!=0)
					{
						driver.findElement(By.xpath("//*[@class='slds-form-element']//*[text()='Sellect Image']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@class='slds-modal__content slds-p-around_medium']/label[1]/img")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("(//*[@class='slds-button slds-button_brand'])[3]")).click();
						Thread.sleep(6000);
					}
					//Compose text Option 1
					if(driver.findElements(By.xpath(Constant.Global_Form_Template_CC_templateEditorComposeText)).size()!=0)
					{
						driver.findElement(By.xpath(Constant.Global_Form_Template_CC_templateEditorComposeText)).sendKeys(textCompose);
						Thread.sleep(3000);
					}
					
					if(driver.getPageSource().contains("Design header/footer using images")) {
					if(driver.findElements(By.xpath("//*[@class='ql-editor slds-rich-text-area__content slds-text-color_weak slds-grow']")).size()!=0)
					{
						driver.findElement(By.xpath("//*[@class='ql-editor slds-rich-text-area__content slds-text-color_weak slds-grow']")).sendKeys(textCompose);
						Thread.sleep(4000);
					}}
					
					//Help Text Check & Text box
					if(checkBox1.equalsIgnoreCase("1")) {
					if(driver.getPageSource().contains("Help Text")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox__label'])[1]/span[1]")).size()!=0) {
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[1]")).click();
						driver.findElement(By.xpath("//*[@placeholder='Help Text']")).sendKeys(helpTextValue);
						Thread.sleep(3000);
					}}}
					
					if(checkBox1.equalsIgnoreCase("0")) {
						if(driver.getPageSource().contains("Help Text")) {
							if(driver.findElements(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[2]/input")).size()!=0) {
								driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[2]/input")).sendKeys("Lorem ipsum");
								Thread.sleep(5000);
						}}}
					
					if(!driver.getPageSource().contains("Help Text")) {
						if(driver.findElements(By.xpath("(//*[@class='slds-checkbox__label'])[1]/span[1]")).size()!=0) {
							driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[1]")).click();
						
							Thread.sleep(3000);
						}}
					
					//Max char Limit text box2-----remove when Help text is not checked
					if(!driver.getPageSource().contains("Text Color")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[3]/input")).size()!=0) {
						driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[3]/input")).sendKeys(maxCharLimit);
						Thread.sleep(4000);
					}}
					
					//Required Checkbox
					if(checkBox2.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[2]")).size()!=0) {
						
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[2]")).click();
					}}
					
					//=======if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[1]")).size()!=0) {
						
						//driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[1]")).click();
					//===========}
					
					//Allow Comment Checkbox
					if(checkBox3.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[3]")).size()!=0) {
						
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[3]")).click();
					}}
					
					
					
					//Allow Attachment Checkbox
					if(checkBox4.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[4]")).size()!=0) {
						
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[4]")).click();
					}}
					
					
					
					//Weight Checkbox
					if(checkBox5.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[5]")).size()!=0) {
						
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[5]")).click();
					}}
					
					if(driver.findElements(By.xpath("(//*[@class='slds-form-element__label'])[6]")).size()!=0) {
						String label=driver.findElement(By.xpath("(//*[@class='slds-form-element__label'])[6]")).getText();
						if(label.equalsIgnoreCase("Weight")) {
							driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[2]/input")).sendKeys("6.2");
						}
					}
					
					//Question Option==Radio
					//=============if(driver.getPageSource().contains("Question Options")) {
									//if(driver.findElements(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[2]/input")).size()!=0) {
									//driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[2]/input")).sendKeys("Question Option1");
									//Thread.sleep(4000);
					//==============	}}
					
					//Score check box
					if(checkBox6.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-checkbox_faux'])[6]")).size()!=0) {
						
						driver.findElement(By.xpath("(//*[@class='slds-checkbox_faux'])[6]")).click();
					}}
					//==============Background Color===not working
						//if(driver.findElements(By.xpath("(//*[@id='input-5'])")).size()!=0) {
						//driver.findElement(By.xpath("(//*[@id='input-5'])")).clear();
						//driver.findElement(By.xpath("(//*[@id='input-5'])")).sendKeys("#5ebbff");
					//}
					
					//Text Color====not working
						//if(driver.findElements(By.xpath("(//*[@id='input-6'])")).size()!=0) {
						//driver.findElement(By.xpath("(//*[@id='input-6'])")).click();
						//driver.findElement(By.xpath("(//*[@id='input-6'])")).clear();
						//driver.findElement(By.xpath("(//*[@id='input-6'])")).sendKeys("#bd35bd");
					//=============}
					if(checkBox5.equalsIgnoreCase("1")) {
					if(driver.findElements(By.xpath("(//*[@class='slds-select_container'])[2]/select")).size()!=0){
						driver.findElement(By.xpath("(//*[@class='slds-select_container'])[2]/select")).click();
					Select docType1a= new Select(driver.findElement(By.xpath("(//*[@class='slds-select_container'])[2]/select")));
					docType1a.selectByValue(Currency);
					}}
					
					if(driver.findElements(By.xpath("(//*[@class='slds-form-element slds-m-top_small'])[1]/label")).size()!=0) {
						String element = driver.findElement(By.xpath("(//*[@class='slds-form-element slds-m-top_small'])[1]/label")).getText();
						if(element.equalsIgnoreCase("Background Color")) {
							driver.findElement(By.xpath("(//*[@class='slds-form-element__control'])[6]//input")).sendKeys("#0b7477");
						}
					}
					
					if(driver.findElements(By.xpath("(//*[@class='slds-form-element slds-m-top_small'])[2]/label")).size()!=0) {
						String element = driver.findElement(By.xpath("(//*[@class='slds-form-element slds-m-top_small'])[2]/label")).getText();
						if(element.equalsIgnoreCase("Text Color")) {
							driver.findElement(By.xpath("(//*[@class='slds-form-element__control'])[7]//input")).sendKeys("#0b7477");
						}
					}
					
					if(driver.findElements(By.xpath("(//*[@class='slds-select_container'])[3]/select")).size()!=0){
						driver.findElement(By.xpath("(//*[@class='slds-select_container'])[3]/select")).click();
						Select docType2= new Select(driver.findElement(By.xpath("(//*[@class='slds-select_container'])[3]/select")));
						docType2.selectByValue(decimalLimit);
					}
					//Save================
					if(driver.findElements(By.xpath("//*[@class='slds-button slds-button_neutral slds-button slds-button_brand']")).size()!=0){
						driver.findElement(By.xpath("//*[@class='slds-button slds-button_neutral slds-button slds-button_brand']")).click();
					}
					
					if(driver.findElements(By.xpath("(//*[@class='slds-button slds-button_brand'])[3]")).size()!=0){
						driver.findElement(By.xpath("(//*[@class='slds-button slds-button_brand'])[3]")).click();
						Thread.sleep(3000);}
					
					if(driver.findElements(By.xpath("//*[@class='slds-button slds-button_brand']")).size()!=0){
					driver.findElement(By.xpath("//*[@class='slds-button slds-button_brand']")).click();
					Thread.sleep(3000);}
					
				}
				}
				/*String content = className+","+"Pass\n";
				bw.write(content);
				bw.flush();
				bw.close();*/
				//Publish--------CR--publish button is removed
				/*driver.findElement(By.xpath("(//*[@class='slds-button slds-button_neutral'])[2]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//*[@class='slds-button slds-button_neutral slds-button slds-button_brand'])")).click();	
				Thread.sleep(10000);*/
				
				//=================================================================================================================================
				
				//===========               CLONE             ===================================
				//===========                      Edit Template            =============================================
				/*driver.findElement(By.xpath("//*[@class='slds-lookup__search-input slds-input input uiInput uiInputText uiInput--default uiInput--input']")).sendKeys(FormName);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//table[@class='slds-table slds-table_bordered slds-border_left slds-border_right slds-table_resizable-cols slds-table_fixed-layout']/tbody/tr[1]/td[3]/button[2]")).click();
				Thread.sleep(8000);
				//===========                      Edit Template            =============================================
				driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[1]/input")).click();
				driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[1]/input")).clear();
				driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[1]/input")).sendKeys("Test");
				Thread.sleep(10000);
				driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[1]/input")).clear();
				driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[1]/input")).sendKeys(FormName+1);
				
				driver.findElement(By.xpath("(//*[@class='slds-form-element__control slds-grow'])[2]/input")).sendKeys(Version);
				driver.findElement(By.xpath("(//*[@class='slds-button slds-button_brand'])[4]")).click(); //Click Save
*/       /* } catch (Exception e) {
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

		}	*/			
	}	
}

