package am.warehouse.domain.warehouse;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WarehouseDto {

    private Long id;
    private Long productId;
    private String productIndividualNumber;
    private Integer units;
}
