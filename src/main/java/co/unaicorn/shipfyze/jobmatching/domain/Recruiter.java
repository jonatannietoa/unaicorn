package co.unaicorn.shipfyze.jobmatching.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "recruiters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recruiter")
    private Long idRecruiter;

    @NotBlank(message = "Full name is required")
    @Size(max = 255, message = "Full name must not exceed 255 characters")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Size(max = 100, message = "Position must not exceed 100 characters")
    @Column(name = "position")
    private String position;

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

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Job> jobs = new ArrayList<>();

    public void addJob(Job job) {
        jobs.add(job);
        job.setRecruiter(this);
    }

    public void removeJob(Job job) {
        jobs.remove(job);
        job.setRecruiter(null);
    }
}
