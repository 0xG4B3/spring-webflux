package com.joaogabee.springwebflux.service;

import com.joaogabee.springwebflux.dto.employeeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<employeeDTO> saveEmployee(employeeDTO employeeDto);
    Mono<employeeDTO> getEmployee(String employeeid);
    Flux<employeeDTO> getAllEmployee();

    Mono<employeeDTO> updateEmployee(employeeDTO employeedto, String employeeId);

    Mono<Void> deleteEmployee(String employeeID);
}
