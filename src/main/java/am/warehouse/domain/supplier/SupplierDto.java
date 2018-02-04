package am.warehouse.domain.supplier;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierDto {

    private Long id;
    private String name;
    private String shortName;
    private String country;
    private String city;
    private String address;
}
