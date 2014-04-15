package cucumber.examples.java.testNG.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "target/test-classes/features/bugs/bug_no_12345.feature",
        glue = {"cucumber.examples.java.testNG.stepDefinitions"},
        format = {"pretty",
                "html:target/cucumber-report/singleFeature",
                "json:target/cucumber-report/singleFeature/cucumber.json",
                "junit:target/cucumber-report/singleFeature/cucumber.xml"})
public class RunSingleFeature extends AbstractTestNGCucumberTests {
}