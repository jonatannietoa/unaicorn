package co.unaicorn.shipfyze.products.application.command.create;

import static org.junit.jupiter.api.Assertions.*;

import co.unaicorn.shipfyze.products.application.dto.ProductRequest;
import co.unaicorn.shipfyze.products.application.dto.ProductResponse;
import co.unaicorn.shipfyze.products.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ProductCreateCommandHandlerTest {

  private ProductCreateCommandHandler handler;

  @BeforeEach
  void setUp() {
    ProductRepository productRepository = new ProductRepositoryStub();
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
        ProductRequest request =
            new ProductRequest("Test Product", "Test Description", 99.99, "Electronics");
        return new ProductCreateCommand(request);
      }
    }
  }
}
