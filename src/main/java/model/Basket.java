package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    private BigDecimal totalShippingCost;
    List<Product> productList = new ArrayList<>();


    public void addNewProductToList(String name, BigDecimal price) {
        for (Product element : productList) {
            if (element.getProductName().equals(name)) {
                element.increaseQuantity();
                return;
            }
        }
        productList.add(new Product(name, price));
    }

    public List<Product> getProductList() {
        return productList;
    }

    public int getQuantityOfProducts() {
        int quantityOfProduct = 0;
        for (Product product : productList) {
            quantityOfProduct += product.getQuantity();
        }
        return quantityOfProduct;
    }

    public BigDecimal getTotalShippingCost() {
        return totalShippingCost;
    }

    public BigDecimal calculateSubTotalPrice() {
        BigDecimal sum = new BigDecimal(0);
        for (Product element : productList) {
            sum = sum.add(element.calculateTotalPrice());
        }
        return sum;
    }

    public BigDecimal getTotalShippingCostWithTotalPrice() {
        return totalShippingCost.add(calculateSubTotalPrice());
    }

    public void setTotalShippingCost(BigDecimal totalShippingCost) {
        this.totalShippingCost = totalShippingCost;
    }

}
