package com.Optimus.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.Optimus.executor.Keywords;

public class ContactUs {

	private static final String MESSAGE = "Contacting to optimus";
	private static int MESSAGE_LENGHT = 21;

	Logger logger;
	Keywords keywords;

	public ContactUs() {
		logger = Logger.getLogger(ContactUs.class);
		keywords = new Keywords();
	}

	@FindBy(xpath = "html/body/div[1]/div/div[2]/div/div/div/div/div[1]/form/fieldset/p[5]/textarea")
	private WebElement message;

	/**
	 * Verifying the length of the entered value in textarea
	 * 
	 */
	public void contactUsMessage() {
		logger.info("Verifying lenght of textarea");
		try {
			keywords.sendTextInTextBox(message, MESSAGE);
			keywords.getTextLenght(message, MESSAGE_LENGHT);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to enter the message");
			Assert.fail();
		}
	}
}