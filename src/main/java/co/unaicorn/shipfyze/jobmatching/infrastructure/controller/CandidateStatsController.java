package co.unaicorn.shipfyze.jobmatching.infrastructure.controller;

import co.unaicorn.shipfyze.jobmatching.application.dto.CandidateDTO;
import co.unaicorn.shipfyze.jobmatching.application.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CandidateStatsController {

    private final CandidateService candidateService;

    @GetMapping("/stats")
    public ResponseEntity<CandidateDTO.CandidateStatsDTO> getCandidateStats() {
        CandidateDTO.CandidateStatsDTO stats = candidateService.getCandidateStats();
        return ResponseEntity.ok(stats);
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