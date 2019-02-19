package uk.co.sainsburys.exercise.service;

import uk.co.sainsburys.exercise.domain.Product;
import uk.co.sainsburys.exercise.domain.Total;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TotalService {

    private static final BigDecimal  VAT_PERCENTAGE = new BigDecimal(0.20);

    public Total calculateTotalFromProducts(List<Product> productList) {
        BigDecimal grossTotal = productList.stream()
                .map(Product::getPricePerUnit)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0.00))
                .setScale(2, RoundingMode.DOWN);
        BigDecimal vat = grossTotal.multiply(VAT_PERCENTAGE).setScale(2, RoundingMode.DOWN);
        return new Total(grossTotal, vat);
    }
}
