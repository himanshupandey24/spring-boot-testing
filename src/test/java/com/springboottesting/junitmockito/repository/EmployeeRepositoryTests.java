package com.springboottesting.junitmockito.repository;


import com.springboottesting.junitmockito.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;


    // Behaviour Driven Development
    //public void given_when_then(){}

    //Junit test for save employee operation
    @DisplayName("Junit test for save employee operation")
    @Test
    public void givenEmployee_whenSave_thenReturnSavedEmployee(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupanddey2411@gmail.com")
                .build();

        //when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void givenEmployees_getList_thenReturnList() {

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupanddey2411@gmail.com")
                .build();

        Employee employee1 = Employee.builder()
                .firstName("Depandre")
                .lastName("Singh")
                .email("depandresingh@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);


        //when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        //then - verify the output
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
    }

    //Junit test for getEmployee by ID

    @DisplayName("Junit Test for getEmployee by ID")
    @Test
    public void givenEmployee_whenFindById_thenReturnEmployee(){
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupanddey2411@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee repoEmployee = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        Assertions.assertThat(repoEmployee).isNotNull();
    }

    @DisplayName("Get Employee by Email Test")
    @Test
    public void givenEmployee_whenFindByEmail_thenReturnEmployee(){
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupanddey2411@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test

        Employee repoEmployee = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        Assertions.assertThat(repoEmployee).isNotNull();
    }


    //Test case for Updating Employee
    @Test
    public void givenEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupandey2411@gmail.com")
                .build();

        employeeRepository.save(employee);
        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();

        savedEmployee.setEmail("himanshu@gmail.com");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("himanshu@gmail.com");
        Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo("Himanshu");
    }

    //Junit test for delete employee operation
    @Test
    @DisplayName("Junit test case for delete employee operation")
    public void givenEmployee_whenDeleteEmployee_thenRemoveEmployee(){
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupandey2411@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        Assertions.assertThat(employeeOptional).isEmpty();

    }

    //Junit test for custom query using JPQL with index
    @Test
    @DisplayName("Junit test for custom query using JPQL with index")
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupandey2411@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQL(
                employee.getFirstName(),
                employee.getLastName()
        );

        // then - verify the output

        Assertions.assertThat(savedEmployee).isNotNull();
    }

    //Junit test for  custom query using JPQL with named params
    @Test
    @DisplayName("Junit test for custom query using JPQL with named params")
    public void givenFirstNameAndLastName_whenFindByJPQLNamed_thenReturnEmployeeObject(){
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupandey2411@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQLNamed(
                employee.getFirstName(),
                employee.getLastName()
        );

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    //Junit test for custom query using Native SQL with index
    @Test
    @DisplayName("Junit test for custom query using Native SQL with index")
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupandey2411@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQL(
                employee.getFirstName(),
                employee.getLastName()
        );

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    //Junit test for custom query using Native SQL with named params
    @Test
    @DisplayName("Junit test for custom query using Native SQL with named params")
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamed_thenReturnEmployeeObject(){
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Himanshu")
                .lastName("Pandey")
                .email("himanshupandey2411@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamed(
                employee.getFirstName(),
                employee.getLastName()
        );

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }

}