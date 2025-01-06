package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

	private final WebDriver driver;
	private LandingPage landingPage;
	private OffersPage offersPage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LandingPage getLandingpage() {
		if (landingPage == null) {
			landingPage = new LandingPage(driver);
		}
		return landingPage;
	}

	public OffersPage getOffersPage() {
		if (offersPage == null) {
			offersPage = new OffersPage(driver);
		}
		return offersPage;
	}

	public CartPage getCartPage() {
		if (cartPage == null) {
			cartPage = new CartPage(driver);
		}
		return cartPage;
	}

	public CheckoutPage getCheckoutPage() {
		if (checkoutPage == null) {
			checkoutPage = new CheckoutPage(driver);
		}
		return checkoutPage;
	}

}
