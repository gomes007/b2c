package com.pg.b2c.controller;

import com.pg.b2c.domain.Product;
import com.pg.b2c.model.Response;
import com.pg.b2c.repository.ProductRepository;
import com.pg.b2c.repository.ProviderRepository;
import com.pg.b2c.service.ProductService;
import com.pg.b2c.service.ProviderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService service;

    private ProviderService providerService;

    private ProductRepository productRepository;
    private ProviderRepository providerRepository;

    public ProductController(ProductService service, ProviderService providerService, ProductRepository productRepository, ProviderRepository providerRepository) {
        this.service = service;
        this.providerService = providerService;
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product register(@RequestBody final Product product) {
        return this.service.register(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Product> find(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        if (page < 1) page = 1;
        if (size < 1) size = 10;

        return this.service.find(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@PathVariable("id") long id, @RequestBody final Product product) {
        this.service.productExists(id);

        product.setId(id);

        return this.service.update(product, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product delete(@PathVariable("id") long id) {
        return this.service.deleteById(id);
    }


}
