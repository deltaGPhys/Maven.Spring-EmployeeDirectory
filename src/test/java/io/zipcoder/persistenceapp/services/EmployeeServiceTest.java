package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.entities.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    
    @InjectMocks
    private EmployeeService employeeService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(stubEmployees());
        employeeService.getAllEmployees();
        verify(employeeRepository, times(1)).findAll();
    }

    private List<Employee> stubEmployees() {
        Employee e1 = new Employee("Jimmy", "Johns", "Poobah", "12345678", "jim@aol.com");
        Employee e2 = new Employee("Jimmy2", "Johns2", "Grunt", "87654321", "joe@aol.com");
        e1.setId(3);
        e2.setId(5);
        return Arrays.asList(e1,e2);
    }

    @Test
    public void getEmployee() {
        when(employeeService.getEmployee(5)).thenReturn(stubEmployees().get(1));
        employeeService.getEmployee(5);
        verify(employeeRepository, times(1)).findOne(5);
    }

    @Test
    public void createEmployee() {
        Employee expected = new Employee("Jimmy", "Johns", "Poobah", "12345678", "jim@aol.com");
        doReturn(expected).when(employeeRepository).save(any(Employee.class));
        Employee actual = employeeService.createEmployee(expected);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void createEmployeeNoReturn() {
        Employee expected = new Employee("Jimmy", "Johns", "Poobah", "12345678", "jim@aol.com");
        doReturn(expected).when(employeeRepository).save(any(Employee.class));
        employeeService.createEmployee(expected);
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(1)).save(captor.capture());
        Assert.assertEquals(expected.toString(), captor.getValue().toString());
    }

    @Test
    public void setManager() {
        Employee e = new Employee("Jimmy", "Johns", "Peon", "12345678", "jim@aol.com");
        Employee m = new Employee("Jimmy2", "Johns2", "Jefe", "87654321", "joe@aol.com");
        e.setId(3);
        m.setId(7);
        doReturn(e).when(employeeRepository).findOne(3);
        doReturn(m).when(employeeRepository).findOne(7);
        employeeService.setManager(3,7);
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(1)).save(captor.capture());
        Assert.assertEquals(m, captor.getValue().getManager());
    }

    @Test
    public void updateEmployee() {
        Employee e = new Employee("Jimmy", "Johns", "Peon", "12345678", "jim@aol.com");
        employeeService.updateEmployee(e);
        verify(employeeRepository, times(1)).save(e);
    }



}