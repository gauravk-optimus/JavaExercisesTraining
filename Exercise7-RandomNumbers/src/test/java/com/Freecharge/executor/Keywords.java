package com.Freecharge.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
//import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import com.Freecharge.utils.PropertiesReader;
import com.google.common.base.Function;

/**
 * This is the class to initialize the browser and to define common
 * verification/assertion methods
 * 
 * @author Gaurav
 * 
 */
public class Keywords {
	static Logger logger = Logger.getLogger(Keywords.class);

	public static WebDriver driver;
	private static final String FIREFOX = "Firefox";
	private static final String WAIT_PROPERTY = "wait";
	private static final String POLLING = "polling";
	private static final String BROWSER_TYPE = "browserType";
	private static final String CHROME = "Chrome";
	private static final String INTERNET_EXPLORER = "IE";
	private static final String STARTING_FIREFOX = "Starting Firefox browser";
	private static final String STARTING_CHROME = "Starting Chrome browser";
	private static final String STARTING_INTERNET_EXPLORER = "Starting Internet Explorer browser";
	private static final String INVALID_BROWSER_NAME = "Please enter a valid browser name. Currently only Firefox, Chrome and IE are supported";
	private static final String OPENING_URL = "Opening URL: ";
	private static final String ACTUAL_VALUE = "Actual value is: ";
	private static final String EXPECTED_VALUE = "Expected value is: ";
	private static final String CLOSE_BROWSER = "Closing the browser";
	private static final String IMPLICIT_WAIT = "implicitWait";
	private static final String START_BROWSER = "Inside startBrowser Method";
	private static final String NAVIGATE_TO = "Inside navigateTo Method";
	private static final String WRITE_TEXT = "Entering text is ";
	private static final String SELECT_VALUE = "Selecting value ";
	private static final String VERIFY_DROPDOWN = "Verifying Selected value ";
	public static HashMap<String, String> passValues = new HashMap<String, String>();
	public static HashMap<String, String> failValues = new HashMap<String, String>();
	public static long startTime;
	public static long endTime;

	/**
	 * Opens a particular browser based on the data in config.properties
	 * 
	 * @return WebDriver driver reference
	 * @throws Exception
	 */
	public void startBrowser() throws Exception {
		logger.info(START_BROWSER);
		try {
			if (PropertiesReader.readProperty(BROWSER_TYPE).equalsIgnoreCase(
					FIREFOX)) {
				// created firefox instance
				driver = new FirefoxDriver();
				logger.info(STARTING_FIREFOX);
			} else if (PropertiesReader.readProperty(BROWSER_TYPE)
					.equalsIgnoreCase(CHROME)) {
				// created chrome instance
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "\\Support Library\\chromedriver.exe");
				driver = new ChromeDriver();
				logger.info(STARTING_CHROME);
			} else if (PropertiesReader.readProperty(BROWSER_TYPE)
					.equalsIgnoreCase(INTERNET_EXPLORER)) {
				// created Internet Explorer instance
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir")
								+ "\\Support Library\\IEDriverServer.exe");
				DesiredCapabilities capabilities = DesiredCapabilities
						.internetExplorer();
				capabilities.setCapability("ie.ensureCleanSession", true);
				driver = new InternetExplorerDriver(capabilities);
				logger.info(STARTING_INTERNET_EXPLORER);
			} else {
				logger.warn(INVALID_BROWSER_NAME);
			}
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Navigating to the particular URL based on the data in config.properties
	 * 
	 * @throws Exception
	 */
	public void navigateTo(String Url) throws Exception {
		logger.info(NAVIGATE_TO);
		driver.manage().window().maximize();
		driver.manage()
				.timeouts()
				.implicitlyWait(
						Integer.parseInt(PropertiesReader
								.readProperty(IMPLICIT_WAIT)), TimeUnit.SECONDS);
		try {
			logger.info(OPENING_URL + PropertiesReader.readProperty(Url));
			driver.get(PropertiesReader.readProperty(Url));
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Clicking the Button
	 * 
	 * @param element
	 *            contains WebElement
	 * @throws Exception
	 */
	public void clickButtonFluently(WebElement element) throws Exception {
		try {

			new FluentWait<>(element)
					.withTimeout(
							Integer.parseInt(PropertiesReader
									.readProperty(WAIT_PROPERTY)),
							TimeUnit.SECONDS)
					.pollingEvery(
							Integer.parseInt(PropertiesReader
									.readProperty(POLLING)),
							TimeUnit.MILLISECONDS)
					.ignoring(WebDriverException.class)
					.until(new Function<WebElement, Boolean>() {
						@Override
						public Boolean apply(WebElement element) {
							element.click();
							return true;
						}
					});
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * 
	 * @param element
	 *            contains WebElement of text box
	 * @param value
	 *            contain input data from user
	 * @throws Exception
	 */
	public void sendTextInTextBox(WebElement element, String value)
			throws Exception {

		try {
			logger.info(WRITE_TEXT + value);
			element.clear();
			element.sendKeys(value);
			logger.info("Text is entered");
		} catch (Exception exception) {
			logger.info("Unable to enter");
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * 
	 * @param element
	 *            contains WebElement of text box
	 * @param value
	 *            contain number of random digits
	 * @throws Exception
	 */
	public void sendRandomNumberInTextBox(WebElement element, int value)
			throws Exception {

		try {
			logger.info("Entering random number");
			Random randomGenerator = new Random();
			int n = (value + 1) / 10;
			int randomInt = 0;
			while (randomInt < n) {
				randomInt = randomGenerator.nextInt(value);
			}
			String s = Integer.toString(randomInt);
			element.sendKeys(s);
			logger.info("Random number is entered");
		} catch (Exception exception) {
			logger.info("Unable to enter random number");
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * closing the browser
	 */
	public void closeBrowser() {
		logger.info(CLOSE_BROWSER);
		driver.quit();
	}

	/**
	 * Select random value in the dropdown
	 * 
	 * @param element
	 *            contains WebElement of text box.
	 * @throws Exception
	 */
	public void selectRandomValueFromDropdown(WebElement element)
			throws Exception {

		try {
			logger.info("Selecting random value");
			Select objSel = new Select(element);
			List<WebElement> weblist = objSel.getOptions();
			int iCnt = weblist.size();
			Random num = new Random();
			int iSelect = num.nextInt(iCnt);
			objSel.selectByIndex(iSelect);
			logger.info("Randomly " + element.getAttribute("value")
					+ " is selected");
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Select value in the dropdown
	 * 
	 * @param element
	 *            contains WebElement of text box.
	 * @param value
	 *            contain input data from user
	 * @throws Exception
	 */
	public void selectValueFromDropdown(WebElement element, String value)
			throws Exception {

		try {
			logger.info(SELECT_VALUE + value);
			element.sendKeys(value);
			logger.info(element.getAttribute("value") + " is selected");
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Verify the selected value in the dropdown
	 * 
	 * @param element
	 *            contains WebElement to get the selected value of the dropdown
	 * @param value
	 *            contains the expected selected item of the dropdown
	 * @throws Exception
	 */
	public void verifyDropdownSelectedValue(WebElement element, String value)
			throws Exception {
		try {
			logger.info(VERIFY_DROPDOWN);
			Thread.sleep(1000);
			Select DropDown = new Select(element);
			Thread.sleep(1000);
			String SelectedText = DropDown.getFirstSelectedOption().getText();
			Thread.sleep(1000);
			if (!SelectedText.contains(value)) {
				throw new Exception();
			}
			logger.info(ACTUAL_VALUE + SelectedText);
			logger.info(EXPECTED_VALUE + value);
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Select random element from the webelement list
	 * 
	 * @param element
	 *            contains WebElement list select a value
	 * @throws Exception
	 */
	public void selectRandomElement(List<WebElement> element,
			String locatorStart, String locatorEnd) throws Exception {
		try {
			logger.info("Selecting random Element");
			int size = element.size();
			Random num = new Random();
			int value = num.nextInt(size);
			String locator = locatorStart + value + locatorEnd;
			driver.findElement(By.xpath(locator)).click();
			logger.info("Selected Value is ");
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}
}