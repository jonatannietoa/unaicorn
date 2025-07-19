package co.unaicorn.shipfyze.jobmatching.infrastructure.repository;

import co.unaicorn.shipfyze.jobmatching.domain.Recruiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    /**
     * Find recruiter by email
     */
    Optional<Recruiter> findByEmail(String email);

    /**
     * Find all active recruiters
     */
    List<Recruiter> findByIsActiveTrue();

    /**
     * Find recruiters by company ID
     */
    List<Recruiter> findByCompanyIdCompany(Long companyId);

    /**
     * Find active recruiters by company ID
     */
    List<Recruiter> findByCompanyIdCompanyAndIsActiveTrue(Long companyId);

    /**
     * Search recruiters by name
     */
    @Query("SELECT r FROM Recruiter r WHERE LOWER(r.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Recruiter> searchByName(@Param("name") String name, Pageable pageable);

    /**
     * Find recruiters by position
     */
    List<Recruiter> findByPositionIgnoreCase(String position);

    /**
     * Find recruiters with active jobs
     */
    @Query("SELECT DISTINCT r FROM Recruiter r JOIN r.jobs j WHERE j.isActive = true")
    List<Recruiter> findRecruitersWithActiveJobs();

    /**
     * Count recruiters by company
     */
    Long countByCompanyIdCompany(Long companyId);

    /**
     * Find recruiters by company and position
     */
    List<Recruiter> findByCompanyIdCompanyAndPositionIgnoreCase(Long companyId, String position);

    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);

    /**
     * Find top recruiters by number of active jobs
     */
    @Query("SELECT r FROM Recruiter r JOIN r.jobs j WHERE j.isActive = true " +
           "GROUP BY r ORDER BY COUNT(j) DESC")
    Page<Recruiter> findTopRecruitersByActiveJobs(Pageable pageable);
}
