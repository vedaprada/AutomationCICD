package vedaprada;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import vedaprada.TestComponents.BaseTest;
import vedaprada.pageobjects.ProductCatalogue;

public class ErrorValidation extends BaseTest {

	@Test(groups = {"ErrorHandeling"}, retryAnalyzer = vedaprada.TestComponents.Retry.class)
	public void ErrorHandeling() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		ProductCatalogue productcatalogue = landingpage.loginApplication("vedaprada@gmail.com", "Stayfocused");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}	

}
