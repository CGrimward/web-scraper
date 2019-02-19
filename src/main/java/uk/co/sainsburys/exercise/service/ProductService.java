package uk.co.sainsburys.exercise.service;

import uk.co.sainsburys.exercise.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.sainsburys.exercise.scraper.PageScraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private final PageScraper scraper;

    public ProductService(PageScraper scraper) {
        this.scraper = scraper;
    }

    public List<Product> extractProductsFromPage(String url) throws IOException {
        LOGGER.info("Attempting to scrape products from {}", url);
        List<String> productLinks = scraper.getProductLinksFromUrl(url);
        List<Product> products = new ArrayList<>();

        for(String productUrl : productLinks){
            products.add(scraper.getProductFromUrl(productUrl));
        }
        return products;
    }
}
