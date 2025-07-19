package co.unaicorn.shipfyze.jobmatching.infrastructure.repository;

import co.unaicorn.shipfyze.jobmatching.domain.Match;
import co.unaicorn.shipfyze.jobmatching.domain.enums.InterestStatus;
import co.unaicorn.shipfyze.jobmatching.domain.enums.MatchStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    /**
     * Find match by job and candidate
     */
    Optional<Match> findByJobIdJobAndCandidateIdCandidate(Long jobId, Long candidateId);

    /**
     * Find all matches by job ID
     */
    List<Match> findByJobIdJob(Long jobId);

    /**
     * Find all matches by candidate ID
     */
    List<Match> findByCandidateIdCandidate(Long candidateId);

    /**
     * Find matches by match status
     */
    List<Match> findByMatchStatus(MatchStatus matchStatus);

    /**
     * Find all successful matches (MATCHED status)
     */
    List<Match> findByMatchStatusOrderByMatchedAtDesc(MatchStatus matchStatus);

    /**
     * Find matches by candidate interest
     */
    List<Match> findByCandidateInterest(InterestStatus candidateInterest);

    /**
     * Find matches by company interest
     */
    List<Match> findByCompanyInterest(InterestStatus companyInterest);

    /**
     * Find pending matches for a candidate
     */
    @Query("SELECT m FROM Match m WHERE m.candidate.idCandidate = :candidateId AND m.candidateInterest = 'PENDING'")
    List<Match> findPendingMatchesForCandidate(@Param("candidateId") Long candidateId);

    /**
     * Find pending matches for a job
     */
    @Query("SELECT m FROM Match m WHERE m.job.idJob = :jobId AND m.companyInterest = 'PENDING'")
    List<Match> findPendingMatchesForJob(@Param("jobId") Long jobId);

    /**
     * Find all successful matches for a candidate
     */
    @Query("SELECT m FROM Match m WHERE m.candidate.idCandidate = :candidateId AND m.matchStatus = 'MATCHED' ORDER BY m.matchedAt DESC")
    List<Match> findSuccessfulMatchesForCandidate(@Param("candidateId") Long candidateId);

    /**
     * Find all successful matches for a job
     */
    @Query("SELECT m FROM Match m WHERE m.job.idJob = :jobId AND m.matchStatus = 'MATCHED' ORDER BY m.matchedAt DESC")
    List<Match> findSuccessfulMatchesForJob(@Param("jobId") Long jobId);

    /**
     * Find all successful matches for a company
     */
    @Query("SELECT m FROM Match m WHERE m.job.company.idCompany = :companyId AND m.matchStatus = 'MATCHED' ORDER BY m.matchedAt DESC")
    List<Match> findSuccessfulMatchesForCompany(@Param("companyId") Long companyId);

    /**
     * Find matches where candidate liked but company is pending
     */
    @Query("SELECT m FROM Match m WHERE m.candidateInterest = 'LIKED' AND m.companyInterest = 'PENDING'")
    List<Match> findCandidateLikedPendingCompany();

    /**
     * Find matches where company liked but candidate is pending
     */
    @Query("SELECT m FROM Match m WHERE m.companyInterest = 'LIKED' AND m.candidateInterest = 'PENDING'")
    List<Match> findCompanyLikedPendingCandidate();

    /**
     * Count matches by status for a candidate
     */
    Long countByCandidateIdCandidateAndMatchStatus(Long candidateId, MatchStatus matchStatus);

    /**
     * Count matches by status for a job
     */
    Long countByJobIdJobAndMatchStatus(Long jobId, MatchStatus matchStatus);

    /**
     * Count matches by status for a company
     */
    @Query("SELECT COUNT(m) FROM Match m WHERE m.job.company.idCompany = :companyId AND m.matchStatus = :matchStatus")
    Long countByCompanyAndMatchStatus(@Param("companyId") Long companyId, @Param("matchStatus") MatchStatus matchStatus);

    /**
     * Find matches created within a date range
     */
    List<Match> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find matches that became successful within a date range
     */
    List<Match> findByMatchedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find recent matches (within last 7 days)
     */
    @Query("SELECT m FROM Match m WHERE m.createdAt >= CURRENT_DATE - 7 DAY ORDER BY m.createdAt DESC")
    List<Match> findRecentMatches();

    /**
     * Find recent successful matches (within last 7 days)
     */
    @Query("SELECT m FROM Match m WHERE m.matchedAt >= CURRENT_DATE - 7 DAY AND m.matchStatus = 'MATCHED' ORDER BY m.matchedAt DESC")
    List<Match> findRecentSuccessfulMatches();

    /**
     * Check if match exists between job and candidate
     */
    boolean existsByJobIdJobAndCandidateIdCandidate(Long jobId, Long candidateId);

    /**
     * Find matches for jobs posted by a specific recruiter
     */
    @Query("SELECT m FROM Match m WHERE m.job.recruiter.idRecruiter = :recruiterId")
    List<Match> findMatchesForRecruiter(@Param("recruiterId") Long recruiterId);

    /**
     * Find successful matches for jobs posted by a specific recruiter
     */
    @Query("SELECT m FROM Match m WHERE m.job.recruiter.idRecruiter = :recruiterId AND m.matchStatus = 'MATCHED' ORDER BY m.matchedAt DESC")
    List<Match> findSuccessfulMatchesForRecruiter(@Param("recruiterId") Long recruiterId);

    /**
     * Find matches with pagination
     */
    Page<Match> findByMatchStatusOrderByCreatedAtDesc(MatchStatus matchStatus, Pageable pageable);

    /**
     * Find matches for candidate with pagination
     */
    Page<Match> findByCandidateIdCandidateOrderByCreatedAtDesc(Long candidateId, Pageable pageable);

    /**
     * Find matches for job with pagination
     */
    Page<Match> findByJobIdJobOrderByCreatedAtDesc(Long jobId, Pageable pageable);

    /**
     * Get match statistics for a candidate
     */
    @Query("SELECT m.matchStatus, COUNT(m) FROM Match m WHERE m.candidate.idCandidate = :candidateId GROUP BY m.matchStatus")
    List<Object[]> getMatchStatisticsForCandidate(@Param("candidateId") Long candidateId);

    /**
     * Get match statistics for a job
     */
    @Query("SELECT m.matchStatus, COUNT(m) FROM Match m WHERE m.job.idJob = :jobId GROUP BY m.matchStatus")
    List<Object[]> getMatchStatisticsForJob(@Param("jobId") Long jobId);

    /**
     * Get match statistics for a company
     */
    @Query("SELECT m.matchStatus, COUNT(m) FROM Match m WHERE m.job.company.idCompany = :companyId GROUP BY m.matchStatus")
    List<Object[]> getMatchStatisticsForCompany(@Param("companyId") Long companyId);

    /**
     * Find stale pending matches (older than specified days)
     */
    @Query("SELECT m FROM Match m WHERE m.matchStatus = 'PENDING' AND m.createdAt <= :cutoffDate")
    List<Match> findStalePendingMatches(@Param("cutoffDate") LocalDateTime cutoffDate);

    /**
     * Delete old no-match records (cleanup)
     */
    @Query("DELETE FROM Match m WHERE m.matchStatus = 'NO_MATCH' AND m.updatedAt <= :cutoffDate")
    void deleteOldNoMatches(@Param("cutoffDate") LocalDateTime cutoffDate);
}
