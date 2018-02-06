package am.warehouse.mapper;

import am.warehouse.domain.product.Product;
import am.warehouse.domain.product.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product mapProductDtoToProduct(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getIndividualNumber(), productDto.getDescription(), productDto.getManufacturer(), productDto.getBasePrice());
    }

    public ProductDto mapProductToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getIndividualNumber(), product.getDescription(), product.getManufacturer(), product.getBasePrice());
    }
}
