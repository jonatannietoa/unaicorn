package co.unaicorn.shipfyze.jobmatching.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruiterDTO {

    private Long idRecruiter;

    @NotBlank(message = "Full name is required")
    @Size(max = 255, message = "Full name must not exceed 255 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;

    @Size(max = 100, message = "Position must not exceed 100 characters")
    private String position;

    @Builder.Default
    private Boolean isActive = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private CompanyDTO.CompanySimpleDTO company;

    @Builder.Default
    private List<JobDTO.JobSimpleDTO> jobs = new ArrayList<>();

    private Long totalJobs;

    private Long activeJobs;

    private Long totalMatches;

    // Nested DTOs for simplified responses
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RecruiterSimpleDTO {
        private Long idRecruiter;
        private String fullName;
        private String email;
        private String position;
        private Boolean isActive;
        private CompanyDTO.CompanySimpleDTO company;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RecruiterCreateDTO {
        @NotBlank(message = "Full name is required")
        @Size(max = 255, message = "Full name must not exceed 255 characters")
        private String fullName;

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        private String email;

        @Size(max = 20, message = "Phone number must not exceed 20 characters")
        private String phoneNumber;

        @Size(max = 100, message = "Position must not exceed 100 characters")
        private String position;

        private Long companyId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RecruiterUpdateDTO {
        private String fullName;
        private String phoneNumber;
        private String position;
        private Boolean isActive;
        private Long companyId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RecruiterProfileDTO {
        private Long idRecruiter;
        private String fullName;
        private String email;
        private String phoneNumber;
        private String position;
        private Boolean isActive;
        private LocalDateTime createdAt;
        private CompanyDTO.CompanySimpleDTO company;
        private Long totalJobs;
        private Long activeJobs;
        private Long totalMatches;
    }
}
