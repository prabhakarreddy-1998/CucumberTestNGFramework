package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
	private final WebDriver driver;

	// Locators for elements on the Checkout Page
	private final By promoCodeInput = By.xpath("//input[@placeholder='Enter promo code']");
	private final By applyButton = By.xpath("//button[.='Apply']");
	private final By promoCodeErrorMessage = By.cssSelector(".promoInfo");
	private final By productNameInCheckOutPage = By.cssSelector(".product-name");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	
	public String getProductNameInCheckoutPage() {
		return driver.findElement(productNameInCheckOutPage).getText();
	}
	// Enter PromoCode
	public void enterPromoCode(String promoCode) {
		driver.findElement(promoCodeInput).sendKeys(promoCode);
	}
	// Click on the "Apply" button
	public void clickOnApplyButton() throws InterruptedException {
		driver.findElement(applyButton).click();
		Thread.sleep(10);
	}
	// Check if the Apply button is enabled
	public boolean isApplyButtonEnabled() {
		return driver.findElement(applyButton).isEnabled();
	}
	 // Get promo code error message
	public String getPromoCodeErrorMessage() {
		return driver.findElement(promoCodeErrorMessage).getText();
	}

}
