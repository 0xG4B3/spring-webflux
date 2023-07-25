package com.joaogabee.springwebflux.service.impl;

import com.joaogabee.springwebflux.dto.employeeDTO;
import com.joaogabee.springwebflux.entity.Employee;
import com.joaogabee.springwebflux.mapper.employeeMapper;
import com.joaogabee.springwebflux.repository.employeeRepository;
import com.joaogabee.springwebflux.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private employeeRepository _employeeRepository;

    @Override
    public Mono<employeeDTO> saveEmployee(employeeDTO employeeDto) {
        Employee employee = employeeMapper.mapToEmployee(employeeDto);
        Mono<Employee> savedEmployee = _employeeRepository.save(employee);
        return savedEmployee.map((emplyoeeEntity) -> employeeMapper.mapToEmployeeDTO(emplyoeeEntity));
    }

    @Override
    public Mono<employeeDTO> getEmployee(String employeeid) {
        Mono<Employee> savedEmployee = _employeeRepository.findById(employeeid);
        return savedEmployee.map((employee -> employeeMapper.mapToEmployeeDTO(employee)));

    }

    @Override
    public Flux<employeeDTO> getAllEmployee() {
        Flux<Employee> employeeFlux = _employeeRepository.findAll();

        return employeeFlux.map(employee -> employeeMapper.mapToEmployeeDTO(employee)).switchIfEmpty(Flux.empty());

    }

    @Override
    public Mono<employeeDTO> updateEmployee(employeeDTO employeedto, String employeeId) {
        Mono<Employee> employeeMono = _employeeRepository.findById(employeeId);
        Mono<Employee> updateEmployee = employeeMono.flatMap((existingEmployee) -> {
            existingEmployee.setFirstName(employeedto.getFirstName());
            existingEmployee.setLastName(employeedto.getLastName());
            existingEmployee.setEmail(employeedto.getEmail());

            return _employeeRepository.save(existingEmployee);
        });
        return updateEmployee.map(employee -> employeeMapper.mapToEmployeeDTO(employee));
    }

    @Override
    public Mono<Void> deleteEmployee(String employeeID) {
        return null;
    }
}
