package com.Freecharge.executor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import org.openqa.selenium.support.ui.Select;
import com.Freecharge.utils.PropertiesReader;
import com.google.common.base.Function;

import com.Freecharge.pages.Home;

/**
 * This is the class to initialize the browser and to define common
 * verification/assertion methods
 * 
 * @author Gaurav
 *
 */
public class Keywords {
	//Home home;
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
	private static final String VERIFY_RADIO = "Verifying the radio button ";
	private static final String SELECT_VALUE = "Selecting value ";
	private static final String VERIFY_DROPDOWN = "Verifying Selected value ";
	private static final String VERIFY_DROPDOWN_COUNT = "Verifying number of items in the dropdown ";
	public static HashMap<String, String> passValues = new HashMap<String, String>();
	public static HashMap<String, String> failValues = new HashMap<String, String>();
	public static long startTime;
	public static long endTime;
	private static final String VERIFY_FOOTERS = "Verifying footer names";
	
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
			logger.info("Unable to enter");
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}
	
	/**
	 * 
	 * @param element
	 *            contains list of elements
	 * @param value
	 *            contain expected number of elements
	 * @throws Exception 
	 */
	public void totalElementsInList(List<WebElement> element, int expected, String name) throws Exception {

		try {
			logger.info("Verifying total number of "+name);
			int actual = element.size();
			logger.info(ACTUAL_VALUE + actual);
			logger.info(EXPECTED_VALUE + expected);			
			if (expected != actual){
				logger.info(name+" Count is not equal");
				throw new Exception();
			}else{
				logger.info("Actual and Expected number of "+name+" are equal");
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
	 *            contains list of web elements
	 * @param Sublist
	 *            contain string to filter the list
	 * @param SubSublist
	 *            contain string to filter the Sublist            
	 * @throws Exception 
	 */
	public void selectEffectivePlan(List<WebElement> element, String Sublist,
			String SubSubList) throws Exception {
		try {
			logger.info("Selecting " + Sublist);
			for (int i = 0; i < element.size(); i++) {
				if (element.get(i).getText().contains(Sublist)) {
					if (element.get(i).getText().contains(SubSubList)) {
						WebElement ele = element.get(i);
						JavascriptExecutor executor = (JavascriptExecutor) driver;
						executor.executeScript("arguments[0].click();", ele);
						break;
					}
				}
			}
			logger.info(Sublist + " is selected");
		} catch (Exception exception) {
			logger.info("Unable to select " + Sublist);
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
	 * Verify radio button is selected
	 * @param element
	 * 			  contains WebElement to find the radio button
	 * @throws Exception 
	 */
	public void verifyRadioSelected(WebElement element) throws Exception {
		try {
			logger.info(VERIFY_RADIO);	
			boolean bValue = false;
			bValue = element.isSelected();
			if (bValue=false){
				logger.info("Radion button is not selected");
				throw new Exception();
			}
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
	/**
	 * Verify the selected value in the dropdown
	 * @param element
	 *            contains WebElement to get the selected value of the dropdown
	 * @param value
	 *            contains the expected selected item of the dropdown
	 * @throws Exception 
	 */
	public void verifyDropdownSelectedValue(WebElement element, String value) throws Exception {
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
	 * Verify the number of items in the dropdown
	 * @param element
	 *            contains WebElement to get the actual values of dropdown
	 * @param value
	 *            contains the expected number of items in dropdown
	 * @throws Exception 
	 */
	public void verifyDropdownValueCount(WebElement element, int value) throws Exception {
		try {
			logger.info(VERIFY_DROPDOWN_COUNT);			
			Select select = new Select(element);
			List<WebElement> list = select.getOptions();
			int actual=list.size();
			logger.info(ACTUAL_VALUE + actual);
			logger.info(EXPECTED_VALUE + value);
			if (actual!=value) {
				logger.info("Expected number of items are not equal to actual");
				throw new Exception();
			}
		} catch (Exception exception) {
			logger.fatal(exception);
			exception.printStackTrace();
//			Assert.fail(exception.getMessage());
			throw new Exception();
		}
	}
	/**
	 *  Verify the footer names
	 *  @param element 
	 *  			contains WebElement to get all the footers
	 *  @throws Exception   
	 */
	public void verifyFooter(WebElement element, String[] footers) throws Exception {
		try {
			logger.info(VERIFY_FOOTERS);
			int i = element.findElements(By.tagName("a")).size(); 
			 System.out.println("Number of Footers found "+i);
			 String footer; 
			 for(int j = 0;j<i;j++){    				 
				   footer=element.findElements(By.tagName("a")).get(j).getText();
				   System.out.println("Actual: "+footer);
				   System.out.println("Expected: "+footers[j]);			 
				   if (!footer.equals(footers[j])) {
					   logger.info("Verification stopped, Footer did not match");
					   throw new Exception();
				   }
			}
		} catch (Exception exception) {		
			logger.info("All Footers not verified or element not found");
			logger.fatal(exception);			
			exception.printStackTrace();
			throw new Exception();
		}
	}	
}