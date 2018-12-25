import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class json2 {
	public static WebDriver driver;

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException{
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "\\Support Library\\chromedriver.exe");
		
		
		driver = new ChromeDriver();	   
      driver.get("https://www.freecharge.in/");
      driver.manage().window().maximize();
      String expected = new String();
      expected="MOBILE RECHARGE"+"\n"+"Airtel"+"\n"+"Aircel"+"\n"+"Vodafone"+"\n"+"BSNL"+"\n"+"Tata Docomo GSM"+"\n"+"Idea"+"\n"+"indicom Walky"+"\n"+"MTNL Delhi"+"\n"+"Reliance CDMA"+"\n"+"Reliance GSM"+"\n"+"Tata Indicom"+"\n"+"Telenor"+"\n"+"MTS"+"\n"+"Videocon"+"\n"+"Virgin CDMA"+"\n"+"Virgin GSM"+"\n"+"Tata Docomo CDMA";
        //WebElement
      List<WebElement> list= driver.findElements(By.xpath("//*[@id='app']/div/footer/div[1]/ul[1]"));		
		String actual = list.get(0).getText().toString();
      System.out.println(actual);
      System.out.println(expected);
		if(expected==actual){
			System.out.println("Pass");
		}else System.out.println("Fail");
		
		
      
      
	}
}