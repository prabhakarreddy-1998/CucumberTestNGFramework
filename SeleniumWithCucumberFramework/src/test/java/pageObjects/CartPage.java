package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	// WebDriver instance
	WebDriver driver;

	// Constructor to initialize WebDriver
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locators for elements on the Cart Page
	private By cartIcon = By.cssSelector("a.cart-icon");
	private By proceedToCheckoutButton = By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]");
	private By productNameInCart = By.cssSelector("p.product-name");
	private By productNameInCheckout = By.cssSelector(".product-name");
	private By productQuantityInCheckout = By.cssSelector("p.quantity");
	private By placeOrderButtonInCart = By.xpath("//button[starts-with(text(),'Place Order')]");
	private By ApplyButtonIncart = By.xpath("//button[contains(text(),'Apply')]");
	private By enterPromoCodeBox = By.xpath("//input[contains(@class,'promoCode')]");

	// Click on the cart icon
	public void clickOnCartIcon() {
		driver.findElement(cartIcon).click();
	}

	// Get the name of the product in the cart
	public String getProductNameInCart() {
		return driver.findElement(productNameInCart).getText();
	}

	// Click on the "Proceed to Checkout" button
	public void proceedToCheckoutButton() {
		driver.findElement(proceedToCheckoutButton).click();
	}

	// Get the product quantity displayed in the checkout page
	public String getProductQuantityInCheckout() {
		return driver.findElement(productQuantityInCheckout).getText();
	}

	// Get the product name displayed in the checkout page
	public String getProductNameInCheckout() {
		return driver.findElement(productNameInCheckout).getText();
	}

	// Check if the "Apply" button is displayed and enabled
	public boolean isApplyButtonDisplayedAndEnabled() {
		return driver.findElement(ApplyButtonIncart).isDisplayed() && driver.findElement(ApplyButtonIncart).isEnabled();
	}

	// Check if the "Place Order" button is displayed and enabled
	public boolean isPlaceOrderButtonDisplayedAndEnabled() {
		return driver.findElement(placeOrderButtonInCart).isDisplayed()
				&& driver.findElement(placeOrderButtonInCart).isEnabled();
	}
	public void enterPromocode(String promo) {
		driver.findElement(enterPromoCodeBox).sendKeys(promo);
	}

}
