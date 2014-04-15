package cucumber.examples.java.testNG;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A factory of remote WebDrivers.
 * Based on the LocalDriverFactory found at: onrationaleemotions.wordpress.com
 * @author: Confusions Personified
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */
public class RemoteWebDriverListener implements IInvokedMethodListener {

    static Logger log = Logger.getLogger(RemoteWebDriverListener.class);

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        log.debug("BEGINNING: org.stng.jbehave.RemoteWebDriverListener.beforeInvocation");
        if (method.isTestMethod()) {
            // get browser name specified in the TestNG XML test suite file
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            URL hubURL = null;
            try {
                // get hub URL specified in the TestNG XML test suite file
                // if not provided then use default http://localhost:4444/wd/hub
                hubURL = new URL(
                        (method.getTestMethod().getXmlTest().getSuite().getParameter("hubURL") != "")
                                ? method.getTestMethod().getXmlTest().getSuite().getParameter("hubURL")
                                : "http://localhost:4444/wd/hub");
            } catch (MalformedURLException e) {
                System.out.println("ex:\n" + e.getMessage() + "");
                e.printStackTrace();
            }
            log.info("HUB URL: " + hubURL);
            // get and set new instance of remote WebDriver
            WebDriver driver = RemoteDriverFactory.createInstance(hubURL, browserName);
            DriverManager.setWebDriver(driver);
            log.info("Done! Created "+ browserName + " driver!" );
        } else {
            log.warn("Provided method is NOT a TestNG testMethod!!!");
        }
        log.debug("END: org.stng.jbehave.RemoteWebDriverListener.beforeInvocation");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        log.debug("BEGINNING: org.stng.jbehave.RemoteWebDriverListener.afterInvocation");
        if (method.isTestMethod()) {
            try {
                String browser = DriverManager.getBrowserInfo();
                // change the name of the test method that will appear in the report to one that will contain
                // also browser name, version and OS.
                // very handy when analysing results.
                BaseTestMethod bm = (BaseTestMethod)testResult.getMethod();
                Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
                f.setAccessible(true);
                String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + " - " + bm.getMethodName() + " - " + browser;
                log.info("Renaming test method name from: '" + bm.getMethodName() + "' to: '" + newTestName + "'");
                f.set(bm, newTestName);
            } catch (Exception ex) {
                System.out.println("afterInvocation exception:\n" + ex.getMessage());
                ex.printStackTrace();
            } finally {
                // close the browser
                WebDriver driver = DriverManager.getDriver();
                if (driver != null) {
                    driver.quit();
                }
            }
        }
        log.debug("END: org.stng.jbehave.RemoteWebDriverListener.afterInvocation");
    }
}