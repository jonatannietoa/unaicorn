package co.unaicorn.shipfyze.jobmatching.domain;

import co.unaicorn.shipfyze.jobmatching.domain.enums.InterestStatus;
import co.unaicorn.shipfyze.jobmatching.domain.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"job_id", "candidate_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_match")
    private Long idMatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @Enumerated(EnumType.STRING)
    @Column(name = "candidate_interest", nullable = false)
    @Builder.Default
    private InterestStatus candidateInterest = InterestStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_interest", nullable = false)
    @Builder.Default
    private InterestStatus companyInterest = InterestStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "match_status", nullable = false)
    @Builder.Default
    private MatchStatus matchStatus = MatchStatus.PENDING;

    @Column(name = "candidate_viewed_at")
    private LocalDateTime candidateViewedAt;

    @Column(name = "company_viewed_at")
    private LocalDateTime companyViewedAt;

    @Column(name = "matched_at")
    private LocalDateTime matchedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void setCandidateInterest(InterestStatus candidateInterest) {
        this.candidateInterest = candidateInterest;
        if (candidateInterest == InterestStatus.LIKED || candidateInterest == InterestStatus.DISLIKED) {
            this.candidateViewedAt = LocalDateTime.now();
        }
        updateMatchStatus();
    }

    public void setCompanyInterest(InterestStatus companyInterest) {
        this.companyInterest = companyInterest;
        if (companyInterest == InterestStatus.LIKED || companyInterest == InterestStatus.DISLIKED) {
            this.companyViewedAt = LocalDateTime.now();
        }
        updateMatchStatus();
    }

    private void updateMatchStatus() {
        if (candidateInterest == InterestStatus.LIKED && companyInterest == InterestStatus.LIKED) {
            this.matchStatus = MatchStatus.MATCHED;
            if (this.matchedAt == null) {
                this.matchedAt = LocalDateTime.now();
            }
        } else if (candidateInterest == InterestStatus.DISLIKED || companyInterest == InterestStatus.DISLIKED) {
            this.matchStatus = MatchStatus.NO_MATCH;
        } else {
            this.matchStatus = MatchStatus.PENDING;
        }
    }

    public boolean isMatched() {
        return matchStatus == MatchStatus.MATCHED;
    }

    public boolean isPending() {
        return matchStatus == MatchStatus.PENDING;
    }

    public boolean isNoMatch() {
        return matchStatus == MatchStatus.NO_MATCH;
    }
}
