package am.warehouse.service;

import am.warehouse.domain.client.Client;
import am.warehouse.domain.discount.Discount;
import am.warehouse.domain.product.Product;
import am.warehouse.domain.supplier.Supplier;
import am.warehouse.domain.delivery.Delivery;
import am.warehouse.domain.order.Order;
import am.warehouse.domain.warehouse.Warehouse;
import am.warehouse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    WarehouseRepository warehouseRepository;


    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findProductByIndividualNumber(String individualNumber) {
        return productRepository.findByIndividualNumber(individualNumber);
    }

    public Discount saveDiscount(Discount offer) {
        return discountRepository.save(offer);
    }

    public Optional<Discount> getDiscountById(Long id) {
        return discountRepository.findById(id);
    }

    public Optional<Discount> getDiscountByProductIndividualNumber(String prductIndividualNumber) {
        return discountRepository.findByProductIndividualNumber(prductIndividualNumber);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findClientById(id);
    }

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Optional<Supplier> getSupplier(Long id) {
        return supplierRepository.findSupplierById(id);
    }

    public Delivery saveDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Optional<Delivery> getDelivery(Long id) {
        return deliveryRepository.findById(id);
    }

    public Order saveSale(Order sale) {
        return orderRepository.save(sale);
    }

    public Optional<Order> getSale(Long id) {
        return orderRepository.findById(id);
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse getProductStatusByIndividualNumber(String productIndividualNumber) {
        return warehouseRepository.findByProductIndividualNumber(productIndividualNumber);
    }

    public List<Delivery> getAllDeliveryForProduct(String productIndividualNumber) {
        return deliveryRepository.findAllByProductIndividualNumber(productIndividualNumber);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }
}
