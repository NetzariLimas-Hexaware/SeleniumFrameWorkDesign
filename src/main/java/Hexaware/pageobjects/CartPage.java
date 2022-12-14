package Hexaware.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	private WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart h3")
	private List<WebElement> cartList;
	
	@FindBy(css=".totalRow button")
	private WebElement checkOutButton;
	
	private By cartListBy = By.cssSelector(".cart h3");	
	
	public List<WebElement> getCartList() {
		waitForElementToAppear(cartListBy);
		return cartList;
	}
	
	public boolean VerifyProductDisplay(String productName) {
		return getCartList().stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
	}
	
	public ShippingInformation goToCheckout() {
		checkOutButton.click();
		return new ShippingInformation(driver);
	}
	
	
}
