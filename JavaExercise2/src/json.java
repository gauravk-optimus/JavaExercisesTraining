import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class json {
	public static WebDriver driver;
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException{
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "\\Support Library\\chromedriver.exe");
		driver = new ChromeDriver();	   
      driver.get("https://www.freecharge.in/");
      driver.manage().window().maximize();
      String d = "MOBILE RECHARGE+\r+\n+Airtel\r\nAircel\r\nVodafone\r\nBSNL\r\nTata Docomo GSM\r\nIdea\r\nindicom Walky\r\nMTNL Delhi\r\nReliance CDMA\r\nReliance GSM\r\nTata Indicom\r\nTelenor\r\nMTS\r\nVideocon\r\nVirgin CDMA\r\nVirgin GSM\r\nTata Docomo CDMA";
        //WebElement/*
      List<WebElement> list= driver.findElements(By.xpath("//*[@id='app']/div/footer/div[1]/ul[1]"));		
		String s = list.get(0).getText();
      System.out.print(s);
      System.out.print(d);
      if(d==s){
    	System.out.println("Pass");  
      }else System.out.println("Fail");
      
		JSONParser parser = new JSONParser();
		Object obj;
		JSONObject jsonobject = null;
		obj = parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\TestData.json"));
		jsonobject = (JSONObject) obj;
		System.out.println(jsonobject);
		
      
      
      
      
      //Test Data
/*	 JSONParser parser = new JSONParser();
  	  Object obj;JSONArray jsonArray = null;	
		obj = parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\TestData.json"));
		jsonArray = (JSONArray) obj;
		//System.out.println(jsonArray);
		List<JSONObject> tArray = new ArrayList<JSONObject>();
		WebElement ele;
	    for (int i = 0; i < jsonArray.size(); i++) {
	        System.out.println(tArray.add((JSONObject) jsonArray.get(i)));
	        System.out.println("A"+tArray.get(i));
	        System.out.println("B"+jsonArray.get(i));
	        System.out.println("C"+tArray.get(i).toString());
	        
	        ele=list.get(i);
	        System.out.println("A"+ele.getText());
	        System.out.println("B"+ele.getText().toString());
	        //if (tArray.get(0).toString()==list.get(0).toString()){
	       // 	System.out.println("Pass");
	       // }else System.out.println("Fail");
	    }*/				
	}
}
