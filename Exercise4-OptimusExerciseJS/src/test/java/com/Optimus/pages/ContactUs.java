package com.Optimus.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.Optimus.executor.Keywords;

public class ContactUs {
	private final String getMessage = "avia_message_1";
	private static final String MESSAGE = "Contacting to optimus";
	private static final String UPDATED_MESSAGE = "Testing Department";

	Logger logger;
	Keywords keywords;

	public ContactUs() {
		logger = Logger.getLogger(ContactUs.class);
		keywords = new Keywords();
	}

	@FindBy(xpath = "//*[@name='avia_message_1']")
	private WebElement message;

	/**
	 * Verifying the length of the entered value in textarea
	 * 
	 */
	public void contactUsMessage() {
		logger.info("Calling JS keywords to enter text in Contact Us message textarea using JS");
		try {
			keywords.sendTextInTextBoxJS(message, MESSAGE);
			keywords.getEnteredTextJS(getMessage);
			keywords.changeTextJS(getMessage, UPDATED_MESSAGE);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to enter the message");
			Assert.fail();
		}
	}
}