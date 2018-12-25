package com.w3s.executor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.google.common.base.Function;
import com.w3s.utils.PropertiesReader;

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
	private static final String UPLOAD_FILE = "Uploading the file";
	private static final String WAIT_PROPERTY = "wait";
	private static final String POLLING = "polling";
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
	private static final String SWITCH_TO_FRAME = "Switching to frame";
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
			// Assert.fail(exception.getMessage());
			throw new Exception();
		}

	}

	
	/**
	 * 
	 * @param element
	 *            contains contains browse button locator
	 * @param PATH
	 *            contains the file path
	 * @param NAME
	 *            contains the file name
	 * @throws Exception
	 */
	public void uploadFile(WebElement element, String PATH, String NAME) throws Exception {
		try {
			logger.info(UPLOAD_FILE);
			String file = PATH+NAME;
			element.sendKeys(file);
			logger.info("File updloaded");
		} catch (Exception exception) {
			logger.info("Unable to upload the file");
			logger.fatal(exception);
			exception.printStackTrace();
			// Assert.fail(exception.getMessage());
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
	 * Verify switching to frame
	 * 
	 * @param frame
	 *            contains frame id
	 * @throws Exception
	 */
	public void switchToIframe(String frame) throws Exception {
		try {
			logger.info(SWITCH_TO_FRAME);
			driver.switchTo().frame(frame);
			logger.info("Switched to iframe");
		} catch (Exception exception) {
			logger.info("Unable to switch to iframe");
			logger.fatal(exception);
			Assert.fail();
			exception.printStackTrace();
			throw new Exception();
		}
	}
}