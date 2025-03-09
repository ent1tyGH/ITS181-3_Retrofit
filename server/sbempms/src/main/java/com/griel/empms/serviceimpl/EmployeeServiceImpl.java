package com.griel.empms.serviceimpl;

import com.griel.empms.entity.EmployeeData;
import com.griel.empms.model.Employee;
import com.griel.empms.repository.EmployeeDataRepository;
import com.griel.empms.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeDataRepository employeeDataRepository;

    @Override
    public Employee[] getEmployees() {
        List<EmployeeData> employeesData = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employeeDataRepository.findAll().forEach(employeesData::add);

        for (EmployeeData employeeData : employeesData) {
            Employee employee = new Employee();
            employee.setId(employeeData.getId());
            employee.setName(employeeData.getName());
            employee.setJobTitle(employeeData.getJobTitle());
            employee.setSalary(employeeData.getSalary());
            employee.setEmploymentType(employeeData.getEmploymentType()); // Enum
            employees.add(employee);
        }

        return employees.toArray(new Employee[0]);
    }

    @Override
    public Employee create(Employee employee) {
        logger.info("add: Input " + employee.toString());
        EmployeeData employeeData = new EmployeeData();
        employeeData.setName(employee.getName());
        employeeData.setJobTitle(employee.getJobTitle());
        employeeData.setSalary(employee.getSalary());
        employeeData.setEmploymentType(employee.getEmploymentType()); // Enum

        employeeData = employeeDataRepository.save(employeeData);

        Employee newEmployee = new Employee();
        newEmployee.setId(employeeData.getId());
        newEmployee.setName(employeeData.getName());
        newEmployee.setJobTitle(employeeData.getJobTitle());
        newEmployee.setSalary(employeeData.getSalary());
        newEmployee.setEmploymentType(employeeData.getEmploymentType());

        return newEmployee;
    }

    @Override
    public Employee update(Employee employee) {
        Optional<EmployeeData> optional = employeeDataRepository.findById(employee.getId());
        if (!optional.isPresent()) {
            return null; // Or handle not found case
        }

        EmployeeData employeeData = optional.get();
        employeeData.setName(employee.getName());
        employeeData.setJobTitle(employee.getJobTitle());
        employeeData.setSalary(employee.getSalary());
        employeeData.setEmploymentType(employee.getEmploymentType());

        employeeData = employeeDataRepository.save(employeeData);

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(employeeData.getId());
        updatedEmployee.setName(employeeData.getName());
        updatedEmployee.setJobTitle(employeeData.getJobTitle());
        updatedEmployee.setSalary(employeeData.getSalary());
        updatedEmployee.setEmploymentType(employeeData.getEmploymentType());

        return updatedEmployee;
    }

    @Override
    public Employee getEmployee(Integer id) {
        Optional<EmployeeData> optional = employeeDataRepository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }

        EmployeeData employeeData = optional.get();
        Employee employee = new Employee();
        employee.setId(employeeData.getId());
        employee.setName(employeeData.getName());
        employee.setJobTitle(employeeData.getJobTitle());
        employee.setSalary(employeeData.getSalary());
        employee.setEmploymentType(employeeData.getEmploymentType());

        return employee;
    }


    @Override
    public void delete(Integer id) {
        logger.info("Input >> " + Integer.toString(id));
         Optional<EmployeeData> optional = employeeDataRepository.findById(id);
         if( optional.isPresent()) {
             EmployeeData employeeDatum = optional.get();
             employeeDataRepository.delete(employeeDatum);
             logger.info("Success >> " + employeeDatum.toString());
         }
         else {
             logger.info("Failed  >> unable to locate employee id: " +  Integer.toString(id));
         }
    }
}
