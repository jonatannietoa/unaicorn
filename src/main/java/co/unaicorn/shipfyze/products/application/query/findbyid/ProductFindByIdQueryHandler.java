package co.unaicorn.shipfyze.products.application.query.findbyid;

import co.unaicorn.shipfyze.products.application.dto.ProductResponse;
import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.products.domain.ProductRepository;
import co.unaicorn.shipfyze.products.domain.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductFindByIdQueryHandler {
    private final ProductRepository productRepository;

    public ProductFindByIdQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse execute(ProductFindByIdQuery query) {
        Product product = productRepository.findById(query.id())
                .orElseThrow(() -> new ProductNotFoundException(query.id()));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory()
        );
    }
}
