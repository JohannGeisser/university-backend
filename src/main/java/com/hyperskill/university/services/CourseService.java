package com.hyperskill.university.services;

import com.hyperskill.university.exceptions.DuplicatedException;
import com.hyperskill.university.exceptions.InvalidException;
import com.hyperskill.university.models.Course;
import com.hyperskill.university.models.Student;

import java.util.List;

public interface CourseService {

    List<Course> getCourses();
    Course addNewCourse(Course course);
    Course enrollStudent(Integer courseId, Student student) throws DuplicatedException, InvalidException;
    Course getCourseById(Integer courseId);
    Course removeStudentFromCourse(Integer courseId, Student student) throws InvalidException, DuplicatedException;
    void removeCourse(Integer courseId);
    List<Course> getCoursesByDepartment(Integer departmentId);
}
