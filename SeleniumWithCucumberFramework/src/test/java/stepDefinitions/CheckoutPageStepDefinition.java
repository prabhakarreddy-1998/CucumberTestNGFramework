package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CheckoutPage;
import pageObjects.LandingPage;
import utilities.TestContextSetup;

public class CheckoutPageStepDefinition {
	private TestContextSetup testContextSetup;
	private CheckoutPage checkoutPage;
	private LandingPage landingPage;
	
	// Constructor for dependency injection
	public CheckoutPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup=testContextSetup;
	}
	@When("Verify that the product name from LandingPage is matched with Checkout page")
	public void verify_that_the_product_name_from_landing_page_is_matched_with_checkout_page() {
		checkoutPage = testContextSetup.pageObjectManager.getCheckoutPage();
	    String productNameInCheckoutPage = checkoutPage.getProductNameInCheckoutPage();
	    System.out.println("productName in Checkout Page: "+productNameInCheckoutPage);
		
		  landingPage = testContextSetup.pageObjectManager.getLandingpage(); String
		  productNameInLandingPage = landingPage.getProductName();
		  System.out.println("productName in landing Page: "+productNameInLandingPage);
		 
	    Assert.assertEquals(productNameInCheckoutPage,productNameInLandingPage,"Product Name in Landing Page doesn't matched with Checkout Page!");
	    System.out.println("Step: Veried that the product name from LandingPage is matched with Checkout page");
	}
	@And("^User enters the promo code (.+)$")
    public void userEntersPromoCode(String promoCode) {
        checkoutPage = testContextSetup.pageObjectManager.getCheckoutPage();
        checkoutPage.enterPromoCode(promoCode);
        System.out.println("Step: Entered promo code: " + promoCode);
    }

    @And("User clicks on the Apply button")
    public void userClicksOnApplyButton() throws InterruptedException {
        Assert.assertTrue(checkoutPage.isApplyButtonEnabled(), "Apply button is not enabled.");
        checkoutPage.clickOnApplyButton();
        System.out.println("Step: Clicked on Apply button.");
    }

    @Then("^User should see a error message (.+)$")
    public void userShouldSeeSuccessMessage(String expectedMessage) {
        String actualMessage = checkoutPage.getPromoCodeErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Promo code error message does not match!");
        System.out.println("Step: Verified error message: " + actualMessage);
    }
}
	


