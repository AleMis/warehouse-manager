package am.warehouse.service;

import am.warehouse.domain.order.Order;
import am.warehouse.domain.warehouse.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    private DbService dbService;

    public boolean checkProductAvailability(Order order) {
        Warehouse warehouse = dbService.getProductStatusByIndividualNumber(order.getProductIndividualNumber());
        if(warehouse == null) {
            return false;
        }else if(warehouse.getUnits() < order.getUnits()) {
            return false;
        }else {
            return true;
        }
    }
}
