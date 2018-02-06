package am.warehouse.domain.product;


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
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="product_name")
    private String name;

    @Column(name="product_individual_number")
    private String individualNumber;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_manufacturer")
    private String manufacturer;

    @Column(name = "base_price")
    private BigDecimal basePrice;
}
