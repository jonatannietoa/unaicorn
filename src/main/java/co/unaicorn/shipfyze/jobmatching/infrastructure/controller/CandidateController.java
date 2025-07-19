package co.unaicorn.shipfyze.jobmatching.infrastructure.controller;

import co.unaicorn.shipfyze.jobmatching.application.dto.CandidateDTO;
import co.unaicorn.shipfyze.jobmatching.application.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateDTO> createCandidate(@Valid @RequestBody CandidateDTO.CandidateCreateDTO createDTO) {
        CandidateDTO candidate = candidateService.createCandidate(createDTO);
        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable Long id) {
        CandidateDTO candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping
    public ResponseEntity<Page<CandidateDTO>> getAllCandidates(Pageable pageable) {
        Page<CandidateDTO> candidates = candidateService.getAllCandidates(pageable);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/active")
    public ResponseEntity<List<CandidateDTO.CandidateSimpleDTO>> getActiveCandidates() {
        List<CandidateDTO.CandidateSimpleDTO> candidates = candidateService.getActiveCandidates();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/available")
    public ResponseEntity<List<CandidateDTO.CandidateSimpleDTO>> getAvailableCandidates() {
        List<CandidateDTO.CandidateSimpleDTO> candidates = candidateService.getAvailableCandidates();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CandidateDTO.CandidateSimpleDTO>> searchCandidates(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer minYears,
            @RequestParam(required = false) Integer maxYears,
            @RequestParam(required = false) List<String> skills,
            @RequestParam(required = false) String experienceLevel,
            Pageable pageable) {

        CandidateDTO.CandidateSearchDTO searchDTO = CandidateDTO.CandidateSearchDTO.builder()
                .name(name)
                .location(location)
                .minYears(minYears)
                .maxYears(maxYears)
                .skills(skills)
                .experienceLevel(experienceLevel)
                .isAvailable(true)
                .build();

        Page<CandidateDTO.CandidateSimpleDTO> candidates = candidateService.searchCandidates(searchDTO, pageable);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/by-skills")
    public ResponseEntity<List<CandidateDTO.CandidateSimpleDTO>> getCandidatesBySkills(
            @RequestParam List<String> skills) {
        List<CandidateDTO.CandidateSimpleDTO> candidates = candidateService.getCandidatesBySkills(skills);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/by-location")
    public ResponseEntity<List<CandidateDTO.CandidateSimpleDTO>> getCandidatesByLocation(
            @RequestParam String location) {
        List<CandidateDTO.CandidateSimpleDTO> candidates = candidateService.getCandidatesByLocation(location);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/by-experience-level/{level}")
    public ResponseEntity<List<CandidateDTO.CandidateSimpleDTO>> getCandidatesByExperienceLevel(
            @PathVariable String level) {
        List<CandidateDTO.CandidateSimpleDTO> candidates = candidateService.getCandidatesByExperienceLevel(level);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<CandidateDTO.CandidateProfileDTO> getCandidateProfile(@PathVariable Long id) {
        CandidateDTO.CandidateProfileDTO profile = candidateService.getCandidateProfile(id);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/matching-job/{jobId}")
    public ResponseEntity<List<CandidateDTO.CandidateSimpleDTO>> getCandidatesMatchingJob(@PathVariable Long jobId) {
        List<CandidateDTO.CandidateSimpleDTO> candidates = candidateService.getCandidatesMatchingJob(jobId);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CandidateDTO> getCandidateByEmail(@PathVariable String email) {
        CandidateDTO candidate = candidateService.getCandidateByEmail(email);
        return ResponseEntity.ok(candidate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateDTO> updateCandidate(
            @PathVariable Long id,
            @Valid @RequestBody CandidateDTO.CandidateUpdateDTO updateDTO) {
        CandidateDTO candidate = candidateService.updateCandidate(id, updateDTO);
        return ResponseEntity.ok(candidate);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateCandidate(@PathVariable Long id) {
        candidateService.activateCandidate(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCandidate(@PathVariable Long id) {
        candidateService.deactivateCandidate(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<Void> updateAvailability(
            @PathVariable Long id,
            @RequestParam Boolean available) {
        candidateService.updateAvailability(id, available);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<Void> addSkill(@PathVariable Long id, @RequestParam String skill) {
        candidateService.addSkill(id, skill);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/skills")
    public ResponseEntity<Void> removeSkill(@PathVariable Long id, @RequestParam String skill) {
        candidateService.removeSkill(id, skill);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/experience")
    public ResponseEntity<Void> addExperience(@PathVariable Long id, @RequestBody String experience) {
        candidateService.addExperience(id, experience);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/education")
    public ResponseEntity<Void> addEducation(@PathVariable Long id, @RequestBody String education) {
        candidateService.addEducation(id, education);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats")
    public ResponseEntity<CandidateDTO.CandidateStatsDTO> getCandidateStats() {
        CandidateDTO.CandidateStatsDTO stats = candidateService.getCandidateStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<CandidateDTO.CandidateSimpleDTO>> getRecentCandidates() {
        List<CandidateDTO.CandidateSimpleDTO> candidates = candidateService.getRecentCandidates();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> candidateExists(@PathVariable Long id) {
        Boolean exists = candidateService.candidateExists(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/email/{email}/exists")
    public ResponseEntity<Boolean> emailExists(@PathVariable String email) {
        Boolean exists = candidateService.emailExists(email);
        return ResponseEntity.ok(exists);
    }
}
