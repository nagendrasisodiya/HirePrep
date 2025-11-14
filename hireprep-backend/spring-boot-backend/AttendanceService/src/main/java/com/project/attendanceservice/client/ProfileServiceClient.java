package com.project.attendanceservice.client;

import com.project.attendanceservice.DTO.UserProfileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import lombok.extern.slf4j.Slf4j;

/**
 * Client to communicate with Profile Service
 */
@Component
@Slf4j
public class ProfileServiceClient {

    private final RestTemplate restTemplate;
    private final String profileServiceUrl;

    public ProfileServiceClient(
            RestTemplate restTemplate,
            @Value("${profile.service.url:http://localhost:8081}") String profileServiceUrl) {
        this.restTemplate = restTemplate;
        this.profileServiceUrl = profileServiceUrl;
    }

    /**
     * Get user profile by username from Profile Service
     */
    public UserProfileDTO getUserProfile(String username) {
        try {
            String url = profileServiceUrl + "/api/profile/" + username;
            log.info("Fetching profile for username: {} from {}", username, url);

            UserProfileDTO profile = restTemplate.getForObject(url, UserProfileDTO.class);

            if (profile == null) {
                throw new RuntimeException("User not found: " + username);
            }

            return profile;
        } catch (HttpClientErrorException.NotFound e) {
            log.error("User not found: {}", username);
            throw new RuntimeException("User not found: " + username);
        } catch (Exception e) {
            log.error("Error fetching profile for user: {}", username, e);
            throw new RuntimeException("Unable to fetch user profile: " + e.getMessage());
        }
    }

    /**
     * Verify if user exists and has the specified role
     */
    public boolean verifyUserRole(String username, String expectedRole) {
        try {
            UserProfileDTO profile = getUserProfile(username);
            return profile.getRole().equalsIgnoreCase(expectedRole);
        } catch (Exception e) {
            log.error("Error verifying user role: {}", username, e);
            return false;
        }
    }
}