package stepDefinitions;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.LandingPage;
import utilities.TestContextSetup;

public class CartStepDefinition {
	private TestContextSetup testContextSetup;
	private LandingPage landingPage;
	private CartPage cartPage;

	public CartStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
	}

	@Given("User navigates to the Greenkart website")
	public void user_navigates_to_the_greenkart_website() {
		testContextSetup.testBase.navigateToHomePage();
		System.out.println("Step: Navigated to Greenkart homepage.");
	}

	@Given("^User searches for a product with the name (.+)$")
	public void user_searches_for_a_product_with_the_name(String productName) throws IOException, InterruptedException {
		landingPage = testContextSetup.pageObjectManager.getLandingpage();
		landingPage.searchItem(productName);
		System.out.println("Step: Searched for product: " + productName);
		 WebDriverWait wait = new WebDriverWait(testContextSetup.testBase.launchBrowser(), Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.presenceOfElementLocated(landingPage.getProductNameLocator()));
		Thread.sleep(5);
	}

	@When("^User increments the product quantity to (.+)$")
	public void user_increments_the_product_quantity_to(Integer quantity) {
		landingPage.incrementProductQuantity(quantity);
		System.out.println("Step: Incremented product quantity to " + quantity);
	}

	@When("User clicks on {string}")
	public void user_clicks_on(String buttonName) {
		landingPage.addToCart();
		System.out.println("Step: Clicked on " + buttonName + " button.");

	}

	@And("User clicks on the cart icon")
	public void userClicksOnCartIcon() {
		cartPage = testContextSetup.pageObjectManager.getCartPage();
		cartPage.clickOnCartIcon();
		System.out.println("Step: User clicked on the cart icon.");
	}

	@And("User clicks on Proceed to Checkout")
	public void userClicksOnProceedToCheckout() {
		cartPage.proceedToCheckoutButton();
		System.out.println("Step: User clicked on Proceed to Checkout.");
	}
}
