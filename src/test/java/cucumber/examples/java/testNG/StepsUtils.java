package cucumber.examples.java.testNG;

import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

/**
 * Created by jay on 10/04/14.
 */
public class StepsUtils {

    static Logger log;

    static {
        log = Logger.getLogger(StepsUtils.class);
    }

    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public static void deleteAllCookies() {
        log.info("Deleting all cookies...");
        DriverManager.getDriver().manage().deleteAllCookies();
    }

    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public static void embedScreenshot(Scenario scenario) {
        if ( scenario.isFailed() ) {
            log.info("Scenario failed. Taking screenshot...");
            scenario.write("Current Page URL is " + DriverManager.getDriver().getCurrentUrl());
            try {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
    }
}
