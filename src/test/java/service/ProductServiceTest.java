package service;

import domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import scraper.PageScraper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private PageScraper pageScraper;

    @InjectMocks
    private ProductService pageService;

    @Test
    public void extractProductsFromUrl() throws IOException {
        List<String> productUrls = Arrays.asList("ProductUrlOne", "ProductUrlTwo", "ProductUrlThree");
        Product productOne = new Product("ProductOne", "ProductOne", new BigDecimal(1.00), 1);
        Product productTwo = new Product("ProductTwo", "ProductTwo", new BigDecimal(2.00), 2);
        Product productThree = new Product("ProductThree", "ProductThree", new BigDecimal(3.00), 3);

        when(pageScraper.getProductLinksFromUrl(any())).thenReturn(productUrls);
        when(pageScraper.getProductFromUrl("ProductUrlOne")).thenReturn(productOne);
        when(pageScraper.getProductFromUrl("ProductUrlTwo")).thenReturn(productTwo);
        when(pageScraper.getProductFromUrl("ProductUrlThree")).thenReturn(productThree);

        List<Product> products = pageService.extractProductsFromPage("ValidUrl");

        assertThat(products).contains(productOne, productTwo, productThree);
    }




}