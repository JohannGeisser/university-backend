package com.hyperskill.university.services;

import com.hyperskill.university.models.Course;
import com.hyperskill.university.models.Student;
import com.hyperskill.university.repositories.CourseRepository;
import com.hyperskill.university.utils.exceptions.DuplicatedException;
import com.hyperskill.university.utils.exceptions.InvalidException;
import com.hyperskill.university.utils.validations.InvalidValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InvalidValidation invalidValidation;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course addNewCourse(Course course) throws DuplicatedException {
        List<Course> courses = courseRepository.findAll();
        for (Course courseToBeCompared : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseToBeCompared.getCourseName())) {
                throw new DuplicatedException();
            }
        }
        return courseRepository.save(course);
    }

    @Transactional
    public Course enrollStudent(Integer courseId, Student student) throws DuplicatedException, InvalidException {
        if (invalidValidation.isNotValid(student)) {
            throw new InvalidException();
        }
        if (invalidValidation.isEnrolled(courseId, student)) {
            throw new DuplicatedException();
        }
        Course course = courseRepository.findCourseByCourseId(courseId);
        List<Student> students = course.getStudents();
        students.add(student);
        return course;
    }

    public Course getCourseById(Integer courseId) {
        return courseRepository.findCourseByCourseId(courseId);
    }

    @Transactional
    public Course removeStudentFromCourse(Integer courseId, Student student) throws InvalidException, DuplicatedException {
        if (invalidValidation.isNotValid(student)) {
            throw new InvalidException();
        }
        if (!invalidValidation.isEnrolled(courseId, student)) {
            throw new DuplicatedException();
        }
        Course course = courseRepository.findCourseByCourseId(courseId);
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
