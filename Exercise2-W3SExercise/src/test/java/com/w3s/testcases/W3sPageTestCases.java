package com.w3s.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.w3s.executor.Keywords;
import com.w3s.pages.W3s;

public class W3sPageTestCases extends BaseClass {

	private final String Website_URL = "URL";

	private final String STARTING_TESTCASE = "**********************Starting test cases *********************";
	Logger logger;
	Keywords keywords;
	W3s w3s = new W3s();

	public W3sPageTestCases() {
		logger = Logger.getLogger(W3sPageTestCases.class);
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

	@Test
	public void verifyDropdownValue() {
		logger.info(STARTING_TESTCASE);
		w3s = PageFactory.initElements(Keywords.driver, W3s.class);
		w3s.dropdownVolvo();
		Keywords.passValues.put(
				Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Recharge initiated successfully");
	}

	@AfterMethod
	public void closeBrowser() {
		logger.info("Closing the browser");
		keywords.closeBrowser();
	}

}
