package co.unaicorn.shipfyze.products.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;

    public Product(String name, String description, Double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public static Product create(String name, String description, Double price, String category) {
        return new Product(
            name,
            description,
            price,
            category
        );
    }

    public static Product fromPrimitive(Long id, String name, String description, Double price, String category) {
            return new Product(
            id,
            name,
            description,
            price,
            category
            );
    }
}
