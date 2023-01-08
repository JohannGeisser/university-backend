package com.hyperskill.university.controllers;

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
        Course newCourse = courseService.addNewCourse(course);
        if (newCourse == null) {
            return new ResponseEntity<>(Collections.singletonMap("msg", "Error creating course"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED) ;
    }

    @PutMapping(path = {"{courseId}"})
    public void enrollStudent(@PathVariable("courseId") Integer courseId,
                              @RequestBody Student student) {
        courseService.enrollStudent(courseId, student);
    }

    @PutMapping(path = {"/remove/{courseId}"})
    public void unenrollStudent(@PathVariable("courseId") Integer courseId,
                                @RequestBody Student student) {
        courseService.removeStudentFromCourse(courseId, student);
    }

    @DeleteMapping(path = {"{courseId}"})
    public void deleteCourse(@PathVariable("courseId") Integer courseId) {
        courseService.removeCourse(courseId);
    }

    @GetMapping(path = {"/dep/{departmentId}"})
    public List<Course> getCoursesByDepartmentId(@PathVariable("departmentId") Integer departmentId) {
        return courseService.getCoursesByDepartment(departmentId);
    }
}
