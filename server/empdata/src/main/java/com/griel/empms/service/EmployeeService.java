package com.griel.empms.service;

import com.griel.empms.model.Employee;

public interface EmployeeService {
    Employee[] getEmployees() throws Exception;

    Employee getEmployee(Integer id) throws Exception;

    Employee create(Employee product) throws Exception;

    Employee update(Employee product) throws Exception;

    void delete(Integer id) throws Exception;
}
