package com.griel.empms.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    int id;
    String name;
    String jobTitle;
    double salary;
    EmploymentType employmentType;

}
