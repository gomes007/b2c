package com.pg.b2c.controller;

import com.pg.b2c.domain.Provider;
import com.pg.b2c.repository.ProductRepository;
import com.pg.b2c.repository.ProviderRepository;
import com.pg.b2c.service.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private ProviderService service;

    private ProductRepository productRepository;
    private ProviderRepository providerRepository;

    public ProviderController(ProviderService service, ProductRepository productRepository, ProviderRepository providerRepository) {
        this.service = service;
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Provider create(@RequestBody Provider provider){
        return service.save(provider);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Provider> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Provider findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Provider update(@PathVariable("id") Long id, @RequestBody Provider provider){
        this.service.exists(id);
        provider.setId(id);
        return this.service.save(provider);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Provider delete(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }


}
