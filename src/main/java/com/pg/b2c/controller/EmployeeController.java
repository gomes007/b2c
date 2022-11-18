package com.pg.b2c.controller;

import com.pg.b2c.domain.Employee;
import com.pg.b2c.model.Response;
import com.pg.b2c.service.EmployeeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee){
        return this.service.save(employee);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Employee> find(
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
    public Employee findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@PathVariable("id") Long id, @RequestBody Employee employee){
        this.service.customerExists(id);
        employee.setId(id);
        return this.service.save(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee delete(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }


}
