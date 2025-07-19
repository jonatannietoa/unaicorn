package co.unaicorn.shipfyze.jobmatching.domain;

import co.unaicorn.shipfyze.jobmatching.domain.enums.JobType;
import co.unaicorn.shipfyze.jobmatching.domain.enums.LocationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Long idJob;

    @NotBlank(message = "Job title is required")
    @Size(max = 255, message = "Job title must not exceed 255 characters")
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "salary_min", precision = 10, scale = 2)
    private BigDecimal salaryMin;

    @Column(name = "salary_max", precision = 10, scale = 2)
    private BigDecimal salaryMax;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type")
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    @Column(name = "location_type")
    private LocationType locationType;

    @Column(name = "location")
    private String location;

    @ElementCollection
    @CollectionTable(name = "job_required_skills",
                     joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "skill")
    @Builder.Default
    private List<String> requiredSkills = new ArrayList<>();

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id", nullable = false)
    private Recruiter recruiter;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Match> matches = new ArrayList<>();

    public void addMatch(Match match) {
        matches.add(match);
        match.setJob(this);
    }

    public void removeMatch(Match match) {
        matches.remove(match);
        match.setJob(null);
    }

    public String getSalaryRange() {
        if (salaryMin != null && salaryMax != null) {
            return "€" + salaryMin + " - €" + salaryMax;
        } else if (salaryMin != null) {
            return "From €" + salaryMin;
        } else if (salaryMax != null) {
            return "Up to €" + salaryMax;
        }
        return "Salary negotiable";
    }

    public void addRequiredSkill(String skill) {
        if (!requiredSkills.contains(skill)) {
            requiredSkills.add(skill);
        }
    }

    public void removeRequiredSkill(String skill) {
        requiredSkills.remove(skill);
    }
}
