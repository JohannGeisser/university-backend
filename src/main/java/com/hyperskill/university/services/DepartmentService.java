package com.hyperskill.university.services;

import com.hyperskill.university.models.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartments();
    Department addNewDepartment(Department department);
    void deleteDepartment(Integer departmentId);
    Department getDepartmentById(Integer departmentId);
}
