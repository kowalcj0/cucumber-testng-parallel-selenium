package cucumber.examples.java.testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Based on the LocalDriverFactory found at: onrationaleemotions.wordpress.com
 * @author: Confusions Personified
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */
public class LocalDriverFactory {
    public static WebDriver createInstance(String browserName) {
        WebDriver driver;
        browserName = (browserName != null) ? browserName : "firefox";

        switch (Browser.valueOf(browserName.toUpperCase())) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "binaries/linux/googlechrome/64bit/2.9/chromedriver");
                driver = new ChromeDriver();
                break;
            case HTMLUNIT:
                driver = new HtmlUnitDriver();
                break;
            case HTMLUNITJS:
                driver = new HtmlUnitDriver(true);
                break;
            default:
                driver = new FirefoxDriver();
                break;
        }
        // maximize browser's window on start
        driver.manage().window().maximize();
        return driver;
    }

    private static enum Browser {
        FIREFOX,
        CHROME,
        IE,
        HTMLUNIT,
        HTMLUNITJS;
    }
}