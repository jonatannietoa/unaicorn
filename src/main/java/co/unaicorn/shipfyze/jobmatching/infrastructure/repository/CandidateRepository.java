package co.unaicorn.shipfyze.jobmatching.infrastructure.repository;

import co.unaicorn.shipfyze.jobmatching.domain.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    /**
     * Find candidate by email
     */
    Optional<Candidate> findByEmail(String email);

    /**
     * Find all active candidates
     */
    List<Candidate> findByIsActiveTrue();

    /**
     * Find all available candidates
     */
    List<Candidate> findByIsAvailableTrue();

    /**
     * Find active and available candidates
     */
    List<Candidate> findByIsActiveTrueAndIsAvailableTrue();

    /**
     * Search candidates by name
     */
    @Query("SELECT c FROM Candidate c WHERE LOWER(c.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Candidate> searchByName(@Param("name") String name, Pageable pageable);

    /**
     * Search candidates by headline
     */
    @Query("SELECT c FROM Candidate c WHERE LOWER(c.headline) LIKE LOWER(CONCAT('%', :headline, '%')) AND c.isActive = true")
    List<Candidate> findByHeadlineContainingIgnoreCase(@Param("headline") String headline);

    /**
     * Find candidates by location
     */
    @Query("SELECT c FROM Candidate c WHERE LOWER(c.location) LIKE LOWER(CONCAT('%', :location, '%')) AND c.isActive = true")
    List<Candidate> findByLocationContainingIgnoreCase(@Param("location") String location);

    /**
     * Find candidates by years of experience range
     */
    @Query("SELECT c FROM Candidate c WHERE " +
           "(:minYears IS NULL OR c.yearsOfExperience >= :minYears) AND " +
           "(:maxYears IS NULL OR c.yearsOfExperience <= :maxYears) AND " +
           "c.isActive = true")
    List<Candidate> findByExperienceRange(@Param("minYears") Integer minYears, @Param("maxYears") Integer maxYears);

    /**
     * Find candidates by skills
     */
    @Query("SELECT DISTINCT c FROM Candidate c JOIN c.skills s WHERE LOWER(s) IN :skills AND c.isActive = true")
    List<Candidate> findBySkillsIn(@Param("skills") List<String> skills);

    /**
     * Find candidates matching job requirements
     */
    @Query("SELECT DISTINCT c FROM Candidate c JOIN c.skills cs " +
           "WHERE LOWER(cs) IN (SELECT LOWER(js) FROM Job j JOIN j.requiredSkills js WHERE j.idJob = :jobId) " +
           "AND c.isActive = true AND c.isAvailable = true")
    List<Candidate> findCandidatesMatchingJob(@Param("jobId") Long jobId);

    /**
     * Search candidates by multiple criteria
     */
    @Query("SELECT c FROM Candidate c WHERE " +
           "(:name IS NULL OR LOWER(c.fullName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:location IS NULL OR LOWER(c.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
           "(:minYears IS NULL OR c.yearsOfExperience >= :minYears) AND " +
           "(:maxYears IS NULL OR c.yearsOfExperience <= :maxYears) AND " +
           "c.isActive = true AND c.isAvailable = true")
    Page<Candidate> findByCriteria(@Param("name") String name,
                                  @Param("location") String location,
                                  @Param("minYears") Integer minYears,
                                  @Param("maxYears") Integer maxYears,
                                  Pageable pageable);

    /**
     * Find candidates with specific skills count
     */
    @Query("SELECT c FROM Candidate c WHERE SIZE(c.skills) >= :minSkills AND c.isActive = true")
    List<Candidate> findByMinimumSkillsCount(@Param("minSkills") int minSkills);

    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);

    /**
     * Find candidates without matches
     */
    @Query("SELECT c FROM Candidate c WHERE NOT EXISTS (SELECT m FROM Match m WHERE m.candidate = c) AND c.isActive = true AND c.isAvailable = true")
    List<Candidate> findCandidatesWithoutMatches();

    /**
     * Find most active candidates (by number of matches)
     */
    @Query("SELECT c FROM Candidate c WHERE c.isActive = true ORDER BY (SELECT COUNT(m) FROM Match m WHERE m.candidate = c) DESC")
    Page<Candidate> findMostActiveCandidates(Pageable pageable);

    /**
     * Find recent candidates (registered within last 30 days)
     */
    @Query("SELECT c FROM Candidate c WHERE c.createdAt >= CURRENT_DATE - 30 DAY AND c.isActive = true ORDER BY c.createdAt DESC")
    List<Candidate> findRecentCandidates();

    /**
     * Count candidates by location
     */
    @Query("SELECT COUNT(c) FROM Candidate c WHERE LOWER(c.location) LIKE LOWER(CONCAT('%', :location, '%')) AND c.isActive = true")
    Long countByLocationContainingIgnoreCase(@Param("location") String location);

    /**
     * Find candidates by experience level categories
     */
    @Query("SELECT c FROM Candidate c WHERE " +
           "((:level = 'JUNIOR' AND c.yearsOfExperience <= 2) OR " +
           "(:level = 'MID' AND c.yearsOfExperience BETWEEN 3 AND 5) OR " +
           "(:level = 'SENIOR' AND c.yearsOfExperience BETWEEN 6 AND 10) OR " +
           "(:level = 'LEAD' AND c.yearsOfExperience > 10)) " +
           "AND c.isActive = true")
    List<Candidate> findByExperienceLevel(@Param("level") String level);
}
