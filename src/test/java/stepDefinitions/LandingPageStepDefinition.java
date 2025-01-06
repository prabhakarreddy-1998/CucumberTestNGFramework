package stepDefinitions;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import utilities.TestBase;
import utilities.TestContextSetup;

public class LandingPageStepDefinition {

	// WebDriver instance
	public WebDriver driver;

	// Variables to store product names from Homepage and Offers page
	public String OPActualProductName;
	public String HMActualProductName;

	// Context setup for dependency injection
	TestContextSetup testContextSetup;

	// LandingPage object
	LandingPage landingPage;
	TestBase testBase;

	// Constructor for initializing context setup
	public LandingPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
	}
	
	// Step to navigate to the Greenkart website
	@Given("User should be able to navigate to the Greenkart website")
	public void userShouldNavigateToGreenkartWebsite() {
		testContextSetup.testBase.navigateToHomePage();
		System.out.println("Step: User has landed on the Homepage.");
	}

	// Step to verify the user can view the homepage
	@Given("User should be able to view the homepage")
	public void userShouldViewHomepage() throws IOException {
		String title = testContextSetup.testBase.launchBrowser().getTitle();
		Assert.assertTrue(title.contains("GreenKart"), "Title validation failed!");
		System.out.println("Home Page Title: " + title);
		System.out.println("Step: User is able to view the Homepage.");
	}

	// Step to search for a product on the homepage using the short name
	@When("^User searches for a product on the homepage with the shortname (.+)$")
	public void userSearchesForProductOnHomepage(String shortName) throws IOException, InterruptedException {
		// Get LandingPage instance from PageObjectManager
		landingPage = testContextSetup.pageObjectManager.getLandingpage();

		// Perform search action
		landingPage.searchItem(shortName);
		System.out.println("Step: User searched for the product on the Homepage.");

		// Explicit wait to ensure search results load
		WebDriverWait wait = new WebDriverWait(testContextSetup.testBase.launchBrowser(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(landingPage.searchObjectLocator()));
		Thread.sleep(1000);
	}

	// Step to extract the text of the filtered product on the homepage
	@When("Extract the text of the filtered product on the homepage")
	public void extractTextOfFilteredProduct() throws IOException, InterruptedException {
	    int retryCount = 0;
	    while (retryCount < 3) { // Retry up to 3 times
	        try {
	            // Wait until the product element is visible
	            WebDriverWait wait = new WebDriverWait(testContextSetup.testBase.launchBrowser(), Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(landingPage.getProductNameLocator()));
	            

	            // Extract product name
	            String productName = testContextSetup.pageObjectManager.getLandingpage().getProductName();
	            testContextSetup.HMActualProductName = productName.split("-")[0].trim();

	            System.out.println("Product Name on the Homepage: " + testContextSetup.HMActualProductName);
	            System.out.println("Step: User extracted the text of the filtered product.");
	            break; // Exit retry loop if successful
	        } catch (org.openqa.selenium.StaleElementReferenceException e) {
	            retryCount++;
	            System.out.println("Retrying due to stale element: Attempt " + retryCount);
	        }
	    }

	    if (retryCount == 3) {
	        throw new RuntimeException("Failed to extract product name due to stale element issues.");
	    }
	}



	// Step to verify the product exists on both Homepage and Offers page
	@Then("Verify that the product available on the homepage also exists on the Offers page")
	public void verifyProductOnOffersPage() {
		System.out.println("Expected Value: " + testContextSetup.HMActualProductName);
		System.out.println("Actual Value: " + testContextSetup.OPActualProductName);

		// Assertion to validate product names match
		Assert.assertEquals(testContextSetup.HMActualProductName, testContextSetup.OPActualProductName,
				"Product names do not match!");
		System.out.println("Step: User verified the product comparison.");
	}

	
}
