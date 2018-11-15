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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SummaryPage extends WebElementManiputator {
    List<Product> productsList;

    public SummaryPage(WebDriver driver, List<Product> productsList) {
        super(driver);
        this.productsList = productsList;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tbody tr")
    private List<WebElement> listOfProducts;

    @FindBy(xpath = "//p[contains(text(),'Total')]")
    private WebElement totalPriceWithShipping;
    @FindBy(xpath = "//p[contains(text(),'Total Shipping')]")
    private WebElement totalShippingPrice;

    @FindBy(css = "td:nth-child(1)")
    private List<WebElement> productName;

    @FindBy(css = "td:nth-child(3)")
    private List<WebElement> quantity;

    @FindBy(css = "td:nth-child(2)")
    private List<WebElement> price;

    @FindBy(css = "td:nth-child(4)")
    private List<WebElement> totalPrice;


    public void verifyTotalOrder(Basket basket) {

        for (int i = 0; i < basket.getProductList().size(); i++) {

            assertEquals(basket.getProductList().get(i).getProductName(), productName.get(i).getText().replaceAll("–", "-"));

            assertEquals(basket.getProductList().get(i).getPrice(), new BigDecimal(price.get(i).getText().substring(1)));

            assertEquals(basket.getProductList().get(i).getQuantity(), Integer.parseInt(quantity.get(i).getText()));

            assertEquals(basket.getProductList().get(i).calculateTotalPrice(), new BigDecimal(totalPrice.get(i).getText().substring(1).replaceAll(",", "")));
        }
        assertTrue(totalPriceWithShipping.getText().replaceAll(",", "").contains(basket.getTotalShippingCostWithTotalPrice().toString()));
        assertTrue(totalShippingPrice.getText().replaceAll(",", "").contains(basket.getTotalShippingCost().toString()));
    }

}
