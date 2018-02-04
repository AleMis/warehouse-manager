package am.warehouse.domain.client;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientDto {

    private Long id;
    private String name;
    private String shortName;
    private String country;
    private String city;
    private String address;

}
