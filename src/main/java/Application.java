import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import domain.Product;
import domain.Total;
import service.ProductService;
import service.TotalService;

import java.util.List;

public class Application {

    private final static ProductService productService = new ProductService();
    private final static TotalService totalService = new TotalService();
    private final static ObjectMapper mapper = createObjectMapper();


    public static void main(String[] args) throws Exception{
        List<Product> products = productService.extractProductsFromPage("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/" +
                "servlet/gb/groceries/berries-cherries-currants6039.html");
        Total total = totalService.calculateTotalFromProducts(products);
        System.out.println(mapper.writeValueAsString(products));

    }

    private static ObjectMapper createObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }
}
