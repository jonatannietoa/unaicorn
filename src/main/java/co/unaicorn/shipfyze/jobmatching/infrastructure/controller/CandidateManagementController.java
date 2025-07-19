package co.unaicorn.shipfyze.jobmatching.infrastructure.controller;

import co.unaicorn.shipfyze.jobmatching.application.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CandidateManagementController {

    private final CandidateService candidateService;

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
}