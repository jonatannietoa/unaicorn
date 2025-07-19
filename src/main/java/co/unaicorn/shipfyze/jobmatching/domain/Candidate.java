package co.unaicorn.shipfyze.jobmatching.domain;

import jakarta.persistence.*;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidate")
    private Long idCandidate;

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

    @Size(max = 500, message = "Headline must not exceed 500 characters")
    @Column(name = "headline")
    private String headline;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "cv_url")
    private String cvUrl;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "location")
    private String location;

    @ElementCollection
    @CollectionTable(
        name = "candidate_skills",
        joinColumns = @JoinColumn(name = "candidate_id")
    )
    @Column(name = "skill")
    @Builder.Default
    private List<String> skills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
        name = "candidate_experience",
        joinColumns = @JoinColumn(name = "candidate_id")
    )
    @Column(name = "experience", columnDefinition = "TEXT")
    @Builder.Default
    private List<String> experience = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
        name = "candidate_education",
        joinColumns = @JoinColumn(name = "candidate_id")
    )
    @Column(name = "education", columnDefinition = "TEXT")
    @Builder.Default
    private List<String> education = new ArrayList<>();

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "is_available")
    @Builder.Default
    private Boolean isAvailable = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void addSkill(String skill) {
        if (skills == null) {
            skills = new ArrayList<>();
        }
        if (!skills.contains(skill)) {
            skills.add(skill);
        }
    }

    public void removeSkill(String skill) {
        if (skills != null) {
            skills.remove(skill);
        }
    }

    public void addExperience(String experienceItem) {
        if (experience == null) {
            experience = new ArrayList<>();
        }
        experience.add(experienceItem);
    }

    public void removeExperience(String experienceItem) {
        if (experience != null) {
            experience.remove(experienceItem);
        }
    }

    public void addEducation(String educationItem) {
        if (education == null) {
            education = new ArrayList<>();
        }
        education.add(educationItem);
    }

    public void removeEducation(String educationItem) {
        if (education != null) {
            education.remove(educationItem);
        }
    }
}
