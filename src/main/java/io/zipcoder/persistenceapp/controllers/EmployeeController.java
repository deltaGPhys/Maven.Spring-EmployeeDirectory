package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.entities.Employee;
import io.zipcoder.persistenceapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> getAllEmployees () {
        return new ResponseEntity<Iterable<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee (@PathVariable Integer id) {
        return new ResponseEntity<Employee>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee (@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee (@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee (@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<Employee>(HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<Employee> deleteEmployees (@RequestBody List<Employee> employees) {
        employeeService.deleteEmployees(employees);
        return new ResponseEntity<Employee>(HttpStatus.OK);
    }

    @DeleteMapping("/employees/deptPurge/{deptId}")
    public ResponseEntity<Employee> deleteEmployeesFromDepartment (@PathVariable int deptId) {
        employeeService.deleteEmployeesFromDepartment(deptId);
        return new ResponseEntity<Employee>(HttpStatus.OK);
    }

    @DeleteMapping("/employees/managerPurge/{managerId}")
    public ResponseEntity<Employee> deleteEmployeesManagedByRecursive (@PathVariable int managerId) {
        employeeService.deleteEmployeesManagedByRecursive(managerId);
        return new ResponseEntity<Employee>(HttpStatus.OK);
    }

    @DeleteMapping("/employees/managerRemap/{managerId}")
    public ResponseEntity<Employee> deleteEmployeesManagedByRemap (@PathVariable int managerId) {
        employeeService.deleteEmployeesManagedByRemap(managerId);
        return new ResponseEntity<Employee>(HttpStatus.OK);
    }

    @PostMapping("/employees/{id}-{managerId}")
    public ResponseEntity<Employee> setManager (@PathVariable int id, @PathVariable int managerId) {
        return new ResponseEntity<Employee>(employeeService.setManager(id,managerId), HttpStatus.OK);
    }

    @GetMapping("/employees/managed/{managerId}")
    public ResponseEntity<Iterable<Employee>> getEmployeesManagedBy (@PathVariable int managerId) {
        return new ResponseEntity<Iterable<Employee>>(employeeService.getEmployeesManagedBy(managerId), HttpStatus.OK);
    }

    @GetMapping("/employees/dept/{deptId}")
    public ResponseEntity<Iterable<Employee>> getEmployeesOfDepartment (@PathVariable int deptId) {
        return new ResponseEntity<Iterable<Employee>>(employeeService.getEmployeesOfDepartment(deptId), HttpStatus.OK);
    }

    @GetMapping("/employees/nomgr")
    public ResponseEntity<Iterable<Employee>> getEmployeesNoManager () {
        return new ResponseEntity<Iterable<Employee>>(employeeService.getEmployeesNoManager(), HttpStatus.OK);
    }

    @GetMapping("/employees/mgrtree/{id}")
    public ResponseEntity<Iterable<Employee>> getManagerTree (@PathVariable int id) {
        return new ResponseEntity<Iterable<Employee>>(employeeService.getManagerTree(id), HttpStatus.OK);
    }

}
