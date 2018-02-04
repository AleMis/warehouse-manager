package am.warehouse.mapper;

import am.warehouse.domain.product.Product;
import am.warehouse.domain.product.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapProductDtoToProduct(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getIndividualNumber(), productDto.getDescription(), productDto.getManufacturer(), productDto.getBasePrice(), productDto.getCurrentDiscount(), productDto.getPriceAfterDiscount());
    }

    public ProductDto mapProductToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getIndividualNumber(), product.getDescription(), product.getManufacturer(), product.getBasePrice(), product.getCurrentDiscount(), product.getPriceAfterDiscount());
    }
}
