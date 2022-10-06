package com.hyperskill.university.configurations;

import com.hyperskill.university.models.Course;
import com.hyperskill.university.models.Department;
import com.hyperskill.university.models.Student;
import com.hyperskill.university.repositories.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


//@Configuration
public class CourseConfiguration {



    //@Bean
    CommandLineRunner commandLineRunner(CourseRepository repository) {
        return args -> {

            Student student1 = Student.builder()
                    .firstName("Juan")
                    .lastName("Perez")
                    .build();

            Student student2 = Student.builder()
                    .firstName("Pedro")
                    .lastName("Aldo")
                    .build();

            Student student3 = Student.builder()
                    .firstName("Marcos")
                    .lastName("Arana")
                    .build();

            Department cs = Department.builder()
                    .depName("CS")
                    .build();

            Course dba = Course.builder()
                    .courseName("DBA")
                    .department(cs)
                    .students(List.of(student1, student2, student3))
                    .build();

            Course python = Course.builder()
                    .courseName("Python")
                    .department(cs)
                    .students(List.of(student1, student3))
                    .build();

            repository.saveAll(List.of(dba, python));

        };
    }

}
