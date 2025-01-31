package vedaprada.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vedaprada.TestComponents.BaseTest;
import vedaprada.pageobjects.CartPage;
import vedaprada.pageobjects.CheckoutPage;
import vedaprada.pageobjects.ConfirmationPage;
import vedaprada.pageobjects.LandingPage;
import vedaprada.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public CartPage cartpage;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingpage = launchApplication();
	}
	
	@Given("^logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		productcatalogue = landingpage.loginApplication(username, password);
	}
	


    @When("^I add product (.+) in the cart$")
    public void I_add_product_in_the_cart(String productName) throws InterruptedException
    {
    	List<WebElement> Cartproducts = productcatalogue.getProductList();
    	productcatalogue.addToCart(productName);
    }
    
    @And("^I checkout (.+) and submit the order$")
    public void I_checkout_and_submit_the_order(String productName)
    {
    	cartpage = productcatalogue.goToCartPage();	
    	Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);		
		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		confirmationpage = checkoutpage.submitOrder();		
    }
    
    @Then("{string} message is displayed on confirmation page")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    @Then("{string} should display")
    public void errorMessage_should_display(String error) throws InterruptedException
    {
		Assert.assertEquals(error, landingpage.getErrorMessage());
		driver.close();
    }
   
}
