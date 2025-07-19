package co.unaicorn.shipfyze.jobmatching.infrastructure.repository;

import co.unaicorn.shipfyze.jobmatching.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Find company by name (case insensitive)
     */
    Optional<Company> findByNameIgnoreCase(String name);

    /**
     * Find all active companies
     */
    List<Company> findByIsActiveTrue();

    /**
     * Find companies by sector
     */
    List<Company> findBySectorIgnoreCase(String sector);

    /**
     * Find companies by sector and active status
     */
    List<Company> findBySectorIgnoreCaseAndIsActiveTrue(String sector);

    /**
     * Find companies by location (partial match)
     */
    @Query("SELECT c FROM Company c WHERE LOWER(c.location) LIKE LOWER(CONCAT('%', :location, '%'))")
    List<Company> findByLocationContainingIgnoreCase(@Param("location") String location);

    /**
     * Search companies by name or description
     */
    @Query("SELECT c FROM Company c WHERE " +
           "LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Company> searchCompanies(@Param("searchTerm") String searchTerm, Pageable pageable);

    /**
     * Find companies with active jobs
     */
    @Query("SELECT DISTINCT c FROM Company c JOIN c.jobs j WHERE j.isActive = true")
    List<Company> findCompaniesWithActiveJobs();

    /**
     * Count active companies by sector
     */
    @Query("SELECT COUNT(c) FROM Company c WHERE c.sector = :sector AND c.isActive = true")
    Long countActiveBySector(@Param("sector") String sector);

    /**
     * Find top companies by number of active jobs
     */
    @Query("SELECT c FROM Company c JOIN c.jobs j WHERE j.isActive = true " +
           "GROUP BY c ORDER BY COUNT(j) DESC")
    Page<Company> findTopCompaniesByActiveJobs(Pageable pageable);

    /**
     * Check if company exists by name
     */
    boolean existsByNameIgnoreCase(String name);
}
