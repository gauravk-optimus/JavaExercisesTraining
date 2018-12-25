import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class footer {
	public static WebDriver driver;
	public static String[] ftr={"MOBILE RECHARGE","Airtel","Aircel","Vodafone","BSNL","Tata Docomo GSM","Idea","indicom Walky","MTNL Delhi","Reliance CDMA","Reliance GSM","Tata Indicom","Telenor","MTS","Videocon","Virgin CDMA","Virgin GSM","Tata Docomo CDMA","DATA CARD RECHARGE","Tata Photon","MTS MBlaze","MTS MBrowse","Airtel","BSNL","Aircel","MTNL Delhi","Vodafone","Idea","MTNL Mumbai","Tata Photon Whiz","DTH(TV) RECHARGE","Airtel Digital TV","Reliance Digital TV","Dish TV","Tata Sky","Sun Direct","Videocon D2H","POSTPAID","Airtel Bill Payment","BSNL Bill Payment","Tata Docomo GSM Bill Payment","Tata Docomo CDMA Bill Payment","Idea Bill Payment","Vodafone Bill Payment","Reliance GSM Bill","Reliance CDMA Bill","About Us","Support","Contact Us","Sitemap","Geekery","T & C","Blog","Android App","Windows","Mobile Site","IOS","","","","","","","",""};
		public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, InterruptedException{
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ "\\Support Library\\chromedriver.exe");
			
			
			driver = new ChromeDriver();	   
	      driver.get("https://www.freecharge.in/");
	      driver.manage().window().maximize(); 
	      WebElement footer= driver.findElement(By.xpath("//*[@id='app']/div/footer/div[1]")); 
		System.out.println(footer.findElements(By.tagName("a")).size()) ; 
		  //List<WebElement> footlinks = footer.findElements(By.tagName("a"));
		  int i = footer.findElements(By.tagName("a")).size(); 
		 System.out.println(i);
		 String n; 
		 for(int j = 0;j<i;j++){    
			  footer= driver.findElement(By.xpath("//*[@id='app']/div/footer/div[1]"));
			   n=footer.findElements(By.tagName("a")).get(j).getText();
			   System.out.println(n);
			   System.out.println(ftr[j]);
			   if(n.equals(ftr[j])){
				   System.out.println("Pass");
			   }else{
				   System.out.println("Fail");
			   }
			   
			   //footer.findElements(By.tagName("a")).get(j).click();
			     // Thread.sleep(3000);
			   //System.out.println(driver.getTitle());
			     // if(driver.getTitle().contains("404")) {
			      // System.out.println("404 Found"); 
		 }
	}
}
