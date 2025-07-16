package co.unaicorn.shipfyze.products.application.command.create;

import co.unaicorn.shipfyze.products.application.dto.ProductRequest;
import co.unaicorn.shipfyze.products.application.dto.ProductResponse;
import co.unaicorn.shipfyze.products.domain.Product;
import co.unaicorn.shipfyze.products.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductCreateCommandHandlerTest {

    @Mock
    private ProductRepository productRepository;

    private ProductCreateCommandHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ProductCreateCommandHandler(productRepository);
    }

    @Nested
    class GivenAProductCreateCommand {

        @Nested
        class WhenExecutingCommand {

            @Test
            void thenShouldCreateProductSuccessfully() {
                ProductResponse response = whenExecutingProductCreation();

                thenProductShouldBeCreatedSuccessfully(response);
            }

            private ProductResponse whenExecutingProductCreation() {
                ProductCreateCommand command = createProductCommand();
                mockRepositorySaveOperation();

                return handler.execute(command);
            }

            private void thenProductShouldBeCreatedSuccessfully(ProductResponse response) {
                assertNotNull(response);
                assertEquals("Test Product", response.name());
                assertEquals("Test Description", response.description());
                assertEquals(99.99, response.price());
                assertEquals("Electronics", response.category());
                assertNotNull(response.id());

                System.out.println("[DEBUG_LOG] Product created successfully with ID: " + response.id());
            }

            private ProductCreateCommand createProductCommand() {
                ProductRequest request = new ProductRequest(
                    "Test Product",
                    "Test Description",
                    99.99,
                    "Electronics"
                );
                return new ProductCreateCommand(request);
            }

            private void mockRepositorySaveOperation() {
                Product savedProduct = new Product(
                    "Test Product",
                    "Test Description", 
                    99.99,
                    "Electronics"
                );

                when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
            }
        }
    }
}
