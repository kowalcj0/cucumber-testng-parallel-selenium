package cucumber.examples.java.testNG.stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.examples.java.testNG.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

/**
 * Based on SharedDriver.java taken from cucumber-jvm/examples/java-webbit-websockets-selenium
 *
 * @src: https://github.com/cucumber/cucumber-jvm/blob/master/examples/java-webbit-websockets-selenium/src/test/java/cucumber/examples/java/websockets/SharedDriver.java
 * @author: https://github.com/aslakhellesoy
 */
public class BeforeAfterHooks {

    static Logger log;

    static {
        log = Logger.getLogger(BeforeAfterHooks.class);
    }

    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    @Before
    public void deleteAllCookies() {
        log.info("Deleting all cookies...");
        DriverManager.getDriver().manage().deleteAllCookies();
    }

    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    @After
    public static void embedScreenshot(Scenario scenario) {
        if ( scenario.isFailed() ) {
            log.error("Scenario failed! Browser: " + DriverManager.getBrowserInfo() + " Taking screenshot...");
            scenario.write("Current Page URL is: " + DriverManager.getDriver().getCurrentUrl());
            scenario.write("Scenario Failed in: " + DriverManager.getBrowserInfo());
            try {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                log.error(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
    }
}