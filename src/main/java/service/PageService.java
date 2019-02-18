package service;

import domain.Product;
import scraper.PageScraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageService {

    private final PageScraper scraper;

    public PageService(PageScraper scraper) {
        this.scraper = scraper;
    }

    public List<Product> extractProductsFromPage(String url) throws IOException {
        List<String> productLinks = scraper.getProductLinksFromUrl(url);
        List<Product> products = new ArrayList<>();
        for(String productUrl : productLinks){
            products.add(scraper.getProductFromUrl(productUrl));
        }
        return products;
    }
}
