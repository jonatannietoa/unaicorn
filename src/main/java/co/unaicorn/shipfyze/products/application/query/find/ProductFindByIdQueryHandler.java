package co.unaicorn.shipfyze.products.application.query.find;

import co.unaicorn.shipfyze.products.application.response.ProductResponse;
import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.products.domain.ProductRepository;
import co.unaicorn.shipfyze.shared.domain.bus.query.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class ProductFindByIdQueryHandler implements QueryHandler<ProductFindByIdQuery, ProductResponse> {

  private final ProductRepository productRepository;

  public ProductFindByIdQueryHandler(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ProductResponse handle(ProductFindByIdQuery query) {
    Product product =
        productRepository
            .findById(query.id())
            .orElseThrow(() -> new RuntimeException("Product not found"));
    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getCategory());
  }
}
