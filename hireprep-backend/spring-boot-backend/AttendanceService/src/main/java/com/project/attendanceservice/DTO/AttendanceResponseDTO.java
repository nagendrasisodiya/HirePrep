package com.project.attendanceservice.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AttendanceResponseDTO {
    private Long id;
    private String studentUsername;
    private String studentName;
    private String facultyUsername;
    private String facultyName;
    private String subject;
    private LocalDate date;
    private Boolean present;
    private String remarks;
}