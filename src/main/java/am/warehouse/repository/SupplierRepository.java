package am.warehouse.repository;

import am.warehouse.domain.supplier.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    @Override
    Supplier save(Supplier supplier);

    Optional<Supplier> findSupplierById(Long id);
}
