package am.warehouse.domain.discount;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @NotNull
    @Column(name = "product_individual_number")
    private String productIndividualNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "since")
    @Temporal(TemporalType.DATE)
    private Date since;

    @Column(name = "until")
    @Temporal(TemporalType.DATE)
    private Date until;

    @Column(name = "discount_size")
    private Double discountSize;
}
