package co.unaicorn.shipfyze.jobmatching.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateDTO {

    private Long idCandidate;

    @NotBlank(message = "Full name is required")
    @Size(max = 255, message = "Full name must not exceed 255 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;

    @Size(max = 500, message = "Headline must not exceed 500 characters")
    private String headline;

    private String summary;

    private String cvUrl;

    private String profileImageUrl;

    private String location;

    @Builder.Default
    private List<String> skills = new ArrayList<>();

    @Builder.Default
    private List<String> experience = new ArrayList<>();

    @Builder.Default
    private List<String> education = new ArrayList<>();

    private Integer yearsOfExperience;

    @Builder.Default
    private Boolean isActive = true;

    @Builder.Default
    private Boolean isAvailable = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long totalMatches;

    private Long successfulMatches;

    private Long pendingMatches;

    // Nested DTOs for simplified responses
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CandidateSimpleDTO {

        private Long idCandidate;
        private String fullName;
        private String headline;
        private String profileImageUrl;
        private String location;
        private Integer yearsOfExperience;
        private Boolean isActive;
        private Boolean isAvailable;
        private List<String> skills;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CandidateCreateDTO {

        @NotBlank(message = "Full name is required")
        @Size(max = 255, message = "Full name must not exceed 255 characters")
        private String fullName;

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        private String email;

        @Size(max = 20, message = "Phone number must not exceed 20 characters")
        private String phoneNumber;

        @Size(max = 500, message = "Headline must not exceed 500 characters")
        private String headline;

        private String summary;
        private String cvUrl;
        private String profileImageUrl;
        private String location;

        @Builder.Default
        private List<String> skills = new ArrayList<>();

        @Builder.Default
        private List<String> experience = new ArrayList<>();

        @Builder.Default
        private List<String> education = new ArrayList<>();

        private Integer yearsOfExperience;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CandidateUpdateDTO {

        private String fullName;
        private String phoneNumber;
        private String headline;
        private String summary;
        private String cvUrl;
        private String profileImageUrl;
        private String location;
        private List<String> skills;
        private List<String> experience;
        private List<String> education;
        private Integer yearsOfExperience;
        private Boolean isActive;
        private Boolean isAvailable;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CandidateSearchDTO {

        private String name;
        private String location;
        private Integer minYears;
        private Integer maxYears;
        private List<String> skills;
        private String experienceLevel; // JUNIOR, MID, SENIOR, LEAD
        private Boolean isAvailable;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CandidateProfileDTO {

        private Long idCandidate;
        private String fullName;
        private String email;
        private String phoneNumber;
        private String headline;
        private String summary;
        private String cvUrl;
        private String profileImageUrl;
        private String location;
        private List<String> skills;
        private List<String> experience;
        private List<String> education;
        private Integer yearsOfExperience;
        private Boolean isActive;
        private Boolean isAvailable;
        private LocalDateTime createdAt;
        private Long totalMatches;
        private Long successfulMatches;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CandidateStatsDTO {

        private Long totalCandidates;
        private Long activeCandidates;
        private Long availableCandidates;
        private Long inactiveCandidates;
        private Long candidatesWithoutMatches;
        private Double averageExperience;
        private Long totalSkills;
        private String mostCommonLocation;
        private String mostCommonSkill;
    }
}
