package scraper;

import domain.Product;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PageScraperTest {

    private PageScraper scraper = new PageScraper();

    @Test
    public void getProductLinksFromUrl_whenValidUrl_thenReturnValidProductUrls() throws IOException {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/" +
                "servlet/gb/groceries/berries-cherries-currants6039.html";

        List<String> productLinks = scraper.getProductLinksFromUrl(url);

        assertThat(productLinks).hasSize(17);
    }

    @Test
    public void getProductLinksFromUrl_whenInvalidUrl_thenThrowException() {
        assertThatThrownBy(() -> scraper.getProductLinksFromUrl("NotAValidUrl"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Malformed URL: NotAValidUrl");
    }


    @Test
    public void getProductFromUrl_whenValidUrl_thenReturnCompleteProduct() throws IOException {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/" +
                "gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";

        Product product = scraper.getProductFromUrl(url);

        assertThat(product.getTitle()).isEqualTo("Sainsbury's Strawberries 400g");
        assertThat(product.getPricePerUnit()).isEqualTo(new BigDecimal(1.75));
        assertThat(product.getKcalPer100grams()).isEqualTo(33);
        assertThat(product.getDescription()).isEqualTo("by Sainsbury's strawberries");

    }

    @Test
    public void getProductFromUrl_whenProductDescriptionHasMultipleLines_onlyExtractFirstLineOfDescription() throws IOException {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/" +
                "berries-cherries-currants/sainsburys-mixed-berry-twin-pack-200g-7696255-p-44.html";

        Product product = scraper.getProductFromUrl(url);

        assertThat(product.getDescription()).isEqualTo("Mixed Berries");
    }

    @Test
    public void getProductFromUrl_whenProductHasNoNutritionalValues_thenKcalPer100GramsIsNull() throws IOException {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/" +
                "berries-cherries-currants/sainsburys-british-cherry---strawberry-pack-600g.html";

        Product product = scraper.getProductFromUrl(url);

        assertThat(product.getKcalPer100grams()).isNull();
    }
}