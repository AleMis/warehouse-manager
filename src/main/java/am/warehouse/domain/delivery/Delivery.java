package am.warehouse.domain.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "product_individual_number")
    private String productIndividualNumber;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "units")
    private Integer units;

    @Column(name = "transaction_value")
    private BigDecimal transactionValue;

}
