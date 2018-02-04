package am.warehouse.repository;

import am.warehouse.domain.warehouse.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

    @Override
    Warehouse save(Warehouse warehouse);

    Warehouse findByProductIndividualNumber(String productIndividualNumber);
}
