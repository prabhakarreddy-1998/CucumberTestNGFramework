package stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.TestContextSetup;

public class Hooks {

	TestContextSetup testContextSetup;

	public Hooks(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
	}

	// Set up method to initialize WebDriver and browser
	@Before
	public void setUp() throws IOException {
		testContextSetup.testBase.launchBrowser();
		System.out.println("Step: Connection and browser initiated.");
	}

	// Tear down method to close browser
	@After
	public void tearDown() throws IOException {
		if (testContextSetup.testBase.launchBrowser() != null) {
			testContextSetup.testBase.launchBrowser().quit();
		}
		System.out.println("Step: Connection and browser closed.");
	}
	@AfterStep
	public void addScreenShot(Scenario scenario) throws IOException {
		WebDriver driver = testContextSetup.testBase.launchBrowser();
		if(scenario.isFailed()) {
			File sourcepath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(sourcepath);
			scenario.attach(fileContent,"image/jpeg" , "image");
		}
		
	}

}
