package com.project.attendanceservice.DTO;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AttendanceRequestDTO {
    private String subject;
    private LocalDate date;
    private String facultyUsername;
    private List<StudentAttendanceDTO> students;

    @Data
    public static class StudentAttendanceDTO {
        private String username;
        private Boolean present;
        private String remarks;
    }
}