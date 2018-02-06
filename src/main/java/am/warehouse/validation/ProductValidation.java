package am.warehouse.validation;

import am.warehouse.domain.discount.Discount;
import am.warehouse.domain.product.Product;
import am.warehouse.domain.product.ProductDto;
import am.warehouse.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductValidation.class);

    @Autowired
    private DbService dbService;

    public ProductDto getCurrentProductData(Long productId) {
        Optional<Product> product = dbService.getProductById(productId);
        return product.map(this::processProductParameters).orElseGet(ProductDto::new);
    }

    public List<ProductDto> getCurrentDataForAllProducts() {
        List<Product> products = dbService.getAllProducts();
        return products.stream().map(product -> processProductParameters(product)).collect(Collectors.toList());
    }

    private ProductDto processProductParameters(Product product) {
        Optional<Discount> discount = dbService.getDiscountByProductIndividualNumber(product.getIndividualNumber());
        Double discountSize = 0.00;
        BigDecimal priceAfterDiscount = product.getBasePrice();
        if(discount.isPresent()) {
            discountSize = discount.get().getDiscountSize();
            priceAfterDiscount = calculatePriceAfterDiscount(discount.get(), product);
        }
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getIndividualNumber(),
                product.getDescription(),
                product.getManufacturer(),
                product.getBasePrice(),
                discountSize,
                priceAfterDiscount);
    }

    private BigDecimal calculatePriceAfterDiscount(Discount discount, Product product) {
        Long currentTimestamp = System.currentTimeMillis();
        if(currentTimestamp >= discount.getSince().getTime() && currentTimestamp <= discount.getUntil().getTime()) {
            BigDecimal  currentPrice = product.getBasePrice().multiply(new BigDecimal(1- discount.getDiscountSize())).setScale(2, BigDecimal.ROUND_UP);

            LOGGER.info("Discount for product: " + product.getIndividualNumber() + " equals " + discount.getDiscountSize()
                    + ". Product price after discount equals " + currentPrice);

            return currentPrice;
        }else {

            LOGGER.info("There is no discount for product: " + product.getIndividualNumber());
            return product.getBasePrice();
        }
    }
}
