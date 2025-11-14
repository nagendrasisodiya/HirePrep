package com.project.profileservice.mapper;

import com.project.profileservice.DTO.ProfileRequestDTO;
import com.project.profileservice.DTO.ProfileResponseDTO;
import com.project.profileservice.model.Role;
import com.project.profileservice.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProfileMapper {

    public UserProfile toEntity(ProfileRequestDTO dto) {
        UserProfile profile = new UserProfile();
        profile.setUsername(dto.getUsername());
        profile.setEmail(dto.getEmail());
        profile.setPassword(dto.getPassword()); // Will be encoded in service
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setPhoneNumber(dto.getPhoneNumber());
        profile.setRole(dto.getRole());
        profile.setGender(dto.getGender());
        profile.setPresentAddress(dto.getPresentAddress());
        profile.setPermanentAddress(dto.getPermanentAddress());

        // Role-specific fields
        if (dto.getRole() == Role.STUDENT) {
            profile.setEducation(dto.getEducation());
            profile.setExperience(dto.getExperience());
            profile.setDisabilities(dto.getDisabilities());
            profile.setResumePath(dto.getResumePath());
        } else if (dto.getRole() == Role.FACULTY) {
            profile.setDepartment(dto.getDepartment());
            profile.setEmployeeId(dto.getEmployeeId());
        }

        return profile;
    }

    public ProfileResponseDTO toDTO(UserProfile profile) {
        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(profile.getId());
        dto.setUsername(profile.getUsername());
        dto.setEmail(profile.getEmail());
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setPhoneNumber(profile.getPhoneNumber());
        dto.setRole(profile.getRole());
        dto.setGender(profile.getGender());
        dto.setPresentAddress(profile.getPresentAddress());
        dto.setPermanentAddress(profile.getPermanentAddress());

        // Role-specific fields
        if (profile.getRole() == Role.STUDENT) {
            dto.setEducation(profile.getEducation());
            dto.setExperience(profile.getExperience());
            dto.setDisabilities(profile.getDisabilities());
            dto.setResumePath(profile.getResumePath());

            // Mentor info
            if (profile.getMentor() != null) {
                ProfileResponseDTO.MentorBasicDTO mentorDTO = new ProfileResponseDTO.MentorBasicDTO();
                mentorDTO.setId(profile.getMentor().getId());
                mentorDTO.setUsername(profile.getMentor().getUsername());
                mentorDTO.setFirstName(profile.getMentor().getFirstName());
                mentorDTO.setLastName(profile.getMentor().getLastName());
                mentorDTO.setEmail(profile.getMentor().getEmail());
                mentorDTO.setDepartment(profile.getMentor().getDepartment());
                dto.setMentor(mentorDTO);
            }
        } else if (profile.getRole() == Role.FACULTY) {
            dto.setDepartment(profile.getDepartment());
            dto.setEmployeeId(profile.getEmployeeId());

            // Mentees info
            if (profile.getMentees() != null && !profile.getMentees().isEmpty()) {
                dto.setMentees(profile.getMentees().stream()
                        .map(mentee -> {
                            ProfileResponseDTO.StudentBasicDTO studentDTO = new ProfileResponseDTO.StudentBasicDTO();
                            studentDTO.setId(mentee.getId());
                            studentDTO.setUsername(mentee.getUsername());
                            studentDTO.setFirstName(mentee.getFirstName());
                            studentDTO.setLastName(mentee.getLastName());
                            studentDTO.setEmail(mentee.getEmail());
                            return studentDTO;
                        })
                        .collect(Collectors.toList()));
            }
        }

        return dto;
    }
}