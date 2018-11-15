package pages;

import model.Basket;
import model.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.WebElementManiputator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductPage extends WebElementManiputator {

    Random random;
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.random=new Random();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h1[class='prodtitle']")
    private WebElement productName;
    @FindBy(css = ".currentprice")
    private WebElement price;
    @FindBy(css = "input[name='Buy']")
    private WebElement addToCardButton;

    @FindBy(css = "em[class='count']")
    private WebElement counter;


    public ProductPage verifyName(String nameToCheck) {
        assertEquals(nameToCheck, productName.getText());
        return this;
    }

    public void clickAddToCart(Basket basket) {
        int amount = random.nextInt(2) + 1;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int i = 0; i <= amount; i++) {
            int counterInt = Integer.parseInt(counter.getText());
            basket.addNewProductToList(productName.getText(), new BigDecimal(price.getText().substring(1).replaceAll(",", "")));
            click(addToCardButton);
            wait.until(ExpectedConditions.attributeContains(counter, "innerHTML", Integer.toString(counterInt + 1)));
        }
    }

}


