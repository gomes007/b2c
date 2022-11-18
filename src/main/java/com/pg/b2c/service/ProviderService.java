package com.pg.b2c.service;

import com.pg.b2c.domain.Provider;
import com.pg.b2c.exceptions.impl.BadRequestException;
import com.pg.b2c.repository.ProductRepository;
import com.pg.b2c.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    private ProductRepository productRepository;

    private ProviderRepository repository;

    public ProviderService(ProductRepository productRepository, ProviderRepository repository) {
        this.productRepository = productRepository;
        this.repository = repository;
    }


    public Provider save(Provider provider){
        return repository.save(provider);
    }

    public List<Provider> findAll(){
        return repository.findAll();
    }

    public Provider findById(Long id){
        return this.repository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("nof found"));
    }

    public Provider deleteById(Long id){
        Provider provider = this.findById(id);
        this.repository.delete(provider);
        return provider;
    }


    public void exists(Long id){
        this.findById(id);
    }





}
