package com.project.profileservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level; // "Secondary", "senior secondary", "Graduation"
    private String schoolName;
    private String board;
    private Integer startYear; // Only for graduation
    private int completionYear;
    private Double percentage; // For 10th and 12th
    private Double cgpa;
}
