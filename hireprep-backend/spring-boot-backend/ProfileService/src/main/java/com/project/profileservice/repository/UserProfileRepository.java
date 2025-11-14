package com.project.profileservice.repository;

import com.project.profileservice.model.Role;
import com.project.profileservice.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUsername(String username);
    Optional<UserProfile> findByEmail(String email);
    List<UserProfile> findByRole(Role role);
    List<UserProfile> findByMentorId(Long mentorId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}