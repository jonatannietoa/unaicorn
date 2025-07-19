package co.unaicorn.shipfyze.jobmatching.application.service;

import co.unaicorn.shipfyze.jobmatching.application.dto.CandidateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CandidateService {

    /**
     * Create a new candidate
     */
    CandidateDTO createCandidate(CandidateDTO.CandidateCreateDTO createDTO);

    /**
     * Get candidate by ID
     */
    CandidateDTO getCandidateById(Long id);

    /**
     * Get candidate by email
     */
    CandidateDTO getCandidateByEmail(String email);

    /**
     * Get all candidates with pagination
     */
    Page<CandidateDTO> getAllCandidates(Pageable pageable);

    /**
     * Get all active candidates
     */
    List<CandidateDTO.CandidateSimpleDTO> getActiveCandidates();

    /**
     * Get all available candidates
     */
    List<CandidateDTO.CandidateSimpleDTO> getAvailableCandidates();

    /**
     * Search candidates by criteria
     */
    Page<CandidateDTO.CandidateSimpleDTO> searchCandidates(CandidateDTO.CandidateSearchDTO searchDTO, Pageable pageable);

    /**
     * Get candidates by skills
     */
    List<CandidateDTO.CandidateSimpleDTO> getCandidatesBySkills(List<String> skills);

    /**
     * Get candidates by location
     */
    List<CandidateDTO.CandidateSimpleDTO> getCandidatesByLocation(String location);

    /**
     * Get candidates by experience level
     */
    List<CandidateDTO.CandidateSimpleDTO> getCandidatesByExperienceLevel(String level);

    /**
     * Get candidate profile
     */
    CandidateDTO.CandidateProfileDTO getCandidateProfile(Long id);

    /**
     * Get candidates matching a specific job
     */
    List<CandidateDTO.CandidateSimpleDTO> getCandidatesMatchingJob(Long jobId);

    /**
     * Update candidate
     */
    CandidateDTO updateCandidate(Long id, CandidateDTO.CandidateUpdateDTO updateDTO);

    /**
     * Activate candidate
     */
    void activateCandidate(Long id);

    /**
     * Deactivate candidate
     */
    void deactivateCandidate(Long id);

    /**
     * Update candidate availability
     */
    void updateAvailability(Long id, Boolean available);

    /**
     * Add skill to candidate
     */
    void addSkill(Long id, String skill);

    /**
     * Remove skill from candidate
     */
    void removeSkill(Long id, String skill);

    /**
     * Add experience to candidate
     */
    void addExperience(Long id, String experience);

    /**
     * Add education to candidate
     */
    void addEducation(Long id, String education);

    /**
     * Delete candidate
     */
    void deleteCandidate(Long id);

    /**
     * Get candidate statistics
     */
    CandidateDTO.CandidateStatsDTO getCandidateStats();

    /**
     * Get recent candidates
     */
    List<CandidateDTO.CandidateSimpleDTO> getRecentCandidates();

    /**
     * Check if candidate exists
     */
    Boolean candidateExists(Long id);

    /**
     * Check if email exists
     */
    Boolean emailExists(String email);
}
