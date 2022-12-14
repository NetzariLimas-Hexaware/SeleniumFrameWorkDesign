package Hexaware.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.CartPage;
import Hexaware.pageobjects.ProductCatalogue;
import Hexaware.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"Error Handling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		landingPage.loginApplication("netzari_limas@hotmail.com", "Angela111231213");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("netzari_limas@hotmail.com", "Angela13");
		productCatalogue.addProductToCart(productName);		
		CartPage cartPage = productCatalogue.goToCart();
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
