package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

public class WebElementManiputator extends BasePage {

    public WebElementManiputator(WebDriver driver) {
        super(driver);
    }

    protected void click(WebElement element){
        System.out.println("Clicking on: "+(element.getText().equals("") ? element.getAttribute("value"): element.getText()));
        waitForClickElement(element);
        element.click();
    }
    protected void sendKeys(WebElement element, String textToSet){
        System.out.println("Setting text: "+textToSet);
        waitToBeVisible(element);
        element.sendKeys(textToSet);
    }
}
