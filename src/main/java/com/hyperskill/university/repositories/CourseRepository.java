package com.hyperskill.university.repositories;

import com.hyperskill.university.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findAllByDepartment_DepartmentId(Integer departmentId);
    Course findCourseByCourseId(Integer courseId);

    @Query(value = "SELECT c.* FROM course c WHERE department_id=?1", nativeQuery = true)
    List<Course> findByDepartmentId(Integer departmentId);
}
