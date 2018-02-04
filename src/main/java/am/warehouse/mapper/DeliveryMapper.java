package am.warehouse.mapper;

import am.warehouse.domain.delivery.Delivery;
import am.warehouse.domain.delivery.DeliveryDto;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {

    public Delivery mapWarehouseInDtoToWarehouseIn(DeliveryDto deliveryDto) {
        return new Delivery(deliveryDto.getId(), deliveryDto.getProductId(), deliveryDto.getSupplierId(), deliveryDto.getProductIndividualNumber(), deliveryDto.getUnitPrice(), deliveryDto.getUnitsChange(), deliveryDto.getTransactionValue());
    }

    public DeliveryDto mapWarehouseInToWarehouseInDto(Delivery delivery) {
        return  new DeliveryDto(delivery.getId(), delivery.getProductId(), delivery.getSupplierId(), delivery.getProductIndividualNumber(), delivery.getUnitPrice(), delivery.getUnits(), delivery.getTransactionValue());
    }
}
