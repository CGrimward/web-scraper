package uk.co.sainsburys.exercise;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import uk.co.sainsburys.exercise.domain.Product;
import uk.co.sainsburys.exercise.domain.Result;
import uk.co.sainsburys.exercise.domain.Total;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.sainsburys.exercise.scraper.PageScraper;
import uk.co.sainsburys.exercise.service.ProductService;
import uk.co.sainsburys.exercise.service.TotalService;

import java.util.List;

public class Application {

    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private final static ProductService productService = createProductService();
    private final static TotalService totalService = new TotalService();
    private final static ObjectMapper mapper = createObjectMapper();


    public static void main(String[] args) throws Exception {
        if(args.length == 0) {
            LOGGER.error("No URL specified for scraping");
        }
        List<Product> products = productService.extractProductsFromPage(args[0]);
        Total total = totalService.calculateTotalFromProducts(products);
        LOGGER.info("Compiling results.....");
        System.out.println(createOutput(products, total));

    }

    private static ObjectMapper createObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    private static ProductService createProductService(){
        return new ProductService(new PageScraper());
    }

    private static String createOutput(List<Product> products, Total total) throws JsonProcessingException {
        Result result = new Result(products, total);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }
}
