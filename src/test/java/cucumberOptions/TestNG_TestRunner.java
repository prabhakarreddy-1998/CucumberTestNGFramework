package cucumberOptions;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features",
                 tags = "@CheckoutProcess", 
                 glue = "stepDefinitions", 
                 plugin = { "pretty","html:target/reports/Cucumber-HTML-Report.html", 
                		             "json:target/reports/Cucumber-Json-Report.json",
                		             "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                		             "rerun:target/failed_scenarios.txt"}, 
                 monochrome = true, 
                 dryRun = false)
public class TestNG_TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();

	}

}
