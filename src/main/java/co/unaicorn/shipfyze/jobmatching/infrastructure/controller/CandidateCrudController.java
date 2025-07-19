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

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CandidateCrudController {

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }
}