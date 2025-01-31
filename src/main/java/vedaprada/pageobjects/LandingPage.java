package vedaprada.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vedaprada.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() throws InterruptedException
	{
		Thread.sleep(1000);
		return errorMessage.getText();
		
	}
	
	}

	



