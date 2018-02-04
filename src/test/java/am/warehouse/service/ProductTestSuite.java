package am.warehouse.service;


import am.warehouse.domain.product.Product;
import am.warehouse.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveProductTest() {
        //Given
        Product product = new Product(1L, "Sony Xperia XL", "123ABC", "16GB, 2GB RAM, 21px", "Sony", new BigDecimal(1500.00), 0.2, new BigDecimal(1200.00) );

        //When
        productRepository.save(product);

        //Then
        Long id = product.getId();
        Optional<Product> readProduct = productRepository.findById(id);

        assertEquals(id, readProduct.get().getId());

        //CleanUp
        productRepository.delete(id);
    }
}
