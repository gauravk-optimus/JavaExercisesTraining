import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class VerifySortingOfDropdown {
	public static void main(String[] args) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver","F:\\workspace\\Lululemon1\\Support Library\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.freecharge.in/desktop/");
		driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div[2]/div/div/div/div[1]/div[2]/form/div[1]/div[1]/input")).sendKeys("9891234567");
		Thread.sleep(1000);
		List<WebElement> list = driver.findElements(By.xpath("html/body/div[2]/div/div[2]/div[2]/div/div/div/div[1]/div[2]/form/div[1]/div[3]/select"));
		List<String> raw = new ArrayList();
		List<String> sorted = new ArrayList();
		for(WebElement s:list){
			String a = s.getText();
		//System.out.println(a);
		raw.add(a);
		sorted.add(a);
		}Collections.sort(sorted);
		//for(String k:sorted){
		//	System.out.println(k);
		//}
		//boolean inOrder = sorted.equals(raw);
		//	System.out.println(inOrder);
		if (!raw.isEmpty()) {
			  Iterator<String> it = raw.iterator();
			  String prev = it.next();
			  while (it.hasNext()) {
			    String next = it.next();
			    if (prev.compareTo(next) > 0) {
			     // return false;
			    	System.out.println("false");
			    }
			    prev = next;
			  }
			}
		System.out.println("true");
			//return true;
		driver.close();
	}
}
