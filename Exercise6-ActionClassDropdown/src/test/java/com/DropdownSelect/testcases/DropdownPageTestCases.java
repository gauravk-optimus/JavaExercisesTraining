package com.DropdownSelect.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.DropdownSelect.executor.Keywords;
import com.DropdownSelect.pages.Dropdown;

public class DropdownPageTestCases extends BaseClass {

	Logger logger;
	Keywords keywords;
	Dropdown dropdown;

	private final String Website_URL = "URL";
	private final String STARTING_TESTCASE = "**********************Starting test cases *********************";

	public DropdownPageTestCases() {
		logger = Logger.getLogger(DropdownPageTestCases.class);
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
	 * Verify dropdown selection operation
	 */
	@Test(description = "User is able select a value from dropdown", priority = 1)
	public void rechargeInitiate(ITestContext context) {
		logger.info("Initiating Recharge");
		dropdown = PageFactory.initElements(Keywords.driver, Dropdown.class);
		dropdown.dropdownSelect();
		keywords.captureScreenshot();
		Keywords.passValues.put(
				Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Test case run successfully");
	}

	@AfterMethod
	public void closeBrowser() {
		logger.info("Closing the browser");
		keywords.closeBrowser();
	}
}
