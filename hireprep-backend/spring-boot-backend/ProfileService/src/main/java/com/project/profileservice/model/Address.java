package com.project.profileservice.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String state;
    private Integer pincode;
}