package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.log4j.Logger;

public class OpportunityTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
	private Logger LOG = Logger.getLogger(OpportunityTest.class);
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://login.salesforce.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test
  public void testOpportunity() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.xpath("//div[@id='username_container']/input")).clear();
    driver.findElement(By.xpath("//div[@id='username_container']/input")).sendKeys("shamshuddink8@gmail.com");
    driver.findElement(By.xpath("//form[@id='login_form']/input")).clear();
    driver.findElement(By.xpath("//form[@id='login_form']/input")).sendKeys("Sharukh@345");
    driver.findElement(By.xpath("//form[@id='login_form']/input[2]")).click();
    driver.findElement(By.xpath("//li[@id='Opportunity_Tab']/a")).click();
    driver.findElement(By.xpath("//form[@id='hotlist']/table/tbody/tr/td[2]/input")).click();
	driver.findElement(By.xpath("//input[@title='Continue']")).click();
    driver.findElement(By.xpath("//div[@id='ep']/div[2]/div[3]/table/tbody/tr[3]/td[2]/div/input")).clear();
    driver.findElement(By.xpath("//div[@id='ep']/div[2]/div[3]/table/tbody/tr[3]/td[2]/div/input")).sendKeys("Demo-Test");
    new Select(driver.findElement(By.xpath("//div[@id='ep']/div[2]/div[3]/table/tbody/tr[4]/td[4]/div/span/select"))).selectByVisibleText("Closed Won");
    driver.findElement(By.xpath("//td[@id='topButtonRow']/input")).click();
  }
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
