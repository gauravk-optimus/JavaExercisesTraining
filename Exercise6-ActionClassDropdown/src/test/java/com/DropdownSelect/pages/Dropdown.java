package com.DropdownSelect.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.DropdownSelect.executor.Keywords;

public class Dropdown {

	Logger logger;
	Keywords keywords;

	public Dropdown() {
		logger = Logger.getLogger(Dropdown.class);
		keywords = new Keywords();
	}

	@FindBy(xpath = "html/body/div[2]/div/div/header/nav/ul/li[2]/a")
	private WebElement Category;

	@FindBy(xpath = "html/body/div[2]/div/div/header/nav/ul/li[2]/ul/li[1]")
	private WebElement Entry;

	/**
	 * Verifying dropdownSelect feature
	 * 
	 */
	public void dropdownSelect() {
		logger.info("Calling dropdownSelection keyword");
		try {
			keywords.dropdownSelection(Category, Entry);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to select value");
			Assert.fail();
		}
	}
}