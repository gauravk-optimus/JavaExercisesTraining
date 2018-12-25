import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SwitchToWindow {	
	public static void main(String[] args) throws InterruptedException{		
		System.setProperty("webdriver.chrome.driver","F:\\workspace\\Lululemon1\\Support Library\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("");
		String ParentHandle = driver.getWindowHandle();
		Set<String> HandlesBeforeClick = driver.getWindowHandles();
		int CurrentNoOfHandle = HandlesBeforeClick.size();
		final int CurrentNoOfHandleAfterClick = CurrentNoOfHandle+1;
		//open window
		
		if(driver.getWindowHandles().size() == CurrentNoOfHandleAfterClick){
			Set<String> handle = driver.getWindowHandles();
			if(handle.contains(ParentHandle)){
				handle.remove(ParentHandle);
			}
			else{
				System.out.println("My Code Here");
			}
			for(String winhandle:handle){
				driver.switchTo().window(winhandle);
			}
		}
		
		
		
		driver.close();		
	}
}
