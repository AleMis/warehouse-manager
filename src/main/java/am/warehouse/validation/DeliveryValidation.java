package am.warehouse.validation;

import am.warehouse.domain.delivery.Delivery;
import am.warehouse.domain.delivery.DeliveryDto;
import am.warehouse.domain.product.Product;
import am.warehouse.mapper.DeliveryMapper;
import am.warehouse.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeliveryValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryValidation.class);

    @Autowired
    private DbService dbService;

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private WarehouseValidation warehouseValidation;

    public DeliveryDto checkProductForDeliveryPurpose(DeliveryDto deliveryDto) {
        Optional<Product> product = dbService.findProductByIndividualNumber(deliveryDto.getProductIndividualNumber());
        DeliveryDto deliveryToReturn = null;
        if (product.isPresent()) {
            deliveryToReturn = deliveryMapper.mapWarehouseInToWarehouseInDto(dbService.saveDelivery(deliveryMapper.mapWarehouseInDtoToWarehouseIn(deliveryDto)));
            warehouseValidation.updateWarehouseAfterDelivery(deliveryMapper.mapWarehouseInDtoToWarehouseIn(deliveryDto));
            LOGGER.info("New delivery added to database " + deliveryToReturn);
        }else {
            LOGGER.info("Product should be added first into database!");
        }
        return deliveryToReturn;
    }
}
