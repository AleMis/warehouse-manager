package am.warehouse.repository;

import am.warehouse.domain.client.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{

    @Override
    Client save(Client client);

    Optional<Client> findClientById(Long id);
}
