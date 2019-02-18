package domain;

import java.math.BigDecimal;

public class Product {

    private String title;
    private BigDecimal pricePerUnit;
    private Integer kcalPer100grams;
    private String description;

    public Product(String title, String description, BigDecimal pricePerUnit, Integer calories) {
        this.title = title;
        this.description = description;
        this.kcalPer100grams = calories;
        this.pricePerUnit = pricePerUnit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getKcalPer100grams() {
        return kcalPer100grams;
    }

    public void setKcalPer100grams(Integer kcalPer100grams) {
        this.kcalPer100grams = kcalPer100grams;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
