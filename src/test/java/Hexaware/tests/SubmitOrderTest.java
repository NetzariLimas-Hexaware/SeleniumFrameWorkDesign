package Hexaware.tests;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.CartPage;
import Hexaware.pageobjects.OrderPage;
import Hexaware.pageobjects.ProductCatalogue;
import Hexaware.pageobjects.ShippingInformation;
import Hexaware.pageobjects.confirmPage;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider= "getData", groups= {"Purchase"} )
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String countrySurName = "mex";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));		
		CartPage cartPage = productCatalogue.goToCart();
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		ShippingInformation shipInformation = cartPage.goToCheckout();
		
		shipInformation.selectCountryFromList(countrySurName);
		confirmPage cmfPage = shipInformation.placeOrder(); 
		
		String confirmMessage = cmfPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("netzari_limas@hotmail.com", "Angela13");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Hexaware//data//Purchase.json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "netzari_limas@hotmail.com");
//	map.put("password", "Angela13");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map.put("email", "netzari_limas@hotmail.com");
//	map.put("password", "Angela13");
//	map.put("product", "ADIDAS ORIGINAL");

}
