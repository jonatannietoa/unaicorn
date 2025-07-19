package co.unaicorn.shipfyze.jobmatching.application.service;

import co.unaicorn.shipfyze.jobmatching.application.dto.CandidateDTO;
import co.unaicorn.shipfyze.jobmatching.application.mapper.CandidateMapper;
import co.unaicorn.shipfyze.jobmatching.domain.Candidate;
import co.unaicorn.shipfyze.jobmatching.infrastructure.exception.CandidateNotFoundException;
import co.unaicorn.shipfyze.jobmatching.infrastructure.exception.EmailAlreadyExistsException;
import co.unaicorn.shipfyze.jobmatching.infrastructure.repository.CandidateRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    @Override
    public CandidateDTO createCandidate(
        CandidateDTO.CandidateCreateDTO createDTO
    ) {
        log.info("Creating new candidate with email: {}", createDTO.getEmail());

        // Check if email already exists
        if (candidateRepository.existsByEmail(createDTO.getEmail())) {
            throw EmailAlreadyExistsException.forCandidate(
                createDTO.getEmail()
            );
        }

        Candidate candidate = candidateMapper.toEntity(createDTO);
        candidate.setCreatedAt(LocalDateTime.now());
        candidate.setUpdatedAt(LocalDateTime.now());
        candidate.setIsActive(true);
        candidate.setIsAvailable(true);

        Candidate savedCandidate = candidateRepository.save(candidate);
        log.info(
            "Successfully created candidate with ID: {}",
            savedCandidate.getIdCandidate()
        );

        return candidateMapper.toDTO(savedCandidate);
    }

    @Override
    @Transactional(readOnly = true)
    public CandidateDTO getCandidateById(Long id) {
        log.info("Fetching candidate with ID: {}", id);

        Candidate candidate = candidateRepository
            .findById(id)
            .orElseThrow(() -> new CandidateNotFoundException(id));

        return candidateMapper.toDTO(candidate);
    }

    @Override
    @Transactional(readOnly = true)
    public CandidateDTO getCandidateByEmail(String email) {
        log.info("Fetching candidate with email: {}", email);

        Candidate candidate = candidateRepository
            .findByEmail(email)
            .orElseThrow(() -> new CandidateNotFoundException("email", email));

        return candidateMapper.toDTO(candidate);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CandidateDTO> getAllCandidates(Pageable pageable) {
        log.info(
            "Fetching all candidates with pagination: page {}, size {}",
            pageable.getPageNumber(),
            pageable.getPageSize()
        );

        Page<Candidate> candidates = candidateRepository.findAll(pageable);
        return candidates.map(candidateMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateDTO.CandidateSimpleDTO> getActiveCandidates() {
        log.info("Fetching all active candidates");

        List<Candidate> candidates = candidateRepository.findByIsActiveTrue();
        return candidates
            .stream()
            .map(candidateMapper::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateDTO.CandidateSimpleDTO> getAvailableCandidates() {
        log.info("Fetching all available candidates");

        List<Candidate> candidates =
            candidateRepository.findByIsActiveTrueAndIsAvailableTrue();
        return candidates
            .stream()
            .map(candidateMapper::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CandidateDTO.CandidateSimpleDTO> searchCandidates(
        CandidateDTO.CandidateSearchDTO searchDTO,
        Pageable pageable
    ) {
        log.info("Searching candidates with criteria: {}", searchDTO);

        Page<Candidate> candidates = candidateRepository.findByCriteria(
            searchDTO.getName(),
            searchDTO.getLocation(),
            searchDTO.getMinYears(),
            searchDTO.getMaxYears(),
            pageable
        );

        return candidates.map(candidateMapper::toSimpleDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateDTO.CandidateSimpleDTO> getCandidatesBySkills(
        List<String> skills
    ) {
        log.info("Fetching candidates by skills: {}", skills);

        // Convert skills to lowercase for case-insensitive search
        List<String> lowerCaseSkills = skills
            .stream()
            .map(String::toLowerCase)
            .collect(Collectors.toList());

        List<Candidate> candidates = candidateRepository.findBySkillsIn(
            lowerCaseSkills
        );
        return candidates
            .stream()
            .map(candidateMapper::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateDTO.CandidateSimpleDTO> getCandidatesByLocation(
        String location
    ) {
        log.info("Fetching candidates by location: {}", location);

        List<Candidate> candidates =
            candidateRepository.findByLocationContainingIgnoreCase(location);
        return candidates
            .stream()
            .map(candidateMapper::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateDTO.CandidateSimpleDTO> getCandidatesByExperienceLevel(
        String level
    ) {
        log.info("Fetching candidates by experience level: {}", level);

        List<Candidate> candidates = candidateRepository.findByExperienceLevel(
            level.toUpperCase()
        );
        return candidates
            .stream()
            .map(candidateMapper::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CandidateDTO.CandidateProfileDTO getCandidateProfile(Long id) {
        log.info("Fetching candidate profile for ID: {}", id);

        Candidate candidate = candidateRepository
            .findById(id)
            .orElseThrow(() -> new CandidateNotFoundException(id));

        return candidateMapper.toProfileDTO(candidate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateDTO.CandidateSimpleDTO> getCandidatesMatchingJob(
        Long jobId
    ) {
        log.info("Fetching candidates matching job ID: {}", jobId);

        List<Candidate> candidates =
            candidateRepository.findCandidatesMatchingJob(jobId);
        return candidates
            .stream()
            .map(candidateMapper::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    public CandidateDTO updateCandidate(
        Long id,
        CandidateDTO.CandidateUpdateDTO updateDTO
    ) {
        log.info("Updating candidate with ID: {}", id);

        Candidate candidate = candidateRepository
            .findById(id)
            .orElseThrow(() -> new CandidateNotFoundException(id));

        candidateMapper.updateEntityFromDTO(updateDTO, candidate);
        candidate.setUpdatedAt(LocalDateTime.now());

        Candidate updatedCandidate = candidateRepository.save(candidate);
        log.info("Successfully updated candidate with ID: {}", id);

        return candidateMapper.toDTO(updatedCandidate);
    }

    @Override
    public void activateCandidate(Long id) {
        log.info("Activating candidate with ID: {}", id);

        Candidate candidate = candidateRepository
            .findById(id)
            .orElseThrow(() -> new CandidateNotFoundException(id));

        candidate.setIsActive(true);
        candidate.setUpdatedAt(LocalDateTime.now());
        candidateRepository.save(candidate);

        log.info("Successfully activated candidate with ID: {}", id);
    }

    @Override
    public void deactivateCandidate(Long id) {
        log.info("Deactivating candidate with ID: {}", id);

        Candidate candidate = candidateRepository
            .findById(id)
            .orElseThrow(() -> new CandidateNotFoundException(id));

        candidate.setIsActive(false);
        candidate.setUpdatedAt(LocalDateTime.now());
        candidateRepository.save(candidate);

        log.info("Successfully deactivated candidate with ID: {}", id);
    }

    @Override
    public void updateAvailability(Long id, Boolean available) {
        log.info(
            "Updating availability for candidate ID: {} to: {}",
            id,
            available
        );

        Candidate candidate = candidateRepository
            .findById(id)
            .orElseThrow(() -> new CandidateNotFoundException(id));

        candidate.setIsAvailable(available);
        candidate.setUpdatedAt(LocalDateTime.now());
        candidateRepository.save(candidate);

        log.info("Successfully updated availability for candidate ID: {}", id);
    }

    @Override
    public void addSkill(Long id, String skill) {
        log.info("Adding skill '{}' to candidate ID: {}", skill, id);

        Candidate candidate = candidateRepository
            .findById(id)
            .orElseThrow(() -> new CandidateNotFoundException(id));

        candidate.addSkill(skill);
        candidate.setUpdatedAt(LocalDateTime.now());
        candidateRepository.save(candidate);

        log.info("Successfully added skill to candidate ID: {}", id);
    }

    @Override
    public void removeSkill(Long id, String skill) {
        log.info("Removing skill '{}' from candidate ID: {}", skill, id);

        Candidate candidate = candidateRepository
            .findById(id)
            .orElseThrow(() -> new CandidateNotFoundException(id));

        candidate.removeSkill(skill);
        candidate.setUpdatedAt(LocalDateTime.now());
        candidateRepository.save(candidate);

        log.info("Successfully removed skill from candidate ID: {}", id);
    }

    @Override
    public void addExperience(Long id, String experience) {
        log.info("Adding experience to candidate ID: {}", id);

        Candidate candidate = candidateRepository
            .findById(id)
            .orElseThrow(() -> new CandidateNotFoundException(id));

        candidate.addExperience(experience);
        candidate.setUpdatedAt(LocalDateTime.now());
        candidateRepository.save(candidate);

        log.info("Successfully added experience to candidate ID: {}", id);
    }

    @Override
    public void addEducation(Long id, String education) {
        log.info("Adding education to candidate ID: {}", id);

        Candidate candidate = candidateRepository
            .findById(id)
            .orElseThrow(() -> new CandidateNotFoundException(id));

        candidate.addEducation(education);
        candidate.setUpdatedAt(LocalDateTime.now());
        candidateRepository.save(candidate);

        log.info("Successfully added education to candidate ID: {}", id);
    }

    @Override
    public void deleteCandidate(Long id) {
        log.info("Deleting candidate with ID: {}", id);

        if (!candidateRepository.existsById(id)) {
            throw new CandidateNotFoundException(id);
        }

        candidateRepository.deleteById(id);
        log.info("Successfully deleted candidate with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public CandidateDTO.CandidateStatsDTO getCandidateStats() {
        log.info("Fetching candidate statistics");

        long totalCandidates = candidateRepository.count();
        long activeCandidates = candidateRepository.findByIsActiveTrue().size();
        long availableCandidates = candidateRepository
            .findByIsAvailableTrue()
            .size();

        return CandidateDTO.CandidateStatsDTO.builder()
            .totalCandidates(totalCandidates)
            .activeCandidates(activeCandidates)
            .availableCandidates(availableCandidates)
            .inactiveCandidates(totalCandidates - activeCandidates)
            .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateDTO.CandidateSimpleDTO> getRecentCandidates() {
        log.info("Fetching recent candidates");

        List<Candidate> candidates = candidateRepository.findRecentCandidates();
        return candidates
            .stream()
            .map(candidateMapper::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean candidateExists(Long id) {
        log.info("Checking if candidate exists with ID: {}", id);
        return candidateRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean emailExists(String email) {
        log.info("Checking if email exists: {}", email);
        return candidateRepository.existsByEmail(email);
    }
}
