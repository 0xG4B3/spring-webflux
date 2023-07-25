package com.joaogabee.springwebflux.repository;

import com.joaogabee.springwebflux.entity.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface employeeRepository extends ReactiveMongoRepository<Employee, String> {

}
