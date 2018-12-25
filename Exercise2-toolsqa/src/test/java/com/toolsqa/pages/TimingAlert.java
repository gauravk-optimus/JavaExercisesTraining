package com.toolsqa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toolsqa.executor.Keywords;

public class TimingAlert {
	
	Logger logger;
	Keywords keywords;
	
	public TimingAlert(){
		logger = Logger.getLogger(TimingAlert.class);
		keywords = new Keywords();
	}
	
	@FindBy(xpath="//*[@id='timingAlert']")
	private WebElement timingAlertBtn;
	
	public void accpetAlert(){
		try{
			logger.info("Verifying accept timing alert operation");
			keywords.clickButtonFluently(timingAlertBtn);
			keywords.accpetAlert();
		}catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to accept the timing alert");
			Assert.fail();			
		}		
	}
}
