package com.DragNDrop.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.DragNDrop.executor.Keywords;
import com.DragNDrop.pages.Droppable;

public class DroppablePageTestCases extends BaseClass {

	Logger logger;
	Keywords keywords;
	Droppable droppable;

	private final String Website_URL = "URL";
	private final String STARTING_TESTCASE = "**********************Starting test cases *********************";

	public DroppablePageTestCases() {
		logger = Logger.getLogger(DroppablePageTestCases.class);
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
	 * Verify drag n drop
	 */
	@Test(description = "User is drag n drop", priority = 1)
	public void rechargeInitiate(ITestContext context) {
		logger.info("Attemptng Drag n Drop");
		droppable = PageFactory.initElements(Keywords.driver, Droppable.class);
		droppable.draggable();
		Keywords.passValues.put(
				Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Drag n drop done successfully");
	}

	@AfterMethod
	public void closeBrowser() {
		logger.info("Closing the browser");
		keywords.closeBrowser();
	}
}
