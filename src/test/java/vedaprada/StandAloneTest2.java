package vedaprada;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vedaprada.TestComponents.BaseTest;
import vedaprada.pageobjects.CartPage;
import vedaprada.pageobjects.CheckoutPage;
import vedaprada.pageobjects.ConfirmationPage;
import vedaprada.pageobjects.OrderPage;
import vedaprada.pageobjects.ProductCatalogue;

public class StandAloneTest2 extends BaseTest {
	String productName = "IPHONE 13 PRO";	
	@Test(dataProvider = "getData", groups = {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		// NEW COMMENTS -- To test CICD	
		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));		
		List<WebElement> Cartproducts = productcatalogue.getProductList();
		productcatalogue.addToCart(input.get("productName"));
		CartPage cartpage = productcatalogue.goToCartPage();		
		Boolean match = cartpage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);		
		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();		
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productcatalogue = landingpage.loginApplication("vedaprada@gmail.com", "Stayfocused@22");
		OrderPage orderpage = productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\vedaprada\\data\\PurchaseOrder.json");
		return new Object[][] {
			{data.get(0)}, 
			{data.get(1)}
			};
	}
	
//	@DataProvider
//	public Object[][] getData() throws IOException
//	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "vedaprada@gmail.com");
//		map.put("password", "Stayfocused@22");
//		map.put("productName", "IPHONE 13 PRO");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "sneha11@gmail.com");
//		map1.put("password", "Stayfocused@22");
//		map1.put("productName", "BANARSI SAREE");
//		
//		return new Object[][] {{map}, {map1}};
//	}
	

}
