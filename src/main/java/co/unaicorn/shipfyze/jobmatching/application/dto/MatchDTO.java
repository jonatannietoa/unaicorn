package co.unaicorn.shipfyze.jobmatching.application.dto;

import co.unaicorn.shipfyze.jobmatching.domain.enums.InterestStatus;
import co.unaicorn.shipfyze.jobmatching.domain.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDTO {

    private Long idMatch;

    private JobDTO.JobSimpleDTO job;

    private CandidateDTO.CandidateSimpleDTO candidate;

    @Builder.Default
    private InterestStatus candidateInterest = InterestStatus.PENDING;

    @Builder.Default
    private InterestStatus companyInterest = InterestStatus.PENDING;

    @Builder.Default
    private MatchStatus matchStatus = MatchStatus.PENDING;

    private LocalDateTime candidateViewedAt;

    private LocalDateTime companyViewedAt;

    private LocalDateTime matchedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Nested DTOs for simplified responses
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MatchSimpleDTO {
        private Long idMatch;
        private Long jobId;
        private String jobTitle;
        private String companyName;
        private Long candidateId;
        private String candidateName;
        private MatchStatus matchStatus;
        private LocalDateTime matchedAt;
        private LocalDateTime createdAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MatchCreateDTO {
        private Long jobId;
        private Long candidateId;
        private InterestStatus candidateInterest;
        private InterestStatus companyInterest;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SwipeDTO {
        private Long jobId;
        private Long candidateId;
        private InterestStatus interest; // LIKED or DISLIKED
        private String swipeType; // "CANDIDATE" or "COMPANY"
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MatchUpdateDTO {
        private InterestStatus candidateInterest;
        private InterestStatus companyInterest;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MatchStatsDTO {
        private Long totalMatches;
        private Long pendingMatches;
        private Long successfulMatches;
        private Long noMatches;
        private Double matchRate; // percentage of successful matches
        private LocalDateTime lastMatchDate;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MatchDetailsDTO {
        private Long idMatch;
        private JobDTO job;
        private CandidateDTO.CandidateProfileDTO candidate;
        private InterestStatus candidateInterest;
        private InterestStatus companyInterest;
        private MatchStatus matchStatus;
        private LocalDateTime candidateViewedAt;
        private LocalDateTime companyViewedAt;
        private LocalDateTime matchedAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PendingMatchDTO {
        private Long idMatch;
        private JobDTO.JobSimpleDTO job;
        private CandidateDTO.CandidateSimpleDTO candidate;
        private String pendingFor; // "CANDIDATE" or "COMPANY"
        private LocalDateTime createdAt;
        private Integer daysPending;
    }
}
