package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.WebElementManiputator;

import java.util.Arrays;
import java.util.List;

public class MenuPage extends WebElementManiputator {

    public MenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitUntilElementsVisible();
    }

    @FindBy(css = "li[id='menu-item-15']")
    private WebElement menuHomeButton;

    @FindBy(css = "li[id='menu-item-33']")
    private WebElement menuProductCategory;

    @FindBy(css = "ul[class='sub-menu'] .menu-item-object-wpsc_product_category")
    private List<WebElement> subMenuCategoryList;

    @FindBy(css = "div[id='header_cart'] span[class='icon']")
    private WebElement basketIcon;

    @FindBy(css = "em[class='count']")
    private WebElement counter;

    public void waitUntilElementsVisible() {
        waitForElements(Arrays.asList(menuHomeButton, menuProductCategory, basketIcon));
    }

    public void clickToBasket() {
        click(basketIcon);
    }

    public MenuPage selectProductCategoryButton() {
        selectHoverElement(menuProductCategory);
        return this;
    }

    public void ClickToRandomSubmenuCategory() {
        WebElement category = randomElement(subMenuCategoryList);
        click(category);
    }

}
