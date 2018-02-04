package am.warehouse.mapper;

import am.warehouse.domain.order.Order;
import am.warehouse.domain.order.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {

    public Order mapSaleDtoToWSale(OrderDto saleDto) {
        return new Order(saleDto.getId(), saleDto.getProductId(), saleDto.getClientId(), saleDto.getProductIndividualNumber(),saleDto.getUnitPrice(), saleDto.getUnitsChange(), saleDto.getTransactionValue());
    }

    public OrderDto mapSaleToSaleDto(Order sale) {
        return new OrderDto(sale.getId(), sale.getProductId(), sale.getClientId(), sale.getProductIndividualNumber() ,sale.getUnitPrice(), sale.getUnits(), sale.getTransactionValue());
    }
}
