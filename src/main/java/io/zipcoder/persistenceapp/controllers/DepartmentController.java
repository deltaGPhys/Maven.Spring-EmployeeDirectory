package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.entities.Department;
import io.zipcoder.persistenceapp.entities.Department;
import io.zipcoder.persistenceapp.entities.Department;
import io.zipcoder.persistenceapp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentController {
    
    @Autowired
    DepartmentService departmentService;

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment (@RequestBody Department department) {
        return new ResponseEntity<Department>(departmentService.updateDepartment(department), HttpStatus.OK);
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> addDepartment (@RequestBody Department department) {
        return new ResponseEntity<Department>(departmentService.createDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment (@PathVariable Integer id) {
        return new ResponseEntity<Department>(departmentService.getDepartment(id), HttpStatus.OK);
    }

    @PostMapping("/departments/{id}-{managerId}")
    public ResponseEntity<Department> setManager (@PathVariable int id, @PathVariable int managerId) {
        Department response = departmentService.setManager(id,managerId);
        if (response != null) {
            return new ResponseEntity<Department>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/merge/{deptAId}-{deotBId}")
    public ResponseEntity<Department> mergeDepartments (@PathVariable int deptAId, @PathVariable int deptBId) {
        Department response = departmentService.mergeDepartments(deptAId, deptBId);
        if (response != null) {
            return new ResponseEntity<Department>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
        }

    }
}
