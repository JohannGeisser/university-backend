package com.hyperskill.university.services;

import com.hyperskill.university.models.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents();
    Student addNewStudent(Student student);
    Student getStudent(Integer studentId);
    void updateStudent(Integer studentId, Student student);
    void deleteStudent(Integer studentId);
    List<Student> getStudentByCourseId(Integer courseId);
    List<Student> getStudentNotEnrolledByCourseId(Integer courseId);
}
