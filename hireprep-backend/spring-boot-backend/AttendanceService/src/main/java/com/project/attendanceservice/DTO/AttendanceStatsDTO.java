package com.project.attendanceservice.DTO;

import lombok.Data;

@Data
public class AttendanceStatsDTO {
    private Long totalClasses;
    private Long classesAttended;
    private Long classesMissed;
    private Double attendancePercentage;
    private String studentUsername;
    private String studentName;
}