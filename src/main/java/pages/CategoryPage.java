package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.WebElementManiputator;

import java.util.List;

public class CategoryPage extends WebElementManiputator {


    public CategoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[class*='wpsc_product_title']")
    private List<WebElement> productNames;

    public String clickToRandomProduct() {
        WebElement element = randomElement(productNames);
        String name = element.getText();
        click(element);
        return name;
    }

}
