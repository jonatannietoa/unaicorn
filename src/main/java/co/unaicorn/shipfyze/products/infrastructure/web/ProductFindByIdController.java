package co.unaicorn.shipfyze.products.infrastructure.web;

import co.unaicorn.shipfyze.products.application.dto.ProductResponse;
import co.unaicorn.shipfyze.products.application.query.findbyid.ProductFindByIdQuery;
import co.unaicorn.shipfyze.products.application.query.findbyid.ProductFindByIdQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductFindByIdController {

    private final ProductFindByIdQueryHandler productFindByIdQueryHandler;

    public ProductFindByIdController(ProductFindByIdQueryHandler productFindByIdQueryHandler) {
        this.productFindByIdQueryHandler = productFindByIdQueryHandler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> execute(@PathVariable Long id) {
        ProductFindByIdQuery query = new ProductFindByIdQuery(id);
        ProductResponse response = this.productFindByIdQueryHandler.execute(query);
        return ResponseEntity.ok(response);
    }
}
