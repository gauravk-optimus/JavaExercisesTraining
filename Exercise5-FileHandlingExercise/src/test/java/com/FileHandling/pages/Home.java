package com.FileHandling.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.FileHandling.executor.Keywords;

public class Home {

	public static final String VALIDPHONENUMBER = "9891234567";
	private static final String FILE_NAME = "Recommended Plans.xlsx";
	private static final String TAB_NAME = "Recommended Plan";
	private static final String HEADER_1 = "Plan Price";
	private static final String HEADER_2 = "Plan Type";
	private static final String HEADER_3 = "Plan Validity";
	private static final String PLAN_TYPE = "Full Talktime";
	
	Logger logger;
	Keywords keywords;

	public Home() {
		logger = Logger.getLogger(Home.class);
		keywords = new Keywords();
	}

	@FindBy(xpath = "//*[@id='wrapper']/div/div/div/div[1]/div[2]/form/div[1]/div[1]/input")
	private WebElement phoneNumber;

	@FindBy(xpath = "//*[@id='wrapper']/div/div/div/div[1]/div[2]/form/div[3]/button")
	private WebElement proceedButton;

	@FindBy(xpath = "//*[@id='wrapper']/div/div[2]/div/div/div[2]/button")
	private WebElement viewAllPlan;
	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[2]")
	private List<WebElement> recommenedPlans;

	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[2]/ul/li/p")
	private List<WebElement> planAmount;

	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[2]/ul/li/div/p[3]/span[2]")
	private List<WebElement> planValidity;

	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[2]/ul/li/div/p[2]")
	private List<WebElement> planTalkTime;

	/**
	 * entering phone number
	 * 
	 */
	public void enterPhoneNumberForRecharge() {

		logger.info("Entering Phone Number for Recharge");
		try {
			keywords.sendTextInTextBox(phoneNumber, VALIDPHONENUMBER);
			if (VALIDPHONENUMBER.length() != 10) {
				logger.info("Invalid phone number entered: " + VALIDPHONENUMBER);
				System.out.println(phoneNumber.getText());
				throw new Exception();
			}
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Phone number not entered or invalid");
			Assert.fail();
		}
	}

	/**
	 * Navigate to recommended plans
	 * 
	 */
	public void navigateToRecommendedPlans() {

		logger.info("Navigating to recommended plans");
		try {
			keywords.clickButtonFluently(proceedButton);
			keywords.clickButtonFluently(viewAllPlan);
			logger.info("Navigated to Recommended Plans");
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to navigate");
			Assert.fail();
		}
	}

	/**
	 * Read Data from freecharge and copy to excel sheet
	 * 
	 */
	public void copyPlansToExcel() {
		logger.info("Reading and copying plans to excel sheet") ;
		try {			
			keywords.fetchDataToList(planAmount, planValidity, planTalkTime);
			keywords.writeDataToExcel(FILE_NAME, TAB_NAME, HEADER_1, HEADER_2, HEADER_3);
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to copy plans");
			Assert.fail();
		}
	}
	/**
	 * entering phone number
	 * 
	 */
	public void colorFullTalkTimeCells() {

		logger.info("Filling FullTalkTime Cells with blue color");
		try {			
			keywords.chnageCellColor(FILE_NAME, TAB_NAME, PLAN_TYPE);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Phone number not entered or invalid");
			Assert.fail();
		}
	}
}