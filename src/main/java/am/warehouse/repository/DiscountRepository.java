package am.warehouse.repository;

import am.warehouse.domain.discount.Discount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long> {

    @Override
    Discount save(Discount offer);

    Optional<Discount> findById(Long id);

    Optional<Discount> findByProductIndividualNumber(String productIndividualNumber);

    List<Discount> findAll();
}
