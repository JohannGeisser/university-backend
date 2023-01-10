package com.hyperskill.university.services;

import com.hyperskill.university.models.Student;
import com.hyperskill.university.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Integer studentId) {
        return studentRepository.findStudentByStudentId(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));
    }

    public void updateStudent(Integer studentId, Student student) {

        Student studentUpdate = studentRepository.findStudentByStudentId(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));

        if (student.getFirstName() != null && student.getFirstName().length() > 0 && !Objects.equals(studentUpdate.getFirstName(), student.getFirstName())) {
            studentUpdate.setFirstName(student.getFirstName());
        }

        if (student.getLastName() != null && student.getLastName().length() > 0 && !Objects.equals(studentUpdate.getLastName(), student.getLastName())) {
            studentUpdate.setLastName(student.getLastName());
        }

        studentRepository.save(studentUpdate);
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> getStudentByCourseId(Integer courseId) {
        return studentRepository.getStudentByCourseId(courseId);
    }

    public List<Student> getStudentNotEnrolledByCourseId(Integer courseId) {
        return studentRepository.getNotEnrolledStudentByCourseId(courseId);
    }
}
