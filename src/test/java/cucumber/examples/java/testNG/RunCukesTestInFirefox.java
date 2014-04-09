package cucumber.examples.java.testNG;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/test-classes/cucumber/examples/java/testNG",
        format = {"pretty",
                "html:target/cucumber-report/firefox",
                "json:target/cucumber-report/firefox/cucumber.json",
                "junit:target/cucumber-report/firefox/cucumber.xml"})
public class RunCukesTestInFirefox extends AbstractTestNGCucumberTests {
}
