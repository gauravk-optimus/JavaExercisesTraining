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
	 * Initiating Re charge with random numbers
	 */
	@Test(description = "User is able initiate re-charge", priority = 1)
	public void randomRechargeInitiate(ITestContext context) {
		logger.info("Initiating Recharge");
		home = PageFactory.initElements(Keywords.driver, Home.class);		
		home.enterRandomPhoneNumber();	
		home.selectOperator();
		home.clickProceedButton();
		home.selectRandomPlan();
		home.freechargeLogin();
		Keywords.passValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Recharge initiated successfully");
	}
	
	@AfterMethod
	public void closeBrowser() {
		logger.info("Closing the browser");
		keywords.closeBrowser();
	}
	
}
