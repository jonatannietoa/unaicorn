package co.unaicorn.shipfyze.products.infrastructure.repository;

import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.products.domain.ProductRepository;
import co.unaicorn.shipfyze.products.infrastructure.repository.entity.ProductEntity;
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

  private ProductEntity toEntity(Product product) {
    ProductEntity entity = new ProductEntity();
    entity.setId(product.getId());
    entity.setName(product.getName());
    entity.setDescription(product.getDescription());
    entity.setPrice(product.getPrice());
    entity.setCategory(product.getCategory());
    return entity;
  }

  private Product toDomain(ProductEntity entity) {
    return Product.fromPrimitive(
        entity.getId(),
        entity.getName(),
        entity.getDescription(),
        entity.getPrice(),
        entity.getCategory());
  }
}
