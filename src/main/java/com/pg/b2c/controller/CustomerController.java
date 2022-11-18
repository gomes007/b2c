package com.pg.b2c.controller;

import com.pg.b2c.domain.Customer;
import com.pg.b2c.model.Response;
import com.pg.b2c.service.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer){
        return this.service.save(customer);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Customer> find(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "filter", defaultValue = "") String filter
    ) {
        if (page < 1) page = 1;
        if (size < 1) size = 10;

        return this.service.find(PageRequest.of(page, size), filter);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer update(@PathVariable("id") Long id, @RequestBody Customer customer){
        this.service.customerExists(id);
        customer.setId(id);
        return this.service.save(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer delete(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }


}
