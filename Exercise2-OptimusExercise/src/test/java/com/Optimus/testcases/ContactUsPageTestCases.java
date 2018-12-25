package com.Optimus.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Optimus.executor.Keywords;
import com.Optimus.pages.ContactUs;

public class ContactUsPageTestCases extends BaseClass{
	
	Logger logger;
	Keywords keywords;
	ContactUs contactUs;
	
	private final String Website_URL = "URL";
	private final String STARTING_TESTCASE = "**********************Starting test cases *********************";

	public ContactUsPageTestCases(){
		logger = Logger.getLogger(ContactUsPageTestCases.class);
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
	 * Verify all the brands in the bottom of the page
	 */
	
	@Test(description = "User is able initiate re-charge", priority = 1)
	public void rechargeInitiate(ITestContext context) {
		logger.info("Initiating Recharge");
		contactUs = PageFactory.initElements(Keywords.driver, ContactUs.class);					
		contactUs.contactUsMessage();
		keywords.captureScreenshot();
		Keywords.passValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Recharge initiated successfully");
	}
	
	
	@AfterMethod
	public void closeBrowser() {
		logger.info("Closing the browser");
		keywords.closeBrowser();
	}
	
}
