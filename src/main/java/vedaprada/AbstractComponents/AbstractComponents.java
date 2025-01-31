package vedaprada.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vedaprada.pageobjects.CartPage;
import vedaprada.pageobjects.OrderPage;

public class AbstractComponents {
WebDriver driver;

	@FindBy(xpath="//button[@routerlink ='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink ='/dashboard/myorders']")
	WebElement orderHeader;
	//Actions a = new Actions(driver);

	public AbstractComponents(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDppear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(2000);	
	}
	
	public CartPage goToCartPage()
	{
		Actions a = new Actions(driver);
		a.moveToElement(cartHeader).click().build().perform();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrdersPage()
	{
		Actions a = new Actions(driver);
		a.moveToElement(orderHeader).click().build().perform();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
	
	}
