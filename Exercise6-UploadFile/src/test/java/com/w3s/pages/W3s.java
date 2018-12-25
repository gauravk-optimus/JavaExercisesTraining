package com.w3s.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.w3s.executor.Keywords;

public class W3s {
	public static final String SELECTED_VALE = "Volvo";
	public static final String iframe = "iframeResult";
	public static final String FILE_PATH = System.getProperty("user.dir")+"\\Support Library\\";
	public static final String FILE_NAME = "image.jpg";  
	Logger logger;
	Keywords keywords;

	@FindBy(xpath = "html/body/form/input[1]")
	private WebElement browseButton;

	@FindBy(css = "input[type='submit']")
	private WebElement submit;

	public W3s() {
		logger = Logger.getLogger(W3s.class);
		keywords = new Keywords();
	}

	public void UploadFile() {
		logger.info("Verying uplaod operation");
		try {
			keywords.switchToIframe(iframe);
			keywords.uploadFile(browseButton, FILE_PATH, FILE_NAME);
			Thread.sleep(1000);
			keywords.clickButtonFluently(submit);
		} catch (Exception e) {
			e.printStackTrace();
			Keywords.failValues.put(
			Thread.currentThread().getStackTrace()[2].getMethodName(),
					"Unable to upload the file");
		}
	}
}