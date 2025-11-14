package com.project.attendanceservice.DTO;

import lombok.Data;

/**
 * DTO to receive user profile data from Profile Service
 * Contains only fields needed by Attendance Service
 */
@Data
public class UserProfileDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String phoneNumber;

    // Helper method to get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}