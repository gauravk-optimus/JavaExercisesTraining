package com.w3s.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.w3s.executor.Keywords;

public class W3s {
	public static final String SELECTED_VALE="Volvo";
	public static final String iframe="iframeResult";
	Logger logger;
	Keywords keywords;

public W3s() {
	logger = Logger.getLogger(W3s.class);
	keywords = new Keywords();
	}

@FindBy(xpath = "html/body/select")
private WebElement dropdown;
	
public void dropdownVolvo() {
	logger.info("Verying the selected value of the dropdown");
	try{
		keywords.switchToIframe(iframe);		
		keywords.verifyDropdownSelectedValue(dropdown,SELECTED_VALE);		
		}catch(Exception e){
			Keywords.failValues.put(Thread.currentThread().getStackTrace()[2].getMethodName(),"Unable to verifyt the value");	
		}
	}	
}