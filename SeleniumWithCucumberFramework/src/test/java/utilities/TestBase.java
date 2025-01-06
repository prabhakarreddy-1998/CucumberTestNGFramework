package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public WebDriver driver;

	FileInputStream fis;
	Properties prop;
	String url;
	String browser_properties;
	String browser_maven;
	String result_browser;

	public WebDriver launchBrowser() throws IOException {
		fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//global.properties");
		prop = new Properties();
		prop.load(fis);
		url = prop.getProperty("QAUrl");
		browser_properties = prop.getProperty("browser").toLowerCase();
		browser_maven = System.getProperty("browser");
		
		result_browser = browser_maven!=null ? browser_maven : browser_properties;
	 

		if (driver == null) {

			switch (result_browser) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
				driver.manage().window().maximize(); // Maximize browser window
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
				driver.manage().window().maximize(); // Maximize browser window
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
				driver.manage().window().maximize(); // Maximize browser window
				break;

			default:
				throw new IllegalArgumentException("Unsupported Browser: " + result_browser);
			}
		}

		return driver;
	}

	public void navigateToHomePage() {
		driver.get(url);
	}

}
