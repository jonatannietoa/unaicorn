package co.unaicorn.shipfyze.jobmatching.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Long idCompany;

    @NotBlank(message = "Company name is required")
    @Size(max = 255, message = "Company name must not exceed 255 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "website")
    private String website;

    @Size(max = 100, message = "Sector must not exceed 100 characters")
    @Column(name = "sector")
    private String sector;

    @Column(name = "location")
    private String location;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Job> jobs = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Recruiter> recruiters = new ArrayList<>();

    public void addJob(Job job) {
        jobs.add(job);
        job.setCompany(this);
    }

    public void removeJob(Job job) {
        jobs.remove(job);
        job.setCompany(null);
    }

    public void addRecruiter(Recruiter recruiter) {
        recruiters.add(recruiter);
        recruiter.setCompany(this);
    }

    public void removeRecruiter(Recruiter recruiter) {
        recruiters.remove(recruiter);
        recruiter.setCompany(null);
    }
}
