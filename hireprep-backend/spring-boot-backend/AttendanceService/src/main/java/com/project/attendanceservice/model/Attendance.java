package com.project.attendanceservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * Modified Attendance entity - NO direct relationship to UserProfile
 * Stores only usernames, names are fetched from Profile Service when needed
 */
@Entity
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Store only username instead of full user object
    @Column(name = "student_username", nullable = false)
    private String studentUsername;

    @Column(name = "student_name")
    private String studentName; // Cache name to reduce API calls

    @Column(name = "faculty_username", nullable = false)
    private String facultyUsername;

    @Column(name = "faculty_name")
    private String facultyName; // Cache name to reduce API calls

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Boolean present;

    private String remarks;
}