package co.unaicorn.shipfyze.products.infrastructure.web;

import co.unaicorn.shipfyze.products.application.ProductResponse;
import co.unaicorn.shipfyze.products.application.command.find.ProductFindByIdQuery;
import co.unaicorn.shipfyze.products.domain.exceptions.ProductNotFoundException;
import co.unaicorn.shipfyze.shared.domain.bus.query.QueryBus;
import co.unaicorn.shipfyze.shared.domain.bus.query.QueryHandlerExecutionError;
import co.unaicorn.shipfyze.shared.infrastructure.web.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ProductFindByIdController extends ApiController {

    public ProductFindByIdController(QueryBus queryBus) {
        super(queryBus);
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<?> index(@PathVariable Long id) throws QueryHandlerExecutionError {
        ProductResponse product = ask(new ProductFindByIdQuery(id));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public HashMap<Class<? extends Throwable>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(ProductNotFoundException.class, HttpStatus.NOT_FOUND);
        }};
    }
}
