import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class UploadFile {
	public static void main(String[] args) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver","F:\\workspace\\Lululemon1\\Support Library\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.get("https://portal.optimusinfo.com/redmine/projects/infonetdiscovery/issues/new");
		driver.findElement(By.xpath("html/body/div[1]/div/div[3]/div[2]/div[1]/form/table/tbody/tr[1]/td[2]/input")).sendKeys("gaurav.kumar");
		driver.findElement(By.xpath("html/body/div[1]/div/div[3]/div[2]/div[1]/form/table/tbody/tr[2]/td[2]/input")).sendKeys("novalueforwork");
		driver.findElement(By.xpath("html/body/div[1]/div/div[3]/div[2]/div[1]/form/table/tbody/tr[4]/td[2]/input")).click();
		driver.findElement(By.xpath("html/body/div[1]/div/div[3]/div[2]/form/div[2]/p[1]/span[1]/span/input[1]")).sendKeys("C:\\Users\\Gaurav Sharma\\Downloads\\ebay rubix cube\\$_57 (1).JPG");
	}
}
