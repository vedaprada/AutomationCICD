package vedaprada;

import io.github.bonigarcia.wdm.WebDriverManager;
import vedaprada.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "IPHONE 13 PRO";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("vedaprada@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Stayfocused@22");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Actions a = new Actions(driver);		
		WebElement addToCartBtn = driver.findElement(By.xpath("//button[@routerlink ='/dashboard/cart']"));
		a.moveToElement(addToCartBtn).click().build().perform();
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		WebElement country = driver.findElement(By.cssSelector(".ta-item:nth-child(3)"));
		a.moveToElement(country).click().build().perform();
		WebElement placeOrderBtn = driver.findElement(By.cssSelector(".action__submit "));
		a.moveToElement(placeOrderBtn).click().build().perform();
		String confirmationMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}	

}
