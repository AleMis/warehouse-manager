package am.warehouse.controller;

import am.warehouse.domain.client.ClientDto;
import am.warehouse.domain.delivery.Delivery;
import am.warehouse.domain.delivery.DeliveryDto;
import am.warehouse.domain.discount.DiscountDto;
import am.warehouse.domain.product.ProductDto;
import am.warehouse.domain.order.Order;
import am.warehouse.domain.order.OrderDto;
import am.warehouse.domain.supplier.SupplierDto;
import am.warehouse.exceptions.ClientNotFoundException;
import am.warehouse.exceptions.DiscountNotFoundException;
import am.warehouse.exceptions.ProductNotFoundException;
import am.warehouse.exceptions.SupplierNotFoundException;
import am.warehouse.mapper.*;
import am.warehouse.service.*;
import am.warehouse.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/warehouse_manager")
public class WarehouseManagerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseManagerController.class);

    @Autowired
    private DbService dbService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private DiscountMapper discountMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderValidation orderValidation;

    @Autowired
    private WarehouseValidation warehouseValidation;

    @Autowired
    private ProductValidation productValidation;

    @Autowired
    private DiscountValidation discountValidation;

    @Autowired
    private DeliveryValidation deliveryValidation;

    @RequestMapping(method = RequestMethod.POST, value = "/create_product")
    private ProductDto saveProduct(@RequestBody ProductDto productDto) {
        return productMapper.mapProductToProductDto(dbService.saveProduct(productMapper.mapProductDtoToProduct(productDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product")
    private ProductDto getProduct(@RequestParam Long productId) throws ProductNotFoundException {
        return productValidation.getCurrentProductData(productId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    private List<ProductDto> getAllProducts() throws ProductNotFoundException {
        return productValidation.getCurrentDataForAllProducts();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create_discount")
    private DiscountDto saveDiscount(@RequestBody DiscountDto discountDto) {
        return discountValidation.checkDiscount(discountDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/discount")
    private DiscountDto getOffer(@RequestParam Long discountId) throws DiscountNotFoundException {
        return discountMapper.mapDiscountToDiscountDto(dbService.getDiscountById(discountId).orElseThrow(DiscountNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create_client")
    private ClientDto saveClient(@RequestBody ClientDto clientDto) {
        return clientMapper.mapClientToClientDto(dbService.saveClient(clientMapper.mapClientDtoToClient(clientDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client")
    private ClientDto getClient(@RequestParam Long clientId) throws ClientNotFoundException {
        return clientMapper.mapClientToClientDto(dbService.getClientById(clientId).orElseThrow(ClientNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create_supplier")
    private SupplierDto saveSupplier(@RequestBody SupplierDto supplierDto) {
        return supplierMapper.mapSupplierToSupplierDto(dbService.saveSupplier(supplierMapper.mapSupplierDtoToSupplier(supplierDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/supplier")
    private SupplierDto getSupplier(@RequestParam Long supplierId) throws SupplierNotFoundException{
        return supplierMapper.mapSupplierToSupplierDto(dbService.getSupplier(supplierId).orElseThrow(SupplierNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create_delivery")
    private DeliveryDto addNewDelivery(@RequestBody DeliveryDto deliveryDto) {
        return deliveryValidation.checkProductForDeliveryPurpose(deliveryDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create_order")
    private OrderDto addNewSale(@RequestBody OrderDto orderDto) {
        Order order  = orderMapper.mapSaleDtoToWSale(orderDto);
        OrderDto newOrder = null;
        System.out.println(order);
        boolean status = orderValidation.checkProductAvailability(order);
        if(status) {
            newOrder = orderMapper.mapSaleToSaleDto(dbService.saveSale(order));
            warehouseValidation.updateWarehouseAfterNewOrder(order);
            LOGGER.info("New order was added to order database!");
        }else {
            LOGGER.error("Product [" + orderDto.getProductIndividualNumber() + "] order cannot be executed.");
        }
        return newOrder;
    }
}
