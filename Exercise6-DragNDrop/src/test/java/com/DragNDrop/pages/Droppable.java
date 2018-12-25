package com.DragNDrop.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.DragNDrop.executor.Keywords;

public class Droppable {

	Logger logger;
	Keywords keywords;

	public Droppable() {
		logger = Logger.getLogger(Droppable.class);
		keywords = new Keywords();
	}

	@FindBy(xpath = "html/body/div[1]/div/div[1]/main/article/div/div/div[1]/div/div[1]")
	private WebElement DragFrom;

	@FindBy(xpath = "html/body/div[1]/div/div[1]/main/article/div/div/div[1]/div/div[2]")
	private WebElement DropTo;

	/**
	 * Verifying drag n drop feature
	 * 
	 */
	public void draggable() {
		logger.info("Calling Drag n Drop keyword");
		try {
			keywords.dragNdrop(DragFrom, DropTo);
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to drag drop");
			Assert.fail();
		}
	}
}