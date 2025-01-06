package utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import pageObjects.PageObjectManager;

public class TestContextSetup {
	public WebDriver driver;
	public String OPActualProductName;
	public String HMActualProductName;
	public PageObjectManager pageObjectManager;
	public TestBase testBase;
	public GenericUtils genericUtils;

	public TestContextSetup() throws IOException {
		testBase = new TestBase();
		pageObjectManager = new PageObjectManager(testBase.launchBrowser());
		genericUtils = new GenericUtils(testBase.launchBrowser());
	}
}
