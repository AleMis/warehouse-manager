package am.warehouse.repository;

import am.warehouse.domain.delivery.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    @Override
    Delivery save(Delivery warehouseIn);

    Optional<Delivery> findById(Long id);

    List<Delivery> findAllByProductIndividualNumber(String productIndividualNumber);
}
