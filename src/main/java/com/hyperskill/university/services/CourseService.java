package com.hyperskill.university.services;

import com.hyperskill.university.models.Course;
import com.hyperskill.university.models.Student;

import java.util.List;

public interface CourseService {

    List<Course> getCourses();
    Course addNewCourse(Course course);
    void enrollStudent(Integer courseId, Student student);
    Course getCourseById(Integer courseId);
    void removeStudentFromCourse(Integer courseId, Student student);
    void removeCourse(Integer courseId);
    List<Course> getCoursesByDepartment(Integer departmentId);
}
