package com.hyperskill.university.services;

import com.hyperskill.university.models.Course;
import com.hyperskill.university.models.Student;
import com.hyperskill.university.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course addNewCourse(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    public void enrollStudent(Integer courseId, Student student) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        List<Student> students = course.getStudents();
        students.add(student);
    }

    public Course getCourseById(Integer courseId) {
        return courseRepository.findCourseByCourseId(courseId);
    }

    @Transactional
    public void removeStudentFromCourse(Integer courseId, Student student) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        List<Student> students = course.getStudents();
        students.remove(student);
    }

    public void removeCourse(Integer courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<Course> getCoursesByDepartment(Integer departmentId) {
        return courseRepository.findByDepartmentId(departmentId);
    }
}
