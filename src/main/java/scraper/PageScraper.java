package scraper;

import domain.Product;
import domain.ProductBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PageScraper {

    public List<String> getProductLinksFromUrl(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements productNames = document.getElementsByAttributeValue("class", "productNameAndPromotions");
        return productNames.stream()
                .map(name -> name.getElementsByTag("a"))
                .map(aTag -> aTag.attr("abs:href"))
                .collect(Collectors.toList());
    }

    public Product getProductFromUrl(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        String title = extractProductTitle(document);
        String description = extractProductDescription(document);
        BigDecimal pricePerUnit = extractProductPricePerUnit(document);
        Integer kcalPer100Grams = extractKcalPer100Grams(document);

        return new ProductBuilder()
                .withTitle(title)
                .withDescription(description)
                .withPricePerUnit(pricePerUnit)
                .withkcalPer100grams(kcalPer100Grams)
                .build();
    }

    private String extractProductTitle(Document document) {
        Elements productTitleElement = document.select("div.productTitleDescriptionContainer > h1");
        return productTitleElement.text();
    }

    private String extractProductDescription(Document document) {
        Elements productDescriptionElements = document.select("h3:contains(Description) + div.productText > p");
        String productDescription = null;
        if(productDescriptionElements != null){
            productDescription = productDescriptionElements.first().text();
        }
        return productDescription;
    }

    private BigDecimal extractProductPricePerUnit(Document document){
        Elements productPricePerUnitElement = document.getElementsByAttributeValue("class", "pricePerUnit");
        String productPricePerUnit = productPricePerUnitElement.first().text();
        productPricePerUnit = productPricePerUnit.split("/")[0].substring(1);
        return new BigDecimal(productPricePerUnit);
    }

    private Integer extractKcalPer100Grams(Document document){
        Elements nutritionTables = document.select("table.NutritionTable");
        String productKcalPer100Grams = null;
        if(nutritionTables.size() >= 1){
            productKcalPer100Grams = nutritionTables.first().select("td:contains(kcal)").text();
            productKcalPer100Grams = productKcalPer100Grams.split("k")[0];
        }
        return productKcalPer100Grams != null ? Integer.valueOf(productKcalPer100Grams) : null;
    }
}
