package com.DropdownSelect.executor;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.DropdownSelect.utils.PropertiesReader;

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
	 * @param element1
	 * 			contains the webelement for dropdown
	 * @param element2
	 * 			contains the webelement for dropdone entry
	 * @throws Exception 
	 */
	public void dropdownSelection(WebElement element1, WebElement element2) throws Exception {
		logger.info("Selecting the value from the dropdown after mouse hover");	
		try {
			Actions builder=new Actions(Keywords.driver);
			builder.moveToElement(element1).perform();
			Thread.sleep(1000);
			element2.click();
		} catch (Exception exception) {
			logger.info("Unable to select value or element not found");
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
}