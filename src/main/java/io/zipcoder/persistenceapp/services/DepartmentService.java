package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.entities.Department;
import io.zipcoder.persistenceapp.entities.Department;
import io.zipcoder.persistenceapp.entities.Department;
import io.zipcoder.persistenceapp.entities.Employee;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeService employeeService;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartment(Integer id) {
        return departmentRepository.findOne(id);
    }

    public Department setManager(int id, int managerId) {
        Department department = getDepartment(id);
        Employee manager = employeeService.getEmployee(managerId);
        if (department != null && manager != null) {
            department.setManager(manager);
            return departmentRepository.save(department);
        } else {
            return null;
        }
    }

    public Department mergeDepartments(int deptAId, int deptBId) {
        Department departmentA = getDepartment(deptAId);
        Department departmentB = getDepartment(deptBId);
        if (departmentA != null && departmentB != null) {
            departmentB.getManager().setDepartmentNum(deptAId); // move mgr
            for (Employee e : employeeService.getEmployeesOfDepartment(deptBId)) {
                e.setDepartmentNum(deptAId); // move the others
            }
            departmentRepository.delete(departmentB);
            return departmentA;
        } else {
            return null;
        }


    }
}
