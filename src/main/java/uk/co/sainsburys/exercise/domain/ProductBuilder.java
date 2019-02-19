package uk.co.sainsburys.exercise.domain;

import java.math.BigDecimal;

public class ProductBuilder {

    private String title;
    private String description;
    private BigDecimal pricePerUnit;
    private Integer kcalPer100g;

    public ProductBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public ProductBuilder withkcalPer100g(Integer kcalPer100grams) {
        this.kcalPer100g = kcalPer100grams;
        return this;
    }

    public Product build() {
        return new Product(title, description, pricePerUnit, kcalPer100g);
    }
}
