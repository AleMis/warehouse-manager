package am.warehouse.validation;

import am.warehouse.domain.discount.Discount;
import am.warehouse.domain.discount.DiscountDto;
import am.warehouse.mapper.DiscountMapper;
import am.warehouse.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DiscountValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountValidation.class);

    @Autowired
    private DiscountMapper discountMapper;

    @Autowired
    private DbService dbService;

    public DiscountDto checkDiscount(DiscountDto discountDto) {
        Optional<Discount> discount = dbService.getDiscountByProductIndividualNumber(discountDto.getProductIndividualNumber());
        if(discount.isPresent()) {
            LOGGER.info("Discount for product [" + discountDto.getProductIndividualNumber() + "]  is present in database.");
            return discountMapper.mapDiscountToDiscountDto(discount.get());
        }else {
            LOGGER.info("New discount for product [" + discountDto.getProductIndividualNumber() + "]  was added to database.");
            return discountMapper.mapDiscountToDiscountDto(dbService.saveDiscount(discountMapper.mapDiscountDtoToDiscount(discountDto)));
        }
    }
}
