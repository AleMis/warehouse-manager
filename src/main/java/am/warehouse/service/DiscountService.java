package am.warehouse.service;

import am.warehouse.domain.discount.Discount;
import am.warehouse.domain.discount.DiscountDto;
import am.warehouse.domain.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class DiscountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountService.class);

    @Autowired
    private DbService dbService;

    public boolean updateProductDiscount(Discount discount) {
        if(checkProductInDatabase(discount.getProductIndividualNumber())) {
           Optional<Product> product = dbService.findProductByIndividualNumber(discount.getProductIndividualNumber());
           product.get().setCurrentDiscount(discount.getDiscountSize());
           product.get().setPriceAfterDiscount(calculateDiscoutPrice(discount, product.get()));
           dbService.saveProduct(product.get());
           LOGGER.info("Product [" + discount.getProductIndividualNumber() + "] discount updated");
            return true;
        }else {
            LOGGER.info("Product  [" + discount.getProductIndividualNumber() + "] cannot be updated. There is no product with this number.");
            return false;
        }
    }

    private boolean checkProductInDatabase(String individualProductNumber) {
        return dbService.findProductByIndividualNumber(individualProductNumber).isPresent();
    }

    private BigDecimal calculateDiscoutPrice(Discount discount, Product product) {
        BigDecimal discountFactor = BigDecimal.ONE.subtract(new BigDecimal(discount.getDiscountSize()));
        return product.getBasePrice().multiply(discountFactor);
    }


}
