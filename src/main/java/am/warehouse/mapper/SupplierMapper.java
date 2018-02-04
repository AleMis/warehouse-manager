package am.warehouse.mapper;

import am.warehouse.domain.supplier.Supplier;
import am.warehouse.domain.supplier.SupplierDto;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier mapSupplierDtoToSupplier(SupplierDto supplierDto) {
        return new Supplier(supplierDto.getId(), supplierDto.getName(), supplierDto.getShortName(), supplierDto.getCountry(), supplierDto.getCity(), supplierDto.getAddress());
    }

    public SupplierDto mapSupplierToSupplierDto(Supplier supplier) {
        return new SupplierDto(supplier.getId(), supplier.getName(), supplier.getShortName(), supplier.getCountry(), supplier.getCity(), supplier.getAddress());
    }
}
