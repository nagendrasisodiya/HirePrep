package com.project.profileservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.STUDENT;

    @Column(nullable = false)
    private String gender;

    // Common fields
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "addressLine1", column = @Column(name = "present_line1")),
            @AttributeOverride(name = "addressLine2", column = @Column(name = "present_line2")),
            @AttributeOverride(name = "state", column = @Column(name = "present_state")),
            @AttributeOverride(name = "pincode", column = @Column(name = "present_pincode"))
    })
    private Address presentAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "addressLine1", column = @Column(name = "permanent_line1")),
            @AttributeOverride(name = "addressLine2", column = @Column(name = "permanent_line2")),
            @AttributeOverride(name = "state", column = @Column(name = "permanent_state")),
            @AttributeOverride(name = "pincode", column = @Column(name = "permanent_pincode"))
    })
    private Address permanentAddress;

    // Student fields
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_profile_id")
    private List<Education> education;

    private String experience;
    private String disabilities;
    private String resumePath;

    // Mentor relationship - only for students
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private UserProfile mentor;

    // For faculty to see their mentees
    @OneToMany(mappedBy = "mentor")
    private List<UserProfile> mentees;

    // Faculty fields
    private String department;
    private String employeeId;
}