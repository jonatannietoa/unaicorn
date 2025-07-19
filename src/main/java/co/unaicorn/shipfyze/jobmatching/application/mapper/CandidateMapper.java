package co.unaicorn.shipfyze.jobmatching.application.mapper;

import co.unaicorn.shipfyze.jobmatching.application.dto.CandidateDTO;
import co.unaicorn.shipfyze.jobmatching.domain.Candidate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    public CandidateDTO toDTO(Candidate candidate) {
        if (candidate == null) {
            return null;
        }

        return CandidateDTO.builder()
            .idCandidate(candidate.getIdCandidate())
            .fullName(candidate.getFullName())
            .email(candidate.getEmail())
            .phoneNumber(candidate.getPhoneNumber())
            .headline(candidate.getHeadline())
            .summary(candidate.getSummary())
            .cvUrl(candidate.getCvUrl())
            .profileImageUrl(candidate.getProfileImageUrl())
            .location(candidate.getLocation())
            .skills(
                candidate.getSkills() != null
                    ? new ArrayList<>(candidate.getSkills())
                    : new ArrayList<>()
            )
            .experience(
                candidate.getExperience() != null
                    ? new ArrayList<>(candidate.getExperience())
                    : new ArrayList<>()
            )
            .education(
                candidate.getEducation() != null
                    ? new ArrayList<>(candidate.getEducation())
                    : new ArrayList<>()
            )
            .yearsOfExperience(candidate.getYearsOfExperience())
            .isActive(candidate.getIsActive())
            .isAvailable(candidate.getIsAvailable())
            .createdAt(candidate.getCreatedAt())
            .updatedAt(candidate.getUpdatedAt())
            .totalMatches(0L)
            .successfulMatches(0L)
            .pendingMatches(0L)
            .build();
    }

    public CandidateDTO.CandidateSimpleDTO toSimpleDTO(Candidate candidate) {
        if (candidate == null) {
            return null;
        }

        return CandidateDTO.CandidateSimpleDTO.builder()
            .idCandidate(candidate.getIdCandidate())
            .fullName(candidate.getFullName())
            .headline(candidate.getHeadline())
            .profileImageUrl(candidate.getProfileImageUrl())
            .location(candidate.getLocation())
            .yearsOfExperience(candidate.getYearsOfExperience())
            .isActive(candidate.getIsActive())
            .isAvailable(candidate.getIsAvailable())
            .skills(
                candidate.getSkills() != null
                    ? new ArrayList<>(candidate.getSkills())
                    : new ArrayList<>()
            )
            .build();
    }

    public CandidateDTO.CandidateProfileDTO toProfileDTO(Candidate candidate) {
        if (candidate == null) {
            return null;
        }

        return CandidateDTO.CandidateProfileDTO.builder()
            .idCandidate(candidate.getIdCandidate())
            .fullName(candidate.getFullName())
            .email(candidate.getEmail())
            .phoneNumber(candidate.getPhoneNumber())
            .headline(candidate.getHeadline())
            .summary(candidate.getSummary())
            .cvUrl(candidate.getCvUrl())
            .profileImageUrl(candidate.getProfileImageUrl())
            .location(candidate.getLocation())
            .skills(
                candidate.getSkills() != null
                    ? new ArrayList<>(candidate.getSkills())
                    : new ArrayList<>()
            )
            .experience(
                candidate.getExperience() != null
                    ? new ArrayList<>(candidate.getExperience())
                    : new ArrayList<>()
            )
            .education(
                candidate.getEducation() != null
                    ? new ArrayList<>(candidate.getEducation())
                    : new ArrayList<>()
            )
            .yearsOfExperience(candidate.getYearsOfExperience())
            .isActive(candidate.getIsActive())
            .isAvailable(candidate.getIsAvailable())
            .createdAt(candidate.getCreatedAt())
            .totalMatches(0L)
            .successfulMatches(0L)
            .build();
    }

    public Candidate toEntity(CandidateDTO.CandidateCreateDTO createDTO) {
        if (createDTO == null) {
            return null;
        }

        return Candidate.builder()
            .fullName(createDTO.getFullName())
            .email(createDTO.getEmail())
            .phoneNumber(createDTO.getPhoneNumber())
            .headline(createDTO.getHeadline())
            .summary(createDTO.getSummary())
            .cvUrl(createDTO.getCvUrl())
            .profileImageUrl(createDTO.getProfileImageUrl())
            .location(createDTO.getLocation())
            .skills(
                createDTO.getSkills() != null
                    ? new ArrayList<>(createDTO.getSkills())
                    : new ArrayList<>()
            )
            .experience(
                createDTO.getExperience() != null
                    ? new ArrayList<>(createDTO.getExperience())
                    : new ArrayList<>()
            )
            .education(
                createDTO.getEducation() != null
                    ? new ArrayList<>(createDTO.getEducation())
                    : new ArrayList<>()
            )
            .yearsOfExperience(createDTO.getYearsOfExperience())
            .isActive(true)
            .isAvailable(true)
            .build();
    }

    public void updateEntityFromDTO(
        CandidateDTO.CandidateUpdateDTO updateDTO,
        Candidate candidate
    ) {
        if (updateDTO == null || candidate == null) {
            return;
        }

        if (updateDTO.getFullName() != null) {
            candidate.setFullName(updateDTO.getFullName());
        }
        if (updateDTO.getPhoneNumber() != null) {
            candidate.setPhoneNumber(updateDTO.getPhoneNumber());
        }
        if (updateDTO.getHeadline() != null) {
            candidate.setHeadline(updateDTO.getHeadline());
        }
        if (updateDTO.getSummary() != null) {
            candidate.setSummary(updateDTO.getSummary());
        }
        if (updateDTO.getCvUrl() != null) {
            candidate.setCvUrl(updateDTO.getCvUrl());
        }
        if (updateDTO.getProfileImageUrl() != null) {
            candidate.setProfileImageUrl(updateDTO.getProfileImageUrl());
        }
        if (updateDTO.getLocation() != null) {
            candidate.setLocation(updateDTO.getLocation());
        }
        if (updateDTO.getSkills() != null) {
            candidate.setSkills(new ArrayList<>(updateDTO.getSkills()));
        }
        if (updateDTO.getExperience() != null) {
            candidate.setExperience(new ArrayList<>(updateDTO.getExperience()));
        }
        if (updateDTO.getEducation() != null) {
            candidate.setEducation(new ArrayList<>(updateDTO.getEducation()));
        }
        if (updateDTO.getYearsOfExperience() != null) {
            candidate.setYearsOfExperience(updateDTO.getYearsOfExperience());
        }
        if (updateDTO.getIsActive() != null) {
            candidate.setIsActive(updateDTO.getIsActive());
        }
        if (updateDTO.getIsAvailable() != null) {
            candidate.setIsAvailable(updateDTO.getIsAvailable());
        }
    }

    public Candidate toEntity(CandidateDTO candidateDTO) {
        if (candidateDTO == null) {
            return null;
        }

        return Candidate.builder()
            .idCandidate(candidateDTO.getIdCandidate())
            .fullName(candidateDTO.getFullName())
            .email(candidateDTO.getEmail())
            .phoneNumber(candidateDTO.getPhoneNumber())
            .headline(candidateDTO.getHeadline())
            .summary(candidateDTO.getSummary())
            .cvUrl(candidateDTO.getCvUrl())
            .profileImageUrl(candidateDTO.getProfileImageUrl())
            .location(candidateDTO.getLocation())
            .skills(
                candidateDTO.getSkills() != null
                    ? new ArrayList<>(candidateDTO.getSkills())
                    : new ArrayList<>()
            )
            .experience(
                candidateDTO.getExperience() != null
                    ? new ArrayList<>(candidateDTO.getExperience())
                    : new ArrayList<>()
            )
            .education(
                candidateDTO.getEducation() != null
                    ? new ArrayList<>(candidateDTO.getEducation())
                    : new ArrayList<>()
            )
            .yearsOfExperience(candidateDTO.getYearsOfExperience())
            .isActive(candidateDTO.getIsActive())
            .isAvailable(candidateDTO.getIsAvailable())
            .createdAt(candidateDTO.getCreatedAt())
            .updatedAt(candidateDTO.getUpdatedAt())
            .build();
    }
}
