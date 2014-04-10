package cucumber.examples.java.testNG.stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.examples.java.testNG.DriverManager;
import cucumber.examples.java.testNG.StepsUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jk on 03/03/14.
 */
public class Feature1And2Stepdefs {

    static Logger log = Logger.getLogger(Feature1And2Stepdefs.class);
    WebDriver driver = DriverManager.getDriver();
    WebElement webElement;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Given("^I am on (.+)$")
    public void givenIAmOn(String URL) {
        log.info("Given a system state<br/>");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        log.info(String.format("I'm running step: Given I am on! using: %s / %s", driver.getClass().toString(), ste[ste.length - 1 - 1].getMethodName()));
        driver.get(URL);
    }

    @When("^I search for element (.+)$")
    public void whenISearchForElement(String element_id) {
        log.info("I'm running step: I search for element");
        webElement = driver.findElement(By.id(element_id));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element_id)));
    }

    @Then("^I should see this element$")
    public void thenIShouldSeeThisElement() {
        log.info("I'm running step: Then system is in a normal state");
        assertThat("Element is not visible!!!", webElement.isDisplayed(), is(true));
    }

    @Before
    public void deleteAllCookies(){ StepsUtils.deleteAllCookies(); }

    @After
    public void embedScreenshot(Scenario scenario){ StepsUtils.embedScreenshot(scenario); }
}
