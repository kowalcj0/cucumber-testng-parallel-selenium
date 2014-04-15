package cucumber.examples.java.testNG;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A factory of remote WebDrivers. Based on an example taken from:
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */
public class RemoteDriverFactory {

    static Logger log = Logger.getLogger(RemoteDriverFactory.class);

    static RemoteWebDriver createInstance(String browserName) {
        URL hubUrl = null;
        try {
            hubUrl = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return RemoteDriverFactory.createInstance(hubUrl,browserName);
    }

    static RemoteWebDriver createInstance(URL hubUrl, String browserName) {
        RemoteWebDriver driver = null;
        if (browserName.equalsIgnoreCase("firefox")) {
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        if (browserName.equalsIgnoreCase("chrome")) {
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        if (browserName.equalsIgnoreCase("ie")) {
            DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        if (browserName.equalsIgnoreCase("safari")) {
            DesiredCapabilities capability = DesiredCapabilities.safari();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        if (browserName.equalsIgnoreCase("opera")) {
            DesiredCapabilities capability = DesiredCapabilities.opera();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        if (browserName.equalsIgnoreCase("phantomjs")) {
            DesiredCapabilities capability = DesiredCapabilities.phantomjs();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        if (browserName.equalsIgnoreCase("android")) {
            DesiredCapabilities capability = DesiredCapabilities.android();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        if (browserName.equalsIgnoreCase("htmlUnit")) {
            DesiredCapabilities capability = DesiredCapabilities.htmlUnit();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        if (browserName.equalsIgnoreCase("htmlUnitWithJs")) {
            DesiredCapabilities capability = DesiredCapabilities.htmlUnitWithJs();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        if (browserName.equalsIgnoreCase("ipad")) {
            DesiredCapabilities capability = DesiredCapabilities.ipad();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        if (browserName.equalsIgnoreCase("iphone")) {
            DesiredCapabilities capability = DesiredCapabilities.iphone();
            driver = new RemoteWebDriver(hubUrl, capability);
            return driver;
        }
        log.info("RemoteDriverFactory created an instance of RemoteWebDriver for: " + browserName);
        return driver;
    }
}