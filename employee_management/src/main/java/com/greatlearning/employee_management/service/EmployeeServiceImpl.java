package com.greatlearning.employee_management.service;

import com.greatlearning.employee_management.dao.EmployeeRepository;
import com.greatlearning.employee_management.entity.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll(String sortBy) {

        List<Employee> employeeList;

        if (sortBy.equals("asc")) {
            employeeList = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        } else {
            employeeList = employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
        }

        return employeeList;
    }

    @Override
    public Employee findById(Long employeeId) {
        Optional<Employee> result = employeeRepository.findById(employeeId);

        Employee employee = null;

        if (result.isPresent()) {
            employee = result.get();
        } else {
            throw new RuntimeException("Not able to find the record of employee " + employeeId);
        }

        return employee;
    }

    @Override
    public void addRecord(Employee employee) {
        employeeRepository.save(employee);

    }

    @Override
    public void deleteRecord(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
