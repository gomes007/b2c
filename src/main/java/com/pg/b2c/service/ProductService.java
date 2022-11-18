package com.pg.b2c.service;

import com.pg.b2c.domain.Price;
import com.pg.b2c.domain.Product;
import com.pg.b2c.domain.Service_s;
import com.pg.b2c.exceptions.impl.BadRequestException;
import com.pg.b2c.exceptions.impl.NotFoundException;
import com.pg.b2c.model.Response;
import com.pg.b2c.repository.ProductRepository;
import com.pg.b2c.repository.ProviderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    private ProviderRepository providerRepository;

    public ProductService(ProductRepository repository, ProviderRepository providerRepository) {
        this.repository = repository;
        this.providerRepository = providerRepository;
    }


    public Product register(final Product product) {
        this.calculateSalePrice(product);
        return this.repository.save(product);
    }


    public Product update(Product product, Long id){
        if (product.getProductInventory() == null || product.getProductInventory().getId() == null){
            throw new BadRequestException("product has invalid inventory id");
        }

        var savedProduct = this.findById(id);
        product.setId(id);

        product.setProductInventory(Optional
                .ofNullable(product.getProductInventory())
                .map(inventory -> {
                    inventory.setCurrentlyQuantity(savedProduct.getProductInventory().getCurrentlyQuantity());
                    return inventory;
                })
                .orElse(savedProduct.getProductInventory()));

        return this.repository.save(product);
    }

    public Response<Product> find(final PageRequest pageRequest) {
        Page<Product> products = this.repository.findAll(PageRequest.of(pageRequest.getPageNumber() - 1, pageRequest.getPageSize()));
        return Response
                .<Product>builder()
                .items(products.getContent())
                .currentPage(Long.parseLong("" + pageRequest.getPageNumber()))
                .itemsPerPage(Long.parseLong("" + pageRequest.getPageSize()))
                .totalRecordsQuantity(products.getTotalElements())
                .build();
    }

    public Product findById(final Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("product not found"));
    }

    public void productExists(final Long id) {
        this.findById(id);
    }

    public Product deleteById(final Long id) {
        final Product found = this.findById(id);

        this.repository.delete(found);

        return found;
    }

    private void calculateSalePrice(Product productValue) {
        Optional.ofNullable(productValue).ifPresent(product -> {

            BigDecimal costValue = Optional.of(product.getPrice())
                    .map(Price::getCostValue)
                    .orElse(BigDecimal.ZERO);

            BigDecimal additionalCosts = Optional.of(product.getPrice())
                    .map(Price::getAdditionalCosts)
                    .orElse(BigDecimal.ZERO);

            BigDecimal finalCosts = costValue.add(additionalCosts);

            product.getPrice().setFinalCosts(finalCosts);

            BigDecimal salePrice = Optional.of(product.getPrice().getMarginProfit())
                    .map(finalCosts::multiply)
                    .map(f -> f.divide(BigDecimal.valueOf(100.00), RoundingMode.CEILING))
                    .map(finalCosts::add)
                    .orElse(BigDecimal.ZERO);

            product.getPrice().setSalePrice(salePrice.setScale(2, RoundingMode.CEILING));

        });
    }

}
