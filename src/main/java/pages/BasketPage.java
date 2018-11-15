package pages;

import model.Basket;
import model.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.WebElementManiputator;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketPage extends WebElementManiputator {

    List<Product> productsList;

    public BasketPage(WebDriver driver, List<Product> productsList) {
        super(driver);
        this.productsList = productsList;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span[class='yourtotal'] span[class='pricedisplay']")
    private WebElement subTotalPrice;

    @FindBy(css = "td.wpsc_product_name")
    private List<WebElement> productName;

    @FindBy(css = "input[name='quantity'][type='text']")
    private List<WebElement> quantity;

    @FindBy(xpath = "//tr[contains(@class,'product_row')]/td[4]/span")
    private List<WebElement> price;

    @FindBy(xpath = "//tr[contains(@class,'product_row')]/td[5]/span")
    private List<WebElement> totalPrice;

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    private WebElement continueButton;

    public void clickContinueButton() {
        click(continueButton);
    }

    public BasketPage addProductToBasketassertions(Basket basket) {

        for (int i = 0; i < basket.getProductList().size(); i++) {

            assertEquals(basket.getProductList().get(i).getProductName().replaceAll("–", "-"), productName.get(i).getText());

            assertEquals(basket.getProductList().get(i).getPrice(), new BigDecimal(price.get(i).getText().substring(1)));

            assertEquals(basket.getProductList().get(i).getQuantity(), Integer.parseInt(quantity.get(i).getAttribute("value")));

            assertEquals(basket.getProductList().get(i).calculateTotalPrice(), new BigDecimal(totalPrice.get(i).getText().substring(1).replaceAll(",", "")));
        }
        assertEquals(basket.calculateSubTotalPrice(), new BigDecimal(subTotalPrice.getText().substring(1).replaceAll(",", "")));
        return this;
    }

}
