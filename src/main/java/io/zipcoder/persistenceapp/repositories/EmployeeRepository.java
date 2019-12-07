package io.zipcoder.persistenceapp.repositories;

import io.zipcoder.persistenceapp.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

    List<Employee> findEmployeeByManagerId(int managerId);

    List<Employee> findEmployeeByDepartmentNum(int departmentId);

    List<Employee> findEmployeeByManagerIsNull();

    void deleteEmployeesByDepartmentNum(int departmentId);

    void deleteEmployeesByManagerIn(Iterable<Employee> managers);
}
