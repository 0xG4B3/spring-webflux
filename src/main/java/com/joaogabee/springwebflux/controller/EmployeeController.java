package com.joaogabee.springwebflux.controller;

import com.joaogabee.springwebflux.dto.employeeDTO;
import com.joaogabee.springwebflux.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {


    private EmployeeService employeeService;

    @PostMapping
    public Mono<employeeDTO> saveEmployee(@RequestBody employeeDTO employeedto)
    {
        return employeeService.saveEmployee(employeedto);
    }
    @GetMapping("{id}")
    public Mono<employeeDTO> getEmployee(@PathVariable("id") String employeeId)
    {
        return employeeService.getEmployee(employeeId );
    }
    @GetMapping
    public Flux<employeeDTO> getAllEmployees()
    {
        return employeeService.getAllEmployee();
    }
    @PutMapping
    public Mono<employeeDTO> updateEmployee(@RequestBody employeeDTO employeedto, @PathVariable("id") String employeeid)
    {
        return employeeService.updateEmployee(employeedto, employeeid);
    }
    @DeleteMapping("{id}")
    public Mono<Void> deleteEmployee(@PathVariable("id") String employeeid)
    {
        return employeeService.deleteEmployee(employeeid);
    }
}
