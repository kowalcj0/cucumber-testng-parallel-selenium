package cucumber.examples.java.testNG.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by jay on 10/04/14.
 */
public class GoogleCom {

    private By search_box = By.id("gbqfq");
    private By search_button = By.id("gbqfb");
    private By search_results = By.cssSelector("li.g");
    private By search_results_list = By.cssSelector("div.srg");
    private WebDriver driver;
    private WebDriverWait wait;
    private List<String> theSearchResults;

    public GoogleCom(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
    }

    public void go(){
        this.driver.get("https://www.google.co.uk/");
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(search_box));
    }


    public void searchFor(String keywords){
        this.driver.findElement(search_box).clear();
        this.driver.findElement(search_box).sendKeys(keywords);
        this.driver.findElement(search_button).click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(search_results_list));
    }

    public List<WebElement> getTheSearchResults() {
        return this.driver.findElements(search_results);
    }
}
