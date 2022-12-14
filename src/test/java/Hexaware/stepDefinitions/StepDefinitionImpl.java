package Hexaware.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.CartPage;
import Hexaware.pageobjects.LandingPage;
import Hexaware.pageobjects.ProductCatalogue;
import Hexaware.pageobjects.ShippingInformation;
import Hexaware.pageobjects.confirmPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public confirmPage cmfPage;
	@Given("^I landed on Ecommerce Page$")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
		
		// code
	}
	
	@Given("^Logged in with username (.+) and (.+)$")
	public void logged_in_with_username_and(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		cartPage = productCatalogue.goToCart();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		ShippingInformation shipInformation = cartPage.goToCheckout();
		
		shipInformation.selectCountryFromList("mexico");
		cmfPage = shipInformation.placeOrder(); 
	}
	
	@Then("^\"([^\"]*)\" message is displayed on ConfirmationPage$")
	public void message_is_displayed_on_confirmationPage(String message) {
		String confirmMessage = cmfPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) {
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
    }
}
