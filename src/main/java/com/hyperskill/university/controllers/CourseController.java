package com.hyperskill.university.controllers;

import com.hyperskill.university.models.Course;
import com.hyperskill.university.models.Department;
import com.hyperskill.university.models.Student;
import com.hyperskill.university.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/course")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> showCourses() {
        return courseService.getCourses();
    }

    @GetMapping(path = "{courseId}")
    public Course showCourse(@PathVariable("courseId") Integer courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseService.addNewCourse(course);
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
