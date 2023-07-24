package com.joaogabee.springwebflux.repository;

import com.joaogabee.springwebflux.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface employeeRepository extends ReactiveCrudRepository<Employee, String> {
    
}
