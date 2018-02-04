package am.warehouse.mapper;

import am.warehouse.domain.warehouse.*;

public class WarehouseMapper {

    public WarehouseDto mapWarehouseToWarehouseDto(Warehouse warehouse) {
        return new WarehouseDto(warehouse.getId(), warehouse.getProductId(), warehouse.getProductIndividualNumber(),warehouse.getUnits());
    }

}
