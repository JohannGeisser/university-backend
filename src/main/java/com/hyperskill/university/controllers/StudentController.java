package com.hyperskill.university.controllers;

import com.hyperskill.university.models.Student;
import com.hyperskill.university.services.StudentService;
import com.hyperskill.university.services.StudentServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/students")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentServiceImp studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> showStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = {"{studentId}"})
    public Student showStudent(@PathVariable("studentId") Integer studentId) {
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @PutMapping(path = {"{studentId}"})
    public void editStudent(
            @PathVariable("studentId") Integer studentId,
            @RequestBody Student student
    ) {
        studentService.updateStudent(studentId, student);
    }

    @DeleteMapping(path = {"{studentId}"})
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        studentService.deleteStudent(studentId);
    }

    @GetMapping(path = {"/enrollment/{courseId}"})
    public List<Student> showStudentByCourse(@PathVariable("courseId") Integer courseId) {
        return studentService.getStudentByCourseId(courseId);
    }

    @GetMapping(path = {"/noenrollment/{courseId}"})
    public List<Student> showStudentNotEnrolledByCourse(@PathVariable("courseId") Integer courseId) {
        return studentService.getStudentNotEnrolledByCourseId(courseId);
    }
}
