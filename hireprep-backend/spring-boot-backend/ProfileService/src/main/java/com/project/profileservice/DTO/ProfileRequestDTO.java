package com.project.profileservice.DTO;

import com.project.profileservice.model.Address;
import com.project.profileservice.model.Education;
import com.project.profileservice.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class ProfileRequestDTO {
    private String username;
    private String email;
    private String password;
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
    private String mentorUsername; // Username of the mentor

    // Faculty-specific
    private String department;
    private String employeeId;
}