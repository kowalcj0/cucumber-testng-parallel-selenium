package cucumber.examples.java.testNG.stepDefinitions.bugs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.examples.java.testNG.DriverManager;
import cucumber.examples.java.testNG.page_objects.GoogleCom;
import cucumber.examples.java.testNG.StepsUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jk on 03/03/14.
 */
public class Bug12345{

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    private GoogleCom googleCom;
    private List<WebElement> searchResults;

    static {
        log = Logger.getLogger(Bug12345.class);
    }

    @Given("^I search on google.com for (.+)")
    public void givenISearchFor(String keywords) {
        googleCom = new GoogleCom(this.driver);
        googleCom.go();
        googleCom.searchFor(keywords);
    }

    @When("^I get the search results$")
    public void whenIGetTheSearchResults() {
        this.searchResults = googleCom.getTheSearchResults();
    }

    @Then("^I should find the answer \"(.+)\" among the search result$")
    public void thenIShouldSeeThisElement(String answer) {
        boolean didIFindTheAnswerToMyQuery = false;
        for ( WebElement we : this.searchResults){
            // change the state of didIFindTheAnswerToMyQuery only if the answer was found
            didIFindTheAnswerToMyQuery = (we.getText().toLowerCase().contains(answer.toLowerCase())) ? true : didIFindTheAnswerToMyQuery;
        }
        assertThat("Couldn't find the answer for my question!!!", didIFindTheAnswerToMyQuery, is(true));
    }

    @Before
    public void deleteAllCookies(){ StepsUtils.deleteAllCookies(); }

    @After
    public void embedScreenshot(Scenario scenario){ StepsUtils.embedScreenshot(scenario); }

}
