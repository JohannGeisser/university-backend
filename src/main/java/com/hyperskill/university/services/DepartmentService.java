package com.hyperskill.university.services;

import com.hyperskill.university.models.Department;
import com.hyperskill.university.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department addNewDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Integer departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    public Department getDepartmentById(Integer departmentId) {
        return departmentRepository.findDepartmentByDepartmentId(departmentId);
    }
}
