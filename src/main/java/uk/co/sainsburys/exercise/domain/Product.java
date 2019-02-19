package uk.co.sainsburys.exercise.domain;

import java.math.BigDecimal;

public class Product {

    private String title;
    private BigDecimal pricePerUnit;
    private Integer kcalPer100g;
    private String description;

    public Product(String title, String description, BigDecimal pricePerUnit, Integer kcalPer100g) {
        this.title = title;
        this.description = description;
        this.kcalPer100g = kcalPer100g;
        this.pricePerUnit = pricePerUnit;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public Integer getKcalPer100g() {
        return kcalPer100g;
    }

    public String getDescription() {
        return description;
    }
}
