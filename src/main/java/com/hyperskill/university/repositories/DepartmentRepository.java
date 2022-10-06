package com.hyperskill.university.repositories;

import com.hyperskill.university.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findDepartmentByDepartmentId(Integer departmentId);

}
