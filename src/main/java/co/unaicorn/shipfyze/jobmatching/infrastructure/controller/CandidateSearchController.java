package co.unaicorn.shipfyze.jobmatching.infrastructure.controller;

import co.unaicorn.shipfyze.jobmatching.application.dto.CandidateDTO;
import co.unaicorn.shipfyze.jobmatching.application.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CandidateSearchController {

    private final CandidateService candidateService;

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

    @GetMapping("/recent")
    public ResponseEntity<List<CandidateDTO.CandidateSimpleDTO>> getRecentCandidates() {
        List<CandidateDTO.CandidateSimpleDTO> candidates = candidateService.getRecentCandidates();
        return ResponseEntity.ok(candidates);
    }
}