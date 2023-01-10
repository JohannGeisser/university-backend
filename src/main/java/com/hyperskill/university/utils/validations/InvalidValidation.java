package com.hyperskill.university.utils.validations;

import com.hyperskill.university.models.Course;
import com.hyperskill.university.models.Student;
import com.hyperskill.university.repositories.CourseRepository;
import com.hyperskill.university.repositories.StudentRepository;
import com.hyperskill.university.utils.exceptions.InvalidException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class InvalidValidation {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    private boolean isValid(Student student) {
        List<Student> students = studentRepository.findAll();
        if (students.contains(student)) {
            return true;
        }
        return false;
    }

    private boolean isEnrolled(Integer courseId, Student student) {
//        //Course course = courseRepository.findCourseByCourseId(courseId);
//        Optional<Student> studentOptional = studentRepository.findById(student.getStudentId());
//        boolean isValidStudent = false;
//
//        if (studentOptional.isPresent()) {
//            Student studentToBeFound = studentOptional.get();
//            isValidStudent = studentToBeFound.equals(student);
//        }
//        if (isValidStudent == false) {
//            throw new InvalidException();
//        }
//    }
        Course course = courseRepository.findCourseByCourseId(courseId);
        if (!course.getStudents().contains(student)) {
            return false;
        }
        return true;
    }
}
