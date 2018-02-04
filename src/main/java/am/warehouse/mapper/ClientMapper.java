package am.warehouse.mapper;

import am.warehouse.domain.client.Client;
import am.warehouse.domain.client.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client mapClientDtoToClient(ClientDto clientDto) {
        return new Client(clientDto.getId(), clientDto.getName(), clientDto.getShortName(), clientDto.getCountry(), clientDto.getCity(), clientDto.getAddress());
    }

    public ClientDto mapClientToClientDto(Client client) {
        return new ClientDto(client.getId(), client.getName(), client.getShortName(), client.getCountry(), client.getCity(), client.getAddress());
    }
}
