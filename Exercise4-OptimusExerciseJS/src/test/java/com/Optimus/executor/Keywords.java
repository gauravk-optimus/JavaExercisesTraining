package com.Optimus.executor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.Optimus.utils.PropertiesReader;

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
	private static final String BROWSER_TYPE = "browserType";
	private static final String CHROME = "Chrome";
	private static final String INTERNET_EXPLORER = "IE";
	private static final String STARTING_FIREFOX = "Starting Firefox browser";
	private static final String STARTING_CHROME = "Starting Chrome browser";
	private static final String STARTING_INTERNET_EXPLORER = "Starting Internet Explorer browser";
	private static final String INVALID_BROWSER_NAME = "Please enter a valid browser name. Currently only Firefox, Chrome and IE are supported";
	private static final String OPENING_URL = "Opening URL: ";
	private static final String CLOSE_BROWSER = "Closing the browser";
	private static final String IMPLICIT_WAIT = "implicitWait";
	private static final String START_BROWSER = "Inside startBrowser Method";
	private static final String NAVIGATE_TO = "Inside navigateTo Method";
	private static final String WRITE_TEXT = "Entering text is ";
	private static final String FETCH_TEXT = "Fetching the entered text";
	private static final String CHANGE_TEXT = "Changing the entered text";
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
	 * 
	 * @param element
	 *            contains WebElement of text box.
	 * @param value
	 *            contain input data from user
	 * @throws Exception
	 */
	public void sendTextInTextBoxJS(WebElement element, String value)
			throws Exception {

		try {
			logger.info(WRITE_TEXT + value);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='" + value + "'", element);
			logger.info("Text is entered using JS");
		} catch (Exception exception) {
			logger.info("Unable to write text or element not found");
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * This method is used for taking a screenshot within a method. Not to be
	 * confused with captureScreenshotOnFailure() under TestListener class
	 */
	public void captureScreenshot() {
		logger.info("Capture the Screen shot");
		File scrFile;
		try {
			String methodName = Thread.currentThread().getStackTrace()[2]
					.getMethodName();
			String GENERIC_SCREENSHOTPATH = System.getProperty("user.dir")
					+ "\\artifacts\\Screenshots\\";

			System.out.println("Capture the Screen shot" + methodName);

			scrFile = new File(GENERIC_SCREENSHOTPATH);

			scrFile = ((TakesScreenshot) Keywords.driver)
					.getScreenshotAs(OutputType.FILE);
			GENERIC_SCREENSHOTPATH = GENERIC_SCREENSHOTPATH
					+ methodName
					+ "_"
					+ new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss")
							.format(Calendar.getInstance().getTime()) + ".png";
			FileUtils.copyFile(scrFile, new File(GENERIC_SCREENSHOTPATH));

		} catch (Exception exception) {
			logger.warn(exception);
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
	 * 
	 * @param element
	 *            contains WebElement of text box.
	 * @param value
	 *            contain input data from user
	 * @throws Exception
	 */
	public void getEnteredTextJS(String element) throws Exception {

		try {
			logger.info(FETCH_TEXT);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String enteredValue = js.executeScript(
					"return document.getElementById('"+element+"').value")
					.toString();
			logger.info("Entered text in message box: " + enteredValue);
		} catch (Exception exception) {
			logger.info("Unable to get entered text");
			logger.fatal(exception);
			exception.printStackTrace();
			Assert.fail();
			throw new Exception();
		}
	}

	/**
	 * 
	 * @param element
	 *            contains WebElement of text box.
	 * @param value
	 *            contain input data from user
	 * @throws Exception
	 */
	public void changeTextJS(String element, String value) throws Exception {

		try {
			logger.info(CHANGE_TEXT);
			String a = "document.getElementById('"+element+"').value=''";
			String b = "document.getElementById('"+element+"').value='"
					+ value + "'";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(a);
			js.executeScript(b);
			String enteredText = js.executeScript(
					"return document.getElementById('"+element+"').value")
					.toString();
			logger.info("Text after changing: " + enteredText);
		} catch (Exception exception) {
			logger.info("Unable to change the entered textusing JS");
			logger.fatal(exception);
			exception.printStackTrace();
			Assert.fail();
			throw new Exception();
		}
	}
}