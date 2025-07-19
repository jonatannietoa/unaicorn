package co.unaicorn.shipfyze.jobmatching.infrastructure.repository;

import co.unaicorn.shipfyze.jobmatching.domain.Candidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@SpringJUnitConfig
public class CandidateRepositoryTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    public void testFindCandidatesWithoutMatches() {
        // This test will verify that the repository can be instantiated
        // and the query validation passes
        assertNotNull(candidateRepository);
        
        // Try to call the method that was causing the validation error
        List<Candidate> candidates = candidateRepository.findCandidatesWithoutMatches();
        assertNotNull(candidates);
    }
}