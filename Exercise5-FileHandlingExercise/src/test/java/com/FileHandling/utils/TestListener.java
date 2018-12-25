package com.FileHandling.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.FileHandling.executor.Keywords;

/**
 * This class is for defining methods relating to capturing screenshots,
 * HTMLdumps and other logger activites
 * 
 * @author Gaurav
 */
public class TestListener extends TestListenerAdapter {

	Logger logger = Logger.getLogger(TestListener.class);

	private static String SCREENSHOT_PATH = null;
	private static String ARTIFACTS_PATH = "\\artifacts\\Screenshots\\";
	private static String EXTENSION = ".png";
	private static String DATE_FORMAT = "dd-MM-yyyy_HH-mm-ss";
	private static String SCREEN_CAPTURE = "Capturing the screen shot for the failed test";
	private static String ERROR_SYMBOL = " *********";
	private static String TEST_CASE_FAILURE = "***** Error test has failed ";
	private static String TEST_START = "Starting test case ";
	private static String TEST_SKIPPED = "Test SKIPPED ";

	static int screenshotCounter = 0;
	static ArrayList<String> screenShotPaths = new ArrayList<String>();

	/**
	 * Capture Screenshot on test failure
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		SCREENSHOT_PATH = System.getProperty("user.dir") + ARTIFACTS_PATH;
		logger.info(TEST_CASE_FAILURE + result.getName() + ERROR_SYMBOL);
		captureScreenshotOnFailure(result.getMethod().getMethodName());
	}

	/**
	 * close the driver after the test case got passed
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		Keywords keywords=new Keywords();
		keywords.closeBrowser();
	}
	/**
	 * 
	 */
	@Override
	public void onTestStart(ITestResult result) {
		logger.info(TEST_START + result.getName() + ERROR_SYMBOL);
	}

	/**
	 * 
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info(TEST_SKIPPED + result.getName() + ERROR_SYMBOL);
	}

	/**
	 * This method capture the screenshot and save with the method Name
	 * 
	 * @param methodName
	 */
	public String captureScreenshotOnFailure(String methodName) {
		logger.info(SCREEN_CAPTURE);
		try {
			File scrFile = new File(SCREENSHOT_PATH);

			scrFile = ((TakesScreenshot) Keywords.driver)
					.getScreenshotAs(OutputType.FILE);
			SCREENSHOT_PATH = SCREENSHOT_PATH
					+ methodName
					+ "_"
					+ new SimpleDateFormat(DATE_FORMAT).format(Calendar
							.getInstance().getTime()) + EXTENSION;
			FileUtils.copyFile(scrFile, new File(SCREENSHOT_PATH));
			screenShotPaths.add(SCREENSHOT_PATH);
			screenshotCounter++;
		} catch (Exception exception) {
			logger.warn(exception);
		}
		return SCREENSHOT_PATH;
	}

}
