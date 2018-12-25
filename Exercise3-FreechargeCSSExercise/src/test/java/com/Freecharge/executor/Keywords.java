package com.Freecharge.executor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
	private static final String VERIFY_TEXT = "Verifying the text";
	private static final String CLOSE_BROWSER = "Closing the browser";
	private static final String IMPLICIT_WAIT = "implicitWait";
	private static final String START_BROWSER = "Inside startBrowser Method";
	private static final String NAVIGATE_TO = "Inside navigateTo Method";
	private static final String WRITE_TEXT = "Entering text is ";
	private static final String SELECT_VALUE = "Selecting value ";
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
	 * 
	 * @param element
	 *            contains list of web elements
	 * @param plan
	 *            contain string for plan type            
	 * @throws Exception 
	 */
	public void findEffectivePlan(List<WebElement> element, String plan) throws Exception {
		try {
			logger.info("Finding " + plan + " plan");
			int count = 0;
			for (int i = 0; i < element.size(); i++) {
				if (element.get(i).getText().contains(plan)) {
					count++;	
					break;					
				}					
			}if (count==0){
				throw new Exception();
			}
			logger.info(plan+" plan found");
		} catch (Exception exception) {
			logger.info("Unable to find " + plan+ " plan");
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}
	/**
	 * 
	 * @param element
	 *            contains list of web elements
	 * @param value
	 *            contain string for plan type            
	 * @throws Exception 
	 */
	public void getLeastValueFromList(List<WebElement> element, String value1, String value2)
			throws Exception {
		try {
			List<Integer> list = new ArrayList<Integer>();
			logger.info("Finding plan");
			String toBeReplaced = " Days";
			String toReplacedWith = "";
			int count=0;
			for (WebElement ele : element) {
				if (!ele.getText().contains(value1)) {
					count++;
					String str = ele.getText();
					String[] astr = str.split(toBeReplaced);
					StringBuffer strb = new StringBuffer();
					for (int i = 0; i <= astr.length - 1; i++) {
						strb = strb.append(astr[i]);
						if (i != astr.length - 1) {
							strb = strb.append(toReplacedWith);
						}
					}
					int num = Integer.parseInt(strb.toString());
					list.add(num);
				}
			}
			if(count>0){
			list.sort(null);
			logger.info("Least value of "+value2+" is "+list.get(0));
			}else{
				logger.info("All elements have "+ value1 +" "+value2);
			}
		} catch (Exception exception) {
			logger.info("Unable to find plan");
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
	 *            contains WebElement of text box.
	 * @param value
	 *            contain input data from user
	 * @throws Exception 
	 */
	public void sendTextInTextBox(WebElement element, String value) throws Exception {

		try {
			logger.info( WRITE_TEXT + value);
			element.clear();
			element.sendKeys(value);
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * 
	 * @param element
	 *            contains WebElement to get the actual Text
	 * @param text
	 *            contains the expected text
	 * @throws Exception 
	 */
	public void verifyTextContains(WebElement element, String text) throws Exception {
		try {
			logger.info(VERIFY_TEXT);
			String actual = element.getText().trim();
			logger.info(ACTUAL_VALUE + actual);
			logger.info(EXPECTED_VALUE + text);
			if(actual.contains(text) == false){
				throw new Exception();
			}
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}
	/**
	 * 
	 * @param element
	 *            contains List of WebElement
	 * @param value
	 *            contains the element to be found
	 * @throws Exception 
	 */
	public void getElementCount(List<WebElement> element, String value)
			throws Exception {
		try {
			logger.info("Count the elements");
			int count = 0;
			for (WebElement ele : element) {
				if (ele.getText().contains(value)) {
					logger.info(ele.getText());
					count++;
				}
			}
			logger.info(value + " plans are " + count);
		} catch (Exception exception) {
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
	 * Verify non existence of the element in the list
	 * @param element
	 * 			  contains WebElement list
	 * @param value
	 * 			  contains unexpected value
	 * @throws Exception 
	 */
	public void verifyListNotContains(List<WebElement> element, String value) throws Exception {
		try {
			logger.info("Verify list not cotains other element");	
			for(WebElement ele : element){
				if(!ele.getText().contains(value)){
					System.out.println(ele.getText());
					throw new Exception();
				}
			}logger.info("List does not contain non "+value+" plan");
			} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}	

	/**
	 * Select value in the dropdown
	 * @param element
	 *            contains WebElement of text box.
	 * @param value
	 *            contain input data from user
	 * @throws Exception 
	 */
	public void selectValueFromDropdown(WebElement element, String value) throws Exception {

		try {
			logger.info(SELECT_VALUE + value);			
			element.sendKeys(value);
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

}