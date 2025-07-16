package co.unaicorn.shipfyze.products.application.command.create;

import co.unaicorn.shipfyze.products.application.dto.ProductRequest;
import co.unaicorn.shipfyze.products.application.dto.ProductResponse;
import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.products.domain.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductCreateCommandHandler {
  private final ProductRepository productRepository;

  public ProductCreateCommandHandler(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public ProductResponse execute(ProductCreateCommand productCreateCommand) {
    ProductRequest request = productCreateCommand.productRequest();

    // Create domain object
    Product product = new Product(
        request.name(),
        request.description(),
        request.price(),
        request.category()
    );

    // Save product
    Product savedProduct = productRepository.save(product);

    // Return response
    return new ProductResponse(
        savedProduct.getId(),
        savedProduct.getName(),
        savedProduct.getDescription(),
        savedProduct.getPrice(),
        savedProduct.getCategory()
    );
  }
}
