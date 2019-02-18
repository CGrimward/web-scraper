package scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
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
}
