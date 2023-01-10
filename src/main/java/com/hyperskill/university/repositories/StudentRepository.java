package com.hyperskill.university.repositories;

import com.hyperskill.university.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    Optional<Student> findStudentByStudentId(Integer studentId);

    //@Query("SELECT cr.students FROM Course cr JOIN cr.students WHERE cr.courseId =?1")

    //@Query("SELECT st FROM Course cr JOIN FETCH cr.students st WHERE cr.courseId =?1")


    @Query(value = "SELECT c.* FROM course a JOIN course_has_student b ON a.course_id = b.course_id JOIN student c on b.student_id = c.student_id WHERE a.course_id =?1", nativeQuery = true)
    List<Student> getStudentByCourseId(Integer courseId);


    @Query(value = "SELECT s.* FROM student s WHERE s.student_id NOT IN (SELECT student_id FROM course_has_student chs WHERE chs.course_id =?1)", nativeQuery = true)
    List<Student> getNotEnrolledStudentByCourseId(Integer courseId);
}
