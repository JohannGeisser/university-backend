package com.hyperskill.university.services;

import com.hyperskill.university.exceptions.DuplicatedException;
import com.hyperskill.university.exceptions.InvalidException;
import com.hyperskill.university.models.Course;
import com.hyperskill.university.models.Student;
import com.hyperskill.university.repositories.CourseRepository;
import com.hyperskill.university.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course addNewCourse(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    public Course enrollStudent(Integer courseId, Student student) throws DuplicatedException, InvalidException {
        Course course = courseRepository.findCourseByCourseId(courseId);
        Optional<Student> studentOptional = studentRepository.findById(student.getStudentId());
        boolean isValidStudent = false;

        if (studentOptional.isPresent()) {
            Student studentToBeFound = studentOptional.get();
            isValidStudent = studentToBeFound.equals(student);
        }
        if (isValidStudent == false) {
            throw new InvalidException();
        }

        if (course.getStudents().contains(student)) {
            throw new DuplicatedException();
        }
        List<Student> students = course.getStudents();
        students.add(student);
        return course;
    }

    public Course getCourseById(Integer courseId) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        if (course == null) {
            return null;
        }
        return course;
    }

    @Transactional
    public Course removeStudentFromCourse(Integer courseId, Student student) throws InvalidException, DuplicatedException {
        Course course = courseRepository.findCourseByCourseId(courseId);
        Optional<Student> studentOptional = studentRepository.findById(student.getStudentId());
        boolean isValidStudent = false;

        if (studentOptional.isPresent()) {
            Student studentToBeFound = studentOptional.get();
            isValidStudent = studentToBeFound.equals(student);
        }
        if (isValidStudent == false) {
            throw new InvalidException();
        }

        if (course.getStudents().indexOf(student) == -1) {
            throw new DuplicatedException();
        }

        List<Student> students = course.getStudents();
        students.remove(student);
        return course;
    }

    public void removeCourse(Integer courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<Course> getCoursesByDepartment(Integer departmentId) {
        List<Course> courses = courseRepository.findByDepartmentId(departmentId);
        if (courses.size() == 0) {
            return null;
        }
        return courses;
    }
}
