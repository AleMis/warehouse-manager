package am.warehouse.domain.product;

import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductDto {

    public ProductDto(Long id, String name, String individualNumber, String description, String manufacturer, BigDecimal basePrice) {
        this.id = id;
        this.name = name;
        this.individualNumber = individualNumber;
        this.description = description;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
    }

    private Long id;
    private String name;
    private String individualNumber;
    private String description;
    private String manufacturer;
    private BigDecimal basePrice;
    private Double currentDiscount;
    private BigDecimal priceAfterDiscount;
}
