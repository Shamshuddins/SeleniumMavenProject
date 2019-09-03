package com.GAP.WS1_0;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.GAP.WS1_0.Helper.Helper;
import com.GAP.WS1_0.Helper.OsUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import jxl.read.biff.BiffException;

public class BaseClass 
{
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static WebDriver driver;
	protected static final String FILENAME = System.getProperty("user.dir") +   File.separator + "Reports" +  File.separator + "Result"+new Date().getTime()+".csv";
	String dataPath = Helper.getProjectConfigPath("Report");
	private static final String OS_VERSION = OsUtil.getArchitecture();

	private static final String OS_NAME = OsUtil.getOsName();

	private String getFirefoxDriverPath() {
		String driverPath = System.getProperty("user.dir") + File.separator+ "webdrivers" + File.separator + "chrome" + File.separator;
		if(OS_NAME.equalsIgnoreCase("linux")){
			driverPath +=  "linux";
			if(OS_VERSION.equals("32")) {
				driverPath += File.separator + "32" + File.separator + "chromedriver";
//				"D:\\VC_checkouts\\git\\selenium_maven\\src\\test\\resources\\webdrivers\\firefox\\linux\\32\\geckodriver"
			} else {
				driverPath += File.separator + "64" + File.separator + "chromedriver";
			}
		}else{
			driverPath +=  "windows" + File.separator + "chromedriver.exe"; 
		}
		File driverFile = new File(driverPath);
		driverFile.setExecutable(true);
		return driverPath;
	}

	@BeforeSuite
	public void Setup() throws InterruptedException, BiffException, IOException
	{
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")  +  File.separator + "Reports" + File.separator + "Automation_Report_"+new Date().getTime()+".html");		
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") +  File.separator + "Reports" + File.separator + "extent-config.xml");
		extent = new ExtentReports();		
		extent.attachReporter(htmlReporter);		
	}

	@BeforeMethod
	public void Initiate_Browser() throws InterruptedException, BiffException, IOException
	{		
		/**Launch FireFox browser*/		
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Girikon\\Downloads\\geckodriver-v0.21.0-win64\\geckodriver.exe");
//		System.setProperty("webDriver.gecko.driver",System.getProperty("user.dir") + "geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver", getFirefoxDriverPath());
		System.setProperty("webdriver.chrome.driver", "D:\\Automation\\GAP\\chromedriver.exe");
		//System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		//System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");		
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);			
		
	}

	@BeforeTest
	public void startReport() throws IOException{
		//ExtentReports(String filePath,Boolean replaceExisting) 
		//filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
		//replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
		//True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
		//False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
		//extent.addSystemInfo("Environment","Environment Name")
		extent.setSystemInfo("Host Name", "Girikon T");
		extent.setSystemInfo("Environment", "UAT");
		extent.setSystemInfo("User Name", "Rajesh Gupta");
		//loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
		//You could find the xml file below. Create xml file in your project and copy past the code mentioned below
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(FILENAME, false));
		String content = "Method Name"+","+"Result\n";
		bw2.write(content);
		bw2.flush();
		bw2.close();
	}

	@AfterMethod
	public void getResult(ITestResult result){
		logger.pass(MarkupHelper.createLabel(result.getName()+" - Execution Completed", ExtentColor.GREEN));
				if(result.getStatus() == ITestResult.FAILURE){
					logger.fail(MarkupHelper.createLabel(result.getName()+" - Test case Failed", ExtentColor.RED));
					logger.fail(result.getThrowable());
				}else if(result.getStatus() == ITestResult.SUCCESS){
					logger.pass(MarkupHelper.createLabel(result.getName()+" - Execution Completed", ExtentColor.GREEN));
				}
	}


	@AfterSuite
	public void Teardown(){
		extent.flush();
		//driver.quit();
	}
}
