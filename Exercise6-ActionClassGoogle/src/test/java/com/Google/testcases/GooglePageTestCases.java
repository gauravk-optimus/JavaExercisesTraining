package com.Google.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.Google.executor.Keywords;
import com.Google.pages.Google;

public class GooglePageTestCases extends BaseClass {

	Logger logger;
	Keywords keywords;
	Google google;

	private final String Website_URL = "URL";
	private final String STARTING_TESTCASE = "**********************Starting test cases *********************";

	public GooglePageTestCases() {
		logger = Logger.getLogger(GooglePageTestCases.class);
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
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to start browser");
			Assert.fail();
		}
	}

	/**
	 * Verifying undo operation after entering text
	 * 
	 * @throws Exception
	 */
	@Test(description = "User is able to remove text using undo", priority = 1)
	public void contextClickUndo(ITestContext context) throws Exception {
		logger.info("Undo operation test case started");
		google = PageFactory.initElements(Keywords.driver, Google.class);
		try {
			google.undoEnterText();
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to enter or remove text");
			Assert.fail();
		}
	}

	@AfterMethod
	public void closeBrowser() {
		logger.info("Closing the browser");
		keywords.closeBrowser();
	}
}
