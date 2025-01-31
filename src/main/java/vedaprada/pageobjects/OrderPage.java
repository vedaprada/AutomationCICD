package vedaprada.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vedaprada.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutele;
	
	public boolean verifyOrderDisplay(String productName)
	{
		return productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
	}
	

}
