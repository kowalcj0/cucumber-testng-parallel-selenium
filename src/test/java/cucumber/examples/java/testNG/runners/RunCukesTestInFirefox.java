package cucumber.examples.java.testNG.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "target/test-classes/features",
        glue = {"cucumber.examples.java.testNG.stepDefinitions"},
        format = {"pretty",
                "html:target/cucumber-report/firefox",
                "json:target/cucumber-report/firefox/cucumber.json",
                "junit:target/cucumber-report/firefox/cucumber.xml"})
public class RunCukesTestInFirefox extends AbstractTestNGCucumberTests {
}
