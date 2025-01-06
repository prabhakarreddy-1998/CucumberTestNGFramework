package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.en.Then;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import pageObjects.PageObjectManager;
import utilities.TestContextSetup;

public class OffersPageStepDefinition {

	// TestContextSetup for dependency injection
	TestContextSetup testContextSetup;

	// OffersPage object
	OffersPage offersPage;
	// PageObjectManager object
	PageObjectManager pageObjectManager;

	// Constructor to initialize the TestContextSetup
	public OffersPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
	}

	// Method to navigate to Offers Page
	public void switchToOffersPage() {
		LandingPage landingPage = testContextSetup.pageObjectManager.getLandingpage();
		landingPage.navigateToOffersPage();
		System.out.println("Step: User has clicked on Offers Page link.");
	}

	// Step to verify navigation to the Offers page
	@Then("User should be able to navigate to the Offers page")
	public void userShouldNavigateToOffersPage() throws InterruptedException {
		offersPage = testContextSetup.pageObjectManager.getOffersPage();
		switchToOffersPage();
		Thread.sleep(1000);
	}

	// Step to handle new tab/window opening
	@Then("A new tab opens")
	public void aNewTabOpens() throws IOException {
		testContextSetup.genericUtils.switchToNewWindow();
		System.out
				.println("Offers page URL and Page Title: " + testContextSetup.testBase.launchBrowser().getCurrentUrl()
						+ " and Page Title: " + testContextSetup.testBase.launchBrowser().getTitle());
		System.out.println("Step: User switched to the new tab/child window.");
	}

	// Step to search for the product on the Offers page using short name
	@Then("^User should search for the same product on the Offers page with the shortname (.+)$")
	public void userShouldSearchForProductOnOffersPage(String shortName) {
		offersPage.searchItem(shortName);
		System.out.println("Step: User located the search bar and entered the short name to filter the product.");
	}

	// Step to extract the product name from the Offers page
	@Then("Extract the text of the filtered product from the offers page")
	public void extractTextOfFilteredProductFromOffersPage() throws InterruptedException {
		// Extract product name from the Offers page
		testContextSetup.OPActualProductName = offersPage.getProductName();
		System.out.println("Product Name on the Offers Page: " + testContextSetup.OPActualProductName);
		System.out.println("Step: User extracted the text of the filtered product from the Offers page.");
	}
}
