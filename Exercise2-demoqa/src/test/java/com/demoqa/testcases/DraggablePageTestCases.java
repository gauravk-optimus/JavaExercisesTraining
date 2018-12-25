package com.demoqa.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demoqa.executor.Keywords;
import com.demoqa.pages.Drag;

public class DraggablePageTestCases extends BaseClass{
	
	Logger logger;
	Keywords keywords;
	//WebDriver driver;
	Drag drag;
	
	private final String Website_URL = "URL";
	private final String STARTING_TESTCASE = "**********************Starting test cases *********************";
	
	public DraggablePageTestCases(){
		logger=Logger.getLogger(Drag.class);
		keywords = new Keywords();
	}
	
	@FindBy(xpath="//*[@id='draggable']")
	private WebElement draggable;
	
	@BeforeMethod
	public void initialSetUp(){
		PropertyConfigurator.configure("log4j.properties");
		logger.info(STARTING_TESTCASE);
		try{
			keywords.startBrowser();
			keywords.navigateTo(Website_URL);			
		}catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to start browser");
			Assert.fail();
		}
	}	

	/**
	 * Verifying the drag operation
	 * @throws Exception 
	 *  
	 */
	@Test
	public void randomDrag() throws Exception {
		logger.info("Drag Intiated");
		drag = PageFactory.initElements(Keywords.driver, Drag.class);
		drag.intiateDrag();
		Keywords.passValues.put(
				Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Test case run successfully");
	}
	@AfterMethod
	public void closeBrowser() {
		logger.info("Closing the browser");
		keywords.closeBrowser();
	}
}
