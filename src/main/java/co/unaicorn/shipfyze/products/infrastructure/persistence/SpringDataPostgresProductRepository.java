package co.unaicorn.shipfyze.products.infrastructure.persistence;

import co.unaicorn.shipfyze.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPostgresProductRepository extends JpaRepository<Product, String> {
}
