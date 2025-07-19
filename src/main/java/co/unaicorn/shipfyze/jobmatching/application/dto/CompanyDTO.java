package co.unaicorn.shipfyze.jobmatching.application.dto;

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
public class CompanyDTO {

    private Long idCompany;

    @NotBlank(message = "Company name is required")
    @Size(max = 255, message = "Company name must not exceed 255 characters")
    private String name;

    private String description;

    private String logoUrl;

    private String website;

    @Size(max = 100, message = "Sector must not exceed 100 characters")
    private String sector;

    private String location;

    @Builder.Default
    private Boolean isActive = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder.Default
    private List<JobDTO> jobs = new ArrayList<>();

    @Builder.Default
    private List<RecruiterDTO> recruiters = new ArrayList<>();

    private Long totalJobs;

    private Long activeJobs;

    private Long totalMatches;

    // Nested DTOs for simplified responses
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CompanySimpleDTO {
        private Long idCompany;
        private String name;
        private String logoUrl;
        private String sector;
        private String location;
        private Boolean isActive;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CompanyCreateDTO {
        @NotBlank(message = "Company name is required")
        @Size(max = 255, message = "Company name must not exceed 255 characters")
        private String name;

        private String description;
        private String logoUrl;
        private String website;

        @Size(max = 100, message = "Sector must not exceed 100 characters")
        private String sector;

        private String location;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CompanyUpdateDTO {
        private String name;
        private String description;
        private String logoUrl;
        private String website;
        private String sector;
        private String location;
        private Boolean isActive;
    }
}
