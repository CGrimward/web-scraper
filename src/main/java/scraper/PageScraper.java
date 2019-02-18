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
        Elements productTitleElement = document.select("div.productTitleDescriptionContainer > h1");
        String productTitle = productTitleElement.text();
        Elements productDescriptionElements = document.select("h3:contains(Description) + div.productText > p");
        String productDescription = null;
        if(productDescriptionElements != null){
            productDescription = productDescriptionElements.first().text();
        }
        Elements productPricePerUnitElement = document.getElementsByAttributeValue("class", "pricePerUnit");
        String productPricePerUnit = productPricePerUnitElement.first().text();
        productPricePerUnit = productPricePerUnit.split("/")[0].substring(1);
        Elements nutritionTables = document.select("table.NutritionTable");
        String productKcalPer100Grams = null;
        if(nutritionTables.size() >= 1){
            productKcalPer100Grams = nutritionTables.first().select("td:contains(kcal)").text();
            productKcalPer100Grams = productKcalPer100Grams.split("k")[0];
        }
        return new ProductBuilder()
                .withTitle(productTitle)
                .withDescription(productDescription)
                .withPricePerUnit(new BigDecimal(productPricePerUnit))
                .withkcalPer100grams(Integer.valueOf(productKcalPer100Grams))
                .build();
    }
}
