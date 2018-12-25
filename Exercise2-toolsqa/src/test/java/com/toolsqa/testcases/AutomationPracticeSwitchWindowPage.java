package com.toolsqa.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toolsqa.executor.Keywords;

import com.toolsqa.pages.SwitchWindows;
import com.toolsqa.pages.TimingAlert;

public class AutomationPracticeSwitchWindowPage extends BaseClass {
	Logger logger;
	Keywords keywords;
	SwitchWindows switchwindows;
	TimingAlert timingAlert;
	
	private final String Website_URL = "URL";
	private final String STARTING_TESTCASE = "**********************Starting test cases *********************";
	
	public AutomationPracticeSwitchWindowPage(){
		logger = Logger.getLogger(AutomationPracticeSwitchWindowPage.class);
		keywords = new Keywords();		
	}
	
	@BeforeMethod
	public void initialSetUp() {
		PropertyConfigurator.configure("log4j.properties");
		logger.info(STARTING_TESTCASE);
		try {
			keywords.startBrowser();
			keywords.navigateTo(Website_URL);
		} catch (Exception e) {
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to start browser");
			Assert.fail();
		}
	}

	/**
	 * Verify text after switching the window
	 * @throws InterruptedException 
	 */
	@Test(description = "Verify text after switching to new window", priority = 1)
	public void verifyAllBrands(ITestContext context) throws InterruptedException {
		logger.info("Verifying text after switching the window");
		switchwindows = PageFactory.initElements(Keywords.driver, SwitchWindows.class);		
		switchwindows.switchToNewWindow();
		switchwindows.verifyText();
		keywords.captureScreenshot();
		Keywords.failValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Unable to verify the text in new window");
	}	
	
	/**
	 * Verify accept alert operation
	 * 
	 */
	@Test(description = "Verifying accept alert operation", priority = 1)
	public void acceptAlert(){
		logger.info("Verify accept alert operation");
		timingAlert = PageFactory.initElements(Keywords.driver, TimingAlert.class);
		timingAlert.accpetAlert();
		keywords.captureScreenshot();
		Keywords.failValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Unable to accept the timimg alert");
	}
		
	@AfterMethod
	public void closeBrowser() {
		logger.info("Closing the browser");
		keywords.closeBrowser();
	}
	
}
