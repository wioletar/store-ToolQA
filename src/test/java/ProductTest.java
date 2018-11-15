import model.Basket;
import model.Product;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import providers.UserFactory;

import java.util.List;

public class ProductTest extends BaseTest {

    private MenuPage menuPage;
    private CategoryPage categoryPage;
    private ProductPage productPage;
    private BasketPage basketPage;
    private List<Product> productListObject;
    private InfoPage infoPage;
    private SummaryPage summaryPage;
    private User user;
    private UserFactory userFactory;
    private Basket basket;


    @BeforeEach
    public void setup() {
        menuPage = new MenuPage(driver);
        categoryPage = new CategoryPage(driver);
        productPage = new ProductPage(driver);
        infoPage = new InfoPage(driver);
        userFactory = new UserFactory();
        user = userFactory.getWholeUser();
        basket = new Basket();

    }

    @Test
    public void selectProductTest() {
        for (int i = 0; i < 3; i++) {
            menuPage.selectProductCategoryButton().ClickToRandomSubmenuCategory();
            String nameToCheck = categoryPage.clickToRandomProduct();
            productPage.verifyName(nameToCheck).clickAddToCart(basket);
        }
        menuPage.clickToBasket();

        basketPage = new BasketPage(driver, productListObject);

        basketPage.addProductToBasketassertions(basket)
                .clickContinueButton();
        infoPage.selectSameAsBillingAddress()
                .setUserData(user)
                .setUserAddressData(user)
                .getTotalShippingCost(basket)
                .clickPurchase();
        summaryPage = new SummaryPage(driver, productListObject);
        summaryPage.verifyTotalOrder(basket);
    }
}
