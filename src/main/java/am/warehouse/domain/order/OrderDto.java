package am.warehouse.domain.order;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {

    private Long id;
    private Long productId;
    private Long clientId;
    private String productIndividualNumber;
    private BigDecimal unitPrice;
    private Integer units;
    private BigDecimal transactionValue;
}
