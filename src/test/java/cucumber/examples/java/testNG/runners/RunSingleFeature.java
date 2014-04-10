package cucumber.examples.java.testNG.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.examples.java.testNG.DriverManager;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "target/test-classes/features/bugs/bug_no_12345.feature",
        glue = {"cucumber.examples.java.testNG.stepDefinitions.bugs",
        "cucumber.examples.java.testNG.hooks.TakeScreenshot"},
        strict = false,
        format = {"pretty",
                "html:target/cucumber-report/singleFeature",
                "json:target/cucumber-report/singleFeature/cucumber.json",
                "junit:target/cucumber-report/singleFeature/cucumber.xml"})
public class RunSingleFeature extends AbstractTestNGCucumberTests {

    static Logger log;

    static {
        log = Logger.getLogger(RunSingleFeature.class);
    }

    @Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public void deleteAllCookies() {
        log.info("Deleting all cookies...");
        DriverManager.getDriver().manage().deleteAllCookies();
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
        log.info("Taking screenshot...");
        scenario.write("Current Page URL is " + DriverManager.getDriver().getCurrentUrl());
        try {
            byte[] screenshot = ((EventFiringWebDriver) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }
}
