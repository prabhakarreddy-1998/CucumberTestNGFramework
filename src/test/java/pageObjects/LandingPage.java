package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {

	// WebDriver instance
	WebDriver driver;

	// Constructor to initialize WebDriver
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locators for elements on the Landing Page
	private By searchObject = By.xpath("//input[@placeholder='Search for Vegetables and Fruits']");
	private By productName = By.cssSelector("h4[class='product-name']");
	private By linkText = By.linkText("Top Deals");
	private By incrementButton = By.xpath("//a[normalize-space()='+']");
	private By addToCartButton = By.xpath("//button[contains(text(),'ADD TO CART')]");

	// Search for a product in the search bar
	public void searchItem(String name) {
		driver.findElement(searchObject).clear(); // Clear any existing text
		driver.findElement(searchObject).sendKeys(name);
	}

	// Get the name of the first product in the filtered results
	public String getProductName() {
		return driver.findElement(productName).getText();
	}

	public By searchObjectLocator() {
		driver.findElement(searchObject);
		return productName;
	}

	// Get the locator for the product name
	public By getProductNameLocator() {
		return productName;
	}

	// Increment product quantity
	public void incrementProductQuantity(int quantity) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    for (int i = 1; i < quantity; i++) {
	        int retryCount = 0;
	        while (retryCount < 3) { // Retry up to 3 times
	            try {
	                wait.until(ExpectedConditions.presenceOfElementLocated(incrementButton));
	                wait.until(ExpectedConditions.elementToBeClickable(incrementButton));
	                driver.findElement(incrementButton).click();
	                break; // Exit retry loop if successful
	            } catch (StaleElementReferenceException e) {
	                retryCount++;
	                System.out.println("Retrying due to stale element: Attempt " + retryCount);
	            }
	        }
	    }
	}


	// Click on the "Add to Cart" button
	public void addToCart() {
		driver.findElement(addToCartButton).click();
	}

	// Navigate to the Offers Page
	public void navigateToOffersPage() {
		driver.findElement(linkText).click();
	}

}
