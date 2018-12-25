import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class CaptureScreenshot {	
	public static void main(String[] args) throws InterruptedException{		
		System.setProperty("webdriver.chrome.driver","F:\\workspace\\Lululemon1\\Support Library\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://en.wikipedia.org/wiki/Android_version_history");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		 // now copy the  screenshot to desired location using copyFile //method
			FileUtils.copyFile(src,new File("C:\\Users\\Gaurav Sharma\\Desktop\\test2.png"));
		}catch (IOException e)
		 {
			  System.out.println(e.getMessage());
			 
			 }
		driver.close();		
	}
}
