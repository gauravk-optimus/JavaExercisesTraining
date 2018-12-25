import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Misc {
	public static WebDriver driver;
   public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
	   System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "\\Support Library\\chromedriver.exe");
		driver = new ChromeDriver();	   
      driver.get("https://www.freecharge.in/");
      driver.manage().window().maximize();
     //WebElement ele2 = driver.findElement(By.xpath("//*[@id='app']/div/footer/div[1]/ul[1]/li[2]/a"));
     //System.out.println(ele2.getText());
      
       List<WebElement> list= driver.findElements(By.xpath("//*[@id='app']/div/footer/div[1]/ul[1]"));
      for(WebElement ele: list){
   	  System.out.println(ele.getText());   	  
   	  JSONParser parser = new JSONParser();
   	  Object obj;
   	  JSONObject jsonObject = null;	
		obj = parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\TestData.json"));
		jsonObject = (JSONObject) obj;
		System.out.println(jsonObject);
		
		driver.close();
      }
   }
   
}