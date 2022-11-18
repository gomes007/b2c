package com.pg.b2c.service;

import com.pg.b2c.domain.Employee;
import com.pg.b2c.exceptions.impl.NotFoundException;
import com.pg.b2c.model.Response;
import com.pg.b2c.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }


    public Employee save(Employee employee){
        return this.repository.save(employee);
    }


    public Response<Employee> find(final PageRequest pageRequest, final String filter) {
        Page<Employee> employees = this.repository.findAllByNameIgnoreCaseContaining(
                filter,
                PageRequest.of(pageRequest.getPageNumber() - 1, pageRequest.getPageSize())
        );
        return Response
                .<Employee>builder()
                .items(employees.getContent())
                .currentPage(Long.parseLong("" + pageRequest.getPageNumber()))
                .itemsPerPage(Long.parseLong("" + pageRequest.getPageSize()))
                .totalRecordsQuantity(employees.getTotalElements())
                .build();
    }

    public Employee findById(final Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("customer not found"));
    }


    public void customerExists(Long id){
        this.findById(id);
    }


    public Employee deleteById(Long id){
        Employee found = this.findById(id);
        this.repository.delete(found);
        return found;
    }

}
