package am.warehouse.domain.delivery;

import lombok.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeliveryDto {

    private Long id;
    private Long productId;
    private Long supplierId;
    private String productIndividualNumber;
    private BigDecimal unitPrice;
    private Integer unitsChange;
    private BigDecimal transactionValue;
}
