package scraper;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PageScraperTest {

    private PageScraper scraper = new PageScraper();

    @Test
    public void getProductLinksFromUrl_whenValidUrl_thenReturnValidProductUrls() throws IOException {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

        List<String> productLinks = scraper.getProductLinksFromUrl(url);

        assertThat(productLinks).hasSize(17);
    }

    @Test
    public void getProductLinksFromUrl_whenInvalidUrl_thenThrowException() {
        assertThatThrownBy(() -> scraper.getProductLinksFromUrl("NotAValidUrl"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Malformed URL: NotAValidUrl");
    }
}