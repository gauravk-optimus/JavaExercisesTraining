package com.FileHandling.executor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;

import com.FileHandling.utils.PropertiesReader;
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
	private static final String CLOSE_BROWSER = "Closing the browser";
	private static final String IMPLICIT_WAIT = "implicitWait";
	private static final String START_BROWSER = "Inside startBrowser Method";
	private static final String NAVIGATE_TO = "Inside navigateTo Method";
	private static final String WRITE_TEXT = "Entering text is ";
	public static HashMap<String, String> passValues = new HashMap<String, String>();
	public static HashMap<String, String> failValues = new HashMap<String, String>();
	public static long startTime;
	public static long endTime;
	List<String> list1;
	List<String> list2;
	List<String> list3;

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
	 * closing the browser
	 */
	public void closeBrowser() {
		logger.info(CLOSE_BROWSER);
		driver.quit();
	}
	/**
	 * Clicking the Button 
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
	public void sendTextInTextBox(WebElement element, String value)
			throws Exception {

		try {
			logger.info(WRITE_TEXT + value);
			element.clear();
			element.sendKeys(value);
		} catch (Exception exception) {
			logger.info("Unable to enter");
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	public void writeDdataToExcel(List<WebElement> element1,
			List<WebElement> element2, List<WebElement> element3)
			throws Exception {
		try {
			List<String> list1 = new ArrayList();
			List<String> list2 = new ArrayList();
			List<String> list3 = new ArrayList();
			for (WebElement ele1 : element1) {
				list1.add(ele1.getText());
			}
			for (WebElement ele2 : element2) {
				list2.add(ele2.getText());
			}
			for (WebElement ele3 : element3) {
				list3.add(ele3.getText());
			}
			int size = list1.size();
			XSSFWorkbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Recommended Plans Details");
			for (int count = 0; count < size; count++) {
				Row newRow = sheet.createRow(count + 1);
				for (int j = 0; j < 3; j++) {
					newRow.createCell(0).setCellValue(list1.get(count));
					newRow.createCell(1).setCellValue(list2.get(count));
					newRow.createCell(2).setCellValue(list3.get(count));
				}
			}
			Row row = sheet.createRow(1);
			row.createCell(0).setCellValue("Plan Price");
			row.createCell(1).setCellValue("Plan Type");
			row.createCell(2).setCellValue("Plan Validity");
			String fileName = "Excel.xlsx";
			File file = new File(System.getProperty("user.dir")
					+ "\\Execution_Results\\" + fileName);
			FileOutputStream inputStream = new FileOutputStream(file);
			workbook.write(inputStream);
			inputStream.close();
			workbook.close();
		} catch (Exception exception) {
			logger.info("Unable to enter");
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * 
	 * @param element1
	 *            contain List of WebElements
	 * @param element2
	 *            contain List of WebElements
	 * @param element3
	 *            contain List of WebElements
	 * @throws Exception
	 */
	public void fetchDataToList(List<WebElement> element1,
			List<WebElement> element2, List<WebElement> element3)
			throws Exception {
		try {
			logger.info("Fetching data to list");
			list1 = new ArrayList();
			list2 = new ArrayList();
			list3 = new ArrayList();
			for (WebElement ele1 : element1) {
				list1.add(ele1.getText());
			}
			for (WebElement ele2 : element2) {
				list2.add(ele2.getText());
			}
			for (WebElement ele3 : element3) {
				list3.add(ele3.getText());
			}
			logger.info("Plan data added to the lists");
		} catch (Exception exception) {
			logger.info("Unable to enter data to list");
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}
	/**
	 * 
	 * @param fileName 
	 * 			contain name of the excel file 
	 * @param tabName 
	 * 			contain name of the tab
	 * @param header1 
	 * 			contain first header
	 * @param header2
	 * 			contain second header
	 * @param header3
	 * 			contain third header
	 */	
	public void writeDataToExcel(String fileName, String tabName, String header1, String header2, String header3)
			throws Exception {
		try {
			logger.info("Starting writing data to Excel");
			int size = list1.size();
			XSSFWorkbook workbook = new XSSFWorkbook();
			logger.info("Creating workbook");		

			Sheet sheet = workbook.createSheet(tabName);
			for (int count = 0; count < size; count++) {
				Row newRow = sheet.createRow(count + 1);
				for (int j = 0; j < 3; j++) {
					newRow.createCell(0).setCellValue(list1.get(count));
					newRow.createCell(1).setCellValue(list2.get(count));
					newRow.createCell(2).setCellValue(list3.get(count));
				}
			}
			logger.info("Adding headers to the Excel Sheet");
			Row row = sheet.createRow(0);			
			
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		    style.setFillPattern(CellStyle.SOLID_FOREGROUND);			
		    
		    Cell cell = row.createCell(0);
		    cell.setCellValue(header1);
		    cell.setCellStyle(style);		    
		    
		    Cell cell1 = row.createCell(1);
		    cell1.setCellValue(header2);
		    cell1.setCellStyle(style);
		    
		    Cell cell2 = row.createCell(2);
		    cell2.setCellValue(header3);
		    cell2.setCellStyle(style);
		    
			//row.createCell(2).setCellValue(header3);
					    
		    logger.info("Creating Excel file :"+fileName);
			File file = new File(System.getProperty("user.dir")
					+ "\\Execution_Results\\" + fileName);
			FileOutputStream inputStream = new FileOutputStream(file);
			workbook.write(inputStream);
			inputStream.close();
			workbook.close();
			logger.info("Data added to the Excel Sheet");
			
		} catch (Exception exception) {
			logger.info("Unable to enter data to the excel sheet");
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}

	
	/**
	 * changing the color of the cell according to the value
	 * @param fileName
	 * 				contains the name of the excel file
	 * @param tabName
	 * 				contains the name of the workbook
	 * @param value
	 * 				contain the search string
	 * 
	 * @throws Exception
	 */
	public void chnageCellColor(String fileName, String tabName, String value)
			throws Exception {
		try {
			logger.info("Inside Change cell color keyword");
			logger.info("Locating the file: " + fileName);
			File file = new File(System.getProperty("user.dir")
					+ "\\Execution_Results\\" + fileName);
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			Sheet sheet = wb.getSheet(tabName);
			/*
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		    style.setFillPattern(CellStyle.SOLID_FOREGROUND);			
		    */
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			logger.info("Locating cells which contains " + value);
			for (int i = 0; i < rowCount + 1; i++) {
				Row rows = sheet.getRow(i);
				for (int j = 0; j < rows.getLastCellNum(); j++) {
					if (rows.getCell(j).getStringCellValue().contains(value)) {
						String data = rows.getCell(j).getStringCellValue();
						logger.info(value+" plan is: " + data);
						// TODO change the color in excel sheet
					}
				}
			}
			inputStream.close();
			wb.close();
		} catch (Exception exception) {
			logger.info("Unable to locate the cells");
			logger.fatal(exception);
			exception.printStackTrace();
			throw new Exception();
		}
	}
}