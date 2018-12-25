import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TableRowColumnCount {
	public static void main(String[] args) throws InterruptedException{
		//int i=7,j=3;
		System.setProperty("webdriver.chrome.driver","F:\\workspace\\Lululemon1\\Support Library\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://en.wikipedia.org/wiki/Android_version_history");
		int rowCount=driver.findElements(By.xpath("html/body/div[3]/div[3]/div[4]/table[1]/tbody/tr")).size();
		int columnCount=driver.findElements(By.xpath("html/body/div[3]/div[3]/div[4]/table[1]/tbody/tr["+rowCount+"]/td")).size();
		System.out.println(rowCount);
		System.out.println(columnCount);
		Thread.sleep(2000);
		for(int i = 2;i<=rowCount;i++){
			for(int j = 1;j<=columnCount;j++){
				String xpath = "html/body/div[3]/div[3]/div[4]/table[1]/tbody/tr["+i+"]/td["+j+"]";
				WebElement actual = driver.findElement(By.xpath(xpath));
				if(actual.getText().equals("May 20, 2010")){
					System.out.println(xpath);
				}
			}
		}driver.close();
		
	}
}
