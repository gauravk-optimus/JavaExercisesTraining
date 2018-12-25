package com.toolsqa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toolsqa.executor.Keywords;

public class SwitchWindows {
	public static final String TEXT_TO_VERIFY = "Selenium Online Trainings";
	
	Logger logger;
	Keywords keywords;
	
	public SwitchWindows(){
		logger = Logger.getLogger(SwitchWindows.class);
		keywords = new Keywords();
	}
	@FindBy(xpath="//*[@id='button1']")
	private WebElement newWindowButton;
	
	@FindBy(xpath="//*[@id='slide-16-layer-1']")
	private WebElement textToVerify;
	
	/**
	 * Switching to 'QA Automation Tools Tutorial' Window
	 * 
	 **/
	public void switchToNewWindow(){
		logger.info("Switching to 'QA Automation Tools Tutorial' Window");
		try{
			keywords.switchToWindow(newWindowButton);			
		}catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to switch to window");
			Assert.fail();
		}
	}
	/**
	 * Verifying text after switching to 'QA Automation Tools Tutorial' Window
	 * 
	 **/
	public void verifyText(){
		logger.info("Verifying 'Selenium Online Trainings' text");
		try{			
			keywords.fluentWaitToCheckVisible(textToVerify);
			keywords.verifyTextContains(textToVerify,TEXT_TO_VERIFY);
		}catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to verify text, not found");
			Assert.fail();
		}
	}
}
