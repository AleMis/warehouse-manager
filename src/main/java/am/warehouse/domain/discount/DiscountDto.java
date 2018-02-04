package am.warehouse.domain.discount;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DiscountDto {

    private Long id;
    private String productIndividualNumber;
    private String description;
    private Date since;
    private Date until;
    private Double discountSize;
}
