package Hexaware.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class ShippingInformation extends AbstractComponent {
	private WebDriver driver;
	public ShippingInformation(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".user__address input")
	private WebElement countryInput;
	
	@FindBy(css=".user__address section button")
	private List<WebElement> countryList;
	
	@FindBy(css=".actions a")
	private WebElement placeOrderButton;
	
	private By countryInputBy = By.cssSelector(".user__address input");
	
	
	public void sendKeysToInputInformation(String msg) {
		waitForElementToAppear(countryInputBy);
		countryInput.sendKeys(msg);
	}
	
	public void selectCountryFromList(String msg) {
		sendKeysToInputInformation(msg);
		countryList.stream()
			.filter(s->s.getText()
			.contains("Mexico"))
			.findFirst().orElse(null)
		.click();
	}
	
	public confirmPage placeOrder() {
		placeOrderButton.click();
		return new confirmPage(driver);
	}
}
