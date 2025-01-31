package vedaprada.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vedaprada.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutele;
	
	public boolean verifyProductDisplay(String productName)
	{
		return cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutele.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
		
	}
}
