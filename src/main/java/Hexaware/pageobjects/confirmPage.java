package Hexaware.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class confirmPage extends AbstractComponent {

	@SuppressWarnings("unused")
	private WebDriver driver;
	
	public confirmPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	private WebElement confirmationMessage;

	public String getConfirmationMessage() {
		return confirmationMessage.getText();
	}

}
