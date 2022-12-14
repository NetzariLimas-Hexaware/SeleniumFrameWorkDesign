package Hexaware.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//	List<WebElement> lst = driver.findElements(By.className("mb-3"));
	
	@FindBy(css=".mb-3")
	private List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	private WebElement spinner;
	
	private By productsBy = By.cssSelector(".mb-3");
	private By addToCart = By.cssSelector(".card-body button:last-of-type");
	private By toastMessageBy = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		return getProductList().stream()
				.filter(product->product.findElement(By.cssSelector("b")).getText()
				.equals(productName))
				.findFirst()
				.orElse(null);
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessageBy);
		waitForElementToDisappear(spinner);
	}
}
