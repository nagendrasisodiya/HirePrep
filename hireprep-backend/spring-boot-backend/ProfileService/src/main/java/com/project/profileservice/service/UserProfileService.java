package com.project.profileservice.service;

import com.project.profileservice.DTO.ProfileRequestDTO;
import com.project.profileservice.DTO.ProfileResponseDTO;
import com.project.profileservice.mapper.ProfileMapper;
import com.project.profileservice.model.Education;
import com.project.profileservice.model.Role;
import com.project.profileservice.model.UserProfile;
import com.project.profileservice.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    @Autowired
    private ProfileMapper mapper;


    @Transactional
    public ProfileResponseDTO createProfile(ProfileRequestDTO dto) {
        UserProfile profile = mapper.toEntity(dto);

        // Set mentor if student
        if (dto.getRole() == Role.STUDENT && dto.getMentorUsername() != null) {
            Optional<UserProfile> mentor = repository.findByUsername(dto.getMentorUsername());
            if (mentor.isPresent() && mentor.get().getRole() == Role.FACULTY) {
                profile.setMentor(mentor.get());
            } else {
                throw new IllegalArgumentException("Invalid mentor username or mentor is not a faculty");
            }
        }

        UserProfile saved = repository.save(profile);
        return mapper.toDTO(saved);
    }

    public Optional<ProfileResponseDTO> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDTO);
    }

    public List<ProfileResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProfileResponseDTO updateProfile(String username, ProfileRequestDTO dto) {
        UserProfile existing = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // Update common fields
        existing.setEmail(dto.getEmail());
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setGender(dto.getGender());
        existing.setPresentAddress(dto.getPresentAddress());
        existing.setPermanentAddress(dto.getPermanentAddress());

        // Update password only if provided
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existing.setPassword(dto.getPassword());
        }

        // Role-specific updates
        if (existing.getRole() == Role.STUDENT) {
            // Handle education update properly
            if (dto.getEducation() != null) {
                // Clear existing education (orphanRemoval will delete them)
                if (existing.getEducation() != null) {
                    existing.getEducation().clear();
                } else {
                    existing.setEducation(new ArrayList<>());
                }

                // Add new education records (without IDs, let JPA generate them)
                for (Education edu : dto.getEducation()) {
                    Education newEdu = new Education();
                    newEdu.setLevel(edu.getLevel());
                    newEdu.setSchoolName(edu.getSchoolName());
                    newEdu.setBoard(edu.getBoard());
                    newEdu.setStartYear(edu.getStartYear());
                    newEdu.setCompletionYear(edu.getCompletionYear());
                    newEdu.setPercentage(edu.getPercentage());
                    newEdu.setCgpa(edu.getCgpa());
                    existing.getEducation().add(newEdu);
                }
            }

            existing.setExperience(dto.getExperience());
            existing.setDisabilities(dto.getDisabilities());
            existing.setResumePath(dto.getResumePath());

            // Update mentor
            if (dto.getMentorUsername() != null && !dto.getMentorUsername().isEmpty()) {
                Optional<UserProfile> mentor = repository.findByUsername(dto.getMentorUsername());
                if (mentor.isPresent() && mentor.get().getRole() == Role.FACULTY) {
                    existing.setMentor(mentor.get());
                } else {
                    throw new IllegalArgumentException("Invalid mentor username or mentor is not a faculty");
                }
            } else {
                // If mentorUsername is null or empty, remove the mentor
                existing.setMentor(null);
            }
        } else if (existing.getRole() == Role.FACULTY) {
            existing.setDepartment(dto.getDepartment());
            existing.setEmployeeId(dto.getEmployeeId());
        }

        UserProfile updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Transactional
    public void deleteByUsername(String username) {
        repository.findByUsername(username).ifPresent(repository::delete);
    }

    public List<ProfileResponseDTO> getMenteesByFacultyUsername(String facultyUsername) {
        UserProfile faculty = repository.findByUsername(facultyUsername)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        if (faculty.getRole() != Role.FACULTY) {
            throw new IllegalArgumentException("User is not a faculty member");
        }

        return faculty.getMentees().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}