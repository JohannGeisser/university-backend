package com.hyperskill.university.controllers;

import com.hyperskill.university.utils.exceptions.DuplicatedException;
import com.hyperskill.university.utils.exceptions.InvalidException;
import com.hyperskill.university.models.Course;
import com.hyperskill.university.models.Student;
import com.hyperskill.university.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/course")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> showCourses() {
        List<Course> courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(path = "{courseId}")
    public ResponseEntity<Object> showCourse(@PathVariable("courseId") Integer courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            String msg = "Course with ID: " + courseId + " not found";
            return new ResponseEntity<>(Collections.singletonMap("msg", msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCourse(@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            Course newCourse = courseService.addNewCourse(course);
            return new ResponseEntity<>(newCourse, HttpStatus.CREATED) ;
        } catch (DuplicatedException duplicatedException) {
            String msg = "Course already created";
            return new ResponseEntity<>(Collections.singletonMap("msg", msg), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(path = {"{courseId}"})
    public ResponseEntity<Object> enrollStudent(@PathVariable("courseId") Integer courseId,
                              @RequestBody Student student) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            String msg = "Course with ID: " + courseId + " not found";
            return new ResponseEntity<>(Collections.singletonMap("msg", msg), HttpStatus.NOT_FOUND);
        }
        try {
            Course updatedCourse = courseService.enrollStudent(courseId, student);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (InvalidException invalidException) {
            String msg = "Invalid student data";
            return new ResponseEntity<>(Collections.singletonMap("msg", msg), HttpStatus.BAD_REQUEST);
        } catch (DuplicatedException duplicatedException) {
            String msg = "Student already enrolled";
            return new ResponseEntity<>(Collections.singletonMap("msg", msg), HttpStatus.CONFLICT);
        }
    }

    @PutMapping(path = {"/remove/{courseId}"})
    public ResponseEntity<Object> unenrollStudent(@PathVariable("courseId") Integer courseId,
                                @RequestBody Student student) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            String msg = "Course with ID: " + courseId + " not found";
            return new ResponseEntity<>(Collections.singletonMap("msg", msg), HttpStatus.NOT_FOUND);
        }
        try {
            Course updatedCourse = courseService.removeStudentFromCourse(courseId, student);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (InvalidException invalidException) {
            String msg = "Invalid student data";
            return new ResponseEntity<>(Collections.singletonMap("msg", msg), HttpStatus.BAD_REQUEST);
        } catch (DuplicatedException duplicatedException) {
            String msg = "Student is not enrolled";
            return new ResponseEntity<>(Collections.singletonMap("msg", msg), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = {"{courseId}"})
    public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Integer courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            String msg = "Course with ID: " + courseId + " not found";
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseService.removeCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = {"/dep/{departmentId}"})
    public ResponseEntity<Object> getCoursesByDepartmentId(@PathVariable("departmentId") Integer departmentId) {
        List<Course> courses = courseService.getCoursesByDepartment(departmentId);
        if (courses == null) {
            String msg = "Department with ID: " + departmentId + " not found";
            return new ResponseEntity<>(Collections.singletonMap("msg", msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
