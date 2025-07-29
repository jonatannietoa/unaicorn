package co.unaicorn.shipfyze.products.infrastructure.repository;

import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.products.domain.ProductRepository;
import co.unaicorn.shipfyze.products.infrastructure.repository.entity.ProductEntity;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJpaRepository implements ProductRepository {

  private final ProductEntityJpaRepository productEntityJpaRepository;

  public ProductJpaRepository(ProductEntityJpaRepository productEntityJpaRepository) {
    this.productEntityJpaRepository = productEntityJpaRepository;
  }

  @Override
  public Product save(Product product) {
    ProductEntity entity = toEntity(product);
    ProductEntity savedEntity = productEntityJpaRepository.save(entity);
    return toDomain(savedEntity);
  }

  @Override
  public Optional<Product> findById(Long id) {
    return productEntityJpaRepository.findById(id).map(this::toDomain);
  }

  private ProductEntity toEntity(Product product) {
    return new ProductEntity(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getCategory());
  }

  private Product toDomain(ProductEntity entity) {
    return new Product(
        entity.getId(),
        entity.getName(),
        entity.getDescription(),
        entity.getPrice(),
        entity.getCategory());
  }
}
