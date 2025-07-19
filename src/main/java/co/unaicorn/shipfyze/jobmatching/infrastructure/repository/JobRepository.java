package co.unaicorn.shipfyze.jobmatching.infrastructure.repository;

import co.unaicorn.shipfyze.jobmatching.domain.Job;
import co.unaicorn.shipfyze.jobmatching.domain.enums.JobType;
import co.unaicorn.shipfyze.jobmatching.domain.enums.LocationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    /**
     * Find all active jobs
     */
    List<Job> findByIsActiveTrue();

    /**
     * Find jobs by company ID
     */
    List<Job> findByCompanyIdCompany(Long companyId);

    /**
     * Find active jobs by company ID
     */
    List<Job> findByCompanyIdCompanyAndIsActiveTrue(Long companyId);

    /**
     * Find jobs by recruiter ID
     */
    List<Job> findByRecruiterIdRecruiter(Long recruiterId);

    /**
     * Find active jobs by recruiter ID
     */
    List<Job> findByRecruiterIdRecruiterAndIsActiveTrue(Long recruiterId);

    /**
     * Find jobs by job type
     */
    List<Job> findByJobTypeAndIsActiveTrue(JobType jobType);

    /**
     * Find jobs by location type
     */
    List<Job> findByLocationTypeAndIsActiveTrue(LocationType locationType);

    /**
     * Search jobs by title
     */
    @Query("SELECT j FROM Job j WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%')) AND j.isActive = true")
    Page<Job> searchByTitle(@Param("title") String title, Pageable pageable);

    /**
     * Search jobs by title or description
     */
    @Query("SELECT j FROM Job j WHERE " +
           "(LOWER(j.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(j.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
           "AND j.isActive = true")
    Page<Job> searchJobs(@Param("searchTerm") String searchTerm, Pageable pageable);

    /**
     * Find jobs by location
     */
    @Query("SELECT j FROM Job j WHERE LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%')) AND j.isActive = true")
    List<Job> findByLocationContainingIgnoreCase(@Param("location") String location);

    /**
     * Find jobs by salary range
     */
    @Query("SELECT j FROM Job j WHERE " +
           "(j.salaryMin IS NULL OR j.salaryMin <= :maxSalary) AND " +
           "(j.salaryMax IS NULL OR j.salaryMax >= :minSalary) AND " +
           "j.isActive = true")
    List<Job> findBySalaryRange(@Param("minSalary") BigDecimal minSalary, @Param("maxSalary") BigDecimal maxSalary);

    /**
     * Find jobs by required skills
     */
    @Query("SELECT DISTINCT j FROM Job j JOIN j.requiredSkills s WHERE LOWER(s) IN :skills AND j.isActive = true")
    List<Job> findByRequiredSkillsIn(@Param("skills") List<String> skills);

    /**
     * Find jobs matching candidate skills
     */
    @Query("SELECT DISTINCT j FROM Job j JOIN j.requiredSkills rs " +
           "WHERE LOWER(rs) IN (SELECT LOWER(cs) FROM Candidate c JOIN c.skills cs WHERE c.idCandidate = :candidateId) " +
           "AND j.isActive = true")
    List<Job> findJobsMatchingCandidateSkills(@Param("candidateId") Long candidateId);

    /**
     * Find jobs by multiple criteria
     */
    @Query("SELECT j FROM Job j WHERE " +
           "(:jobType IS NULL OR j.jobType = :jobType) AND " +
           "(:locationType IS NULL OR j.locationType = :locationType) AND " +
           "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
           "(:minSalary IS NULL OR j.salaryMax IS NULL OR j.salaryMax >= :minSalary) AND " +
           "(:maxSalary IS NULL OR j.salaryMin IS NULL OR j.salaryMin <= :maxSalary) AND " +
           "j.isActive = true")
    Page<Job> findByCriteria(@Param("jobType") JobType jobType,
                            @Param("locationType") LocationType locationType,
                            @Param("location") String location,
                            @Param("minSalary") BigDecimal minSalary,
                            @Param("maxSalary") BigDecimal maxSalary,
                            Pageable pageable);

    /**
     * Count active jobs by company
     */
    Long countByCompanyIdCompanyAndIsActiveTrue(Long companyId);

    /**
     * Count active jobs by recruiter
     */
    Long countByRecruiterIdRecruiterAndIsActiveTrue(Long recruiterId);

    /**
     * Find recent jobs (within last 30 days)
     */
    @Query("SELECT j FROM Job j WHERE j.createdAt >= CURRENT_DATE - 30 DAY AND j.isActive = true ORDER BY j.createdAt DESC")
    List<Job> findRecentJobs();

    /**
     * Find jobs without matches
     */
    @Query("SELECT j FROM Job j WHERE NOT EXISTS (SELECT m FROM Match m WHERE m.job = j) AND j.isActive = true")
    List<Job> findJobsWithoutMatches();

    /**
     * Find most popular jobs (by number of matches)
     */
    @Query("SELECT j FROM Job j WHERE j.isActive = true ORDER BY (SELECT COUNT(m) FROM Match m WHERE m.job = j) DESC")
    Page<Job> findMostPopularJobs(Pageable pageable);
}
