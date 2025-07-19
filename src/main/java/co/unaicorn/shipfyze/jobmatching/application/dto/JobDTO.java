package co.unaicorn.shipfyze.jobmatching.application.dto;

import co.unaicorn.shipfyze.jobmatching.domain.enums.JobType;
import co.unaicorn.shipfyze.jobmatching.domain.enums.LocationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDTO {

    private Long idJob;

    @NotBlank(message = "Job title is required")
    @Size(max = 255, message = "Job title must not exceed 255 characters")
    private String title;

    private String description;

    private BigDecimal salaryMin;

    private BigDecimal salaryMax;

    private String salaryRange;

    private JobType jobType;

    private LocationType locationType;

    private String location;

    @Builder.Default
    private List<String> requiredSkills = new ArrayList<>();

    @Builder.Default
    private Boolean isActive = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private CompanyDTO.CompanySimpleDTO company;

    private RecruiterDTO.RecruiterSimpleDTO recruiter;

    private Long totalMatches;

    private Long successfulMatches;

    private Long pendingMatches;

    // Nested DTOs for simplified responses
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class JobSimpleDTO {
        private Long idJob;
        private String title;
        private String salaryRange;
        private JobType jobType;
        private LocationType locationType;
        private String location;
        private Boolean isActive;
        private CompanyDTO.CompanySimpleDTO company;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class JobCreateDTO {
        @NotBlank(message = "Job title is required")
        @Size(max = 255, message = "Job title must not exceed 255 characters")
        private String title;

        private String description;
        private BigDecimal salaryMin;
        private BigDecimal salaryMax;
        private JobType jobType;
        private LocationType locationType;
        private String location;

        @Builder.Default
        private List<String> requiredSkills = new ArrayList<>();

        private Long companyId;
        private Long recruiterId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class JobUpdateDTO {
        private String title;
        private String description;
        private BigDecimal salaryMin;
        private BigDecimal salaryMax;
        private JobType jobType;
        private LocationType locationType;
        private String location;
        private List<String> requiredSkills;
        private Boolean isActive;
        private Long recruiterId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class JobSearchDTO {
        private String title;
        private JobType jobType;
        private LocationType locationType;
        private String location;
        private BigDecimal minSalary;
        private BigDecimal maxSalary;
        private List<String> skills;
        private Long companyId;
        private Boolean isActive;
    }
}
