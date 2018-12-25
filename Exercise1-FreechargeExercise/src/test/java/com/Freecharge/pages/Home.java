package com.Freecharge.pages;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.Freecharge.executor.Keywords;

public class Home {
	public static final String[] BRANDS = { "MOBILE RECHARGE", "Airtel",
			"Aircel", "Vodafone", "BSNL", "Tata Docomo GSM", "Idea",
			"indicom Walky", "MTNL Delhi", "Reliance CDMA", "Reliance GSM",
			"Tata Indicom", "Telenor", "MTS", "Videocon", "Virgin CDMA",
			"Virgin GSM", "Tata Docomo CDMA", "DATA CARD RECHARGE",
			"Tata Photon", "MTS MBlaze", "MTS MBrowse", "Airtel", "BSNL",
			"Aircel", "MTNL Delhi", "Vodafone", "Idea", "MTNL Mumbai",
			"Tata Photon Whiz", "DTH(TV) RECHARGE", "Airtel Digital TV",
			"Reliance Digital TV", "Dish TV", "Tata Sky", "Sun Direct",
			"Videocon D2H", "POSTPAID", "Airtel Bill Payment",
			"BSNL Bill Payment", "Tata Docomo GSM Bill Payment",
			"Tata Docomo CDMA Bill Payment", "Idea Bill Payment",
			"Vodafone Bill Payment", "Reliance GSM Bill", "Reliance CDMA Bill",
			"About Us", "Support", "Contact Us", "Sitemap", "Geekery", "T & C",
			"Blog", "Android App", "Windows", "Mobile Site", "IOS", "", "", "",
			"", "", "", "", "" };
	public static final String INVALIDPHONENUMBER = "1234";
	public static final String VALIDPHONENUMBER = "9891234567";
	public static final String DEFAULT_OPERATOR = "Idea";
	public static final String SELECT_OPERATOR = "Idea";
	public static final String PRICE_VALUE = "";
	public static final int DROPDOWN_COUNT = 17;
	public static final int RECOMMENDED_PLAN_COUNT = 4;
	public static final String LOGIN = "LOGIN";
	public static final int RECOMMENDED_PLANS = 40;
	public static final String NAME = "Recommended Plans";
	public static final String PLAN = "Most Effective Plan";
	public static final String XPATH_START = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[2]/ul/li[";
	public static final String XPATH_END = "]";
	public static String ele;
	public static String PLAN_TYPE = "Full Talktime";
	public static String PLAN_VALIDITY = "Unlimited";
	
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

	@FindBy(xpath = "//*[@id='wrapper']/div/div/div/div[1]/div[2]/form/div[1]/div[2]/label[1]/span[1]")
	private WebElement defaultOption;

	@FindBy(xpath = "//*[@id='wrapper']/div/div/div/div[1]/div[2]/form/div[3]/button")
	private WebElement proceedButton;

	@FindBy(xpath = "//*[@id='wrapper']/div/div[2]/div/div/div[3]/div[1]/div/form/div/input")
	private WebElement priceField;

	@FindBy(xpath = "//*[@id='wrapper']/div/div[2]/div/div/div[1]/ul")
	private WebElement recommendedPlans;

	@FindBy(xpath = "//*[@id='wrapper']/div/div[3]/div/div[1]/span[2]")
	private WebElement fullTtTab;

	@FindBy(xpath = "//*[@id='wrapper']/div/div[3]/div/div[2]/ul/li[1]/p")
	private WebElement effectivePlan;

	@FindBy(xpath = "//*[@id='wrapper']/div/div[2]/div/div/div[2]/button")
	private WebElement viewAllPlan;

	@FindBy(xpath = "//*[@id='wrapper']/div/div[4]/div/div/div[2]/p/span[1]")
	private WebElement login;

	@FindBy(xpath = "//*[@id='app']/div/footer/div[1]")
	private WebElement footer;

	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[2]/ul/li")
	private List<WebElement> recommenedPlans;
	
	@FindBy(xpath = "html/body/div[2]/div/div[2]/div[2]/div/div[3]/div/div[2]/ul/li")
	private List<WebElement> recommendedPlansTypes;
	
	/**
	 * verifying brands on the homepage
	 * 
	 */
	public void verifyBrands() {

		logger.info("Verifying brands on the bottom of the page");
		try {
			keywords.verifyFooter(footer, BRANDS);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to verify footer, not found");
			Assert.fail();
		}
	}

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
	 * verifying the default selected option postpaid/prepaid
	 * 
	 */
	public void verifyDefaultOption() {

		logger.info("Verifying the default option postpaid/prepaid");
		try {
			keywords.verifyRadioSelected(defaultOption);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Default selected option is not prepaid or not found");
			Assert.fail();
		}
	}

	/**
	 * verifying the default selected operator
	 * 
	 */
	public void verifyDefaultOperator() {

		logger.info("Verifying the default operator");
		try {
			keywords.verifyDropdownSelectedValue(selectOperator,
					DEFAULT_OPERATOR);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Default selected operator is not " + DEFAULT_OPERATOR
							+ " or not found");
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
	 * select an operator
	 * 
	 */
	public void verifyValueOfPrice() {

		logger.info("Verifying the value of Price field");
		try {
			keywords.verifyTextContains(priceField, PRICE_VALUE);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Value of price field is not " + PRICE_VALUE);
			Assert.fail();
		}
	}

	/**
	 * count and verify the number of values in the dropdown
	 * 
	 */
	public void verifyNumberOfOperator() {
		logger.info("Verifying number of operators");
		try {
			keywords.verifyDropdownValueCount(selectOperator, DROPDOWN_COUNT);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to count the number of operators");
			Assert.fail();
		}
	}

	/**
	 * verify recommended plans
	 * 
	 */
	public void verifyRecommededPlansCount() {
		logger.info("Counting number of recommended plans");
		try {
			keywords.totalElementsInList(recommenedPlans, RECOMMENDED_PLANS, NAME);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Recommended plan count is not equal to expected");
			Assert.fail();
		}
	}

	/**
	 * select plan
	 * 
	 */
	public void selectPlan() {
		logger.info("Selecting effective plans");
		try {
			keywords.clickButtonFluently(viewAllPlan);
			keywords.selectEffectivePlan(recommendedPlansTypes, PLAN_TYPE, PLAN_VALIDITY );
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to select the plan");
			Assert.fail();
		}
	}
}