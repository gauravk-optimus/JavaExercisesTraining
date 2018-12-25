package com.FileHandling.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.FileHandling.executor.Keywords;
import com.FileHandling.pages.Home;

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
	 * Copy all the plans to Excel sheet
	 */
	@Test(description = "User is able add all the recommended plan to a excel sheet", priority = 1)
	public void rechargeInitiate(ITestContext context) {
		logger.info("Coppying plans to excel initiated");
		home = PageFactory.initElements(Keywords.driver, Home.class);		
		home.enterPhoneNumberForRecharge();	
		home.navigateToRecommendedPlans();
		home.copyPlansToExcel();
		home.colorFullTalkTimeCells();
		Keywords.passValues.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Recharge initiated successfully");
	}
	
	@AfterMethod
	public void closeBrowser() {
		logger.info("Closing the browser");
		keywords.closeBrowser();
	}
	
}
