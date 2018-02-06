package am.warehouse.repository;

import am.warehouse.domain.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

    @Override
    Order save(Order warehouseOut);

    Optional<Order> findById(Long id);
}
