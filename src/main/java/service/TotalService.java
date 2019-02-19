package service;

import domain.Product;
import domain.Total;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TotalService {

    public Total calculateTotalFromProducts(List<Product> productList) {
        BigDecimal grossTotal = productList.stream()
                .map(Product::getPricePerUnit)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0.00))
                .setScale(2, RoundingMode.DOWN);
        BigDecimal vat = grossTotal.multiply(new BigDecimal(0.20)).setScale(2, RoundingMode.DOWN);
        return new Total(grossTotal, vat);
    }
}
