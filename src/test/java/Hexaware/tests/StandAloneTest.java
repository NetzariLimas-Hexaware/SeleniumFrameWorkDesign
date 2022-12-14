package Hexaware.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
				
		driver.findElement(By.id("userEmail")).sendKeys("netzari_limas@hotmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Angela13");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("mb-3")));
		List<WebElement> lst = driver.findElements(By.className("mb-3"));
		
		WebElement prod = lst.stream()
				.filter(product->product.findElement(By.cssSelector("b")).getText()
				.equals(productName))
				.findFirst()
				.orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
		
		//ng-animating
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		
		Boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector(".user__address input")).sendKeys("mex");
		
		List<WebElement> inputButtons = driver.findElements(By.cssSelector(".user__address section button"));
		WebElement itemButton = inputButtons.stream().filter(s->s.getText().contains("Mexico")).findFirst().orElse(null);
		
		itemButton.click();
		
		driver.findElement(By.cssSelector(".actions a")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

}
