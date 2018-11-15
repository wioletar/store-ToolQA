package pages;

import model.Basket;
import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.base.WebElementManiputator;

import java.math.BigDecimal;

public class InfoPage extends WebElementManiputator {


    public InfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[data-wpsc-meta-key='billingemail'][type='text']")
    private WebElement email;
    @FindBy(css = "input[data-wpsc-meta-key='billingfirstname'][type='text']")
    private WebElement firstName;
    @FindBy(css = "input[data-wpsc-meta-key='billinglastname'][type='text']")
    private WebElement lastName;
    @FindBy(css = "textarea[title='billingaddress']")
    private WebElement address;
    @FindBy(css = "input[data-wpsc-meta-key='billingcity'][type='text']")
    private WebElement city;
    @FindBy(css = "input[data-wpsc-meta-key='billingstate'][type='text']")
    private WebElement province;
    @FindBy(css = "select[data-wpsc-meta-key='billingcountry']")
    private WebElement country;
    @FindBy(css = "input[data-wpsc-meta-key='billingphone'][type='text']")
    private WebElement phone;
    @FindBy(css = "input[name='shippingSameBilling']")
    private WebElement sameAsBillingAddress;
    @FindBy(css = ".total_shipping .checkout-shipping .pricedisplay")
    private WebElement totalShippingCost;
    @FindBy(css = "input[value='Purchase']")
    private WebElement purchase;

    public InfoPage selectSameAsBillingAddress() {
        click(sameAsBillingAddress);
        return this;
    }

    public void clickPurchase() {
        click(purchase);
    }


    public InfoPage getTotalShippingCost(Basket basket) {
        basket.setTotalShippingCost(new BigDecimal(totalShippingCost.getText().substring(1).replaceAll(",", "")));
        return this;
    }

    public InfoPage setUserData(User user) {
        setEmail(user.getEmail());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setPhone(user.getPhone());
        return this;
    }

    public InfoPage setUserAddressData(User user) {
        setAddress(user.getAddress());
        setCity(user.getCity());
        setProvince(user.getProvince());
        setCountry(user.getCountry());
        return this;
    }

    public void setEmail(String email) {
        sendKeys(this.email, email);
    }

    public void setFirstName(String firstName) {
        sendKeys(this.firstName, firstName);
    }

    public void setLastName(String lastName) {
        sendKeys(this.lastName, lastName);
    }

    public void setAddress(String address) {
        sendKeys(this.address, address);
    }

    public void setCity(String city) {
        sendKeys(this.city, city);
    }

    public void setProvince(String province) {
        sendKeys(this.province, province);
    }

    public void setCountry(String countryName) {
        Select select = new Select(country);
        select.selectByVisibleText(countryName);
    }

    public void setPhone(String phone) {
        sendKeys(this.phone, phone);
    }


}
