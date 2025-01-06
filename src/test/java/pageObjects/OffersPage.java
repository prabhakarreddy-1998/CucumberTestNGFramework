package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OffersPage {

    // WebDriver instance
    WebDriver driver;

    // Constructor to initialize WebDriver
    public OffersPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for elements on the Offers page
    private By searchProduct = By.cssSelector("#search-field");
    private By productName = By.cssSelector("tbody tr td:nth-child(1)");

    // Search for a product in the search bar
    public void searchItem(String name) {
        driver.findElement(searchProduct).clear(); // Clear any existing text
        driver.findElement(searchProduct).sendKeys(name);
    }

    // Get the name of the first product in the filtered results
    public String getProductName() {
        return driver.findElement(productName).getText();
    }
}
