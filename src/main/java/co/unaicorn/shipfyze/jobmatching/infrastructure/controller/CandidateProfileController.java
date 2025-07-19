package co.unaicorn.shipfyze.jobmatching.infrastructure.controller;

import co.unaicorn.shipfyze.jobmatching.application.dto.CandidateDTO;
import co.unaicorn.shipfyze.jobmatching.application.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CandidateProfileController {

    private final CandidateService candidateService;

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
}