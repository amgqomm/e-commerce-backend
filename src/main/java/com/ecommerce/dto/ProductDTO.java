/**
 * @author Enkh-Amgalan G.
 *
 * @description This DTO class represents a product, including its ID, name, price, category, description, rating, color, stock status, SKU, product code, and image URLs.
 */

package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Represents a product with its details including ID, name, price, category, description, rating, color, stock status, SKU, product code, and associated image URLs.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String category;
    private String description;
    private Double rating;
    private String color;
    private Boolean stock;
    private String sku;
    private String code;
    private List<String> imageUrls ;
}