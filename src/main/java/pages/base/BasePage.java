package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MenuPage;

import java.util.List;
import java.util.Random;

public abstract class BasePage {

   private WebDriver driver;
   //private Actions action;
   protected Random random;

    public BasePage(WebDriver driver) {
        this.driver = driver;
      //  action = new Actions(driver);
        random = new Random();
    }

    protected void waitForElements(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void waitToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForText(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected WebElement randomElement(List<WebElement> listOfElements) {
        int n = random.nextInt(listOfElements.size());
        WebElement element = listOfElements.get(n);
        return element;
    }
    public void selectHoverElement(WebElement element) {
        Actions action= new Actions(driver);
        action.moveToElement(element).build().perform();
    }

}
