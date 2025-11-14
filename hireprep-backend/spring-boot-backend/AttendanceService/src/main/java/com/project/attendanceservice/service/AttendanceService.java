package com.project.attendanceservice.service;

import com.project.attendanceservice.DTO.AttendanceRequestDTO;
import com.project.attendanceservice.DTO.AttendanceResponseDTO;
import com.project.attendanceservice.DTO.AttendanceStatsDTO;
import com.project.attendanceservice.DTO.UserProfileDTO;
import com.project.attendanceservice.client.ProfileServiceClient;
import com.project.attendanceservice.mapper.AttendanceMapper;
import com.project.attendanceservice.model.Attendance;
import com.project.attendanceservice.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ProfileServiceClient profileServiceClient;

    @Autowired
    private AttendanceMapper mapper;

    @Transactional
    public List<AttendanceResponseDTO> markAttendance(AttendanceRequestDTO request) {
        // Verify faculty exists and has FACULTY role
        log.info("Verifying faculty: {}", request.getFacultyUsername());
        UserProfileDTO faculty = profileServiceClient.getUserProfile(request.getFacultyUsername());

        if (!"FACULTY".equalsIgnoreCase(faculty.getRole())) {
            throw new IllegalArgumentException("User is not a faculty member");
        }

        List<AttendanceResponseDTO> responses = new ArrayList<>();

        for (AttendanceRequestDTO.StudentAttendanceDTO studentDto : request.getStudents()) {
            // Check if attendance already exists
            if (attendanceRepository.existsByStudentUsernameAndSubjectAndDate(
                    studentDto.getUsername(), request.getSubject(), request.getDate())) {
                throw new IllegalArgumentException(
                        "Attendance already marked for " + studentDto.getUsername() +
                                " on " + request.getDate() + " for " + request.getSubject());
            }

            // Verify student exists and has STUDENT role
            log.info("Verifying student: {}", studentDto.getUsername());
            UserProfileDTO student = profileServiceClient.getUserProfile(studentDto.getUsername());

            if (!"STUDENT".equalsIgnoreCase(student.getRole())) {
                throw new IllegalArgumentException(studentDto.getUsername() + " is not a student");
            }

            // Create attendance record
            Attendance attendance = new Attendance();
            attendance.setStudentUsername(student.getUsername());
            attendance.setStudentName(student.getFullName());
            attendance.setFacultyUsername(faculty.getUsername());
            attendance.setFacultyName(faculty.getFullName());
            attendance.setSubject(request.getSubject());
            attendance.setDate(request.getDate());
            attendance.setPresent(studentDto.getPresent());
            attendance.setRemarks(studentDto.getRemarks());

            Attendance saved = attendanceRepository.save(attendance);
            responses.add(mapper.toDTO(saved));
        }

        return responses;
    }

    public List<AttendanceResponseDTO> getStudentAttendance(String username) {
        // Verify student exists
        UserProfileDTO student = profileServiceClient.getUserProfile(username);

        if (!"STUDENT".equalsIgnoreCase(student.getRole())) {
            throw new IllegalArgumentException("User is not a student");
        }

        return attendanceRepository.findByStudentUsername(username).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public AttendanceStatsDTO getStudentStats(String username) {
        // Verify student exists
        UserProfileDTO student = profileServiceClient.getUserProfile(username);

        if (!"STUDENT".equalsIgnoreCase(student.getRole())) {
            throw new IllegalArgumentException("User is not a student");
        }

        Long total = attendanceRepository.countByStudentUsername(username);
        Long present = attendanceRepository.countPresentByStudentUsername(username);
        Long absent = total - present;

        AttendanceStatsDTO stats = new AttendanceStatsDTO();
        stats.setStudentUsername(username);
        stats.setStudentName(student.getFullName());
        stats.setTotalClasses(total);
        stats.setClassesAttended(present);
        stats.setClassesMissed(absent);

        if (total > 0) {
            stats.setAttendancePercentage((present * 100.0) / total);
        } else {
            stats.setAttendancePercentage(0.0);
        }

        return stats;
    }

    public List<AttendanceResponseDTO> getFacultyAttendanceRecords(String facultyUsername) {
        // Verify faculty exists
        UserProfileDTO faculty = profileServiceClient.getUserProfile(facultyUsername);

        if (!"FACULTY".equalsIgnoreCase(faculty.getRole())) {
            throw new IllegalArgumentException("User is not a faculty member");
        }

        return attendanceRepository.findByFacultyUsername(facultyUsername).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AttendanceResponseDTO> getAttendanceBySubject(String subject) {
        return attendanceRepository.findBySubject(subject).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

    public List<String> getAllSubjects() {
        return List.of(
                "Mathematics",
                "Physics",
                "Chemistry",
                "Computer Science",
                "Data Structures",
                "Algorithms",
                "Database Management",
                "Operating Systems",
                "Software Engineering",
                "Web Development"
        );
    }
}