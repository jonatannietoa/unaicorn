package co.unaicorn.shipfyze.products.application.command.find;

import co.unaicorn.shipfyze.products.application.ProductResponse;
import co.unaicorn.shipfyze.products.application.find.ProductFinder;
import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.shared.domain.bus.query.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class ProductFindByIdQueryHandler implements QueryHandler<ProductFindByIdQuery, ProductResponse> {

    private final ProductFinder finder;

    public ProductFindByIdQueryHandler(ProductFinder finder) {
        this.finder = finder;
    }

    @Override
    public ProductResponse handle(ProductFindByIdQuery query) {
        Product product = finder.find(query.id());
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getWeight(),
                product.getDimensions().getWidth(),
                product.getDimensions().getHeight(),
                product.getDimensions().getLength(),
                product.getSku(),
                product.getGtin(),
                product.getHsCode(),
                product.getCountryOfOrigin()
        );
    }
}
