package co.unaicorn.shipfyze.products.infrastructure.web;

import co.unaicorn.shipfyze.products.application.ProductResponse;
import co.unaicorn.shipfyze.products.application.query.findbyid.ProductFindByIdQuery;
import co.unaicorn.shipfyze.products.domain.ProductNotFoundException;
import co.unaicorn.shipfyze.shared.domain.bus.query.QueryBus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductFindByIdController {

    private final QueryBus queryBus;

    public ProductFindByIdController(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        try {
            ProductFindByIdQuery query = new ProductFindByIdQuery(id);
            ProductResponse response = queryBus.ask(query);
            return ResponseEntity.ok(response);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
