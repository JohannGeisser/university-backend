package com.hyperskill.university.utils.validations;

import com.hyperskill.university.models.Course;
import com.hyperskill.university.models.Student;
import com.hyperskill.university.repositories.CourseRepository;
import com.hyperskill.university.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvalidValidation {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public boolean isNotValid(Student student) {
        List<Student> students = studentRepository.findAll();
        if (students.contains(student)) {
            return false;
        }
        return true;
    }

    public boolean isEnrolled(Integer courseId, Student student) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        if (!course.getStudents().contains(student)) {
            return false;
        }
        return true;
    }
}
