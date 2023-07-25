package com.joaogabee.springwebflux.mapper;

import com.joaogabee.springwebflux.dto.employeeDTO;
import com.joaogabee.springwebflux.entity.Employee;

public class employeeMapper {
    public static employeeDTO mapToEmployeeDTO(Employee employee)
    {
        return new employeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
    }
    public static Employee mapToEmployee(employeeDTO employeeDTO)
    {
        return new Employee(employeeDTO.getId(), employeeDTO.getFirstName(), employeeDTO.getLastName(), employeeDTO.getEmail());
    }
}
