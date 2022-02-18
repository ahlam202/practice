package PracticeU;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrokUrl {
public static WebDriver driver;
	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\eclipse-workspace\\PracticeU\\src\\chromedriver.exe");
		driver=new ChromeDriver();
		
         //broken URL
        //Step 1 - IS to get all urls tied up to the links using Selenium
        //  Java methods will call URL's and gets you the status code
        //if status code >400 then that url is not working-> link which tied to url is broken
        //a[href*="soapui"]'
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
       // String url=driver.findElement(By.cssSelector("a[href*=\"soapui\"]")).getAttribute("href");// this is for verify 1 url
     List<WebElement> links=   driver.findElements(By.cssSelector("li[class='gf-li'] a"));
     SoftAssert a =new SoftAssert();
    for(WebElement link : links)
    {
         String url= link.getAttribute("href");
        HttpURLConnection   conn= (HttpURLConnection)new URL(url).openConnection();
         conn.setRequestMethod("HEAD");
         conn.connect();
         int respCode = conn.getResponseCode();
         System.out.println(respCode);
        // System.out.println("The link with Text"+link.getText()+" is broken with code" +respCode);
     // Assert.assertTrue(false);
         a.assertTrue(respCode<400, "The link with Text"+link.getText()+" is broken with code" +respCode);// get msg when we got brok url
     }
     a.assertAll();
	}

}
