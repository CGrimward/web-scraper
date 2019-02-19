package service;

import domain.Product;
import domain.Total;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class TotalServiceTest {

    private TotalService totalService = new TotalService();

    @Test
    public void calculateTotalFromProducts_givenListOfProducts_thenCalculatesCorrectTotal(){
        List<Product> productList = Arrays.asList(new Product(null, null, new BigDecimal(2.50), 0),
                new Product(null, null, new BigDecimal(10.50), 0),
                new Product(null, null, new BigDecimal(7.00), 0));

        Total total = totalService.calculateTotalFromProducts(productList);

        assertThat(total.getGross()).isEqualTo(new BigDecimal(20.00).setScale(2));
        assertThat(total.getVat()).isEqualTo(new BigDecimal(4.00).setScale(2));

    }

    @Test
    public void calculateTotalFromProducts_givenNoProducts_returnsZeroTotal() {
        Total total = totalService.calculateTotalFromProducts(Collections.emptyList());

        assertThat(total.getGross()).isEqualTo(new BigDecimal(0.00).setScale(2));
        assertThat(total.getVat()).isEqualTo(new BigDecimal(0.00).setScale(2));    }
}