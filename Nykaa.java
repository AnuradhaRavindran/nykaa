package week4.day2;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		//Mouseover on Brands and Mouseover on Popular

		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		WebElement popular = driver.findElement(By.xpath("//a[text()='Popular']"));
		builder.moveToElement(popular).perform();
		//popular.click();
		//Thread.sleep(1000);
		
		//Go to the newly opened window and check the title contains L'Oreal Paris
		driver.findElement(By.xpath("(//li[@class='brand-logo menu-links' ])[5]//img")).click();
		Set<String> windowHandlesset = driver.getWindowHandles();
		List<String> windowhandleList = new ArrayList<String>(windowHandlesset);
		driver.switchTo().window(windowhandleList.get(1));
		String title = driver.getTitle();
		System.out.println(title);
		String actualTitle = "L'Oreal Paris - Buy L'Oreal Paris Products Online at Best Price | Nykaa";
		if(title.equals(actualTitle))
		{
			System.out.println("Title matched");
		}
		else {
			System.out.println("Title not matched");
		}
		
		// Click sort By and select customer top rated

         driver.findElement(By.xpath("//span[@class='pull-right']")).click();
         driver.findElement(By.xpath("(//div[@class='control__indicator radio'])[4]")).click();
         
         // Click Category and click Shampoo
         JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,-500)");
		 Thread.sleep(1000);
         driver.findElement(By.xpath("//div[text()='Category']")).click();
         driver.findElement(By.xpath("//span[text()='Personal Care']")).click();
         driver.findElement(By.xpath("//span[text()='Bath & Shower']")).click();
         driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
        // check whether the Filter is applied with Shampoo
         String text = driver.findElement(By.xpath("//li[text()='Shampoo']")).getText();
         System.out.println(text);
         //String searchText = "Shampoo";
         if(text.contains("Shampoo"))
         {
        	 System.out.println("Filtered Text is Shampoo");
         }
         else {
        	 System.out.println("Filtered Text is not Shampoo");
         }
       //  Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[contains(text(),'Concern')]")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
        Thread.sleep(1000);
		driver.findElement(By.xpath("(//img[@class='listing-img'])[1]")).click();
		Set<String> windowHandlesset1 = driver.getWindowHandles();
		List<String> windowhandleList1 = new ArrayList<String>(windowHandlesset1);
		driver.switchTo().window(windowhandleList1.get(2));
		WebElement size = driver.findElement(By.xpath("(//span[@class='size-pallets'])[1]"));
		String text2 = size.getText();
		System.out.println(text2);
		WebElement price = driver.findElement(By.xpath("(//span[@class='post-card__content-price-offer'])[1]"));
		String text3 = price.getText().replaceAll("[^0-9]", " ");
		System.out.println("The price of the product is : " +text3);
		driver.findElement(By.xpath("(//span[@class='mkr-New-Shopping-Bag font-mkr']/parent::button)[1]")).click();
		driver.findElement(By.xpath("//div[@class='BagItems ']")).click();
		WebElement grandTotal = driver.findElement(By.xpath("(//div[@class='value'])[4]"));
		String text4 = grandTotal.getText().replaceAll("[^0-9]", " ");
	  	System.out.println("The price of product is : " +text4);
	  	//WebElement frame = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
	  	//driver.switchTo().frame(frame);
	  //	Thread.sleep(1000);
	  	//driver.switchTo().window(windowhandleList1.get(2));
	  	js.executeScript("window.scrollBy(0,-500)");
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//div[@class='close']")).click();
	  	driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		WebElement finaltotal = driver.findElement(By.xpath("(//div[@class='value']/span)[2]"));
		String text5 = finaltotal.getText().replaceAll("[^0-9]", " ");
        System.out.println(text5);
		if(text4.equals(text5))
		{
			System.out.println("The total got matched");
		}
		else
		{
			System.out.println("The total got matched");
		}
		
		driver.quit();
	}

}
