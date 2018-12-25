package com.Freecharge.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.Freecharge.executor.Keywords;

public class Home {

	public static final String START_DIGIT = "9891";
	public static final int RANDOM_DIGIT = 999999;
	public static final String SELECT_OPERATOR = "Idea";
	public static final String PLAN_LOCATOR_START = "//*[@class='_2cvp1']/li[";
	public static final String PLAN_LOCATOR_END = "]/p[3]";
	public static final String USERNAME = "9891234567";
	public static final String PASSWORD = "optimus";

	Logger logger;
	Keywords keywords;

	public Home() {
		logger = Logger.getLogger(Home.class);
		keywords = new Keywords();
	}

	@FindBy(xpath = "//*[@id='wrapper']/div/div/div/div[1]/div[2]/form/div[1]/div[1]/input")
	private WebElement phoneNumber;

	@FindBy(xpath = "//*[@id='wrapper']/div/div/div/div[1]/div[2]/form/div[1]/div[3]/select")
	private WebElement selectOperator;

	@FindBy(xpath = "//*[@id='wrapper']/div/div/div/div[1]/div[2]/form/div[3]/button")
	private WebElement proceedButton;

	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[2]/div/div/div[1]/ul/li/p[3]")
	private List<WebElement> plans;

	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[4]/div/div/div[2]/div[1]/div/form/div/div[1]/input")
	private WebElement username;

	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[4]/div/div/div[2]/div[1]/div/form/div/div[2]/input")
	private WebElement password;

	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[4]/div/div/div[2]/div[1]/div/form/div/button")
	private WebElement loginBtn;

	/**
	 * entering random phone number
	 * 
	 */
	public void enterRandomPhoneNumber() {
		logger.info("Entering Random Phone Number for Recharge");
		try {
			keywords.sendTextInTextBox(phoneNumber, START_DIGIT);
			keywords.sendRandomNumberInTextBox(phoneNumber, RANDOM_DIGIT);

		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to enter random Phone number");
			Assert.fail();
		}
	}

	/**
	 * select an operator
	 * 
	 */
	public void selectOperator() {

		logger.info("Selecting an operator");
		try {
			keywords.selectRandomValueFromDropdown(selectOperator);
			keywords.selectValueFromDropdown(selectOperator, SELECT_OPERATOR);

		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to select operator " + SELECT_OPERATOR);
			Assert.fail();
		}
	}

	/**
	 * Click Proceed Button
	 * 
	 */
	public void clickProceedButton() {

		logger.info("Clicking on proceed button");
		try {
			keywords.clickButtonFluently(proceedButton);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to click on Proceed Button");
			Assert.fail();
		}
	}

	/**
	 * select random plan
	 * 
	 */
	public void selectRandomPlan() {
		logger.info("Selecting random plans");
		try {
			keywords.selectRandomElement(plans, PLAN_LOCATOR_START,
					PLAN_LOCATOR_END);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to select random plan");
			Assert.fail();
		}
	}

	/**
	 * login to freecharge
	 * 
	 */
	public void freechargeLogin() {
		logger.info("Logining In");
		try {
			keywords.sendTextInTextBox(username, USERNAME);
			keywords.sendTextInTextBox(password, PASSWORD);
			keywords.clickButtonFluently(loginBtn);
			logger.info("User is logged in");
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to login");
			Assert.fail();
		}
	}
}