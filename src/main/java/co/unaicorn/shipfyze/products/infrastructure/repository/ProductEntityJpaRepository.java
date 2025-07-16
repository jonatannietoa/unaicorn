package co.unaicorn.shipfyze.products.infrastructure.repository;

import co.unaicorn.shipfyze.products.infrastructure.repository.entity.ProductEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEntityJpaRepository extends JpaRepository<ProductEntity, UUID> {}
