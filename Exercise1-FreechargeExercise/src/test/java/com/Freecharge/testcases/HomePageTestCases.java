package com.Freecharge.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Freecharge.executor.Keywords;
import com.Freecharge.pages.Home;

public class HomePageTestCases extends BaseClass{
	
	Logger logger;
	Keywords keywords;
	Home home;
	
	private final String Website_URL = "URL";
	private final String STARTING_TESTCASE = "**********************Starting test cases *********************";

	public HomePageTestCases(){
		logger = Logger.getLogger(HomePageTestCases.class);
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
	@Test(description = "Verifying all the brands in the bottom of the page", priority = 1)
	public void verifyAllBrands(ITestContext context) {
		logger.info("Verifying brands");
		home = PageFactory.initElements(Keywords.driver, Home.class);				
		home.verifyBrands();		
		keywords.captureScreenshot();
		Keywords.passValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Brand Verified on homepage");
	}
	/**
	 * Initiating Re charge
	 */
	//@Test(description = "User is able initiate re-charge", priority = 1)
	public void rechargeInitiate(ITestContext context) {
		logger.info("Initiating Recharge");
		home = PageFactory.initElements(Keywords.driver, Home.class);			
		home.enterPhoneNumberForRecharge();	
		home.verifyDefaultOption();
		home.verifyDefaultOperator();
		home.verifyNumberOfOperator();
		home.selectOperator();
		home.clickProceedButton();
		home.verifyValueOfPrice();
		home.verifyRecommededPlansCount();
		keywords.captureScreenshot();
		Keywords.passValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Recharge initiated successfully");
	}
	/**
	 * Select effective plan
	 */
	//@Test(description = "User is able select effective plan", priority = 1)
	public void selectEffectivePlan(ITestContext context) {
		logger.info("Selecting effective plan");
		home = PageFactory.initElements(Keywords.driver, Home.class);			
		home.enterPhoneNumberForRecharge();	
		home.clickProceedButton();
		home.selectPlan();
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
