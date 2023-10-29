package com.greatlearning.employee_management.controller;

import com.greatlearning.employee_management.entity.Employee;
import com.greatlearning.employee_management.entity.Role;
import com.greatlearning.employee_management.entity.User;
import com.greatlearning.employee_management.service.EmployeeService;
import com.greatlearning.employee_management.service.RoleService;
import com.greatlearning.employee_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RoleService roleService;



    @GetMapping("/api/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return new ResponseEntity<>(employeeService.findAll("asc"), HttpStatus.OK);
    }

    @GetMapping("/api/employees/sort")
    public ResponseEntity<List<Employee>> getAll(@RequestParam String order) {
        return new ResponseEntity<>(employeeService.findAll(order), HttpStatus.OK);
    }
   /* public List<Employee> findAllEmployees(@RequestBody Employee employee) {
        return employeeService.findAll();
    }*/

    @GetMapping("/api/employees/{employeeId}")
    public ResponseEntity<Employee> getById(@PathVariable Long employeeId) {

        boolean employeeFound = true;
        Employee employee = null;
        try {
            employee = employeeService.findById(employeeId);
        } catch (Exception ex) {
            employeeFound = false;
        }
        if (employeeFound) {
            return new ResponseEntity<>(employeeService.findById(employeeId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }

    }


    @PostMapping("/api/employees")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addRecord(employee);
    }

    @PutMapping("/api/employees/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@RequestBody Employee employeeDetails, @PathVariable Long employeeId) {

        boolean employeeFound = true;
        Employee employee = null;
        try {
            employee = employeeService.findById(employeeId);
        } catch (Exception ex) {
            employeeFound = false;
        }
        if (employeeFound) {

            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());

            employeeService.deleteRecord(employeeId);

            employeeService.addRecord(employee);

        }
    }

    @DeleteMapping("/api/employees/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteRecord(employeeId);
    }

    @PostMapping("/api/role/save")
    public void saveRoleDetails(@RequestBody Role role) {
        roleService.save(role);
    }
}
