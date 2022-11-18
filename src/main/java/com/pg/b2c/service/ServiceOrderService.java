package com.pg.b2c.service;

import com.pg.b2c.domain.*;
import com.pg.b2c.exceptions.impl.BadRequestException;
import com.pg.b2c.repository.ServiceOrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceOrderService {


    private ServiceOrderRepository serviceOrderRepository;
    private ProductService productService;
    private Service_sService service_sService;

    private ProductInventoryService productInventoryService;

    private PaymentService paymentService;

    public ServiceOrderService(ServiceOrderRepository serviceOrderRepository, ProductService productService, Service_sService service_sService, ProductInventoryService productInventoryService, PaymentService paymentService) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.productService = productService;
        this.service_sService = service_sService;
        this.productInventoryService = productInventoryService;
        this.paymentService = paymentService;
    }


    public ServiceOrder open(ServiceOrder serviceOrder) {
        this.fetchProducts(serviceOrder);
        this.fetchService_s(serviceOrder);
        this.fetchPayments(serviceOrder);
        this.validateProductInventory(serviceOrder);
        this.calculateTotalCostServiceOrder(serviceOrder);

        serviceOrder.setServiceOrderStatus(ServiceOrderStatus.Open);

        var updateServiceOrder = this.serviceOrderRepository.save(serviceOrder);

        serviceOrder.getServiceOrderProducts().forEach(product -> this.productInventoryService.subtractAmount(product.getProduct().getProductInventory().getId(), product.getQuantity()));

        return updateServiceOrder;

    }

    private void fetchPayments(ServiceOrder serviceOrder) {
        serviceOrder.getPayments().forEach(payment -> {
         Payment payment1 = this.paymentService.findById(payment.getId());
            serviceOrder.setPayments(Collections.singletonList(payment1));
        });
    }

    private void fetchProducts(ServiceOrder serviceOrder) {
        serviceOrder.getServiceOrderProducts().forEach(serviceOrderProduct -> {
            Product product = this.productService.findById(serviceOrderProduct.getProduct().getId());
            serviceOrderProduct.setProduct(product);
        });
    }

    private void fetchService_s(ServiceOrder serviceOrder) {
        serviceOrder.getServiceOrderService_s().forEach(serviceOrderService_s -> {
            Service_s service_s = this.service_sService.findById(serviceOrderService_s.getService_s_().getId());
            serviceOrderService_s.setService_s_(service_s);
        });
    }




    private void validateProductInventory(ServiceOrder serviceOrder) {
        serviceOrder.getServiceOrderProducts().forEach(serviceOrderProduct -> {
            int quantityRequested = serviceOrderProduct.getQuantity();
            int stock = serviceOrderProduct.getProduct().getProductInventory().getCurrentlyQuantity();
            boolean availability = quantityRequested > stock;
            if (availability == true) {
                throw new BadRequestException("quantity not available");
            }
        });
    }


    private void calculateServiceOrderProductSubtotal(ServiceOrderProduct serviceOrderProductValue) {
        Optional.ofNullable(serviceOrderProductValue).ifPresent(serviceOrderProduct -> {

            BigDecimal quantity = Optional.of(serviceOrderProduct.getQuantity())
                    .map(BigDecimal::valueOf)
                    .orElse(BigDecimal.ZERO);

            BigDecimal price = Optional.of(serviceOrderProduct.getProduct())
                    .map(Product::getPrice)
                    .map(Price::getSalePrice)
                    .orElse(BigDecimal.ZERO);

            BigDecimal value = quantity.multiply(price);

            BigDecimal valueWithDiscount = Optional.of(serviceOrderProduct.getDiscountAmount())
                    .map(discountAmount -> {
                        if (discountAmount.setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO)) {
                            return Optional.of(serviceOrderProduct.getDiscountPercent())
                                    .map(value::multiply)
                                    .map(v -> v.divide(BigDecimal.valueOf(100.00), RoundingMode.CEILING))
                                    .orElse(BigDecimal.ZERO);
                        }
                        return discountAmount;
                    })
                    .or(() -> Optional.of(serviceOrderProduct.getDiscountPercent())
                            .map(value::multiply))
                            .or(() -> Optional.of(BigDecimal.ZERO))
                    .map(value::subtract)
                    .orElse(BigDecimal.ZERO);

            serviceOrderProduct.setTotalValueProducts(valueWithDiscount.setScale(2, RoundingMode.CEILING));

        });
    }

    public void calculateServiceOrderProductTotal(ServiceOrder serviceOrder){
        serviceOrder.getServiceOrderProducts().forEach(this::calculateServiceOrderProductSubtotal);
    }


    public void calculateServiceOrderService_sSubtotal(ServiceOrderService_s serviceOrderService_sValue){
        Optional.ofNullable(serviceOrderService_sValue).ifPresent(serviceOrderService_s -> {

            BigDecimal quantity = Optional.of(serviceOrderService_s.getQuantity())
                    .map(BigDecimal::valueOf)
                    .orElse(BigDecimal.ZERO);

            BigDecimal price = Optional.of(serviceOrderService_s.getService_s_())
                    .map(Service_s::getPrice)
                    .map(Price::getSalePrice)
                    .orElse(BigDecimal.ZERO);

            BigDecimal value = quantity.multiply(price);

            BigDecimal valueWithDiscount = Optional.of(serviceOrderService_s.getDiscountAmount())
                    .map(discountAmount -> {
                        if (discountAmount.setScale(0,RoundingMode.CEILING) == BigDecimal.ZERO){
                            return Optional.of(serviceOrderService_s.getDiscountPercent())
                                    .map(value::multiply)
                                    .map(v -> v.divide(BigDecimal.valueOf(100.00), RoundingMode.CEILING))
                                    .orElse(BigDecimal.ZERO);
                        }
                        return discountAmount;
                    })
                    .or(() -> Optional.of(serviceOrderService_s.getDiscountPercent())
                            .map(value::multiply)
                            .or(() -> Optional.of(BigDecimal.ZERO)))
                    .map(value::subtract)
                    .orElse(BigDecimal.ZERO);

            serviceOrderService_s.setTotalValueService_s(valueWithDiscount.setScale(2,RoundingMode.CEILING));

        });
    }


    public void calculateServiceOrderService_sTotal(ServiceOrder serviceOrder){
        serviceOrder.getServiceOrderService_s().forEach(this::calculateServiceOrderService_sSubtotal);
    }


    public void calculateTotalCostServiceOrder(ServiceOrder serviceOrder){

        this.calculateServiceOrderProductTotal(serviceOrder);
        this.calculateServiceOrderService_sTotal(serviceOrder);

        BigDecimal productsTotalCostValue = serviceOrder.getServiceOrderProducts()
                .stream()
                .map(ServiceOrderProduct::getTotalValueProducts)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        serviceOrder.setTotalProducts(productsTotalCostValue);

        BigDecimal service_sTotalCostValue = serviceOrder.getServiceOrderService_s()
                .stream()
                .map(ServiceOrderService_s::getTotalValueService_s)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        serviceOrder.setTotalService_s(service_sTotalCostValue);

        BigDecimal totalValueOfProductsAndService_s = serviceOrder.getTotalProducts().add(serviceOrder.getTotalService_s());


        BigDecimal totalValueWithDiscount = Optional.of(serviceOrder.getDiscountAmount())
                .map(discountAmount -> {
                    if (discountAmount.setScale(0, RoundingMode.CEILING) == BigDecimal.ZERO){
                        return Optional.of(serviceOrder.getDiscountPercent())
                                .map(totalValueOfProductsAndService_s::multiply)
                                .map(t -> t.divide(BigDecimal.valueOf(100.00), RoundingMode.CEILING))
                                .orElse(BigDecimal.ZERO);
                    }
                    return discountAmount;
                })
                .or(() -> Optional.of(serviceOrder.getDiscountPercent())
                        .map(totalValueOfProductsAndService_s::multiply)
                        .or(() -> Optional.of(BigDecimal.ZERO)))
                .map(totalValueOfProductsAndService_s::subtract)
                .orElse(BigDecimal.ZERO);

        BigDecimal additionalCosts = serviceOrder.getOtherCosts().add(serviceOrder.getShippingCost());

        serviceOrder.setTotalCostServiceOrder(totalValueOfProductsAndService_s.setScale(2,RoundingMode.CEILING).add(additionalCosts));

    }


    public List<ServiceOrder> findAll(){
        return serviceOrderRepository.findAll();
    }


    public ServiceOrder findById(Long id){
        return this.serviceOrderRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("nof found"));
    }

    public ServiceOrder deleteById(Long id){
        ServiceOrder serviceOrder = this.findById(id);
        this.serviceOrderRepository.delete(serviceOrder);
        this.updateStock(serviceOrder);
        return serviceOrder;
    }


    private void updateStock(ServiceOrder serviceOrder){
        serviceOrder.getServiceOrderProducts().forEach(serviceOrderProduct -> {
            this.productInventoryService.addAmount(serviceOrderProduct.getProduct().getProductInventory().getId(), serviceOrderProduct.getQuantity());
        });
    }


    public void exists(Long id){
        this.findById(id);
    }
}
