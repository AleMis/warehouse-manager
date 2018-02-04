package am.warehouse.repository;

import am.warehouse.domain.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    Product save(Product product);

    Optional<Product> findById(Long id);

    Optional<Product> findByIndividualNumber(String individualNumber);

}
