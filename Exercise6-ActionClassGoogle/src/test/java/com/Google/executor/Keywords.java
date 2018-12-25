package com.Google.executor;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.Google.utils.PropertiesReader;

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
	 * Navigating to the particular URL based on the data in config.properties
	 * 
	 * @throws Exception
	 */
	public void dragNdrop(WebElement From, WebElement To) throws Exception {
		logger.info("Attempting to drag drop");
		try {
			Actions builder = new Actions(Keywords.driver);
			builder.dragAndDrop(From, To).perform();
		} catch (Exception exception) {
			logger.info("Unable to to drag drop or element not found");
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
	public void sendTextInTextBox(WebElement element, String value)
			throws Exception {

		try {
			logger.info(WRITE_TEXT + value);
			element.clear();
			element.sendKeys(value);
			logger.info(value + " entered");
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			// Assert.fail(exception.getMessage());
			throw new Exception();
		}
	}

	/**
	 * 
	 * @param element
	 *            contains WebElement of text box.
	 * @throws Exception
	 */
	public void selectText(WebElement element) throws Exception {

		try {
			logger.info("Highlighting the Text");
			element.sendKeys(Keys.CONTROL + "a");
			logger.info("Text is highlighted");
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			// Assert.fail(exception.getMessage());
			throw new Exception();
		}
	}

	/**
	 * 
	 * @param element
	 *            contains WebElement of text box.
	 * @throws Exception
	 */
	public void rightClickUndo(WebElement element) throws Exception {

		try {
			logger.info("Doing Undo operation");
			Actions action = new Actions(Keywords.driver);
			action.contextClick(element).sendKeys(Keys.ARROW_DOWN)
					.sendKeys(Keys.RETURN).build().perform();
			logger.info("Text Removed from the textbox by Undo");
		} catch (Exception exception) {
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
}