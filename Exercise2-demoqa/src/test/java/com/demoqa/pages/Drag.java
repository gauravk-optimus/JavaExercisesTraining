package com.demoqa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.demoqa.executor.Keywords;

public class Drag {
	public static int X = 100;
	public static int Y = 100;
	Logger logger;
	Keywords keywords;
	
	public Drag(){
		logger = Logger.getLogger(Drag.class);
		keywords = new Keywords();
	}
	@FindBy(xpath="//*[@id='draggable']")
	private WebElement draggable;
	
	/**
	 * Initiating Drag n drop operation
	 * 
	 */

	public void intiateDrag(){
		logger.info("Dragging by mouse");
		try{
			keywords.dragByOffset(draggable, X, Y);
			keywords.accpetAlert();
		} catch (Exception e) {
			Keywords.failValues.put(
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to drag");
			Assert.fail();
		}
	}
	
}
