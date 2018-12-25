package com.Google.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.Google.executor.Keywords;

public class Google {

	private final String SEARCH_STRING = "WikipediaSearch";
	Logger logger;
	Keywords keywords;

	public Google() {
		logger = Logger.getLogger(Google.class);
		keywords = new Keywords();
	}

	@FindBy(xpath = "html/body/div[4]/div[1]/div[3]/div[3]/form/div/input[1]")
	private WebElement SearchBox;

	/**
	 * Verifying drag n drop feature
	 * 
	 * @throws Exception
	 * 
	 */
	public void undoEnterText() throws Exception {
		logger.info("Inside undoEnterText method");
		try {
			keywords.sendTextInTextBox(SearchBox, SEARCH_STRING);
			keywords.selectText(SearchBox);
			keywords.rightClickUndo(SearchBox);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to enter enter text");
			e.printStackTrace();
			throw new Exception();

		}
	}
}