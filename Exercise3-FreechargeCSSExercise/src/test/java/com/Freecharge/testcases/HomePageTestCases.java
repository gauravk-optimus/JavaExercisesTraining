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
	 * Counting number of Recommended plans
	 * @throws InterruptedException 
	 */
	@Test(description = "Counting number of Recommended plans", priority = 1)
	public void getPlanCount() throws InterruptedException {
		logger.info("Initiating Recharge");
		home = PageFactory.initElements(Keywords.driver, Home.class);					
		home.enterPhoneNumberForRecharge();	
		home.selectOperator();
		home.clickProceedButton();
		home.noOfRecommendedPlans();
		keywords.captureScreenshot();
		Keywords.passValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Recommended plans are counted");
	}
	/**
	 * Find effective plan
	 */
	@Test(description = "User is able find effective plan", priority = 1)
	public void getEffectivePlan(ITestContext context) {
		logger.info("Finding effective plan");
		home = PageFactory.initElements(Keywords.driver, Home.class);			
		home.enterPhoneNumberForRecharge();	
		home.clickProceedButton();
		home.findPlan();
		keywords.captureScreenshot();
		Keywords.passValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Test case successfull");
	}
	/**
	 * Find non FTT plan
	 */
	@Test(description = "Verify non FTT plan does not exist in FTT tab", priority = 1)
	public void verifyNonFTTPlan(ITestContext context) {
		logger.info("Findingting effective plan");
		home = PageFactory.initElements(Keywords.driver, Home.class);			
		home.enterPhoneNumberForRecharge();	
		home.clickProceedButton();
		home.getNonFTTPlan();
		keywords.captureScreenshot();
		Keywords.passValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"FTT tab does contain non FTT plans");
	}

	/**
	 * Counting ISD plans
	 */
	@Test(description = "Counting ISD plan in SPL/Rate Cutters tab", priority = 1)
	public void countNoOfIsdPlans() {
		logger.info("Counting ISD plan");
		home = PageFactory.initElements(Keywords.driver, Home.class);			
		home.enterPhoneNumberForRecharge();	
		home.clickProceedButton();
		home.getISDPlanCount();
		keywords.captureScreenshot();
		Keywords.passValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Recharge initiated successfully");
	}
	/**
	 * Verifying least validity plan
	 */
	@Test(description = "Verifying least validity plan", priority = 1)
	public void findLeastValidityPlan() {
		logger.info("Verifying least validity plan");
		home = PageFactory.initElements(Keywords.driver, Home.class);			
		home.enterPhoneNumberForRecharge();	
		home.clickProceedButton();
		home.getLeastValidityPlan();
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
