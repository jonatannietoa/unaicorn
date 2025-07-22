package co.unaicorn.shipfyze.products.application.query.findbyid;

import co.unaicorn.shipfyze.products.application.ProductResponse;
import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.products.domain.ProductNotFoundException;
import co.unaicorn.shipfyze.products.domain.ProductRepository;
import co.unaicorn.shipfyze.shared.domain.bus.query.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class ProductFindByIdQueryHandler implements QueryHandler<ProductFindByIdQuery, ProductResponse> {

    private final ProductRepository repository;

    public ProductFindByIdQueryHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductResponse handle(ProductFindByIdQuery query) {
        Product product = repository.findById(query.id())
                .orElseThrow(() -> new ProductNotFoundException(query.id()));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getWeight()
        );
    }
}
