package vedaprada.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import vedaprada.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By results = By.cssSelector(".ta-results");
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	@FindBy(css = ".ta-item:nth-child(3)")
	WebElement selectCountry;
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		country.sendKeys(countryName);
		waitForElementToAppear(results);
		a.moveToElement(selectCountry).click().build().perform();
	}
	
	public ConfirmationPage submitOrder()
	{
		Actions a = new Actions(driver);
		a.moveToElement(submit).click().build().perform();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}
}
