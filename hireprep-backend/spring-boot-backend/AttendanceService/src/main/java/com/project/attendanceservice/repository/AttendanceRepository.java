package com.project.attendanceservice.repository;

import com.project.attendanceservice.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Find attendance by student username
    List<Attendance> findByStudentUsername(String username);

    // Find attendance by faculty username
    List<Attendance> findByFacultyUsername(String username);

    // Find attendance by subject
    List<Attendance> findBySubject(String subject);

    // Find attendance by date
    List<Attendance> findByDate(LocalDate date);

    // Find attendance by student and date
    Optional<Attendance> findByStudentUsernameAndSubjectAndDate(
            String studentUsername, String subject, LocalDate date);

    // Check if attendance already exists
    boolean existsByStudentUsernameAndSubjectAndDate(
            String studentUsername, String subject, LocalDate date);

    // Get attendance count for a student
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.studentUsername = :username")
    Long countByStudentUsername(@Param("username") String username);

    // Get present count for a student
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.studentUsername = :username AND a.present = true")
    Long countPresentByStudentUsername(@Param("username") String username);

    // Get attendance by student and subject
    List<Attendance> findByStudentUsernameAndSubject(String username, String subject);
}