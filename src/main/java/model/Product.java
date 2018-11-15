package model;

import java.math.BigDecimal;

public class Product {

    private String productName;
    private int quantity;
    private BigDecimal price;

    public Product(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
        this.quantity = 1;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal calculateTotalPrice() {
        return BigDecimal.valueOf(quantity).multiply(price);
    }
}
