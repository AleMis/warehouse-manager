package am.warehouse.validation;

import am.warehouse.domain.delivery.Delivery;
import am.warehouse.domain.order.Order;
import am.warehouse.domain.warehouse.Warehouse;
import am.warehouse.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WarehouseValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseValidation.class);

    @Autowired
    private DbService dbService;

    public Warehouse updateWarehouseAfterDelivery(Delivery delivery) {
        Warehouse warehouse = null;
        if (checkProductInDatabase(delivery.getProductIndividualNumber())) {

            warehouse = checkProductStatusInWarehouse(delivery.getProductIndividualNumber());

            if (warehouse == null) {
                LOGGER.info("Product [" + delivery.getProductIndividualNumber() + "] not found in warehouse");
                warehouse = saveNewProductInWarehouse(delivery);
                LOGGER.info("Product [" + delivery.getProductIndividualNumber() + "] add to warehouse!");

            } else {
                LOGGER.info("Product status [" + delivery.getProductIndividualNumber() + "] updated in warehouse!");
                warehouse = updateProductStocksInWarehouse(delivery);
            }

        } else {
            LOGGER.error("Finding product [" + delivery.getProductIndividualNumber() + "] in database failed. Add product to database first!");
        }
        return warehouse;

    }

    private boolean checkProductInDatabase(String productIndividualNumber) {
        return dbService.findProductByIndividualNumber(productIndividualNumber).isPresent();
    }

    private Warehouse checkProductStatusInWarehouse(String productIndividualNumber) {
        return dbService.getProductStatusByIndividualNumber(productIndividualNumber);
    }

    private Long getProductId(String productIndividualNumber) {
        return dbService.findProductByIndividualNumber(productIndividualNumber).get().getId();
    }

    private Warehouse saveNewProductInWarehouse(Delivery delivery) {
        Long productId = getProductId(delivery.getProductIndividualNumber());
        Warehouse warehouse = new Warehouse(null, productId, delivery.getProductIndividualNumber(), delivery.getUnits());
        return dbService.saveWarehouse(warehouse);
    }

    private Warehouse updateProductStocksInWarehouse(Delivery delivery) {
        Warehouse warehouse = getProductCurrentWarehouseStatus(delivery);
        Integer warehouseUnits = 0;
        if(warehouse.getUnits() != null) {
            warehouseUnits = warehouse.getUnits();
        }
        warehouse.setUnits(warehouseUnits + delivery.getUnits());
        System.out.println(warehouse.getUnits());
        return warehouse;
    }

    private Warehouse getProductCurrentWarehouseStatus(Delivery delivery) {
        return dbService.getProductStatusByIndividualNumber(delivery.getProductIndividualNumber());
    }


    public void updateWarehouseAfterNewOrder(Order order) {
        Warehouse warehouse = checkProductStatusInWarehouse(order.getProductIndividualNumber());
        warehouse.setUnits(warehouse.getUnits() - order.getUnits());
        dbService.saveWarehouse(warehouse);
        LOGGER.info("Product [" + order.getProductIndividualNumber() + "] stocks updated in warehouse!'");
    }
}
