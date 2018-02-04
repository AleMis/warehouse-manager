package am.warehouse.mapper;

import am.warehouse.domain.discount.Discount;
import am.warehouse.domain.discount.DiscountDto;
import am.warehouse.domain.product.Product;
import am.warehouse.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DiscountMapper {

    @Autowired
    private DbService dbService;

    public Discount mapDiscountDtoToDiscount(DiscountDto discountDto) {
        Optional<Product> product = getProductByIndividualNumber(discountDto.getProductIndividualNumber());
        Long productId = null;
        if(product.isPresent()) {
            productId = product.get().getId();
        }
        return new Discount(discountDto.getId(), productId, discountDto.getProductIndividualNumber(), discountDto.getDescription(),
                discountDto.getSince(), discountDto.getUntil(), discountDto.getDiscountSize());
    }

    public DiscountDto mapDiscountToDiscountDto(Discount discount) {
        return new DiscountDto(discount.getId(), discount.getProductIndividualNumber(), discount.getDescription(), discount.getSince(),
                discount.getUntil(), discount.getDiscountSize());
    }

    private Optional<Product> getProductByIndividualNumber(String individualNumber) {
        return dbService.findProductByIndividualNumber(individualNumber);
    }
}
