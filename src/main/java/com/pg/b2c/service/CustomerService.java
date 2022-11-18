package com.pg.b2c.service;

import com.pg.b2c.domain.Customer;
import com.pg.b2c.exceptions.impl.NotFoundException;
import com.pg.b2c.model.Response;
import com.pg.b2c.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    public Customer save(Customer customer){
        return this.repository.save(customer);
    }


    public Response<Customer> find(final PageRequest pageRequest, final String filter) {
        Page<Customer> customers = this.repository.findAllByNameIgnoreCaseContaining(
                filter,
                PageRequest.of(pageRequest.getPageNumber() - 1, pageRequest.getPageSize())
        );
        return Response
                .<Customer>builder()
                .items(customers.getContent())
                .currentPage(Long.parseLong("" + pageRequest.getPageNumber()))
                .itemsPerPage(Long.parseLong("" + pageRequest.getPageSize()))
                .totalRecordsQuantity(customers.getTotalElements())
                .build();
    }

    public Customer findById(final Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("customer not found"));
    }


    public void customerExists(Long id){
        this.findById(id);
    }


    public Customer deleteById(Long id){
        Customer found = this.findById(id);
        this.repository.delete(found);
        return found;
    }

}
