package com.project.profileservice.DTO;

import com.project.profileservice.model.Address;
import com.project.profileservice.model.Education;
import com.project.profileservice.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class ProfileResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Role role;
    private String gender;

    // Address
    private Address presentAddress;
    private Address permanentAddress;

    // Student-specific
    private List<Education> education;
    private String experience;
    private String disabilities;
    private String resumePath;
    private MentorBasicDTO mentor;

    // Faculty-specific
    private String department;
    private String employeeId;
    private List<StudentBasicDTO> mentees;

    @Data
    public static class MentorBasicDTO {
        private Long id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String department;
    }

    @Data
    public static class StudentBasicDTO {
        private Long id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
    }
}