package cucumber.examples.java.calculator;

import org.apache.log4j.Logger;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

/**
 * Created by jk on 03/03/14.
 */
public class BasicStepdefs {


    static Logger log = Logger.getLogger(BasicStepdefs.class);
    WebDriver driver = DriverManager.getDriver();;

/*    public BasicStepdefs(){
        this.driver = DriverManager.getDriver();
    }*/


    @Given("^a system state$")
    public void givenASystemState() {
        log.info("Given a system state<br/>");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        //log.info(String.format("I'm running step: Given a system state! using: %s / %s", driver.getClass().toString(), ste[ste.length - 1 - 1].getMethodName()));
        driver.get("http://google.pl");
    }

    @When("^I do something$")
    public void whenIDoSomething() {
        log.info("I'm running step: When I do something");
    }

    @Then("^system is in a normal state$")
    public void thenSystemIsInANormalState() {
        log.info("I'm running step: Then system is in a normal state");
    }
}
