package com.pg.b2c.controller;

import com.pg.b2c.domain.Product;
import com.pg.b2c.repository.ProductRepository;
import com.pg.b2c.repository.ProviderRepository;
import com.pg.b2c.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/provider")
public class ProductProviderController {

    private ProductRepository productRepository;
    private ProviderRepository providerRepository;

    private ProductService productService;

    public ProductProviderController(ProductRepository productRepository, ProviderRepository providerRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
        this.productService = productService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProductWithProvider(@RequestBody Product product){

        return this.productRepository.save(product);
//        return this.productService.check(product);
    }

    @GetMapping
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }



}
