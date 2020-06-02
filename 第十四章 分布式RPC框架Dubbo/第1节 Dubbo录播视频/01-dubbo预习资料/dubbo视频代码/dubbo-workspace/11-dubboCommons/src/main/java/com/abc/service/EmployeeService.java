package com.abc.service;


import com.abc.bean.Employee;

public interface EmployeeService {
    void addEmployee(Employee employee);
    Employee findEmployeeById(int id);
    Integer findEmployeeCount();
}
