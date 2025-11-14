package com.project.attendanceservice.mapper;

import com.project.attendanceservice.DTO.AttendanceResponseDTO;
import com.project.attendanceservice.model.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public AttendanceResponseDTO toDTO(Attendance attendance) {
        AttendanceResponseDTO dto = new AttendanceResponseDTO();
        dto.setId(attendance.getId());
        dto.setStudentUsername(attendance.getStudentUsername());
        dto.setStudentName(attendance.getStudentName());
        dto.setFacultyUsername(attendance.getFacultyUsername());
        dto.setFacultyName(attendance.getFacultyName());
        dto.setSubject(attendance.getSubject());
        dto.setDate(attendance.getDate());
        dto.setPresent(attendance.getPresent());
        dto.setRemarks(attendance.getRemarks());
        return dto;
    }
}